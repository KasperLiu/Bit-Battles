package jbitlanguage;

public class ASTGlobalCur extends SimpleNode {
	int cur;
	public ASTGlobalCur(int id) {
	    super(id);
	  }

	  public ASTGlobalCur(JBLParser p, int id) {
	    super(p, id);
	  }

	@Override
	public void interpret() {
		// TODO Auto-generated method stub
		cur = GlobalVariables.getCUR();
		stack[++top] = cur;
	}

	public String toString() {
	    return "CUR: " + cur;
	}
}
