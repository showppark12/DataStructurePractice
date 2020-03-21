package jongminlist;

public class Polynomial {
	public char name;
	public JSList<Term> terms; //�� �׵��� ���Ḯ��Ʈ�� �̿��ؼ� ǥ���Ҳ���
	
	public Polynomial(char name) {
		this.name = name;
		terms = new JSList<Term>();
		// ���׽��� ���鶧 ���׽��̸��� �޾��ְ� ���Ḯ��Ʈ�� �ϳ� ������ָ鼭 �ʱ�ȭ
	}
	
	public void addTerm(int coef, int expo) {
		if (coef == 0 )
			return;
		Node<Term> p = terms.head , q= null; //Term �� �����Ͱ� �� ��� ��ü�� ����� �̸��� p���ϰ� ��ó������ ���´� ���� �־������ �˾ƾ��ϴϱ�
		while (p != null && p.data.expo > expo) { //p�� null�� �ƴϰ� ���簴ü�� �־��� expo�� ����Ʈ�� �����ִ� p�� expo���� Ŀ���� �׾տ��ٰ� �־����
			q = p ;
			p = p.next;
		}
		if (p != null && p.data.expo == expo) {
			p.data.coef += coef; // ������ ������ ����� ����
			if (p.data.coef == 0) { //�ٵ� ���ؼ� 0�� ��µ�
				if( q == null ) //�װ� ������ ����ū���̸� 
					terms.removeFirst(); //��ó�� ���� ������
				else
					terms.removeAfter(q);//�ƴϸ� q���� ���� ������
			}
		}
		else {
			//���� ���ο� ���� �����ߵ� ���ʰ� ���� ���ο� term�� ����� �װ� ���Ḯ��Ʈ�� add�� �׷��� ���Ḯ��Ʈ���� �� �����͸� ������ ��带 ����°��� �׷��ϱ�
			//ó���� JSList<Term>�ΰŴ� JSList<node>�� �ƴ϶� node�� ���� �־��ִ°� �ƴ϶� 
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
		while ( p != null) { //ó������ ������
			result += p.data.calc(x); //�� ���� ����Ѱ��� ����
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
