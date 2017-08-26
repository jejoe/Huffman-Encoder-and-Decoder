all:
	javac encoder.java decoder.java Heap.java HuffmanEncode.java HuffmanDecode.java Node.java DaryHeap.java FrequencyTableBuilder.java FrequencyTable.java PairHeap.java PriorityQueue.java PairNode.java
clean:
	rm -rf *.class