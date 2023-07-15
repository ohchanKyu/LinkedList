package linkedList;

import java.util.*;
import linkedList.FirstFitMemory;

class AllocateNode
{
	int startTag = 1;
	int endTag = 1;
	int size;
	int startNum;
	AllocateNode link;
}

class FreeNode2
{
	int startTag = 0;
	int endTag = 0;
	int size;
	int startNum;
	FreeNode2 llink;
	FreeNode2 rlink;
	
}

class Allocate
{
	AllocateNode head = new AllocateNode();
	AllocateNode attach;
	
	public Allocate() {
		head.size = 0;
		head.link = null;
		attach = head;
	}
	public void insert(int size) {
		
		AllocateNode newNode = new AllocateNode();
		newNode.size = size;
		if(head.size == 0) {
			newNode.link = null;
			newNode.startNum = 1;
			head = newNode;
			attach = head;
		}else {
			attach.link = newNode;
			newNode.startNum = attach.startNum+attach.size;
			newNode.link = null;
			attach = newNode;
		}
	}
	
	public void print() {
		AllocateNode tmp = head;
		int count = 0;
		while(tmp!=null) {
			if(tmp.endTag != 0 && tmp.startTag!=0) {
				System.out.print("Memory StartNum: "+tmp.startNum+ " MemorySize: "+tmp.size);
				System.out.println();
				count++;
			}
			tmp = tmp.link;
		}
		if(count == 0) {
			System.out.println("Block이 모두 할당되었습니다.");
		}
	}
}

class Free
{
	FreeNode2 av = new FreeNode2();
	public Free() {
		av.size = 0;
		av.rlink = av;
		av.llink = av.rlink;
		av.startNum = 0;
	}
	
	public void insertA(FreeNode2 newNode) {
		
		if(av.rlink == av) {
			av.rlink = newNode;
			newNode.llink = av;
			newNode.rlink = av;
			av.llink = newNode;
		}else {
			newNode.llink = av;
			newNode.rlink = av.rlink;
			newNode.llink.rlink = newNode;
			newNode.rlink.llink = newNode;
		}
		this.print();
	}
	
	public void insertB(FreeNode2 newNode,int beforeStartNum) {
		
		FreeNode2 tmp = av.rlink;
		while(tmp!=av) {
			if(tmp.startNum == beforeStartNum) {
				break;
			}
			tmp = tmp.rlink;
		}
		tmp.size = tmp.size+newNode.size;
		this.print();
	}
	
	public void insertC(FreeNode2 newNode) {
		
		int afterNum = newNode.startNum+newNode.size;
		FreeNode2 tmp = av.rlink;
		while(tmp!=av) {
			if(tmp.startNum == afterNum) {
				break;
			}
			tmp = tmp.rlink;
		}
		tmp.startNum = newNode.startNum;
		tmp.size = tmp.size+newNode.size;
		this.print();
	}
	
	public void insertD(FreeNode2 newNode,int beforeNum,int afterNumber) {
		
		FreeNode2 tmp = av.rlink;
		FreeNode2 tmp2 = av.rlink;
		int afterNum = newNode.startNum+newNode.size;
		
		while(tmp!=av) {
			if(tmp.startNum+tmp.size >= beforeNum) {
				break;
			}
			tmp = tmp.rlink;
		}
		while(tmp2!=av) {
			if(tmp2.startNum == afterNum) {
				break;
			}
			tmp2 = tmp2.rlink;
		}
		if(tmp2.size == tmp.size && beforeNum == -1) {
			tmp.size = tmp.size+newNode.size;
			tmp.startNum = newNode.startNum;
			this.print();
		}else if(tmp2.size == tmp.size && afterNumber == -1) {
			tmp.size = tmp.size+newNode.size;
			this.print();
		}else {
			tmp.size = tmp.size+newNode.size+tmp2.size;
			tmp.rlink = tmp2.rlink;
			tmp2.rlink.llink = tmp;
			this.print();
		}
	}
	
	public void print() {
		FreeNode2 tmp = av.rlink;
		if(tmp.size != 0) {
			System.out.print("Result => ");
		}else if(tmp.size == 0){
			System.out.println("Free Memory Space Empty");
		}
		while(tmp!=av) {
			System.out.print("Free Memory StartNum: "+tmp.startNum+" Free Memory Size: "+tmp.size+" ");
			tmp = tmp.rlink;
		}
		System.out.println();
	}
	
}

public class BestFitMemory {

	public static void blockToFree(Allocate block,Free free,int startNum) {
		
		AllocateNode targetNode = block.head;
		AllocateNode beforeNode = null;
		AllocateNode afterNode = targetNode.link;
		while(targetNode!=null) {
			if(targetNode.startNum == startNum) {
				break;
			}
			beforeNode = targetNode;
			targetNode = targetNode.link;
			afterNode = targetNode.link;
		}
		
		int size = targetNode.size;
		if(beforeNode == null && afterNode.startTag == 1) {
			beforeNode = new AllocateNode();
			beforeNode.endTag = 1;
			beforeNode.startNum = -1;
		}else if(beforeNode == null && afterNode.startTag == 0) {
			beforeNode = new AllocateNode();
			beforeNode.endTag = 0;
			beforeNode.startNum = -1;
		}
		
		if(afterNode == null && beforeNode.endTag == 1) {
			afterNode = new AllocateNode();
			afterNode.startTag = 1;
			afterNode.startNum = -1;
		}else if(afterNode == null && beforeNode.endTag == 0) {
			afterNode = new AllocateNode();
			afterNode.startTag = 0;
			afterNode.startNum = -1;
		}
		
		if(beforeNode.endTag == 1 && afterNode.startTag == 1) {
			
			targetNode.endTag = 0;
			targetNode.startTag = 0;
			
			FreeNode2 freeNode = new FreeNode2();
			freeNode.size = size;
			freeNode.startNum = targetNode.startNum;
			free.insertA(freeNode);
			
			
		}else if(beforeNode.endTag == 0 && afterNode.startTag ==1) {
			
			targetNode.endTag = 0;
			targetNode.startTag = 0;
			FreeNode2 freeNode = new FreeNode2();
			freeNode.size = size;
			freeNode.startNum = startNum;
			
			int beforeSize = beforeNode.startNum;
			free.insertB(freeNode,beforeSize);
			
		}else if(beforeNode.endTag == 1 && afterNode.startTag == 0) {
			targetNode.endTag = 0;
			targetNode.startTag = 0;
			
			FreeNode2 freeNode = new FreeNode2();
			freeNode.size = size;
			freeNode.startNum = targetNode.startNum;
			free.insertC(freeNode);
			
		}else if(beforeNode.endTag == 0 && afterNode.endTag == 0) {
			targetNode.endTag = 0;
			targetNode.startTag = 0;
			
			FreeNode2 freeNode = new FreeNode2();
			freeNode.size = size;
			freeNode.startNum = targetNode.startNum;
			
			int beforeNum = beforeNode.startNum;
			int afterNum = afterNode.startNum;
			free.insertD(freeNode,beforeNum,afterNum);
		}
	}
	public static void allocate(Free free,Allocate block,int size,int shortMemory) {
		
		FreeNode2 tmp = free.av.rlink;
		while(tmp!= free.av) {
			if(tmp.size >= size) {
				int diff = tmp.size-size;
				if(diff <= shortMemory) {
					tmp.llink.rlink = tmp.rlink;
					tmp.rlink.llink = tmp.llink;
					block.insert(tmp.size);
					free.print();
					return;
				}else {
					tmp.size = diff;
					block.insert(size);
					free.print();
					return;
				}
			}
			tmp = tmp.rlink;
		}
		
	}
	
	public static void main(String[] args) {
		
		Allocate block = new Allocate();
		Allocate newBlock = new Allocate();
		Free freeBlock = new Free();
		
		block.insert(1000);
		block.insert(1500);
		block.insert(700);
		block.insert(900);
		block.insert(600);
		block.insert(300);
		block.print();
		
		int shortMemory = 100; //메모리 남은 양이 100이하면 그냥 전부 할당
		
		//blockToFree(block,freeBlock,4701);
		//blockToFree(block,freeBlock,2501);
		//blockToFree(block,freeBlock,3201);
		//blockToFree(block,freeBlock,1001);
		blockToFree(block,freeBlock,4101);
		blockToFree(block,freeBlock,1);
		
		block.print();
		
		allocate(freeBlock,newBlock,800,shortMemory);
		allocate(freeBlock,newBlock,200,shortMemory);
		allocate(freeBlock,newBlock,300,shortMemory);
		newBlock.print();		
	}

}
