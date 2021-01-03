package linked;


public class DNALinkedList 
{
	private Node<Character>			head;	// head.prev is always null
	private Node<Character>			tail;	// tail.next is always null
	
	
	public DNALinkedList(String s)
	{
		append(s);		
	}
	
	
	// Used by extraction methods. Not for public use.
	private DNALinkedList(Node<Character> head, Node<Character> tail)
	{ 
		this.head = head;
		head.setPrev(null);
		this.tail = tail;
		tail.setNext(null);
	}
	
	
	// Converts arg to nodes which are appended to end of this list.
	public void append(String s)
	{
		for (int i=0; i<s.length(); i++)
			append(s.charAt(i));
	}
	
	
	// Creates a node for ch and appends it to the linked list.
	// "Append" always means "at the end".
	public void append(char ch)
	{
		append(new Node<Character>(ch));
	}
	
	
	// Appends n to tail of this list.
	public void append(Node<Character> n)
	{
		// Corner case: empty list.
		if (tail == null)
		{
			n.setPrev(null);
			n.setNext(null);
			head = n;
			tail = n;
		}
		
		// Vanilla case.
		else
		{
			tail.setNext(n);
			n.setPrev(tail);
			tail = n;
		}
	}
	
	
	public String toString()
	{
		String s = "DNALinkedList: ";
		if (head == null)
			s += "Empty";
		else
		{
			Node<Character> n = head;
			while (n != null)
			{
				s += n.getData();
				n = n.getNext();
			}
		}
		return s;
	}
	
	
	// Returns true if the nodes starting at startNode match the target string. For example,
	// if the linked list looks like this:
	// (A) â€”> (B) -> (C) -> (D) -> (E) -> (F)
	// and if startNode is the 2nd node (â€œBâ€�), then matches(startNode, â€œBCDEâ€�) should return true.
	private boolean matches(Node<Character> startNode, String target)
	{
		
		int i = 0;
		while(startNode != null && i<target.length()) {
			if (!startNode.getData().equals(target.charAt(i))) 
				return false;
			i++;
			startNode = startNode.getNext();
		}
		return true;
		
	}
	
	
	// If this list contains a chain of nodes whose data is the target, returns
	// the node at the start of that chain. If the target appears multiple times
	// in this list, returns the first occurrence. If the target is not in this list,
	// returns null.
	public Node<Character> find(String target)
	{
		// Hint: call matches(). A lot.
		Node<Character> temp=head;
		while(temp!=null) {
			if(matches(temp,target)==true) {
				return temp;
			}
			temp=temp.getNext();
		}
		return null;

	}
	
	
	// Extract and return the nodes starting at firstExtractedNode and ending at lastExtractedNode.
	// The returned nodes should be a DNALinkedList. Donâ€™t worry about any corner cases: assume
	// firstExtractedNode and lastExtractedNode are both in the list and are not near the head or tail.
	public DNALinkedList extract(Node<Character> firstExtractedNode, Node<Character> lastExtractedNode)
	{
		// Find nodes just before and just after the chain to be extracted. Assume these
		// aren't null.
		Node<Character> beforeFirst = firstExtractedNode.getPrev();
		Node<Character> afterLast = lastExtractedNode.getNext();
		
		// Connect beforeFirst to afterLast.
		//beforeFirst.setNext(firstExtractedNode);
		//afterLast.setPrev(lastExtractedNode);
		beforeFirst.setNext(afterLast);
		afterLast.setPrev(beforeFirst);
		// Return a DNALinkedList containing the extracted chain.
		return new DNALinkedList(firstExtractedNode, lastExtractedNode);
	}

	
	// Reverses the order of the nodes.
	public void reverse()
	{
		// Swap next and prev of every node. Caution: in your loop, you won't be able
		// to advance n by setting n = n.next(). Why? How should you advance n?
		Node<Character> n = head;
		while (n != null)
		{
			Node<Character> temp=n.getPrev();
			n.setPrev(n.getNext());
		    n.setNext(temp);
			n = n.getPrev();
		}
		
		// Swap head and tail.
		Node<Character> temp = head;
		head = tail;
		tail = temp;
	}
	
	
	// Inserts insertMe into this list, at the node before insertionPoint. Assumes
	// insertionPoint is not the head or tail. 
	public void insert(DNALinkedList insertMe, Node<Character> insertionPoint)
	{
		// Find node immediately before insertion point.
		Node<Character> beforeInsertionPoint = insertionPoint.getPrev();
		
		// Connect node immediately before insertion point to head of insertMe.
		beforeInsertionPoint.setNext(insertMe.head);
		insertMe.head.setPrev(beforeInsertionPoint);
		
		// Connect tail of insertMe to insertionPoint node.
		insertMe.tail.setNext(insertionPoint);
		insertionPoint.setPrev(insertMe.tail);
	}
	

	// Removes sequence matching transposon, reverses it, and inserts it back into
	// this list immediately before target. Throws IllegalArgumentException if
	// can't find transposon or target. Use the methods you just wrote.
	public void transpose(String transposon, String target) throws IllegalArgumentException
	{
		// Find starting node of transposon.
		Node<Character> firstNodeOfTransposon = find(transposon);
		if (firstNodeOfTransposon == null)
			throw new IllegalArgumentException("transposon not found");
		
		// Find starting node of target.
		Node<Character> firstNodeOfTarget = this.find(target);
		if (firstNodeOfTarget == null)
			throw new IllegalArgumentException("target not found");
		
		// Find last node of transposon. You'll need several lines. Set a variable to the first node
		// of the transposon, then do a loop where for every char in the transposon, you set the variable
		// to its "next".
		Node<Character> lastNodeOfTransposon = firstNodeOfTransposon.getNext();
		int i = 0;	
		while (lastNodeOfTransposon != null && i < (transposon.length() - 1) ){
			if (lastNodeOfTransposon.getData().equals(transposon.charAt(i))) 
				lastNodeOfTransposon = lastNodeOfTransposon.getNext();	
			i++;		
		}
		
				
		// Extract the transposon.
		DNALinkedList transposonList = extract(firstNodeOfTransposon,lastNodeOfTransposon);
		
		// Reverse the transposon.
		transposonList.reverse();
		
		// Insert immediately before target.
		insert(transposonList, firstNodeOfTarget);
	}
		
	
	public static void main(String[] args) 
	{
		String chromosome = "1324567890";
		DNALinkedList list = new DNALinkedList(chromosome);
		System.out.println("original: " + list);
		String transposon = "67";
		String target = "4";
		list.transpose(transposon, target);
		System.out.println("transposed: " + list);
		
	}
}
