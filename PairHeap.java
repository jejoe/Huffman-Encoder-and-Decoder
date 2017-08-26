
public class PairHeap implements PriorityQueue{
	private PairNode root;
	private PairNode[] pairHeap=new PairNode[6];
	int size;

	public PairHeap() {
		
	//	this.pairHeap = new PairNode[this.size];
		this.root = null;
	}
	public int getSize(){
		return size;
	}
	public boolean isEmpty() {
		return root == null;
	}

	public PairNode add(Node element) {
		size++;
		PairNode newNode = new PairNode(element);
		if (root == null)
			root = newNode;
		else
			root = compareAndLink(root, newNode);
		return newNode;
	}

	private PairNode compareAndLink(PairNode first, PairNode second) {
		if (second == null)
			return first;

		if (second.key.getFreq() < first.key.getFreq()) {
			second.prev = first.prev;
			first.prev = second;
			first.nextSibling = second.leftChild;
			if (first.nextSibling != null)
				first.nextSibling.prev = first;
			second.leftChild = first;
			return second;
		} else {
			second.prev = first;
			first.nextSibling = second.nextSibling;
			if (first.nextSibling != null)
				first.nextSibling.prev = first;
			second.nextSibling = first.leftChild;
			if (second.nextSibling != null)
				second.nextSibling.prev = second;
			first.leftChild = second;
			return first;
		}
	}

	public Node removeMin() {
		size--;
		if (isEmpty())
			return null;
		Node removed = root.key;
		if (root.leftChild == null)
			root = null;
		else
			root = combineSiblings(root.leftChild);
		return removed;
	}

	private PairNode combineSiblings(PairNode firstSibling) {
		if (firstSibling.nextSibling == null)
			return firstSibling;
		int numSiblings = 0;
		for (; firstSibling != null; numSiblings++) {
			pairHeap = doubleIfFull(pairHeap, numSiblings);
			pairHeap[numSiblings] = firstSibling;
			firstSibling.prev.nextSibling = null;
			firstSibling = firstSibling.nextSibling;
		}
		pairHeap = doubleIfFull(pairHeap, numSiblings);
		pairHeap[numSiblings] = null;
		int i = 0;
		for (; i + 1 < numSiblings; i += 2)
			pairHeap[i] = compareAndLink(pairHeap[i], pairHeap[i + 1]);
		int j = i - 2;
		if (j == numSiblings - 3)
			pairHeap[j] = compareAndLink(pairHeap[j], pairHeap[j + 2]);
		for (; j >= 2; j -= 2)
			pairHeap[j - 2] = compareAndLink(pairHeap[j - 2], pairHeap[j]);
		return pairHeap[0];
	}

	private PairNode[] doubleIfFull(PairNode[] array, int index) {
		if (index == array.length) {
			PairNode[] oldArray = array;
			array = new PairNode[index * 2];
			for (int i = 0; i < index; i++)
				array[i] = oldArray[i];
		}
		return array;
	}
	public PairNode min(){
		if(!isEmpty()){
			return root;
		}
		return null;
	}
	public void print() {
		System.out.println(root.key.getFreq());

	}
	@Override
	public void insert(Node x) {
		add(x);
	}
	@Override
	public Node findMin() {
		// TODO Auto-generated method stub
		return this.root.key;
	}
	@Override
	public Node deleteMin() {
		// TODO Auto-generated method stub
		return removeMin();
	}
	@Override
	public int getHeapSize() {
		// TODO Auto-generated method stub
		return getSize();
	}
}
