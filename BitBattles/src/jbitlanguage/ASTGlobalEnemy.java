package jbitlanguage;

public class ASTGlobalEnemy extends SimpleNode {
	int index;
    int value;
    
	public ASTGlobalEnemy(int i) {
		super(i);
		// TODO Auto-generated constructor stub
	}

	public ASTGlobalEnemy(JBLParser p, int i) {
		super(p, i);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void interpret() {
		// TODO Auto-generated method stub
		//get the index of array
		jjtGetChild(0).interpret();
		
		//System.out.println(" ID: " + getID());
		index = ((Integer)stack[top]).intValue();
		stack[top] = GlobalVariables.getValue(getID(),index-1);
		value = ((Integer)stack[top]).intValue();
	}

	public String toString() {
	    return "GlobalEnemy: " + value;
	}
}
