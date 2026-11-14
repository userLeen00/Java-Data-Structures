package Data_Structure_sec_3_B;

public class Array_list {
	
	private int arr[];
	int end;
	
	Array_list(){
		arr = new int [3];
		end =0;
	}
	
	// insert at last 
	public void insert_last (int val) {
		
		if (end == arr.length-1) {
			//resize O(n)
			
			int arr2[]= new int [arr.length*2];
			
			//deep copy
			for (int i=0; i<arr.length; i++ ) {
				arr2[i]= arr[i];
			}
			//rename
			arr= arr2;
		}
		
		arr[end]= val;
		end++;
		
	}
	
	
	// insert first
	public void insert_first (int val) {}
	
	public void insert_at_position (int pos, int val) { // call function insert first, last
		
		if (end == arr.length-1) {
			//resize O(n)
			
			int arr2[]= new int [arr.length*2];
			
			//deep copy
			for (int i=0; i<arr.length; i++ ) {
				arr2[i]= arr[i];
			}
			//rename
			arr= arr2;
		}
		if (pos <0 || pos > arr.length-1) {
			System.out.print("out of the range \n");
			return ;
		}
		
		if (pos == end) {
			// call function insert last
			insert_last(val);
		}
		
		for (int i = end ; i> pos; i--) {
			arr[i]= arr[i-1];
		}
		arr[pos]=val;
		end++;
		
	}
	
	public void remove_last () {
		
		end--;
	}
	
	public void remove_at_possition (int pos) {
		
		
		
		for (int i = pos; i< end; i++) {
			arr[i]=arr[i+1];
		}
		end--;
		
	} // call function remove last and first
	
	public void remove_first() {}
	;
	public void print () {
		
		for (int i =0; i<end;i++) {
			System.out.print(arr[i]+"  ");
		}
		
		System.out.print("\n");
	}
	
	public int size () {
		
		return end;
	}
	
	public int get_last () {
		
		return arr[end-1];
	}
	
	public int get_first () {
		
		return arr[0];
	}
	
	public int search (int val) {
		
		for (int i=0; i<end; i++) {
			if (arr[i]== val)
				return i;
		}
		return -1;
	}
	
	public boolean isEmpty()
	{
		if (end==0) {
		
			return true;
		}
		return false;
	}
	
	

}
