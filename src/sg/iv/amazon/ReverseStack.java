package sg.iv.amazon;

import java.util.Stack;

public class ReverseStack {
	
	Stack<Integer> stk = new Stack<Integer>();
	
	public void generateStack(int size) {
		for (int i = 0; i < size; i ++) {
			stk.push(i);
		}
		revStack(stk);
	}
	
	public void push(Integer n) {
		stk.push(n);
	}
	
	public Integer pop() {
		return stk.pop();
	}
	
	public void revStack() {
		revStack(this.stk);
	}
	
	private void revStack(Stack<Integer> stk) {
		if (stk.isEmpty()) {
			return;
		}
		Integer num = stk.pop();
//		stk.push(stk.pop());
//		System.out.println("popped :" + num);
		revStack(stk);
		System.out.println("calling pushStk isempty: "+stk.isEmpty());
		pushStk(stk, num);
		
	}
	
	private void pushStk(Stack<Integer> stk, Integer num) {
		if (stk.isEmpty()) {
//			System.out.println("pushing num: "+num);
			stk.push(num);
		} else {
			Integer n = stk.pop();
			pushStk(stk, num);
//			System.out.println("pushing num: "+n);
			stk.push(n);
		}
	}
	
	public static void main(String[] args) {
		
		ReverseStack test = new ReverseStack();
		test.generateStack(5);
		System.out.println("stk: "+test.stk);
		test.revStack();
		System.out.println("reversed stk: "+test.stk);
	}
	
	
	
	
}
