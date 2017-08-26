
public class decoder {
	
	public static void main(String args[]){
	//System.out.println("Decode input");
	
	HuffmanDecode h = new HuffmanDecode(args[0].toString(),args[1].toString());
	h.decode(); 
		
		/* String path1 ="C:\\Users\\jenij\\workspace\\decoder\\src\\sample_input_large_out.bin";
		String path2 ="C:\\Users\\jenij\\workspace\\decoder\\src\\sample_input_large_sym.txt";
		HuffmanDecode h = new HuffmanDecode(path1,path2);
		h.decode(); */
		
	}
}
