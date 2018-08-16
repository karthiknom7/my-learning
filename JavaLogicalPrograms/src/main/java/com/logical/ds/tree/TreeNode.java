package com.logical.ds.tree;

public class TreeNode {

	private int data;

	private TreeNode leftChild;
	private TreeNode rightChild;
	private boolean isDeleted;

	public TreeNode(int data) {
		this.data = data;
	}

	public int getData() {
		return data;
	}

	public TreeNode getLeftChild() {
		return leftChild;
	}

	public void setLeftChild(TreeNode leftChild) {
		this.leftChild = leftChild;
	}

	public TreeNode getRightChild() {
		return rightChild;
	}

	public void setRightChild(TreeNode rightChild) {
		this.rightChild = rightChild;
	}

	public TreeNode find(int data) {
		if (this.data == data && !isDeleted) {
			return this;
		}

		if (leftChild != null && data < this.data) {
			return leftChild.find(data);
		} else if (rightChild != null) {
			return rightChild.find(data);
		} else {
			return null;
		}

	}

	public void insertNode(int data) {
		if (this.data < data) {
			if (rightChild == null) {
				rightChild = new TreeNode(data);
			} else {
				rightChild.insertNode(data);
			}
		} else {
			if (leftChild == null) {
				leftChild = new TreeNode(data);
			} else {
				leftChild.insertNode(data);
			}
		}
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public TreeNode minimum() {
		if (this.leftChild == null) {
			return this;
		} else {
			return this.leftChild.minimum();
		}
	}

	public TreeNode max() {
		if (this.rightChild == null) {
			return this;
		} else {
			return this.rightChild.max();
		}
	}

	public void inOrderTravelsal() {
		if (this.leftChild != null) {
			this.leftChild.inOrderTravelsal();
		}
		System.out.print(this.data + " ");
		if (this.rightChild != null) {
			this.rightChild.inOrderTravelsal();
		}

	}
	
	public void traverseInOrder() {
		if (this.leftChild != null)
			this.leftChild.traverseInOrder();
		System.out.print(this + " ");
		if (this.rightChild != null)
			this.rightChild.traverseInOrder();
	}
	
	
	@Override
	public String toString() {
		return String.valueOf(this.data);
	}
}
