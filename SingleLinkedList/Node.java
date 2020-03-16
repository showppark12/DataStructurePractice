//권오흠교수님 강의를 들으면서 만든 노드
package jongminlist;

public class Node<T> {
	public T data;
	public Node<T> next;
	
	public Node(T item) {
		data = item;
		next = null;
	}
	

}
