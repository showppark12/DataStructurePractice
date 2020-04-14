package kwlinkedlist;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

public class Polynomial {
	public char name;
	//�ڹٿ��� �����ϴ� ��ũ�� ����Ʈ �̿�
	public LinkedList<Term> terms; //�� �׵��� ���Ḯ��Ʈ�� �̿��ؼ� ǥ���Ҳ���
	
	public Polynomial(char name) {
		this.name = name;
		terms = new LinkedList<Term>();
		// ���׽��� ���鶧 ���׽��̸��� �޾��ְ� ���Ḯ��Ʈ�� �ϳ� ������ָ鼭 �ʱ�ȭ
	}
	
/*
 * ���� ���Ḯ��Ʈ�� �����ϰ� implementation�� interface�� �и��س����� �������� ��������� ����� addTerm�� �̿��ϱ�����
 * �׷��� ���߿��Ḯ��Ʈ�� �ʿ������� ����
 * 
 * 
 * 
 */
	public void addTerm(int coef, int expo) {
		if (coef == 0 )
			return;
		
		ListIterator<Term> iter = terms.listIterator();
		while(iter.hasNext()) {
			Term t = iter.next();
			if (t.expo == expo) {
				t.coef += coef;
				if ( t.coef == 0) {
					iter.remove();
				return;
				}
			}
			else if (t.expo < expo) {
				iter.previous();
				iter.add(new Term(coef,expo));
			}
		}
//		Node<Term> p = terms.head , q= null; //Term �� �����Ͱ� �� ��� ��ü�� ����� �̸��� p���ϰ� ��ó������ ���´� ���� �־������ �˾ƾ��ϴϱ�
//		while (p != null && p.data.expo > expo) { //p�� null�� �ƴϰ� ���簴ü�� �־��� expo�� ����Ʈ�� �����ִ� p�� expo���� Ŀ���� �׾տ��ٰ� �־����
//			q = p ;
//			p = p.next;
//		}
//		if (p != null && p.data.expo == expo) {
//			p.data.coef += coef; // ������ ������ ����� ����
//			if (p.data.coef == 0) { //�ٵ� ���ؼ� 0�� ��µ�
//				if( q == null ) //�װ� ������ ����ū���̸� 
//					terms.removeFirst(); //��ó�� ���� ������
//				else
//					terms.removeAfter(q);//�ƴϸ� q���� ���� ������
//			}
//		}
//		else {
//			//���� ���ο� ���� �����ߵ� ���ʰ� ���� ���ο� term�� ����� �װ� ���Ḯ��Ʈ�� add�� �׷��� ���Ḯ��Ʈ���� �� �����͸� ������ ��带 ����°��� �׷��ϱ�
//			//ó���� JSList<Term>�ΰŴ� JSList<node>�� �ƴ϶� node�� ���� �־��ִ°� �ƴ϶� 
//			Term t = new Term(coef , expo);
//			if (q == null)
//				terms.addFirst(t);
//			else
//				terms.addAfter(q,t);
//		}
	}
	
	public int calc(int x) {
		int result = 0 ;
//		Node<Term> p = terms.head;
//		while ( p != null) { //ó������ ������
//			result += p.data.calc(x); //�� ���� ����Ѱ��� ����
//			p = p.next;
//		}
//������ �̷��� �ؼ� ����Ʈ�� ��ȸ�߾��µ� ��带 private���� ����Ʈ ���ηοű�鼭 ���ο��� ��ȸ�ϴ°� �Ұ��� �׷��� �̤��ͷ����� �ʿ�
//		for (int i = 0; i<terms.size(); i++) {
//			Term t = terms.get(i);
//			result += t.calc(x);
//		}
//�̷��� �ϸ� �ð������� �ʹ� ���ؾ� �Ź� ó������ ���ƾߵȴ�. �׷��� iterator�� �ʿ���
		Iterator<Term> iter = terms.iterator();
		while(iter.hasNext()) {
			Term t = iter.next();
			result += t.calc(x);
		}
		return result;
		
	}
	
	public String toString() {
		String result = "";
		Iterator<Term> iter = terms.iterator();
		while ( iter.hasNext()) {
			Term t = iter.next();
			result += ("+"+ t.toString());
		}
		return result;
	}





}
