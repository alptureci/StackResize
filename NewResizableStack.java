import java.util.ArrayList;
public class NewResizableStack {
	
	int size; // stack size
	int index; // local index
	int ix; // General index to point how many items added in total.
	int[] stack; //first stack of array
	ArrayList<int[]> stackList;
	
	public NewResizableStack(int size){
		// initialize all
		this.size = size;
		index = 0;
		ix = 0;
		stack = new int[size];
		stackList = new ArrayList<int[]>();
		stackList.add(stack);
	}
	
	public void push(int d){
		
		// if index >= size start a new stack of Array and add it to list of Stacks.
		if (index >= size){
			index = 0;
			int[] newStack = new int[size];			
			this.stackList.add(newStack);			
		}
		// Get the last stack from the list. 
		// Add the item to the stack of Array
		int[] s = getLastStack();
		s[index] = d;
		index++;	
		ix++;
	}
	
	// Getting the last Stack form the list of stack of Arrays
	public int[] getLastStack(){
		return stackList.get(stackList.size() - 1);
	}
	
	//popping the last element
	public int pop(){
		// Decrease General index
		ix--;
		// Get the last item from the index
		int r = this.stackList.get(stackList.size() - 1)[ix % size];
		
		// if the last stack of array is unnecessary remove it from the list of stacks
		if (ix % size == 0){
			this.stackList.remove(stackList.size() - 1);
		}
		return r;
	}
	
	// Resize the stack
	public void resize(int newSize){
		
		NewResizableStack tempstack = new NewResizableStack(newSize);
		NewResizableStack stack = new NewResizableStack(newSize);
		
		// Keep popping from the old stack and pushing to the new stack until old stack is empty
		// I did it twice to keep the order
		while (!isEmpty()){
			tempstack.push(this.pop());
		}
		
		while (!tempstack.isEmpty()){
			stack.push(tempstack.pop());
		}
		
		// Set all to the last 
		this.size = newSize;
		this.stackList = stack.stackList;
		this.index = stack.index;
		this.ix = stack.ix;
		this.stack = stack.stack;
	}
	
	
	public boolean isEmpty(){
		if (this.ix <= 0){
			return true;
		} else {
			return false;
		}
	}
	
	public void printStack(){
		int count = ix;
		for (int i = 0; i < stackList.size(); i++){
			for (int j = 0; j < size; j++){
				if (count > 0)
				System.out.print(stackList.get(i)[j] + "-");
				count--;
			}
			System.out.println("");
			System.out.println("");
		}
	}
	
	public static void main(String[] args){
		int initialSize = 5;
		NewResizableStack stack = new NewResizableStack(initialSize);
		
		for (int i = 1; i < 14; i++){
			stack.push(i);
		}
		System.out.println("Printing Initial Stack; with size: " + stack.size);
		stack.printStack();
		
		stack.resize(10);		
		System.out.println("Printing the first Resized Stack; with size: " + stack.size);
		stack.printStack();
		
		stack.resize(3);
		System.out.println("Printing the second Resized Stack; with size: " + stack.size);
		stack.printStack();
	}

}
