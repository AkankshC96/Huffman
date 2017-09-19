import java.util.*;

public class HuffProcessor implements Processor {

	@Override
	public void compress(BitInputStream in, BitOutputStream out) {
		// TODO Auto-generated method stub
		int[] array = new int[256];
		int value = in.readBits(BITS_PER_WORD);
		
    while (value != -1) {
			array[value]++;
			value = in.readBits(BITS_PER_WORD);}
		
    in.reset();
		//Create Priority Queue
		
    PriorityQueue<HuffNode> pq = new PriorityQueue<HuffNode>();
		int count=0;
		
    for (int i = 0; i < ALPH_SIZE; i++) {
			if (array[i] != 0) {
				pq.add(new HuffNode(i, array[i]));
				count++;	}
		}
		
    System.out.println(count);
		
    pq.add(new HuffNode(PSEUDO_EOF, 0));
		
    while (pq.size() > 1) {
			HuffNode left = pq.poll();
			HuffNode right = pq.poll();
			HuffNode updated = new HuffNode(-1, left.weight() + right.weight(),
					left, right);
			pq.add(updated);}
		
    HuffNode last_node = pq.poll();
		String[] array_new = new String[257];
		extractCodes(last_node, "", array_new);
		out.writeBits(BITS_PER_INT, HUFF_NUMBER);
		
    //create header
		writeHeader(last_node, out);
		int secondValue = in.readBits(BITS_PER_WORD);
		
    while (secondValue != -1) {
			String code = array_new[secondValue];
			out.writeBits(code.length(), Integer.parseInt(code, 2));
			secondValue = in.readBits(BITS_PER_WORD);}
		
    String code_final = array_new[PSEUDO_EOF];
		out.writeBits(code_final.length(), Integer.parseInt(code_final, 2));}
	
  private void extractCodes(HuffNode current, String path, String[] final_path) {
		if (current.left() == null && current.right() == null) {
			final_path[current.value()] = path;
			return;}
		extractCodes(current.left(), path + 0, final_path);
		extractCodes(current.right(), path + 1, final_path);}
	
  private void writeHeader(HuffNode current, BitOutputStream out) {
		if (current.left() == null && current.right() == null) {
			out.writeBits(1, 1);
			out.writeBits(9, current.value());
			return;}
		out.writeBits(1, 0);
		writeHeader(current.left(), out);
		writeHeader(current.right(), out);}
	
  @Override
	
  public void decompress(BitInputStream in, BitOutputStream out) {
		// TODO Auto-generated method stub
		//Check for error
		if (in.readBits(BITS_PER_INT) != HUFF_NUMBER) {
			throw new HuffException("Beginning of file is invalid(HuffNumber)");}
		HuffNode root = readHeader(in);
		HuffNode current = root;
		int value = in.readBits(1);
		while (value != -1) {
			if (value == 1) {
				current = current.right();
			} else {
				current = current.left();}
			if (current.left() == null && current.right() == null) {
				if (current.value() == PSEUDO_EOF) {
					return;
				} else {
					out.writeBits(8, current.value());
					current = root;}
			}
			value = in.readBits(1);}
		//check if you go past the text file
		throw new HuffException("Error at end of file(Problem at Pseudo-EOF");}
	
  private HuffNode readHeader(BitInputStream in) {
		if (in.readBits(1) == 0) {
			HuffNode left = readHeader(in);
			HuffNode right = readHeader(in);
			return new HuffNode(-1, 0, left, right);
		} else {
			return new HuffNode(in.readBits(9), 0);}
	}
}
