
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class FrequencyTable {
	
	public FrequencyTableBuilder[] frequencyArray = new FrequencyTableBuilder[1000000];
	public String path = "";
	private int count = 0;
	
	public FrequencyTable(String path) {
		super();
		
		this.path = path;
		//System.out.println("inside frequency table constructor "+path);
		frequencyArray = new FrequencyTableBuilder[1000000];
		count = 0;
		initializeArray();
	}
	
	private void initializeArray(){
		int size = frequencyArray.length;
		for(int i = 0; i< size; i++) {
			if(frequencyArray[i]==null) {
				//frequencyArray[i] = new FrequencyTableBuilder(0, i);
				frequencyArray[i] = new FrequencyTableBuilder(i,0);
			}
		}
	}
	
	public FrequencyTableBuilder[] getFileInput(){
		try {
			//System.out.println("inside try of getfileinput");
			
			
			
		//	System.out.println("printing  file path ");
			StringBuffer stringBuffer = new StringBuffer();
			FileReader fileReader = new FileReader(new File(path));
			
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			
			while (true) {
			//	System.out.println("inside while -------------");
				String string = bufferedReader.readLine();
		//		System.out.println(string);
				if(string!=null){
			//		System.out.println("inside -------------");
					stringBuffer.append(string);
					try {
						Integer num = Integer.parseInt(string);
				//		System.out.println("Integer num"+ num);
						if(num != null) {
							if(frequencyArray[num]==null){
								frequencyArray[num] = new FrequencyTableBuilder(num);
							}
							FrequencyTableBuilder freqObj = frequencyArray[num];
							//freqObj.incrementFrequency(num);
							freqObj.updateFrequency();
							frequencyArray[num] = freqObj;
						} else {
//							System.out.println("Not Num");
						}				
					} catch (NumberFormatException e) {
						//Do Nothing
					}
					
				} else {
					break;
				}				
		}
			return frequencyArray;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return frequencyArray;
	}
	
	public FrequencyTableBuilder[] createFrequencyTable() {
		//System.out.println("inside createfrequencytable");
		FrequencyTableBuilder[] array = getFileInput();
		int size = array.length;
		Arrays.sort(array);
		for(int i = 0; i<size; i++){
			FrequencyTableBuilder obj = array[i];
			if(obj.getFrequency()!=0){
			//	System.out.println(obj.getElementIndex() + " ==> " + obj.getFrequency());
			}
		}
		return array;
	}
}
