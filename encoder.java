public class encoder {

	public encoder() {
		// TODO Auto-generated constructor stub
	}
	public static void main(String args[])throws Exception{
		
		/*System.out.println("Encode input");
		String path ="C:\\Users\\jenij\\workspace\\encoder\\src\\sample_input_large.txt";
		HuffmanEncode h1 = new HuffmanEncode(path);
		h1.encode();
		
		System.out.println("aa3");
		String path1 ="C:\\Users\\jenij\\workspace\\encoder\\src\\sample_input_large_out.bin";
		String path2 ="C:\\Users\\jenij\\workspace\\encoder\\src\\sample_input_large_sym.txt";
		HuffmanDecode h = new HuffmanDecode(path1,path2);
		h.decode();   
		
		
	/*	
		
		if(args.length == 1)
		{*/
		//	System.out.println("Encode input");
			//String path ="C:\\Users\\jenij\\workspace\\encoder\\src\\sample_input_small.txt";
			
			HuffmanEncode h = new HuffmanEncode(args[0].toString());
			//HuffmanEncode h = new HuffmanEncode(path);
			h.encode();  
	/*	}
		
		else if (args.length == 2)
		{
			System.out.println("Decode input");
			
			HuffmanDecode h = new HuffmanDecode(args[0].toString(),args[1].toString());
			h.decode();
		}
		
		else
			System.out.println("Incorrect input");  */
		     
	}
}
