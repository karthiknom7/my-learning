package com.logical.ds.tree;

public class BinarySearchTree {

	private TreeNode root;

	public void insertNode(int data) {
		if (root == null)
			root = new TreeNode(data);

		root.insertNode(data);
	}

	public void removeNode(int data) {

		TreeNode current = this.root;
		TreeNode parent = this.root;
		if (current == null)
			return;
		boolean isLeftChild = false;
		// Iterate through the tree
		while (current != null && current.getData() != data) {
			parent = current;
			if (current.getData() < data) {
				current = current.getRightChild();
				isLeftChild = false;
			} else {
				current = current.getLeftChild();
				isLeftChild = true;
			}
		}

		if (current == null)
			return;

		// check if it is leaf node
		if (current.getLeftChild() == null && current.getRightChild() == null) {
			if (current == root) {
				root = null;
			} else {
				if (isLeftChild)
					parent.setLeftChild(null);
				else
					parent.setRightChild(null);
			}

			// check if node has single child
		} else if (current.getRightChild() == null) {
			if (current == root) {
				root = current.getLeftChild();
			} else if (isLeftChild) {
				parent.setLeftChild(current.getLeftChild());
			} else {
				parent.setRightChild(current.getLeftChild());
			}
		} else if (current.getLeftChild() == null) {
			if (current == root) {
				root = current.getRightChild();
			} else if (isLeftChild) {
				parent.setLeftChild(current.getRightChild());
			} else {
				parent.setRightChild(current.getRightChild());
			}

			// if node has both the child
		} else {
			// TODO get code
		}

	}

	// soft delete
	public void deleteNode(int data) {
		TreeNode delNode = search(data);
		delNode.setDeleted(true);
	}

	public TreeNode search(int data) {
		if (root != null) {
			return root.find(data);
		}
		return null;
	}

	public Integer findMinimum() {
		if (root == null)
			return null;
		return root.minimum().getData();
	}

	public Integer findMax() {
		if (root == null)
			return null;
		return root.max().getData();
	}
	
	public void inOrder(){
		if(root != null){
			root.inOrderTravelsal();
			System.out.println(" ");
			root.traverseInOrder();
		}
	}
	
	public static void main(String[] args) {
		BinarySearchTree binarySearchTree = new BinarySearchTree();
		binarySearchTree.insertNode(20);
		binarySearchTree.insertNode(25);
		binarySearchTree.insertNode(6);
		binarySearchTree.insertNode(7);
		binarySearchTree.insertNode(15);
		binarySearchTree.insertNode(11);
		binarySearchTree.insertNode(4);
		binarySearchTree.insertNode(2);
		binarySearchTree.insertNode(13);
		
		binarySearchTree.inOrder();
	}
}
