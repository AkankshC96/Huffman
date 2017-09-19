# Huffman

Name: Akanksh Chaudhary
NetID: ac391
Hours Spent: 15.5 hrs
----------------------------------------------------------------------






Part 1: Benchmark and analyze my code

	Name:		       Compression Rate       Time Elapsed:	Alphabet Size:    Length:
        
	Calgary.book1          1.753x                 1.006 s           82 characters     6,150,168 bits
	Calgary.news           1.530x		      0.58 s		98 characters     3,016,872 bits
	Calgary.paper1         1.588x                 0.066 s           95 characters     425,288 bits
	Waterloo.color.lena    1.028x                 1.472 s           256 characters    6,292,544 bits
	Waterloo.color.clegg   1.057x                 3.849 s           256 characters    17,192,768 bits
	Waterloo.gs1.lena      1.047x                 0.182 s		233 characters    525,328 bits
	Waterloo.gs2.lena      1.069x                 0.475 s           227 characters    2,098,192 bits
	
	Holding alphabet size constant, the time elapsed goes up as the size of the file increases, but the compression rate stays the same.
	Keeping the length of the file the same, increasing the alphabet size increases the time elapsed and decreases the compression rate.	

Part 2: Text vs. Binary 
	From the data above, text files compress more than binary (image) files because the alphabet size is larger for images so that means
	that those files have larger trees and longer bitcodes, therefore leading to less compression. Also the text files have more diversity
	in characters distributions than images, so the compression for text files is more efficient. Images have their colors more evenly
	distributed in terms of amount of each color, and thus the compression is not as efficient.   

Part 3: Compressing compressed files
	Name:                 Compression Rate:
	Calgary.book1         1.012x
	Waterloo.color.clegg  1.003x
	
	It is apparent, that regardless of whether you compress an already compressed text file or image, the compression rate is much less
	than when you compress the original file. 
