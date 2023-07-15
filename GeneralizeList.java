package linkedList;

import java.util.*;

class ListNode
{
	int tag;
	char data;
	ListNode link;
	ListNode dlink;
}

class GeneralizedList
{
	ListNode head = new ListNode();
	public GeneralizedList() {
		head = null;
	}
	
	public void insertNode(ListNode newNode) {
		
		if(newNode.tag == 0) {
			newNode.link = null;
			newNode.dlink = null;
			if(head == null) {
				head = newNode;
			}else {
				ListNode tmp = head;
				while(tmp.link != null) {
					tmp = tmp.link;
				}
				tmp.link = newNode;
			}
		}else if(newNode.tag==1) {
			return;
		}
	}
	public void insertList(GeneralizedList list) {
		
		ListNode newNode = new ListNode();
		newNode.tag = 1;
		newNode.data = 0;
		newNode.dlink = list.head;
		newNode.link = null;
	
		ListNode tmp = head;
		if(head == null) {
			head = newNode;
		}else {
			while(tmp.link != null) {
				tmp = tmp.link;
			}
			tmp.link = newNode;
		}
	}
	
}
public class GeneralizeList {

	public static void printList(ListNode head) {

			ListNode tmp = head;
			while(tmp!=null) {
				if(tmp.tag == 0) {
					System.out.println("Data : "+tmp.data);
				}else if(tmp.tag == 1){
					printList(tmp.dlink);
				}
				tmp = tmp.link;
			}
	}
	
	public static int depth(ListNode node) {
		int max = 0;
		if(node == null) {
			return max;
		}
		int ans = 0;
		ListNode ptr = node;
		while(ptr != null) {
			if(ptr.tag == 0) {
				ans = 0;
			}else {
				ans = depth(ptr.dlink);
			}
			if(ans > max) {
				max = ans;
			}
			ptr = ptr.link;
		}
		return (max+1);
	}
	
	public static boolean equal(ListNode fisrtNode,ListNode secondNode) {
		boolean answer = false;
		if(fisrtNode == null && secondNode == null) {
			answer = true;
		}else if(fisrtNode!=null && secondNode!=null) {
			if(fisrtNode.tag == secondNode.tag) {
				
				if(fisrtNode.tag == 0) {
					answer = fisrtNode.data == secondNode.data;
				}else {
					answer = equal(fisrtNode.dlink,secondNode.dlink);
				}
			}
			if(answer) {
				answer = equal(fisrtNode.link,secondNode.link);
			}
		}
		return answer;
	}
	
	public static void main(String[] args) {
		
		GeneralizedList A = new GeneralizedList();
		GeneralizedList B = new GeneralizedList();
		GeneralizedList C = new GeneralizedList();
		ListNode a = new ListNode();
		ListNode b = new ListNode();
		ListNode c = new ListNode();
		ListNode d = new ListNode();
		ListNode e = new ListNode();
		ListNode f = new ListNode();
		ListNode g = new ListNode();
		
		b.data = 'B';
		b.tag = 0;
		c.data = 'C';
		c.tag = 0;
		a.data ='A';
		a.tag = 0;
		
		d.data = 'D';
		d.tag = 0;
		e.data = 'E';
		e.tag = 0;
		f.data = 'F';
		f.tag = 0;
		g.data = 'G';
		g.tag = 0;
		
		A.insertNode(a);
		C.insertNode(d);
		C.insertNode(e);
		C.insertNode(f);
		C.insertNode(g);
		
		B.insertList(C);
		B.insertNode(b);
		B.insertNode(c);
		
		A.insertList(B);
		
		printList(A.head);
		int k = depth(A.head);
		System.out.println(k);
		B = C;
		System.out.println(equal(C.head,B.head));
	}

}
