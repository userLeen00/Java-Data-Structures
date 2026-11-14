package Data_Structure_sec_3_B;
/*
 * push
 * pop
 * peek
 * size
 * isEmpty
 * 
 * */
public class Stack_array_list {

	Array_list list = new Array_list();	
	int top;
	
	// push last
	public void push(int val) {
		
		list.insert_last(val);
		//top=val;
		
	}
	
	
	// pop last
	public int pop () {
		if (isEmpty()) {
			return -1;
		}
		int val = peek();
		
		list.remove_last();
		return val;
		
	}
	
	public void push_first(int val) {
			
			list.insert_first(val);
			//top=val;
			
		}
	
	
	public int peek() {
		
		return list.get_last();
		
	}
	
	public int peek_first() {
		
		return list.get_first();
		
	}
	
	public boolean isEmpty() {
		
		return list.isEmpty();
	}
	
	public int size () {
		
		return list.size();
	}
	
}
