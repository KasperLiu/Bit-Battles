package jbitlanguage;

/* Copyright (c) 2006, Sun Microsystems, Inc.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *     * Redistributions of source code must retain the above copyright notice,
 *       this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
 *     * Neither the name of the Sun Microsystems, Inc. nor the names of its
 *       contributors may be used to endorse or promote products derived from
 *       this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF
 * THE POSSIBILITY OF SUCH DAMAGE.
 */

/**
 * Stupid Programming Language parser.
 */
public class JBL {

  /** Main entry point. */
  public static void main(String args[]) {
    JBLParser parser,parser1;
    String filename;
    String directory = "D:\\WorksSpace\\JAVAWorkspace\\JavaTest\\T1.jbl";
    String directory1 = "D:\\WorksSpace\\JAVAWorkspace\\JavaTest\\T5.jbl";
    filename = "test.spl";
    if (true) {
      System.out.println("Stupid Programming Language Interpreter Version 0.1:  Reading from file " + filename + " . . .");
      try {
        parser = new JBLParser(new java.io.FileReader(directory));
        parser1 = new JBLParser(new java.io.FileReader(directory1));
      } catch (java.io.FileNotFoundException e) {
        System.out.println("Stupid Programming Language Interpreter Version 0.1:  File " + filename + " not found.");
        return;
      } 
    } else {
      System.out.println("Stupid Programming Language Interpreter Version 0.1:  Usage :");
      System.out.println("         java SPL inputfile");
      return;
    }
    try {
    	parser.CompilationUnit();
    	
    	/*
      GlobalVariables.setCUR(1);
      parser.CompilationUnit();
     ((MyNode)parser.jjtree.rootNode()).setID(1);
      parser.jjtree.rootNode().interpret();
      System.out.println("");
      System.out.println("");
      SimpleNode root = (SimpleNode)parser.jjtree.rootNode();
      root.dump("");
      MyNode.ReInit();
      System.out.println("");
      System.out.println("");
      JBLParser parser1 = new JBLParser(new java.io.FileReader(directory1));
      parser1.CompilationUnit();
      ((MyNode)parser1.jjtree.rootNode()).setID(2);
      parser1.jjtree.rootNode().interpret();
       */
    	/*
      GlobalVariables.setGameValue(5);
      for(int i = 1; i <= GlobalVariables.getGameValue(); i++){
    	  GlobalVariables.setCUR(i);
    	  
    	  parser = new JBLParser(new java.io.FileReader(directory));
    	  parser.CompilationUnit();
    	  MyNode.setID(1);
          parser.jjtree.rootNode().interpret();
          GlobalVariables.setFirstValue(MyNode.getReturnValue());
          MyNode.ReInit();
          
          parser1 = new JBLParser(new java.io.FileReader(directory1));
          parser1.CompilationUnit();
          MyNode.setID(2);
          parser1.jjtree.rootNode().interpret();
          GlobalVariables.setSecondValue(MyNode.getReturnValue());
//          System.out.println("");
//          SimpleNode root = (SimpleNode)parser1.jjtree.rootNode();
//          root.dump("");
          MyNode.ReInit();
      }
      GlobalVariables.Calculate();
      System.out.println("the First score is : " + GlobalVariables.getFirstResult());
      System.out.println("the Second score is : " + GlobalVariables.getSecondResult());
      GlobalVariables.Reinitialize();
      */
      
    } catch (ParseException e) {
      System.out.println("Stupid Programming Language Interpreter Version 0.1:  Encountered errors during parse.");
      e.printStackTrace();
    } catch (Exception e1) {
      System.out.println("Stupid Programming Language Interpreter Version 0.1:  Encountered errors during interpretation/tree building.");
      e1.printStackTrace();
    }
  }
}
