package io.readerwriter;

import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ReaderWriterTest
{
	public static void main( String args[])
	{
		UIForm3 ui = new UIForm3();
		ui.addLayout();
		ui.eventProc();	
	}	
}

//========================================
//	화면을 관리하는 클래스 
//----------------------------------------
class UIForm3 extends JFrame
{
	JTextArea	ta;
	JButton 		bSave, bLoad, bClear;
	
	UIForm3()
	{
		ta		= new JTextArea();		
		bSave 	= new JButton("파일저장");
		bLoad	= new JButton("파일읽기");
		bClear	= new JButton("화면지우기");
	}
	
	void addLayout()
	{
		JPanel pCenter 	= new JPanel();
		pCenter.setLayout( new BorderLayout() );
		pCenter.add(new JScrollPane(ta) ,  BorderLayout.CENTER);
	
		JPanel pSouth	= new JPanel();
		pSouth.add( bSave );
		pSouth.add( bLoad );
		pSouth.add( bClear );
		
		add(pCenter, BorderLayout.CENTER );
		add(pSouth, BorderLayout.SOUTH );
		
		setSize( 400, 350 );
		setVisible( true );
		
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
	}
	
	void eventProc()
	{
		// "저장하기" 버튼이 눌렸을 
		bSave.addActionListener(new ActionListener() { 
			public void actionPerformed( ActionEvent ev ){
				saveData();
			}
		});
		
		// "읽어오기" 버튼이 눌렸을 때
		bLoad.addActionListener(new ActionListener() { 
			public void actionPerformed( ActionEvent ev ){
				readData();
			}
		});	
		
	}
	
	void saveData() {
		JFileChooser fd = new JFileChooser();
		int returnValue = fd.showSaveDialog( null );
		if( returnValue == JFileChooser.APPROVE_OPTION )
		{
			File f = fd.getSelectedFile();
			try{
				/** 
				 * @@@@@@@@@@@@@@@@@@@@@@@@@
				 * */
				
				FileWriter fw = new FileWriter(f);
				fw.write(ta.getText());
				fw.close();
				
			}catch(Exception ex){
				System.out.println("저장 실패");
			}	
		}
	}
	
	void readData() {
		
		JFileChooser fd = new JFileChooser();
		int returnValue = fd.showOpenDialog(null);
		if( returnValue == JFileChooser.APPROVE_OPTION )
		{
			File f = fd.getSelectedFile();
			// 파일 용량을 계산해서 char[] 설정 필요
			f.length();
			try{
				/** 
				 * @@@@@@@@@@@@@@@@@@@@@@@@@
				 * */
				
				FileReader fw = new FileReader(f);
				char[] data = new char[1024];
				fw.read(data);
				ta.setText(String.valueOf(data));
				fw.close();
				
			}catch(Exception ex){
				System.out.println("저장 실패");
			}	
		}
		
	}
	

}