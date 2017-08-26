
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class HuffmanEncode {
	private String inputFilePath = "";
	private FrequencyTableBuilder[] freq;
	private HashMap<Integer, String> symbolMap = new HashMap<>();
	// private Heap heap = new Heap(1000000);
	//private DaryHeap dHeap;
	PriorityQueue inputHeap;

	public HuffmanEncode(String inputFilePath) {
		super();
		this.inputFilePath = inputFilePath;

	}

	public void encode() {
	//	System.out.println("reached here");
		FrequencyTable frequencyTable = new FrequencyTable(inputFilePath);
	//	System.out.println("reached here 1");
		freq = frequencyTable.createFrequencyTable();
		buildHeap();
		generateTree();
		generateCode(inputHeap.findMin(), "");
		//setSymbolMap();
		encodeFile();
		writeSymbolFile();
	}

	private PriorityQueue buildHeap() {
		inputHeap = new DaryHeap(freq.length);
		int size = freq.length;
		for (int i = 0; i < size; i++) {
			FrequencyTableBuilder obj = freq[i];
			if (obj.getFrequency() != 0) {
				// System.out.println(obj.getElementIndex() + " ==> " +
				// obj.getFrequency());
				Node myNode = new Node(obj.getFrequency(), obj.getElementIndex());
				myNode.setToLeaf();
				inputHeap.insert(myNode);
			}
		}
		return inputHeap;
	}

	/*
	 * private void createTree() { initializeHeap(); int n = heap.getHeapSize();
	 * for (int i = 0; i < (n - 1); ++i) { Node z = new Node();
	 * z.setLeft(heap.deleteMin()); z.setRight(heap.deleteMin());
	 * z.setFreq(z.getLeft().getFreq() + z.getRight().getFreq());
	 * heap.insert(z); } System.out.println("Heap size:" + heap.getHeapSize());
	 * }
	 */

	private Node createTree(PriorityQueue priorityQueue) {
		int n = priorityQueue.getHeapSize();
		for (int i = 0; i < (n - 1); ++i) {
			Node z = new Node();
			z.setLeft(priorityQueue.deleteMin());
			z.setRight(priorityQueue.deleteMin());
			z.setFreq(z.getLeft().getFreq() + z.getRight().getFreq());
			priorityQueue.insert(z);

		}
		return priorityQueue.findMin();
	}

	/*
	 * not required now private void initializeHeap() { heap = new
	 * Heap(1000000); for (int i = 0; i < freqs.length; ++i) { if
	 * (freqs[i].getFrequency() != 0) { Node node = new
	 * Node(freqs[i].getFrequency(), i); node.setToLeaf(); heap.insert(node); }
	 * } }
	 */
	private Node generateTree() {
		return createTree(buildHeap());

		// for timing
		
		/*  Node min = null;
		   long startTime = System.nanoTime() / 1000; 
		   for (int i = 0; i < 10; i++) { min = createTree(buildHeap()); } long stopTime
		  = System.nanoTime() / 1000; long elapsedTime = stopTime - startTime;
		  System.out.println("4way cache optimised Heap elapsed time: " + elapsedTime); return
		  min;  */
		
	}

	private void generateCode(Node node, String code) {
		if (node != null) {
			if (node.isLeaf())
			// symbolTable[node.getKey()] = code;
			{
				//freqs[node.getKey()].setCode(code);
				symbolMap.put(node.getKey(), code);
			} else {
				generateCode(node.getLeft(), code + "0");
				generateCode(node.getRight(), code + "1");
			}
		}
	}

	private void setSymbolMap() {
		for (FrequencyTableBuilder obj : freq) {
			if (obj != null && obj.getFrequency() != 0) {
			//	 System.out.println(""+ obj.getElementIndex() + " : " + obj.getCode());
				if (!symbolMap.containsKey(obj.getElementIndex())) {
					symbolMap.put(obj.getElementIndex(), obj.getCode());
				} else {
					// Something went wrong
				}
			}
		}
	}

	private void writeSymbolFile() {
	//	System.out.println("inside writeSymbol");
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
			//System.out.println("inputFilePath "+inputFilePath);
		//	String path_sym = inputFilePath.substring(0, inputFilePath.length() - 4) + "_sym.txt";
			String path_sym = "code_table.txt";
			fileWriter = new FileWriter(path_sym);
			bufferedWriter = new BufferedWriter(fileWriter);
			// System.out.println(stringBuffer.toString());
			bufferedWriter.write(stringBuffer.toString());
			bufferedWriter.close();
		//	System.out.println("Symbol table writing complete");
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
	}

	private void encodeFile() {
		FileReader fileReader = null;
		BufferedReader bufferedReader = null;
		// FileWriter fileWriter = null;
		// BufferedWriter bufferedWriter = null;
		FileOutputStream fileOutputStream = null;
		try {
		//	String pathNew = inputFilePath.substring(0, inputFilePath.length() - 4) + "_out.bin";
			String pathNew = "encoded.bin";
			fileOutputStream = new FileOutputStream(pathNew);
			fileReader = new FileReader(new File(inputFilePath));
			bufferedReader = new BufferedReader(fileReader);
			StringBuffer stringBuffer = new StringBuffer();
			while (true) {
				String string = bufferedReader.readLine();
				if (string != null) {
					if (string.matches("[0-9]+")) {
						int num = Integer.parseInt(string);
						String code = symbolMap.get(num);
				//		System.out.println("code" + code);
						stringBuffer.append(code);
						// System.out.println("Adding : " + code);
					}
				} else {
					break;
				}
			}
			// System.out.println("string out :" + stringBuffer.toString());

			fileOutputStream.write(getBitSet(stringBuffer.toString()));
			fileReader.close();

			// System.out.println("Content : " + content);
			// fileWriter = new FileWriter(pathNew);
			// bufferedWriter = new BufferedWriter(fileWriter);
			// bufferedWriter.write(content);
			// bufferedWriter.close();

			// byte bytes = Byte.valueOf(content);
			fileOutputStream.close();
	//		System.out.println("Writing Complete");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		} finally {
			try {
				if (fileReader != null) {
					fileReader.close();
			//		System.out.println("fileReader closed");
				}
				if (bufferedReader != null) {
					bufferedReader.close();
				//	System.out.println("bufferedReader closed");
				}
				// if(fileWriter!=null) {
				// fileWriter.close();
				// System.out.println("fileWriter closed");
				// }
				// if(bufferedWriter!=null) {
				// bufferedWriter.close();
				// System.out.println("bufferedWriter closed");
				// }
				if (fileOutputStream != null) {
					fileOutputStream.close();
				//	System.out.println("fileOutputStream closed");
				}
			} catch (Exception e) {

			}
		}
	}

	private byte[] getByteArray(char[] inputChars) {
		ByteArrayOutputStream buffer = new ByteArrayOutputStream();
		for (char c : inputChars) {
			byte b = 1;
			if (c == '0') {
				b = 0;
			}
			buffer.write(b);
		}
		byte[] output = buffer.toByteArray();
		return output;
	}

	private byte[] getBitSet(String huff_code) {
		BitSet buffer = new BitSet(Integer.MAX_VALUE);
		int bitIndex = -1;
		for (char c : huff_code.toCharArray()) {
			++bitIndex;
			if (c == '1') {
				buffer.set(bitIndex, true);// .set(bitIndex);

			} else if (c == '0') {
				buffer.set(bitIndex, false);

			} else {
				continue;
			}
		}
		return buffer.toByteArray();
	}

}
