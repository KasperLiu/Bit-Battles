package jbitlanguage;

import java.io.IOException;

public class ASTReturnStatement extends SimpleNode {
  String name;

  public ASTReturnStatement(int id) {
    super(id);
  }

  public ASTReturnStatement(JBLParser p, int id) {
    super(p, id);
  }

  public void interpret()
  {
	  jjtGetChild(0).interpret();
	  
	  returnValue = ((Integer)stack[top--]).intValue();
	  isReturn = true;
  }

}
