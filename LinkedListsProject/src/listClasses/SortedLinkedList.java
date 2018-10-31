package listClasses;

import java.nio.channels.UnsupportedAddressTypeException;
import java.util.Collection;
import java.util.Comparator;

import com.sun.org.apache.xalan.internal.xsltc.compiler.Template;

import listClasses.BasicLinkedList.Node;

public class SortedLinkedList<T> extends BasicLinkedList<T> {

	private Node head;
	private Node tail;
	private int N;
	Comparator<T> comparator;

	public SortedLinkedList(Comparator<T> comparator2) {
		super();
		comparator = comparator2;
	}

	// Inserts the specified element at the correct position in the sorted list.
	// Notice we can insert the same element several times. Your implementation must
	// traverse the list only once in order to perform the insertion. Do not
	// implement this method using iterators. Notice that you don't need to call any
	// of the super class methods in order to implement this method.

	public T add(T element) {

		Node previous = head;
		Node currentNode = head;
		Node temp = new Node(element, null);	

		if (element == null) {
			throw new NullPointerException("The first argument for addLast() is null.");
		}
		
		if (!isEmpty()) {			
			while (currentNode.next != null || currentNode == tail) {
				
				    int comparison = element.toString().compareTo(currentNode.toString());
				   
				    if (comparison >= 0) { // Or > 0 for stable sort.
				        temp.next = currentNode; // Insert in front.
				        return temp.data;
				    }
				}
				previous = currentNode;
				currentNode = previous.next;
			} else {
			head = new Node(element, null);
			tail = head;
		}
		return temp.data;
	}
	
	
	public SortedLinkedList<T> remove(T targetData){
		super.remove(targetData, comparator);
		return this;
	}

	@Override
	public BasicLinkedList<T> addToFront(T data) {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public BasicLinkedList<T> addToEnd(T data) {
		throw new UnsupportedOperationException();
	}
	
	class Node {
		T data;
		protected Node next;

		public Node(T data, Node next) {
			this.setData(data);
			this.setNext(next);
		}

		public T getData() {
			return data;
		}

		public void setData(T data) {
			this.data = data;
		}

		public Node getNext() {
			return next;
		}

		public void setNext(Node next) {
			this.next = next;
		}
	}
	
}
