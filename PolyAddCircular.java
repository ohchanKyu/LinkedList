package linkedList;

import java.util.*;
import linkedList.PolyAddLinkedList;

class CircularList
{
	PolyNode head = new PolyNode();
	PolyNode attachPointer;
	public CircularList() {
		head.coef = -1;
		head.exp = -1;
		head.link = null;
		attachPointer = head;
	}
	public void attach(int coef,int exp) {
		
		PolyNode newNode = new PolyNode();
		newNode.coef = coef;
		newNode.exp = exp;
		newNode.link = head;
		attachPointer.link = newNode;
		attachPointer = newNode;
	}
	public void printResult(CircularList list) {
		
		PolyNode tmp = list.head.link;
		while(tmp!=head) {
			if(tmp.link==head && tmp.exp != 0) {
				System.out.print(tmp.coef+"x^"+tmp.exp);
			}else if(tmp.link == head && tmp.exp == 0){
				System.out.print(tmp.coef);
			}else {
				System.out.print(tmp.coef+"x^"+tmp.exp+"+");
			}
			tmp = tmp.link;
		}
		System.out.println();
	}
	
}

public class PolyAddCircular {

	public static void padd(CircularList A,CircularList B,CircularList C) {
		
		PolyNode p = A.head.link;
		PolyNode q = B.head.link;
		int coef;
		while(true) {
			
			if(p.exp == q.exp) {
				if(p.exp == -1) {
					break;
				}else {
					coef = p.coef+q.coef;
					if(coef!=0) {
						C.attach(coef,p.exp);
					}
					p = p.link;
					q = q.link;
				}
			}else if(p.exp < q.exp) {
				C.attach(q.coef, q.exp);
				q = q.link;
			}else {
				C.attach(p.coef,p.exp);
				p = p.link;
			}
		}
	}
	
	public static void main(String[] args) {
		
		CircularList A = new CircularList();
		CircularList B = new CircularList();
		CircularList C = new CircularList();
		
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
