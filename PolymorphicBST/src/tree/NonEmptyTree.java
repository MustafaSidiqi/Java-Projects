package tree;

import java.util.Collection;

/**
 * This class represents a non-empty search tree. An instance of this class
 * should contain:
 * <ul>
 * <li>A key
 * <li>A value (that the key maps to)
 * <li>A reference to a left Tree that contains key:value pairs such that the
 * keys in the left Tree are less than the key stored in this tree node.
 * <li>A reference to a right Tree that contains key:value pairs such that the
 * keys in the right Tree are greater than the key stored in this tree node.
 * </ul>
 * 
 */
public class NonEmptyTree<K extends Comparable<K>, V> implements Tree<K, V> {

	/* Provide whatever instance variables you need */

	/**
	 * Only constructor we need.
	 * 
	 * @param key
	 * @param value
	 * @param left
	 * @param right
	 */

	K MyKey;
	V MyValue;
	Tree<K, V> MyLeft;
	Tree<K, V> MyRight;

	public NonEmptyTree(K key, V value, Tree<K, V> left, Tree<K, V> right) {
		MyKey = key;
		MyValue = value;
		MyLeft = left;
		MyRight = right;
	}

	public V search(K key) {
		if (key.compareTo(this.MyKey) == 0) {
			return this.MyValue;
		} else {
			if (key.compareTo(MyKey) < 0) {
				return this.MyLeft.search(key);
			}
			if (key.compareTo(MyKey) > 0) {
				return this.MyRight.search(key);
			}
		}
		return null;
	}

	/**
	 * Insert/update the Tree with a new key:value pair. If the key already exists
	 * in the tree, update the value associated with it. If the key doesn't exist,
	 * insert the new key value pair.
	 * 
	 * This method returns a reference to an Tree that represents the updated value.
	 * In many, but not all cases, the method may just return a reference to this.
	 * This method is annotated @CheckReturnValue because you have to pay attention
	 * to the return value; if you simply invoke insert on a Tree and ignore the
	 * return value, your code is almost certainly wrong.
	 * 
	 * @param key
	 *            -- Key
	 * @param value
	 *            -- Value that the key maps to
	 * @return -- updated tree
	 */
	public NonEmptyTree<K, V> insert(K key, V value) {
		int result = key.compareTo(this.MyKey);

		if (result == 0) {
			MyValue = value;

		} else if (result > 0) {
			MyRight = MyRight.insert(key, value);

		} else if (result < 0) {
			MyLeft = MyLeft.insert(key, value);
		}

		return this;
	}

	public Tree<K, V> delete(K key) {
		if (key.compareTo(this.MyKey) > 0) {
			this.MyRight.delete(key);
		}

		else if (key.compareTo(this.MyKey) < 0) {
			this.MyLeft.delete(key);
		}

		else {
			try {
				this.MyValue = this.search(this.MyLeft.max());
				this.MyKey = this.MyLeft.max();
				this.MyLeft.delete(this.MyKey);
			} catch (TreeIsEmptyException e) {

				try {
					this.MyValue = this.search(this.MyRight.min());
					this.MyKey = this.MyRight.min();
					this.MyRight.delete(this.MyKey);
				} catch (TreeIsEmptyException f) {
					// remove 'this'
				}
			}
		}

		return this;
	}

	public K max() {
		if (this.MyRight == null) {
			return MyKey;
		} else {
			try {
				MyRight.max();
			} catch (TreeIsEmptyException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return this.MyKey;
	}

	public K min() {
		if (this.MyLeft == null) {
			return MyKey;
		} else {
			try {
				MyLeft.min();
			} catch (TreeIsEmptyException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return this.MyKey;
	}

	public int size() {
		return 1 + MyLeft.size() + MyRight.size();
	}

	public void addKeysToCollection(Collection<K> c) {
		c.add(MyKey);
		MyLeft.addKeysToCollection(c);
		MyRight.addKeysToCollection(c);
	}

	public Tree<K, V> subTree(K fromKey, K toKey) {
		if (this.MyKey.compareTo(fromKey) < 0) {
			return this.MyRight.subTree(fromKey, toKey);
		} else if (toKey.compareTo(this.MyKey) < 0) {
			return this.MyLeft.subTree(fromKey, toKey);
		} else {
			return new NonEmptyTree<K, V>(this.MyKey, this.MyValue, MyLeft.subTree(fromKey, toKey),
					MyRight.subTree(fromKey, toKey));
		}
	}

	public int height() {
		return 1 + Math.max(MyLeft.height(), MyRight.height());
	}

	public void inorderTraversal(TraversalTask<K, V> p) {
		if (MyKey == null)
			return;

		/* first recur on left child */
		MyLeft.inorderTraversal(p);

		/* now recur on root */
		p.performTask(MyKey, MyValue);

		/* now recur on right child */
		MyRight.inorderTraversal(p);
	}

	public void rightRootLeftTraversal(TraversalTask<K, V> p) {
		/* first recur on left child */
		MyRight.inorderTraversal(p);

		/* now recur on root */
		p.performTask(MyKey, MyValue);

		/* now recur on right child */
		MyLeft.inorderTraversal(p);
	}
}