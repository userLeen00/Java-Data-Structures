package Data_Structure_sec_3_B;

public class main {
	
	public static void main(String[] args) {
		
		Linked_list list = new Linked_list ();
		list.print();
		list.insertFisrt(5);
		list.print();
		
		list.insertFisrt(7);
		list.print(); // 7 5
		
		System.out.print(list.size());
		System.out.print("\n");
		list.insertLast(8);
		list.print(); // 7 5 8
		
		list.insertAtPosition(1,8);
		list.print(); // 7 8 5 8
		
		list.insertAtPosition(0,11);
		list.print(); // 11 7 8 5 8
		
		list.deleteLast();
		list.print(); // 7 5 
		
		list.deleteFirst();
		list.print(); // 5 
		list.deleteFirst(); //empty
		list.deleteFirst();
		
	}

}
