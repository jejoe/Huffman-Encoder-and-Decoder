
//package DataStructures;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.math.BigInteger;
import java.nio.file.Files;
import java.util.BitSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;


//import javafx.scene.shape.Path;


public class HuffmanDecode {
	private String symbolFilePath;
	private String inputFilePath;
	private HashMap<String, Integer> symbolMap;
	//private Heap heap = new Heap(1000000);
	
	public HuffmanDecode(String inputFilePath, String symbolFilePath) {
		super();
		this.symbolFilePath = symbolFilePath;
		this.inputFilePath = inputFilePath;
	//	System.out.println("inside HUffman Decode constructor");

	}
	
	public void decode(){
	//	System.out.println("inside decode");
		readSymbols();
	//	System.out.println("after readSymbols");
	//	printSymbols();
	//	System.out.println("after printSymbols");
		decompress();
	//	System.out.println("after decompress");
		
	}
	
	private void decompress() {
		StringBuffer output = new StringBuffer();
		FileWriter fileWriter = null;
		BufferedWriter bufferedWriter = null;
		try {
			StringBuffer leftOver = new StringBuffer();
			byte[] bytes = Files.readAllBytes(new File(inputFilePath).toPath());
			if (bytes != null) {
				StringBuffer codeSoFar = new StringBuffer();
				codeSoFar.append(leftOver);
				for (byte b : bytes) {
					for (int i = 0; i < 8; i++) {
						int currentBit = (b << (7 - i)) & 0x80;
						if (currentBit == 0x80) {
							codeSoFar.append("1");
						} else {
							codeSoFar.append("0");
						}
						if (symbolMap.containsKey(codeSoFar.toString())) {
						//	System.out.println("from alreadycontaining "+symbolMap.get(codeSoFar.toString()));
							output.append(symbolMap.get(codeSoFar.toString()));
							output.append("\n");
							codeSoFar = new StringBuffer();
						}
					}
				}
				leftOver = new StringBuffer();
			//System.out.println("from not containing "+codeSoFar.toString());
				leftOver.append(codeSoFar.toString());
			}
			//String pathOut = inputFilePath.substring(0,inputFilePath.length() - 7) + "decoded.txt";
			String pathOut = "decoded.txt";
			fileWriter = new FileWriter(pathOut);
			bufferedWriter = new BufferedWriter(fileWriter);
			bufferedWriter.write(output.toString());
			bufferedWriter.close();
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				if (fileWriter != null) {
					fileWriter.close();
				}
				if (bufferedWriter != null) {
					bufferedWriter.close();
				}
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}
	
	

	
	private void printSymbols() {
		Iterator iterator = symbolMap.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<String, Integer> obj = (Map.Entry<String, Integer>) iterator.next();
		//	System.out.println("Code : " + obj.getKey() + " , Num : " + obj.getValue());
		}
	} 
	
	private void readSymbols(){

		
		symbolMap = new HashMap<>();
		
		FileReader fileReader = null;
		BufferedReader bufferedReader = null;
		try{
			fileReader = new FileReader(symbolFilePath);
			bufferedReader = new BufferedReader(fileReader);
			while(true) {
				//System.out.println(bufferedReader.readLine());
				String currentLine = bufferedReader.readLine();
				if(currentLine != null) {
					String[] strings = currentLine.split(" ");
					if(strings[0].matches("[0-9]+")){
						int num = Integer.valueOf(strings[0]);
						symbolMap.put(strings[1], num);
					//	System.out.println("readSymbols "+strings[1]+" "+ num);
					}
				} else {
					break;
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				if(fileReader!=null){
					fileReader.close();
				}
				if(bufferedReader!=null) {
					bufferedReader.close();
				}
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}
	
	/*private void writeSymbolFile() {
		System.out.println("inside write file function");
		FileWriter fileWriter = null;
		BufferedWriter bufferedWriter = null;
		try {
			StringBuffer stringBuffer = new StringBuffer();
			Iterator symbIterator = symbolMap.entrySet().iterator();
			while (symbIterator.hasNext()) {
				Map.Entry<Integer, String> pair = (Map.Entry) symbIterator.next();

				stringBuffer.append(pair.getKey());
			//	System.out.println(pair.getKey());
				stringBuffer.append(" ");
				stringBuffer.append(pair.getValue());
				//System.out.println(pair.getValue());
				stringBuffer.append("\n");
			}
			String path_sym = inputFilePath.substring(0, inputFilePath.length() - 4) + "_sym.txt";
			fileWriter = new FileWriter(path_sym);
			bufferedWriter = new BufferedWriter(fileWriter);
			// System.out.println(stringBuffer.toString());
			bufferedWriter.write(stringBuffer.toString());
			bufferedWriter.close();
			System.out.println("Symbol table writing complete");
		} catch (Exception e) {

		} finally {
			try {
				if (fileWriter != null) {
					fileWriter.close();
				}
				if (bufferedWriter != null) {
					bufferedWriter.close();
				}
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	} */
}
