package listClasses;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

import com.sun.corba.se.impl.activation.ProcessMonitorThread;

public class BasicLinkedList<T> implements Iterable<T> {

	private Node head;
	private Node tail;
	private int N;

	public BasicLinkedList() {
		head = null;
		tail = null;
		N = 0;
	}

	public Iterator<T> iterator() {
		return new LinkedListIterator();
	}

	private class LinkedListIterator implements Iterator<T> {
		private Node current = head;

		public T next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			T item = current.data;
			current = current.next;
			return item;
		}

		public boolean hasNext() {
			return current != null;
		}

		public void remove() {
			throw new UnsupportedOperationException();
		}
	}

	public BasicLinkedList<T> addToEnd(T item) {
		// TODO Auto-generated method stub
		if (item == null) {
			throw new NullPointerException("The first argument for addLast() is null.");
		}
		if (!isEmpty()) {
			Node temp = new Node(item, null);
			tail.next = temp;
			tail = temp;
			// temp.setNext(tail);
		} else {
			tail = new Node(item, null);
			head = tail;
		}
		N++;
		return this;
	}

	public boolean isEmpty() {
		return N == 0;
	}

	public BasicLinkedList<T> addToFront(T item) {
		// TODO Auto-generated method stub
		if (item == null) {
			throw new NullPointerException("The first argument for addLast() is null.");
		}
		if (!isEmpty()) {
			Node temp = head;
			head = new Node(item, temp);
			// head.setNext(temp);

		} else {
			head = new Node(item, null);
			tail = head;
		}
		N++;
		return this;
	}

	public T getFirst() {
		// TODO Auto-generated method stub
		return head.data;
	}

	public int getSize() {
		// TODO Auto-generated method stub
		return N;
	}

	// Removes and returns the first element from the list. If there are no elements
	// the method returns null. Do not implement this method using iterators.
	public T retrieveFirstElement() {
		// TODO Auto-generated method stub
		if (isEmpty()) {
			return null;
		}
		if (N == 1) {
			Node temp = head;
			head = null;
			tail = null;
			N--;
			return temp.data;
		}
		if (!isEmpty()) {
			Node temp = head;
			head = temp.next;
			N--;
			return temp.data;
		}
		return null;
	}

	public BasicLinkedList<T> remove(T selectedNode, Comparator<T> comparator) {
		// TODO Auto-generated method stub
		if (isEmpty()) {
			throw new IllegalStateException("Cannot remove() from and empty list.");
		}

		System.out.println("Remove Color " + selectedNode.toString());

		Node previous = head;
		Node currentNode = head;

		while (currentNode.next != null) {
			// if (currentNode.data.equals(selectedNode)) {
			if (comparator.compare(currentNode.data, selectedNode) == 0) {
				// remove the last remaining element
				if (N == 1) {
					head = null;
					tail = null;
					N--;
				}

				// remove first element
				// else if (currentNode.equals(head)) {
				else if (comparator.compare(currentNode.data, head.data) == 0) {
					head = head.next;
					N--;
				}

				// remove last element
				else if (currentNode.equals(tail)) {
					tail = previous;
					tail.next = null;
					N--;
					break;
				}

				// remove element
				else {
					previous.next = currentNode.next;
					N--;
				}

			}
			previous = currentNode;
			currentNode = previous.next;
		}
		return this;
	}

	// Removes and returns the last element from the list. If there are no elements
	// the method returns null.
	// Do not implement implement this method using iterators.

	public T retrieveLastElement() {
		// TODO Auto-generated method stub
		if (isEmpty()) {
			return null;
		}
		if (N == 1) {
			Node temp = head;
			head = null;
			tail = null;
			N--;
			return temp.data;
		}
		if (!isEmpty()) {
			Node prev = head;
			Node curr = head;
			Node temp = null;

			while (curr.next != null || curr == tail) {
				if (curr.next == tail) {

					temp = tail;
					tail = curr;
					curr.next = null;
					N--;
					return temp.data;
				}
				prev = curr;
				curr = prev.next;
			}

			// return temp.data;
		}
		return null;
	}

	public T getLast() {
		// TODO Auto-generated method stub
		return tail.data;
	}

	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		for (T item : this)
			s.append(item + " ");
		return s.toString();
	}

	public String ItemString(Node item) {
		item.data.toString();
		return null;
	}

	class Node {

		protected T data;
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
