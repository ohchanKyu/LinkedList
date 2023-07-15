package linkedList;

class Node
{
	char data;
	Node link;
}
class List
{
	Node head;
	public List() {
		head = null;
	}
	public void insert(char data) {
		Node newNode = new Node();
		newNode.data = data;
		if(head==null) {
			head = newNode;
			newNode.link = null;
		}else {
			Node tmp = head;
			while(tmp.link!=null) {
				tmp = tmp.link;
			}
			tmp.link = newNode;
			newNode.link = null;
		}
	}
	public void insertNextNode(char data,Node ptr) {
		
		Node newNode = new Node();
		newNode.data = data;
		newNode.link = ptr.link;
		ptr.link = newNode;
	}
	public void deleteFirstNode() {
		
		if(head==null) {
			System.out.println("Empty");
		}else {
			Node firstNode = head;
			System.out.println("Delete Data: "+firstNode.data);
			head = firstNode.link;
		}
	}
	public void deleteNextNode(char data,Node ptr) {
		
		Node deleteNode = ptr.link;
		System.out.println("Delete Data: "+deleteNode.data);
		ptr.link = deleteNode.link;
	}
	
	public void deleteDataNode(char data) {
		Node tmp = head;
		Node beforeTmp = null;
		while(tmp.data != data) {
			beforeTmp = tmp;
			tmp = tmp.link;
		}
		beforeTmp.link = tmp.link;
	}
	
	public void erase() {
		if(head==null) {
			System.out.println("Empty");
		}else {
			Node tmp = head;
			List av = new List();
			av.head = tmp;
			while(tmp.link != null) {
				tmp = tmp.link;
			}
			tmp.link = av.head;
		}
	}
	
	public void invert(List A) {
		
		Node p = A.head;
		Node q = null;
		Node r = null;
		while(p != null) {
			r = q;
			q = p;
			p = p.link;
			q.link = r;
		}
		A.head = q;
	}
	
	public List concatenate(List A,List B) {
		
		List C = new List();
		if(A==null) {
			C.head = B.head;
			return C;
		}else if(B==null) {
			C.head = A.head;
			return C;
		}
		
		Node tmp = A.head;
		while(tmp.link != null) {
			tmp = tmp.link;
		}
		tmp.link = B.head;
		C.head = A.head;
		return C;
	}
	
	public void printNode(List A) {
		
		Node tmp = A.head;
		while(tmp!=null) {
			if(tmp.link == null) {
				System.out.print(tmp.data);
			}else {
				System.out.print(tmp.data+"->");
			}
			tmp = tmp.link;
		}
		System.out.println();
	}
}

class Circular
{
	Node head;
	public Circular() {
		head = null;
	}
	
	public void insert(char data) {
		
		Node newNode = new Node();
		newNode.data = data;
		
		if(head==null) {
			head = newNode;
			newNode.link = newNode;
		}else {
			Node tmp = head;
			while(tmp.link != head) {
				tmp.link = newNode;
				newNode.link = head;
			}
		}
	}
	
	public void deleteFirstNode() {
		
		if(head == null) {
			System.out.println("Empty");
		}else {
			Node tmp = head;
			System.out.println("Delete Data: "+tmp.data);
			
			while(tmp.link != head) {
				tmp.link = head.link;
			}
			if(tmp == head) {
				head = null;
			}else {
				head = head.link;
			}
		}
	}
	
	public void erase() {
		if(head == null) {
			System.out.println("Empty");
			
		}else {
			List av = new List();
			
			Node tmp = head.link;
			av.head = head;
			av.head.link = tmp;
		}
	}
	
}
public class LinkedList {

	public static void main(String[] args) {
		List A = new List();
		A.insert('A');
		A.insert('B');
		A.insert('C');
		A.insert('D');
		A.printNode(A);
		A.invert(A);
		A.printNode(A);
		
		List B = new List();
		B.insert('E');
		B.insert('F');
		B.insert('G');
		B.insert('H');
		
		List C = A.concatenate(A, B);
		C.printNode(C);
		
	}

}
