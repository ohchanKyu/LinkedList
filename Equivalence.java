package linkedList;

import java.util.*;

class EquivalenceNode
{
	int data;
	EquivalenceNode link;
}

class SEQ
{
	int bit;
	EquivalenceNode head;
	EquivalenceNode attach;
	
	public SEQ() {
		bit = 0;
		head = new EquivalenceNode();
		head.link = null;
		attach = head;
	}
	
	public void insertData(int data) {
		
		EquivalenceNode newNode = new EquivalenceNode();
		newNode.data = data;
		
		if(head.link == null) {
			head.link = newNode;
			newNode.link = null;
			attach = newNode;
		}else {
			attach.link = newNode;
			newNode.link = null;
			attach = newNode;
		}
	}
	
	public void printClass() {
		
		EquivalenceNode tmp = head.link;
		while(tmp!=null) {
			System.out.print(tmp.data);
			tmp = tmp.link;
		}
		System.out.println();
	}
}

public class Equivalence {

	public static void print(SEQ seq[]) {
		int index = 1;
		Stack<Integer> stack = new Stack<>();
		boolean nullCheck;
		
		while(index <= seq.length-1) {
			nullCheck = true;
			if(seq[index].bit == 0) {
				seq[index].bit = 1;
				EquivalenceNode ptr = seq[index].head.link;
				if(seq[index].head.link!=null) {
					System.out.print("New Class "+"Set: [ "+index+" ");
					nullCheck = false;
					
				}
				while(true) {
					while(ptr!=null) {
						 
						int j = ptr.data;
						if(seq[j].bit == 0) {
							System.out.print(j+" ");
							seq[j].bit = 1;
							stack.push(j);
							ptr = ptr.link;
							
						}else {
							ptr = ptr.link;
						}
					}
					if(stack.isEmpty()) {
						break;
					}
					ptr = seq[stack.pop()].head.link;
				}
			}
			if(nullCheck==false) {
				System.out.println("]");
			}
			
			index++;
		}
	}
	
	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		System.out.print("동치관계의 식 수를 입력해주세요: ");
		int countNum = scanner.nextInt();
		
		int temporaryArr[][] = new int[countNum][2];
		
		int max = 1;
		
		for(int i=0;i<countNum;i++) {
			System.out.println("동치관계의 두 수를 입력해주세요(x>=1): ");
			int m = scanner.nextInt();
			int n = scanner.nextInt();
			if(n>max) {
				max = n;
			}
			temporaryArr[i][0] = m;
			temporaryArr[i][1] = n;
		}
		
		SEQ seq[] = new SEQ[max+1];
		for(int i=1;i<seq.length;i++) {
			seq[i] = new SEQ();
		}
		for(int i=0;i<countNum;i++) {
			int a = temporaryArr[i][0];
			int b = temporaryArr[i][1];
			seq[a].insertData(b);
			seq[b].insertData(a);
		}
		print(seq);
		
		
	}

}
