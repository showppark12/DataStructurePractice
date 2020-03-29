//�ǿ��� ������ ���Ǹ� �����鼭 ���� �ܹ��� ��ũ�帮��Ʈ 
package jongminlist2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class JSList<T> {
	private Node<T> head; //head ���� ������ ��尡 �����״ϱ� �翬�� Node<T>Ÿ������ �����ؾ���
	private int size; //����Ʈ�� ��� ���������� �˰� ������ �����ϱ� size�� ����
	
	public JSList() { //�����ڸ� �ϳ� �������� head�� �翬�� ��ó���̴ϱ� ���̰� ����� ��
		head = null;
		size = 0;
	}
	
	//inner class ����Ʈ �ܺο��� ����� �ſ����ؼ� �˼������� private���� ��������ϱ�
	//static 
	private static class Node<T> { 
		public T data;
		public Node<T> next;
		
		public Node(T item) {
			data = item;
			next = null;
		}
		

	}
	
	
	//���ͷ����͸� �����ϸ鼭 ���� ������������ ��
	public Iterator<T> iterator(){
		return new MyIterator();
	}
	
	private class MyIterator implements Iterator<T>{
		private Node<T> nextNode; //���������� ����� �ּҸ� �������־���ϱ⶧���� ��带 �ϳ� �������־�ߴ�
		public MyIterator() {
			nextNode = head;
		}
		
		public boolean hasNext() {
			return (nextNode != null);
		}
		public T next() {
			if (nextNode == null)
				throw new NoSuchElementException();
			T val = nextNode.data;
			nextNode = nextNode.next;
			return val;
		}
		public void remove() {
			
		}
	}
	
	public void add(int index , T item) { //add�޼ҵ� ���� add�ϴµ��� ���� return ���ٰ��� �ʿ䰡����
		//��ó������ ���ϳ� �߰����� ���ϳ� ���� ���ϳ� ���� ����� �׻� ������Ѵ� �׷��Ͽ츮�� ��ó�� ���ϴ°Ŷ� �߰��� ���ϴ°� ������ �� �������ش�
		if(index < 0 && index > size ) {//�߸������̽� index�� ������ �������־����
			throw new IndexOutOfBoundsException() ; //������ ����� ����ó������ߵǴµ� �ϴ� �̷��� 
		}
		if (index == 0) { //�ε����� 0�̸� ����Ʈ�� ��ù��°�ϱ� 
			addFirst(item);//�ֵ��۽�Ʈ �ϸ��
		}else {//�ƴϰ� �ε����� 1�̻��̸� ����Ʈ�� �߰��̴ϱ� ����� �������ٰ� ���ϴ°Ű���
			//�׷��ϱ� �ֵ������
			//���⼭ �׷��� �ε����� �޾Ƽ� �� �ε������� � ��尡�ִ��� �˷��ִ� �޼ҵ尡 �־�߰��� ������带 �˾ƾߵǴϱ�
			Node<T> before =  getNode(index-1); //before���� �츮�� �����͸� add�� �� �ϳ� ������ ��带 �����Ų��
			addAfter(before,item); //�׷��� �� �޾ƿ� ��� �������ٰ� ���ο� �� ��带 ����
			
		}
	}
	
	private Node<T> getNode(int index) { //�ε����� �޾Ƽ� ���ڸ��� �ִ� ��带 �������ִ� �޼ҵ�
		//���� ����Ʈ�� ��ȸ�ϸ鼭 ã�� ��带 �־��� ���������ߴ� 
		//�ϴ� ������ �̻��� index�����Դ��� �˻�
		if(index <0 || index >= size) //�������۰� size�� �Ȱ��Ƶ� �ȴ� �ֳĸ� ���̵尡 3�̸� index�� 2���� �����Űŵ�
			return null;
		Node<T> p = head;//ã�� ���־��� ����ϳ������
		for (int i = 0; i <index; i++) //����Ʈ ó������ ���� �ε��� ã��������
			p=p.next;
		return p;
	}

	private void addFirst(T item) { //�Ǿտ��� add���ִ� �޼ҵ�
		Node<T> newNode = new Node<T>(item);
		//���� �̰� �󸮽�Ʈ���� �ƴ����� ���ؼ� ������ ¥�ߵȴ�
		//���� �󸮽�Ʈ�� �ƴϸ� ���θ��� ����� �ؽ�Ʈ�� �����ֶ� head�� �־�����Ѵ�.
		newNode.next = head;
		//�״����� head�� p�� �־��ش�.
		head = newNode;
		//������ �ƴ϶� ���̵嵵 Ű�ӿ�����
		size++;		
	}
	private void addAfter(Node<T> before, T item) { //���� ��ó���� �ƴ� ����� �ڿ��� ���ϴ� �޼ҵ�
		Node<T> newNode = new Node<T>(item); //�ϴ� ���ϴ� �޼ҵ�ϱ� ������ ���ο� ��带 �����ߵȴ�
		//�״��� ����� �ڿ��ٴ��Ϸ��� index�� �޾Ƽ� ���������� ã�� �޼ҵ嵵 �������߰��� �װ� find
		//���� ������ Ʋ�Ⱦ� ��ũ�� ����Ʈ���� ����� �ڿ��ٰ� ���Ϸ��� before��带 �˾ƾߵ�
		newNode.next = before.next; //���ο����� next�� ��������� ������带 �־���
		before.next = newNode; // ��������� next���ٰ� ���ο� ��带 �־���
		size++; // sizeŰ��� 
	}
	
	public int indexOf(T item) { //���� ó���� find��� �޼ҵ尡 �ʿ����پ˾Ҵµ� ¶�� ���� �����̱������� indexOf��� �޼ҵ常���ߴ�
		//�������� ������ �� �������� �ε����� �������ִ� �޼ҵ�
		//�׷��� ����Ʈ�� ��ȸ�ϸ鼭 �׾������� ã�ƾ߰���
		Node<T> p = head; //��ȸ�� ���
		int index = 0; //ã�� ����� �ε���
		while(p !=null) { //p�� null�� �ɶ����� = ������ ��������
			if(p.data.equals(item)) // �Է¹��� �����۰� ���� �����ִ� p�� �����Ŷ��
				return index;//�ε��� ����
			p=p.next;//�ƴϸ� ���� ���ΰ���
			index++;//�ε����� �÷���
		}
		return -1; //������ �ȳ����� -1 ��������
	}
	
	public T remove(int index) {
		if (index <0 && index >= size ) {
			throw new IndexOutOfBoundsException() ;
		}
		//������ �Ǿտ��ִ°��� �߰����ִ°����� ���ߵ���
		if(index == 0) {
			return removeFirst();
		}
		else {
			Node<T> before = getNode(index-1);//������������带 �˾ƾ���
			return removeAfter(before);
		}
		
	}
	
	public boolean remove(T item) { // delete �� ����Ʈ���� Ǯ�� ����Ʈ������ �׻� ���ο� �ֶ�
		Node<T> p = head;
		Node<T> q = null;
		while(p!=null && !p.data.equals(item)) {
			q = p;
			p = p.next;
		}
		if (p == null)
			return false;
		if (q==null) {
			T tmp = removeFirst();
			return (tmp != null);
		}
		else {
			T tmp = removeAfter(q);
			return (tmp != null);
		}	
	}
	
	
	private T removeAfter(Node<T> before) {
		if(before.next==null)//���࿡ ������ ��尡 ���ٸ� ���� �����ؾ���
			return null;
		T tmp = before.next.data;//������ ����� �����͸� �̸�������
		before.next = before.next.next;
		return tmp;
	}

	private T removeFirst() { //�Ǿտ� ��� �����ϱ�
		T tmp = head.data;//�����ҵ���Ÿ�� ���� ����������
		head=head.next;//�׸��� ��忡�ٰ� �����ִ� ����� �ؽ�Ʈ�� ��������ָ� �����Ϸ�
		size--;//������ ���̰�
		return tmp;
	}
	public T get( int index) {
		if ( index < 0 || index >= size) {
			return null;
		}
		return getNode(index).data;
	}

	public int size() {
		return size;
	}

}
