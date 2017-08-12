package com.henrybear.util;

/**
 * @author xrong2011 2017/8/12 导入栈类，push(T item)入栈，pop()出栈
 *
 * @param <T>
 */
public class Stack<T> {

	private static class Node<U> {
		U item;
		Node<U> next;
		Node(){
			item = null;
			next = null;
		}
		Node(U item,Node<U> next){
			this.item = item;
			this.next = next;
		}
		
		boolean end(){
			return item == null && next == null;
		}
	}
	private Node<T> top = new Node<T>();
	
	public void push(T item){
		top = new Node<T>(item,top);
	}
	public T pop(){
		T result = top.item;
		if(!top.end()){
			top = top.next;
		}
		return result;
	}
	
	public boolean isEmpty(){
		return top.item == null;
	}
}
