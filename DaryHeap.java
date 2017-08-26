import java.util.Arrays;
import java.util.NoSuchElementException;

public class DaryHeap implements PriorityQueue {
	private Node[] DaryHeap;
	
	private int size = 0;
	
	//private static final int FRONT = 1;
	//private int maxSize;
	// public Heap(int i) {
	// heap = new Node[i];
	// heap[0] = new Node(Integer.MAX_VALUE,-1);//Integer.MAX_VALUE;
	// }

	public DaryHeap(int size) {
		// this.maxSize = maxSize;
		// this.size = size;
		//this.size=0;
//////		//DaryHeap = new Node[size+1];
		DaryHeap = new Node[size+3];
		
		
		//Arrays.fill(DaryHeap, -1);
		// heap = new Node[this.maxSize = maxSize + 1];
		// heap[0] = new Node(Integer.MAX_VALUE,-1);

	}

	public int getHeapSize() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public boolean isFull() {
		return size == DaryHeap.length;
	}

	private int parent(int i) {
		//return (i - 1) / 4;
		return (i/4)+2;
	}

	private int kthChild(int i, int k) {
		//return 4 * i + k;
		return (4*i) + k -9;
	}

	public void insert(Node element) {
		if (!isFull()) {
			DaryHeap[size+3] = element;
			size++;
		///////	minHeapifyUp(size - 1);
			minHeapifyUp(size+2);
			
		}
	}
	private void minHeapifyUp(int childInd) {
		Node tmp = DaryHeap[childInd];
		while (childInd > 3 && tmp.getFreq() < DaryHeap[parent(childInd)].getFreq()) {
			DaryHeap[childInd] = DaryHeap[parent(childInd)];
			childInd = parent(childInd);
		}
		DaryHeap[childInd] = tmp;
	}
	public Node findMin() {
		if (isEmpty())
            throw new NoSuchElementException("Underflow Exception");           
        return DaryHeap[3];
//		if (!isEmpty())
//			return DaryHeap[0];
//
//		return null;
	}

	public Node deleteMin() {
		if (!isEmpty()) {
//			Node removed = DaryHeap[0];
//			DaryHeap[0] = DaryHeap[size - 1];
//			size--;
//			minHeapifyDown(0);
			Node removed = DaryHeap[3];
			DaryHeap[3] = DaryHeap[size + 2];
			size--;
			minHeapifyDown(3);

			return removed;
		}
		return null;
	}

	private void minHeapifyDown(int ind) {
		int child;
		Node tmp = DaryHeap[ind];
		while (kthChild(ind, 1) < size+3) {
			child = minChild(ind);
//			if (DaryHeap[child].getFreq() == tmp.getFreq())
//				if(!tmp.isLeaf())
//					DaryHeap[ind] = DaryHeap[child];
				
			if (DaryHeap[child].getFreq() < tmp.getFreq())
				DaryHeap[ind] = DaryHeap[child];
			else
				break;
			ind = child;
		}
		DaryHeap[ind] = tmp;
	}

	private int minChild(int ind) {
		int bestChild = kthChild(ind, 1);
		int k = 2;
		int pos = kthChild(ind, k);
		while ((k <= 4) && (pos < size+3)) {
//			if(DaryHeap[pos].getFreq()==DaryHeap[bestChild].getFreq())
//				if(DaryHeap[pos].isLeaf())
//					bestChild=pos;
				
			if (DaryHeap[pos].getFreq() < DaryHeap[bestChild].getFreq())
				bestChild = pos;
			//k+=1;
			pos = kthChild(ind, ++k);
		}
		return bestChild;
	}

	
	//String code="";
	
}
