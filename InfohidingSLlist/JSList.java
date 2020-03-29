//권오흠 교수님 강의를 들으면서 만든 단방향 링크드리스트 
package jongminlist2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class JSList<T> {
	private Node<T> head; //head 에는 무조건 노드가 들어올테니까 당연히 Node<T>타입으로 설정해야지
	private int size; //리스트에 몇개가 들어오는지는 알고 있으면 좋으니까 size도 설정
	
	public JSList() { //생성자를 하나 만들어놓자 head는 당연히 맨처음이니까 널이고 사이즈도 영
		head = null;
		size = 0;
	}
	
	//inner class 리스트 외부에선 노드라는 거에대해선 알수가없어 private으로 만들었으니까
	//static 
	private static class Node<T> { 
		public T data;
		public Node<T> next;
		
		public Node(T item) {
			data = item;
			next = null;
		}
		

	}
	
	
	//이터레이터를 구현하면서 좋은 폴리몰피즘의 예
	public Iterator<T> iterator(){
		return new MyIterator();
	}
	
	private class MyIterator implements Iterator<T>{
		private Node<T> nextNode; //내부적으로 노드의 주소를 가지고있어야하기때문에 노드를 하나 가지고있어야대
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
	
	public void add(int index , T item) { //add메소드 구현 add하는데는 따로 return 해줄것이 필요가없다
		//맨처음에다 더하냐 중간에다 더하냐 끝에 더하냐 류의 고민을 항상 해줘야한다 그러니우리는 맨처음 더하는거랑 중간에 더하는걸 나눠서 더 구현해준다
		if(index < 0 && index > size ) {//잘못된케이스 index가 정상적 범위에있어야지
			throw new IndexOutOfBoundsException() ; //원래는 제대로 예외처리해줘야되는데 일단 이렇게 
		}
		if (index == 0) { //인덱스가 0이면 리스트의 맨첫번째니까 
			addFirst(item);//애드퍼스트 하면됨
		}else {//아니고 인덱스가 1이상이면 리스트의 중간이니까 어떤거의 다음에다가 더하는거겟지
			//그러니까 애드애프터
			//여기서 그러면 인덱스를 받아서 그 인덱스에는 어떤 노드가있는지 알려주는 메소드가 있어야겟지 이전노드를 알아야되니까
			Node<T> before =  getNode(index-1); //before노드는 우리가 데이터를 add할 곳 하나 이전의 노드를 저장시킨다
			addAfter(before,item); //그러면 그 받아온 노드 다음에다가 새로운 뉴 노드를 저장
			
		}
	}
	
	private Node<T> getNode(int index) { //인덱스를 받아서 그자리에 있는 노드를 리턴해주는 메소드
		//먼저 리스트를 순회하면서 찾는 노드를 넣어줄 통을만들어야대 
		//일단 그전에 이상한 index가들어왔는지 검사
		if(index <0 || index >= size) //영보다작고 size와 똑같아도 안대 왜냐면 사이드가 3이면 index는 2까지 있을거거든
			return null;
		Node<T> p = head;//찾는 노드넣어줄 노드하나만들고
		for (int i = 0; i <index; i++) //리스트 처음부터 돌아 인덱스 찾을때까지
			p=p.next;
		return p;
	}

	private void addFirst(T item) { //맨앞에다 add해주는 메소드
		Node<T> newNode = new Node<T>(item);
		//이제 이게 빈리스트인지 아닌지도 겸해서 로직을 짜야된다
		//만약 빈리스트가 아니면 새로만든 노드의 넥스트에 원래있떤 head를 넣어줘야한다.
		newNode.next = head;
		//그다음에 head에 p를 넣어준다.
		head = newNode;
		//끝ㅌ이 아니라 사이드도 키ㅣ워야지
		size++;		
	}
	private void addAfter(Node<T> before, T item) { //이젠 맨처음이 아닌 어떤거의 뒤에다 더하는 메소드
		Node<T> newNode = new Node<T>(item); //일단 더하는 메소드니까 무조건 새로운 노드를 만들어야된다
		//그다음 어떤거의 뒤에다더하려면 index를 받아서 같ㅇ은건지 찾는 메소드도 만들어줘야겟지 그게 find
		//위의 생각은 틀렸어 링크드 리스트에서 어떤거의 뒤에다가 더하려면 before노드를 알아야돼
		newNode.next = before.next; //새로운노드의 next에 비포노드의 다음노드를 넣어줘
		before.next = newNode; // 비포노드의 next에다가 새로운 노드를 넣어줘
		size++; // size키우기 
	}
	
	public int indexOf(T item) { //나는 처음에 find라는 메소드가 필요할줄알았는데 쨋든 같은 느낌이긴하지만 indexOf라는 메소드만들어야댕
		//아이템을 받으면 그 아이템의 인덱스를 리턴해주는 메소드
		//그러면 리스트를 순회하면서 그아이템을 찾아야겟지
		Node<T> p = head; //순회할 노드
		int index = 0; //찾은 노드의 인덱스
		while(p !=null) { //p가 null이 될때까지 = 끝까지 돌때까지
			if(p.data.equals(item)) // 입력받은 아이템과 내가 돌고있던 p와 같은거라면
				return index;//인덱스 리턴
			p=p.next;//아니면 다음 노드로가고
			index++;//인덱스를 늘려줘
		}
		return -1; //끝까지 안나오면 -1 리턴해줘
	}
	
	public T remove(int index) {
		if (index <0 && index >= size ) {
			throw new IndexOutOfBoundsException() ;
		}
		//이제또 맨앞에있는건지 중간에있는건지도 봐야되지
		if(index == 0) {
			return removeFirst();
		}
		else {
			Node<T> before = getNode(index-1);//삭제도이전노드를 알아야지
			return removeAfter(before);
		}
		
	}
	
	public boolean remove(T item) { // delete 빈 리스트인지 풀방 리스트인지를 항상 염두에 둬라
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
		if(before.next==null)//만약에 삭제할 노드가 없다면 널을 리턴해야지
			return null;
		T tmp = before.next.data;//삭제할 노드의 데이터를 미리저장해
		before.next = before.next.next;
		return tmp;
	}

	private T removeFirst() { //맨앞에 노드 삭제하기
		T tmp = head.data;//삭제할데이타를 따로 변수에저장
		head=head.next;//그리고 헤드에다가 원래있던 헤드의 넥스트를 저장시켜주면 삭제완료
		size--;//사이즈 줄이고
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
