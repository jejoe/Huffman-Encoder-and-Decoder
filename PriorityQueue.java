public interface PriorityQueue {

	public void insert(Node x);
	public Node findMin();
	public Node deleteMin();
	public int getHeapSize();

}