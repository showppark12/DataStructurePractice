package kwlinkedlist;

public class Term {
	//�� �׿��� ����� �ְ� ������ ��������
	public int coef;
	public int expo;
	
	public Term(int c , int e ) { // �����ڷ� ����� ���� �ʱ�ȭ
		coef = c;
		expo = e;
	}
	public int calc(int x) { // �� ���� ���� ������ִ� �޼ҵ�
		return coef * (int)Math.pow(x, expo);
	}
	public String toString() { // ���� ���
		return coef + "x" + expo;
	}
	
}
