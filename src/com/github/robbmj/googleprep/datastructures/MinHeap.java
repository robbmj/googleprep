package com.github.robbmj.googleprep.datastructures;

public final class MinHeap<T extends Comparable<T>> extends Heap<T> {

	public MinHeap(Linkedlist<T> values) {
		for (T value: values) {
			add(value);
		}
	}

	@Override
	public void add(T value) {
		super.add(value);
		heapifyUp(heap.size() - 1);
	}

	@Override
	public T remove() {
		T obj = super.remove();
		heapifyDown(0);
		return obj;
	}

	
	
	@Override
	protected void heapifyUp(int index) {
		if (index == 0) {
			return;
		}
		
		// find the parent
		int parentIndex = (index - 1) / 2;
		int comparison = heap.get(parentIndex).compareTo(heap.get(index));
		
		if (comparison > 0) {
			swap(parentIndex, index);
		}
		
		heapifyUp(parentIndex);
		
	}

	@Override
	protected void heapifyDown(int index) {
		int indexOfLeftChild = (index * 2) + 1;
		int indexOfRightChild = (index * 2) + 2;
		
		boolean leftChildExists = indexOfLeftChild < this.sortedPartition();
		boolean rightChildExists = indexOfRightChild < this.sortedPartition();
		
		if (!leftChildExists && !rightChildExists && index >= this.sortedPartition()) {
			return;
		}
		
		T leftChild = null;
		T rightChild = null;
		
		if (leftChildExists) {
			leftChild = heap.get(indexOfLeftChild);
		}
		
		if (rightChildExists) {
			rightChild = heap.get(indexOfRightChild);
		}
		
		if ( ( leftChildExists && leftChild.compareTo(heap.get(index)) < 0 ) || 
			 ( rightChildExists && rightChild.compareTo(heap.get(index)) < 0)) {
			
			int indexSwapedOn;
			
			if (leftChildExists && !rightChildExists) {
				swap(index, indexOfLeftChild);
				indexSwapedOn = indexOfLeftChild;
			}
			else if (!leftChildExists && rightChildExists) {
				swap(index, indexOfRightChild);
				indexSwapedOn = indexOfRightChild;
			}
			else if (leftChild.compareTo(rightChild) < 0) {
				swap(index, indexOfLeftChild);
				indexSwapedOn = indexOfLeftChild;
			}
			else if (leftChild.compareTo(rightChild) > 0) {
				swap(index, indexOfRightChild);
				indexSwapedOn = indexOfRightChild;
			}
			else {
				swap(index, indexOfLeftChild);
				indexSwapedOn = indexOfLeftChild;
			}
			
			heapifyDown(indexSwapedOn);
		}
		
		
	}
		
}

