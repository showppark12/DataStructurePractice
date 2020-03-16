//�ǿ��� ������ ���Ǹ� �����鼭 ���� �ܹ��� ��ũ�帮��Ʈ 
package jongminlist;

public class JSList<T> {
	private Node<T> head; //head ���� ������ ��尡 �����״ϱ� �翬�� Node<T>Ÿ������ �����ؾ���
	private int size; //����Ʈ�� ��� ���������� �˰� ������ �����ϱ� size�� ����
	
	public JSList() { //�����ڸ� �ϳ� �������� head�� �翬�� ��ó���̴ϱ� ���̰� ����� ��
		head = null;
		size = 0;
	}
	
	public void add(int index , T item) { //add�޼ҵ� ���� add�ϴµ��� ���� return ���ٰ��� �ʿ䰡����
		//��ó������ ���ϳ� �߰����� ���ϳ� ���� ���ϳ� ���� ����� �׻� ������Ѵ� �׷��Ͽ츮�� ��ó�� ���ϴ°Ŷ� �߰��� ���ϴ°� ������ �� �������ش�
		if(index < 0 && index > size ) {//�߸������̽� index�� ������ �������־����
			return ; //������ ����� ����ó������ߵǴµ� �ϴ� �̷��� 
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
	
	public Node<T> getNode(int index) { //�ε����� �޾Ƽ� ���ڸ��� �ִ� ��带 �������ִ� �޼ҵ�
		//���� ����Ʈ�� ��ȸ�ϸ鼭 ã�� ��带 �־��� ���������ߴ� 
		//�ϴ� ������ �̻��� index�����Դ��� �˻�
		if(index <0 || index >= size) //�������۰� size�� �Ȱ��Ƶ� �ȴ� �ֳĸ� ���̵尡 3�̸� index�� 2���� �����Űŵ�
			return null;
		Node<T> p = head;//ã�� ���־��� ����ϳ������
		for (int i = 0; i <index; i++) //����Ʈ ó������ ���� �ε��� ã��������
			p=p.next;
		return p;
	}

	public void addFirst(T item) { //�Ǿտ��� add���ִ� �޼ҵ�
		Node<T> newNode = new Node<T>(item);
		//���� �̰� �󸮽�Ʈ���� �ƴ����� ���ؼ� ������ ¥�ߵȴ�
		//���� �󸮽�Ʈ�� �ƴϸ� ���θ��� ����� �ؽ�Ʈ�� �����ֶ� head�� �־�����Ѵ�.
		newNode.next = head;
		//�״����� head�� p�� �־��ش�.
		head = newNode;
		//������ �ƴ϶� ���̵嵵 Ű�ӿ�����
		size++;		
	}
	public void addAfter(Node<T> before, T item) { //���� ��ó���� �ƴ� ����� �ڿ��� ���ϴ� �޼ҵ�
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
			return null;
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
	
	
	public T removeAfter(Node<T> before) {
		if(before.next==null)//���࿡ ������ ��尡 ���ٸ� ���� �����ؾ���
			return null;
		T tmp = before.next.data;//������ ����� �����͸� �̸�������
		before.next = before.next.next;
		return tmp;
	}

	publicT removeFirst() { //�Ǿտ� ��� �����ϱ�
		T tmp = head.data;//�����ҵ���Ÿ�� ���� ����������
		head=head.next;//�׸��� ��忡�ٰ� �����ִ� ����� �ؽ�Ʈ�� ��������ָ� �����Ϸ�
		size--;//������ ���̰�
		return tmp;
	}

	public static void main(String[] args) {
		JSList<String> list = new JSList<String>();
		list.add(0,"saturday");
		System.out.println(list.getNode(0).data);
		list.add(1, "sunday");
		System.out.println(list.getNode(1).data);
		list.remove(0);
		System.out.println(list.getNode(0).data);
		System.out.println(list.indexOf("sunday"));
	}

}
