package jongminlist;

public class Polynomial {
	public char name;
	public JSList<Term> terms; //각 항들을 연결리스트를 이용해서 표현할꺼다
	
	public Polynomial(char name) {
		this.name = name;
		terms = new JSList<Term>();
		// 다항식을 만들때 다항식이름을 받아주고 연결리스트를 하나 만들어주면서 초기화
	}
	
	public void addTerm(int coef, int expo) {
		if (coef == 0 )
			return;
		Node<Term> p = terms.head , q= null; //Term 형 데이터가 들어갈 노드 객체를 만들고 이름을 p로하고 맨처음으로 놓는다 어디다 넣어야할지 알아야하니까
		while (p != null && p.data.expo > expo) { //p가 null이 아니고 현재객체에 넣어줄 expo가 리스트를 돌고있는 p의 expo보다 커지면 그앞에다가 넣어야지
			q = p ;
			p = p.next;
		}
		if (p != null && p.data.expo == expo) {
			p.data.coef += coef; // 지수가 같으면 계수를 더해
			if (p.data.coef == 0) { //근데 더해서 0이 됬는데
				if( q == null ) //그게 지수가 가장큰항이면 
					terms.removeFirst(); //맨처음 항을 삭제해
				else
					terms.removeAfter(q);//아니면 q다음 항을 삭제해
			}
		}
		else {
			//드디어 새로운 항을 만들어야될 차례가 오면 새로운 term을 만들고 그걸 연결리스트에 add해 그러면 연결리스트에선 그 데이터를 가지고 노드를 만드는거지 그러니까
			//처음에 JSList<Term>인거다 JSList<node>가 아니라 node는 직접 넣어주는게 아니라 
			Term t = new Term(coef , expo);
			if (q == null)
				terms.addFirst(t);
			else
				terms.addAfter(q,t);
		}
	}
	
	public int calc(int x) {
		int result = 0 ;
		Node<Term> p = terms.head;
		while ( p != null) { //처음부터 끝까지
			result += p.data.calc(x); //각 항을 계산한것을 더해
			p = p.next;
		}
		return result;
		
	}
	
	public String toString() {
		String result = "";
		Node<Term> p = terms.head;
		while ( p != null) {
			result += ("+"+ p.data.toString());
			p= p.next;
		}
		return result;
	}





}
