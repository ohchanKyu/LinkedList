package linkedList;

import java.util.*;

class MemoryNode
{
	int size;
	MemoryNode link;
	int startNum;
	
}

class FreeNode
{
	MemoryNode av = new MemoryNode();
	MemoryNode attach;
	
	public FreeNode() {
		av.size = 0;
		av.link = null;
		attach = av;
	}
	
	public void insertFreeNode(int size,int startNum) {
		
		MemoryNode newNode = new MemoryNode();
		newNode.size = size;
		newNode.startNum = startNum;
		if(av.link == null) {
			av.link = newNode;
			newNode.link = null;
			attach = newNode;
		}else {
			attach.link = newNode;
			newNode.link = null;
			attach = newNode;
		}
	}
}

public class FirstFitMemory {

	public static void allocate(FreeNode free,int size) {
		
		MemoryNode p = free.av.link;
		MemoryNode q = free.av;
		while(p!=null) {
			if(p.size >= size) {
				
				p.size = p.size - size;
				if(p.size == 0) {
					q.link = p.link;
					int beforeAllocate = p.startNum-p.size;
					int beforeSize = p.size+size+beforeAllocate;
					
					System.out.println("할당 전 자유공간 메모리크기 => "+beforeAllocate+"~"+beforeSize);
					System.out.println("할당 후 자유공간 메모리크기 => "+"모두 할당 완료");
				}else {
					p.startNum = p.startNum+p.size;	
					int beforeAllocate = p.startNum-p.size;
					int beforeSize = p.size+size+beforeAllocate;
					int afterAllocateSize = beforeSize-size;
					
					System.out.println("할당 전 자유공간 메모리크기 => "+beforeAllocate+"~"+beforeSize);
					System.out.println("할당 후 자유공간 메모리크기 => "+beforeAllocate+"~"+afterAllocateSize);
				}
				return;
			}
			q = p;
			p = p.link;
		}
	}
	
	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		FreeNode free = new FreeNode();
		
		System.out.println("자유공간 메모리 수를 입력해주세요: ");
		int freeNum = scanner.nextInt();
		for(int i=0;i<freeNum;i++) {
			System.out.println("자유공간 메모리 크기를 입력해주세요: ");
			int size = scanner.nextInt();
			System.out.println("자유공간 메모리 시작 위치를 입력해주세요: ");
			int startNum = scanner.nextInt();
			free.insertFreeNode(size, startNum);
		}
		System.out.println("할당하고 싶은 블럭의 크기를 입력해주세요: ");
		int allocateSize = scanner.nextInt();
		
		allocate(free,allocateSize);
		
	}

}
