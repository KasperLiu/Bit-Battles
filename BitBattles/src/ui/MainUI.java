package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;

import jbitlanguage.GlobalVariables;
import jbitlanguage.JBLParser;
import jbitlanguage.MyNode;
import jbitlanguage.ParseException;
import jbitlanguage.SimpleNode;

import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class MainUI {

	private JFrame frmJbitLanguage;
	private JScrollPane editAreaScrollPane;
	private JScrollPane resultAreaScrollPane;
	private JScrollPane syntaxAreaScrollPane;
	
	private JTextArea editArea, resultArea, syntaxTreeArea, logArea;
	private JButton Btn_Edit_Open, Btn_Edit_Save, Btn_Battle_Add, Btn_Battle_Start, Btn_Tree_Open, Btn_Tree_Save;
	
	private String openfilename,savefilename;
	
	private boolean compilationFlag,runFlag;
	private ArrayList<String> filename;
	private Hashtable recorder;
	/* to get the OS line separator */
	String lineSeparator = System.getProperty("line.separator");
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainUI window = new MainUI();
					window.frmJbitLanguage.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		compilationFlag = false;
		runFlag = false;
		filename = new ArrayList<String>();
		recorder = new Hashtable();
		
		frmJbitLanguage = new JFrame();
		frmJbitLanguage.setResizable(false);
		frmJbitLanguage.getContentPane().setBackground(UIManager.getColor("List.selectionBackground"));
		frmJbitLanguage.setBackground(UIManager.getColor("List.selectionBackground"));
		frmJbitLanguage.setTitle("JBit Language");
		frmJbitLanguage.setBounds(100, 100, 945, 600);
		frmJbitLanguage.setLocation(0, 0);
		frmJbitLanguage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmJbitLanguage.getContentPane().setLayout(null);
		
		editArea = new JTextArea();
		editArea.setBounds(10, 50, 300, 310);
		
		editAreaScrollPane = new JScrollPane(editArea);
		editAreaScrollPane.setBounds(10, 50, 300, 310);
		frmJbitLanguage.getContentPane().add(editAreaScrollPane);
		
		resultArea = new JTextArea();
		resultArea.setEditable(false);
		resultArea.setBounds(320, 50, 300, 310);
		
		resultAreaScrollPane = new JScrollPane(resultArea);
		resultAreaScrollPane.setBounds(320, 50, 300, 310);
		frmJbitLanguage.getContentPane().add(resultAreaScrollPane);
		
		syntaxTreeArea = new JTextArea();
		syntaxTreeArea.setEditable(false);
		syntaxTreeArea.setBounds(630, 50, 284, 310);
		
		syntaxAreaScrollPane = new JScrollPane(syntaxTreeArea);
		syntaxAreaScrollPane.setBounds(630, 50, 300, 310);
		frmJbitLanguage.getContentPane().add(syntaxAreaScrollPane);
		
		logArea = new JTextArea();
		logArea.setEditable(false);
		logArea.setBounds(10, 390, 900, 160);
		
		JScrollPane logAreaScrollPane = new JScrollPane(logArea);
		logAreaScrollPane.setBounds(10, 390, 920, 175);
		frmJbitLanguage.getContentPane().add(logAreaScrollPane);
		
		JLabel lblNewLabel = new JLabel("LogInfo :");
		lblNewLabel.setFont(new Font("SimSun", Font.PLAIN, 12));
		lblNewLabel.setBounds(15, 365, 54, 20);
		frmJbitLanguage.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Edit Strategy :");
		lblNewLabel_1.setFont(new Font("ËÎÌו", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(15, 18, 115, 20);
		frmJbitLanguage.getContentPane().add(lblNewLabel_1);
		
		JLabel lblBattleResult = new JLabel("Battle Result :");
		lblBattleResult.setFont(new Font("ËÎÌו", Font.PLAIN, 12));
		lblBattleResult.setBounds(325, 18, 90, 20);
		frmJbitLanguage.getContentPane().add(lblBattleResult);
		
		JLabel lblSyntaxTree = new JLabel("Syntax Tree :");
		lblSyntaxTree.setFont(new Font("ËÎÌו", Font.PLAIN, 12));
		lblSyntaxTree.setBounds(635, 18, 80, 20);
		frmJbitLanguage.getContentPane().add(lblSyntaxTree);
		
		Btn_Edit_Open = new JButton("open");
		Btn_Edit_Open.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openfilename = null;
				EditOpen();
			}
		});
		Btn_Edit_Open.setBounds(160, 20, 65, 23);
		frmJbitLanguage.getContentPane().add(Btn_Edit_Open);
		
		Btn_Edit_Save = new JButton("save");
		Btn_Edit_Save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				savefilename = null;
				EditSave();
			}
		});
		Btn_Edit_Save.setBounds(230, 20, 65, 23);
		frmJbitLanguage.getContentPane().add(Btn_Edit_Save);
		
		Btn_Battle_Add = new JButton("Add Strategy");
		Btn_Battle_Add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openfilename = null;
				AddStrategy();
			}
		});
		Btn_Battle_Add.setBounds(426, 20, 115, 23);
		frmJbitLanguage.getContentPane().add(Btn_Battle_Add);
		
		Btn_Battle_Start = new JButton("Start");
		Btn_Battle_Start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Start();
			}
		});
		Btn_Battle_Start.setBounds(545, 20, 65, 23);
		frmJbitLanguage.getContentPane().add(Btn_Battle_Start);
		
		Btn_Tree_Open = new JButton("open");
		Btn_Tree_Open.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openfilename = null;
				SyntaxTree();
			}
		});
		Btn_Tree_Open.setBounds(775, 20, 65, 23);
		frmJbitLanguage.getContentPane().add(Btn_Tree_Open);
		
		Btn_Tree_Save = new JButton("save");
		Btn_Tree_Save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				savefilename = null;
				SyntaxTreeSaveFile();
			}
		});
		Btn_Tree_Save.setBounds(845, 20, 65, 23);
		frmJbitLanguage.getContentPane().add(Btn_Tree_Save);
	}
	
	/* open file for EditArea */
	public void EditOpen(){
		/* get the filename to open */
		OpenFile();
		if(!openfilename.isEmpty()){
			BufferedReader bre = null;
			try {
				String str;
				bre = new BufferedReader(new FileReader(openfilename));
				while ((str = bre.readLine())!= null){
					editArea.append(str + lineSeparator);
				}
			} catch(java.io.FileNotFoundException e1){
				PrintInfo("JBit Language Interpreter Version 0.1:  File : " + openfilename + " not found.");
			} catch(java.io.IOException io){
	            PrintInfo(io.toString());
			} finally{
				try {  
					bre.close();  
                } catch (IOException e1) {  
    	            PrintInfo(e1.toString());
                }
			}
		}
	}
	
	/* to save the edited file from EditArea */
	public void EditSave(){
		SaveFile();
		if(!savefilename.isEmpty()){
			FileWriter fw = null;  
            BufferedWriter bw = null;             
            try {
                fw = new FileWriter(savefilename);  
                bw = new BufferedWriter(fw);
                /* add the line separator */
                for(String str : editArea.getText().split(lineSeparator)){
                	bw.write(str);
                	bw.newLine();
                }
                PrintInfo(" file : " + savefilename + " saved successfully !");
            } catch (Exception e1) {
                PrintInfo(e1.toString());
            } finally {  
                try {  
                    bw.close();  
                    fw.close();  
                } catch (IOException e1) {  
                    PrintInfo(e1.toString());
                }  
            }  
		}
	}
	
	/* to get filename to open */
	public void OpenFile(){
		JFileChooser chooser = new JFileChooser();
		chooser.setDialogTitle("Open JBL file");
	    FileNameExtensionFilter filter = new FileNameExtensionFilter(
	        "JBit file", "jbl");
	    chooser.setFileFilter(filter);
	    int returnVal = chooser.showOpenDialog(frmJbitLanguage);
	    if(returnVal == JFileChooser.APPROVE_OPTION) {
	       openfilename = chooser.getSelectedFile().getAbsolutePath();
	    }
	}
	
	/* to get the path for saving file */
	public void SaveFile(){
		JFileChooser chooser = new JFileChooser();
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		chooser.setDialogTitle("Save as");
		int returnVal = chooser.showOpenDialog(frmJbitLanguage);
	    if(returnVal == JFileChooser.APPROVE_OPTION) {
	       savefilename = chooser.getSelectedFile().getAbsolutePath();
	    }
	}
	
	/* add strategies to fight */
	public void AddStrategy(){
		OpenFile();
		JBLParser parser = null;
		if(!openfilename.isEmpty()){
			try {
				parser = new JBLParser(new java.io.FileReader(openfilename));
			} catch (FileNotFoundException e) {
				PrintInfo("JBit Language Interpreter Version 0.1:  File : " + openfilename + " not found.");
			}
			try {
				/* compile the file */
				parser.CompilationUnit();
			} catch (ParseException e) {
				// set the flag 
				compilationFlag = true;
				PrintInfo("JBit Language Interpreter Version 0.1: file : "+ openfilename + " Encountered errors during parse");
                PrintInfo(e.toString());
			} finally{
				if(!compilationFlag){
					/* add the strategy */
					filename.add(openfilename);
					PrintInfo("Strategy "+ openfilename + " added successfully !");
					PrintInfo("The number of the strategies is " + filename.size() + " now !");
				} else{
					compilationFlag = false;
					// show error dialog and clear the filename
					filename.clear();
					PrintInfo("Remove all the strategies !");
					PrintInfo("The number of the strategies is " + filename.size() + " now !");
					String str = openfilename + "\n" +"has some errors, please check it !";
					JOptionPane.showMessageDialog(frmJbitLanguage,
						    str,
						    "Parse error",
						    JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}
	
	/* Start the fight */
	@SuppressWarnings("unused")
	public void Start(){
		/* reinitialize */
		recorder.clear();
		GlobalVariables.Reinitialize();
		
		// the number of strategies aren't enough
		if( filename.size() < 2){
			String str = "The number of the strategies must be greater than 2 ! ";
			JOptionPane.showMessageDialog(frmJbitLanguage,
				    str,
				    "Execute error",
				    JOptionPane.ERROR_MESSAGE);
		} else {
			String inputValue = JOptionPane.showInputDialog("Please input a value");
			if(Integer.valueOf(inputValue) > 0){
				// clear
				resultArea.setText("");
				InitRecorder();
				GlobalVariables.setGameValue(Integer.valueOf(inputValue));
				
				outer:{
				for(int i = 0; i < filename.size(); i++){
					for(int k = i+1; k < filename.size(); k++){
						Run(i,k);
						/* if some errors occur during the fight */
						if(runFlag){
							runFlag = false;
							String str = "something wrong occurs while fighting";
							JOptionPane.showMessageDialog(frmJbitLanguage,
								    str,
								    "Execute error",
								    JOptionPane.ERROR_MESSAGE);
							break outer;
						}
					}
				}
				// sort by hashtable.value
				List<Integer> v = new ArrayList<Integer>(recorder.keySet()); 
			    Collections.sort(v,new Comparator<Object>(){ 
			        public int compare(Object arg0,Object arg1) 
			        {  
			            return ((Integer)recorder.get(arg1)).intValue() - ((Integer)recorder.get(arg0)).intValue(); 
			        } 
			    } 
			    );
			    resultArea.append(" the order of strategies is : " + lineSeparator);
			    for (int str : v) { 
			      resultArea.append(" " + filename.get(str) + " ( "+ recorder.get(str) +" )" + lineSeparator);
			    }
				} // end outer
				// battles end 
				filename.clear();
				PrintInfo("Battles end !");
				PrintInfo("Remove all the strategies !");
				PrintInfo("The number of the strategies is " + filename.size() + " now !");
				
			} else {
				String str = "The number of the games must be greater than 0 ! ";
				JOptionPane.showMessageDialog(frmJbitLanguage,
					    str,
					    "Execute error",
					    JOptionPane.ERROR_MESSAGE);
			}
			
		}
	}
	
	/* initialize the Hashtable.recorder( key = the position in the filename, val = score ) */
	public void InitRecorder(){
		for(int i = 0; i < filename.size(); i++){
			recorder.put(i, new Integer(0));
		}
	}
	
	/* run to calculate the score , once a pair */
	public void Run(int val,int val2){
		try{
			for(int i = 1; i <= GlobalVariables.getGameValue(); i++){
    	    GlobalVariables.setCUR(i);
    	  
    	    JBLParser parser = new JBLParser(new java.io.FileReader(filename.get(val)));
    	    parser.CompilationUnit();
    	    MyNode.setID(1);
            parser.jjtree.rootNode().interpret();
            GlobalVariables.setFirstValue(MyNode.getReturnValue());
            MyNode.ReInit();
          
            JBLParser parser1 = new JBLParser(new java.io.FileReader(filename.get(val2)));
            parser1.CompilationUnit();
            MyNode.setID(2);
            parser1.jjtree.rootNode().interpret();
            GlobalVariables.setSecondValue(MyNode.getReturnValue());
            MyNode.ReInit();
	      }
	      GlobalVariables.Calculate();
	      
	      int temp = ((Integer)recorder.get(val)).intValue();
	      recorder.put(val, GlobalVariables.getFirstResult() + temp);
	      temp = ((Integer)recorder.get(val2)).intValue();
	      recorder.put(val2, GlobalVariables.getSecondResult() + temp);
	      
	      PrintBattleResult(val,val2);
	      
		} catch(ParseException e){
			runFlag = true;
			PrintInfo("JBit Language Interpreter Version 0.1:  Encountered errors during parse.");
            PrintInfo(e.toString());
		} catch (Exception e1) {
			runFlag = true;
			PrintInfo("JBit Language Interpreter Version 0.1:  Encountered errors during interpretation/tree building.");
            PrintInfo(e1.toString());
		} finally {
			MyNode.ReInit();
			int temp = GlobalVariables.getGameValue();
			GlobalVariables.Reinitialize();
			GlobalVariables.setGameValue(temp);
		}
		
	}
	
	/* to build a syntax tree */
	public void SyntaxTree(){
		OpenFile();
		JBLParser parser = null;
		if(!openfilename.isEmpty()){
			try {
				parser = new JBLParser(new java.io.FileReader(openfilename));
			} catch (FileNotFoundException e) {
				PrintInfo("JBit Language Interpreter Version 0.1:  File : " + openfilename + " not found.");
			}
			try {
				parser.CompilationUnit();
				SimpleNode root = (SimpleNode)parser.jjtree.rootNode();
			    root.dump("");
			    syntaxTreeArea.setText(GlobalVariables.getDump());
			    PrintInfo("file: " + openfilename + " building syntax tree successfully !");
			} catch (ParseException e) {
                PrintInfo("JBit Language Interpreter Version 0.1:  Encountered errors during building syntax tree.");
                PrintInfo(e.toString());
			}
		}
	}
	
	/* save the syntax tree */
	public void SyntaxTreeSaveFile(){
		SaveFile();
		if(!savefilename.isEmpty()){
			FileWriter fw = null;  
            BufferedWriter bw = null;             
            try {  
                fw = new FileWriter(savefilename);  
                bw = new BufferedWriter(fw); 
                for(String str : syntaxTreeArea.getText().split(lineSeparator)){
                	bw.write(str);
                	bw.newLine();
                }
                PrintInfo(" file : " + savefilename + " saved successfully !");
            } catch (Exception e) {
                PrintInfo(e.toString());
            } finally {  
                try {  
                    bw.close();  
                    fw.close();  
                } catch (IOException e1) {  
                    PrintInfo(e1.toString());
                }  
            }  
		}
	}
	
	public void PrintBattleResult(int val, int val2){
		resultArea.append(" in the whole "+ GlobalVariables.getGameValue() + " battles :"+ lineSeparator);
		resultArea.append(" " + filename.get(val) + " VS " + filename.get(val2) + " = " + GlobalVariables.getFirstResult()
						+ " : " + GlobalVariables.getSecondResult() + "  "+ lineSeparator);
	}
	
	public void PrintInfo(String Info){
		logArea.append(" " + Info + lineSeparator);
	}
}
