package linkedList;

import java.util.*;

class PolyNode
{
	int coef;
	int exp;
	PolyNode link;
}
class Poly
{
	PolyNode head;
	PolyNode attachPointer;
	public Poly() {
		head = null;
		attachPointer = null;
	}
	public void attach(int coef,int exp) {
		PolyNode newNode = new PolyNode();
		newNode.coef = coef;
		newNode.exp = exp;
		if(head==null) {
			head = newNode;
			head.link = null;
			attachPointer = head;
		}else {
			attachPointer.link = newNode;
			newNode.link = null;
			attachPointer = newNode;
		}
	}
	
	public void printResult(Poly C) {
		
		PolyNode tmp = C.head;
		while(tmp!=null) {
			if(tmp.link==null && tmp.exp != 0) {
				System.out.print(tmp.coef+"x^"+tmp.exp);
			}else if(tmp.link == null && tmp.exp == 0){
				System.out.print(tmp.coef);
			}else {
				System.out.print(tmp.coef+"x^"+tmp.exp+"+");
			}
			tmp = tmp.link;
		}
		System.out.println();
	}
}

public class PolyAddLinkedList {

	public static void padd(Poly A,Poly B,Poly C) {
		
		PolyNode p = A.head;
		PolyNode q = B.head;
		int coef;
		while(p!=null && q!=null) {
			if(p.exp == q.exp) {
				coef = p.coef+q.coef;
				if(coef != 0) {
					C.attach(coef,p.exp);
				}
				p = p.link;
				q = q.link;
			}else if(p.exp < q.exp) {
				coef = q.coef;
				C.attach(coef,q.exp);
				q = q.link;
			}else {
				coef = p.coef;
				C.attach(coef,p.exp);
				p = p.link;
			}
		}
		if(p==null && q!=null) {
			while(q!=null) {
				coef = q.coef;
				C.attach(coef,q.exp);
				q = q.link;
			}	
		}else if(p!=null && q==null) {
			while(p!=null) {
				coef = p.coef;
				C.attach(coef, p.exp);
				p = p.link;
			}
		}else {
			return;
		}
	}
	
	public static void main(String[] args) {
		
		Poly A = new Poly();
		Poly B = new Poly();
		Poly C = new Poly();
		
		A.attach(3,14);
		A.attach(2,10);
		A.attach(3,8);
		
		B.attach(2, 14);
		B.attach(-3, 8);
		B.attach(10, 6);
		B.attach(8,0);
		
		A.printResult(A);
		B.printResult(B);
		padd(A, B, C);
		System.out.print("Result: ");
		C.printResult(C);
		
	}

}
