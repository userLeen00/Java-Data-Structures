package Data_Structure_sec_3_B;

// when I use tail does not mean DLLL, or i will not use head
// delete one node (head + tail) in the same node
// dublly

class Node{
	int data;
	Node next;
	Node prev;
	
	Node (int value){
		data= value;
		next=null;
		prev=null;
	}
}
public class Linked_list {
	
	Node head;
	Node tail;
	//Node tail;
	int size;
	
	Linked_list(){
		head= null;
		tail=null;
		size =0;
	}
	
	/*public void insertAtPosition(int pos, int val) {
		
		Node new_node = new Node (val);
		Node add= head;
		//if pos ==0 
			//	insert first;
		
		for (int i=0 ; i<pos-1; i++) {
			
			add= add.next;
		}
		
		new_node.next=add.next;
		add.next= new_node;
		
	}*/
	
	public void insertAtPosition(int pos, int val) {
			
			Node new_node = new Node (val);
			Node add= head;
			if (pos==size)
			{
				insertLast(val);
			}
			else {
			for (int i=0 ; i<pos-1; i++) {
				
				add= add.next;
			}
			
			new_node.next=add.next;
			add.next= new_node;
			size++;
			}
			
		}
	
	/*public void insertLast(int val) {
		
		Node new_node = new Node (val);
		
		Node current =head;
		
		if (isEmpty ()) { // true
			head= new_node;
			
			
		}
		else {
			while (current.next != null) {
				current = current.next;
			}
			current.next = new_node ;
		}
		size++;
	}*/
	
	public void insertLast(int val) {
			
			Node new_node = new Node (val);
			
			//Node current =head;
			
			if (isEmpty ()) { // true
				head= new_node;
				tail= new_node;
				
			}
			else {
				tail.next=new_node;
				new_node.prev=tail;
				tail= tail.next;
			
			}
			size++;
		}
	
	/*public void insertFisrt (int val) {
		
		Node new_node = new Node (val);
		if (isEmpty()) {
			head = new_node;
			
		}
		else {
			
			new_node.next= head;
			head= new_node;
		}
		
		size++;
		
	}*/
	
	public void insertFisrt (int val) {
			
			Node new_node = new Node (val);
			if (isEmpty()) {
				head = new_node;
				tail = new_node;
				
			}
			else {
				
				new_node.next= head;
				head.prev=new_node;
				head= new_node;
				
			}
			
			size++;
			
		}
	
	public void deleteAtPosition (int pos) {
		
		
		
	}
	
	public void deleteFirst() {
		if (isEmpty ()) {
			System.out.print("Empty!\n");
			return;
		}
		
		if (head.next ==null) {// one node
			head=tail=null;
		}
		else {
		
			Node temp= head;
			head = head.next;
			temp.next= null;
		}
		size--;
	}
	
	public void deleteLast() {
		
		Node current=head;
		Node prev= head;
		
		if (isEmpty ()) {
			System.out.print("Empty!\n");
			return;
		}
		
		while (prev.next != tail) {
			
			prev = prev.next;
		}
		
		prev.next= null;
		tail=prev;
		size--;
	}
	
	public void print() {
		
		if (isEmpty()) {
			System.out.print("is Empty \n");
			return;
		}
		
		Node current =head;
		while (current!=null) {
			
			System.out.print(current.data+ " -> ");
			current=current.next;
			
		}
		System.out.print("\n");
		
	}
	
	public boolean isEmpty() {
		
		if (head == null) {
			return true;
		}
		return false ;
	}
	
	public int size () {
		return size;
	}
	
	//public int search (int val) {}
	
	
}
