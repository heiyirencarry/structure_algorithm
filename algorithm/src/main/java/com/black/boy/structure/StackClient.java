package com.black.boy.structure;

public class StackClient {
	public static <T> void main(String[] args) {
//		Stack<String> stack = new Stack<String>();
//		
//		stack.put("aa");
//		stack.put("bb");
//		stack.put("cc");
//		stack.put("dd");
//		String pop = stack.pop();
//		System.out.println(pop);
//		System.out.println(stack.size());
		String s = ")yre)erq(eqt)rqwt";
		boolean b = matchBrackets(s);
		System.out.println(b);
	}
	
	public static boolean matchBrackets(String s) {
		Stack<Character> stack = new Stack<Character>();
		for(int i=0;i<s.length();++i) {
			char c = s.charAt(i);
			if(c == '(') {
				stack.put(c);
			} else if (c == ')') {
				Character pop = stack.pop();
				if(pop == null)
					return false;
			}
		}
		return stack.isEmpty();
	}
}
