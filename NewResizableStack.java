import java.util.ArrayList;
public class NewResizableStack {
	
	int size;
	int index;
	int ix;
	int[] stack;
	ArrayList<int[]> stackList;
	
	public NewResizableStack(int size){
		this.size = size;
		index = 0;
		ix = 0;
		stack = new int[size];
		stackList = new ArrayList<int[]>();
		stackList.add(stack);
	}
	
	public void push(int d){
		
		if (index >= size){
			index = 0;
			int[] newStack = new int[size];			
			this.stackList.add(newStack);			
		}
		int[] s = getLastStack();
		s[index] = d;
		index++;	
		ix++;
	}
	
	public int[] getLastStack(){
		return stackList.get(stackList.size() - 1);
	}
	
	public int pop(){
		//if (!isEmpty()){
			ix--;
			int r = this.stackList.get(stackList.size() - 1)[ix % size];
			//this.stackList.get(stackList.size() - 1)[ix % size] = 0;
			
			if (ix % size == 0){
				this.stackList.remove(stackList.size() - 1);
			}
			return r;
		//} 
	}
	
	public void resize(int newSize){
		
		NewResizableStack stack = new NewResizableStack(newSize);
		while (!isEmpty()){
			stack.push(this.pop());
		}
		
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
		stack.printStack();
		
		stack.resize(10);
		
		System.out.println("Printing new Resized Stack; with size: " + stack.size);
		stack.printStack();
	}

}
