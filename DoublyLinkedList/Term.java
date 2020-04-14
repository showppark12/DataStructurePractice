package kwlinkedlist;

public class Term {
	//각 항에는 계수도 있고 지수도 있을꺼야
	public int coef;
	public int expo;
	
	public Term(int c , int e ) { // 생성자로 계수와 지수 초기화
		coef = c;
		expo = e;
	}
	public int calc(int x) { // 그 항의 값을 계산해주는 메소드
		return coef * (int)Math.pow(x, expo);
	}
	public String toString() { // 그항 출력
		return coef + "x" + expo;
	}
	
}
