package Data_Structure_sec_3_B;
import java.util.Stack;
 

class Nodee {
	
	int data ;
	Nodee left, right ;
	
	Nodee (int val){
		data= val;
	}
}

public class BST implements BST_Interface {
	
	private Nodee root ;
	
	public void insert (int val) {
		
		Nodee new_node = new Nodee (val);
		if (root == null) {
			root = new_node;
			return ;
		}
		
		Nodee current =root ;
		while (true) {
			if (current.data > val) {
				if (current.left == null) {
					current.left=new_node;
					break;
				}
			    current = current.left;			
			}
			else {
				if (current.right == null) {
					current.right=new_node;
					break;
				}
			    current = current.right;	
			}
		
		}
	}
	
	public boolean search (int val) {
		
		if (root == null) {
			System.out.print("Empty \n");
			return false ;
		}
		
		
		
		Nodee current =root ;
		while (current != null) {
			if (current.data== val) {
				return true;
			}
			if (current.data > val) {
				
			    current = current.left;			
			}
			else {
				
			    current = current.right;	
			}
		
		}
		return false ;
		
	}
	
	public void InOrderTraversal () {
		
		Stack<Nodee> stack = new Stack<>();
		
		Nodee current =root ;
		while (current != null || !stack.isEmpty()) {
			
			while (current !=null) {
				stack.push (current);
			    current = current.left;			
			}
			
				current = stack.pop();
				System.out.print(current.data+" ");
			    current = current.right;	
			
		
		}
		
		
	}
	
	

}
