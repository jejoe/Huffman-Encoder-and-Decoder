class PairNode {
	Node key;
	PairNode leftChild;
	PairNode nextSibling;
	PairNode prev;

	/* Constructor */
	public PairNode(Node x) {
		key = x;
		leftChild = null;
		nextSibling = null;
		prev = null;
	}
}

