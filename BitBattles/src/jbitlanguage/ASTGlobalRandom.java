package jbitlanguage;

public class ASTGlobalRandom extends SimpleNode {
	int random;

	public ASTGlobalRandom(int i) {
		super(i);
		// TODO Auto-generated constructor stub
	}

	public ASTGlobalRandom(JBLParser p, int i) {
		super(p, i);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void interpret() {
		// TODO Auto-generated method stub
		//get the parameter of random value
		jjtGetChild(0).interpret();
		
		final double i = Math.random();
		random = (int)((i*((Integer)stack[top]).intValue()) + 1);
		//cover the old value
		stack[top] = random;
	}
	
	public String toString() {
	    return "RandomMethod: " + random;
	  }

}
