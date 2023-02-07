package network3.chat;

import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;
import javax.swing.*;
import java.util.*;

class ChatClient extends Thread {
	JFrame f;

	JTextField connTF, sendTF;
	JButton connB, sendB;
	JTextArea ta;
	
	Socket s;
	BufferedReader in;
	OutputStream out;

	// 추가0 : 대화명을 바꾸기
	JTextField changeNameTF;
	JButton    changeNameB;

	// 추가2 : 방인원의 대명 보여주기
	// 변수 선언
	JList  memberList;
	Vector list;
	
	public ChatClient() {
		f = new JFrame("Chat Client");
		

		connTF = new JTextField();
		sendTF = new JTextField();
		connB = new JButton("접 속");
		sendB = new JButton("확 인");
		ta = new JTextArea(15,40);
		
		// 추가0: 대화명 바꾸기
		changeNameTF	= new JTextField("guest", 10);
		changeNameB		= new JButton("바꾸기");
		JPanel p_changeName = new JPanel();
		p_changeName.add( new JLabel("대화명 : "),"West" );
		p_changeName.add(changeNameTF, "Center");
		p_changeName.add(changeNameB, "East");
		
		JPanel p_serverName = new JPanel();
		p_serverName.setLayout( new BorderLayout() );
		p_serverName.add( new JLabel("서버입력 : "),"West" );
		p_serverName.add(connTF, "Center");
		p_serverName.add(connB, "East");

		JPanel p_north = new JPanel();
		p_north.setLayout( new GridLayout(1, 2));
		p_north.add( p_changeName );
		p_north.add( p_serverName );

		JPanel p2 = new JPanel();
		p2.setLayout( new BorderLayout() );
		p2.add( new JLabel("메세지입력 : "),"West" );
		p2.add(sendTF,"Center");
		p2.add(sendB, "East");
		
		// 추가2 : 방인원의 대명 보여주기
		memberList = new JList();
		list		= new Vector();
		JPanel  p_east = new JPanel();
		p_east.setLayout( new BorderLayout());
		p_east.add("North", new JLabel("   우 리 방 멤 버   "));
		p_east.add("Center", memberList );
		


		f.getContentPane().add("North", p_north);
		f.getContentPane().add("Center", new JScrollPane(ta));
		f.getContentPane().add("South", p2);
		f.getContentPane().add("East", p_east);
		
		//f.setSize(500, 300);
		f.pack();
		f.setVisible(true);

		//-------------------------------------------- 이벤트처리
		connTF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				connProc();
			}			
		});
		connB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				connProc();
			}			
		});
		sendTF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sendProc();
			}			
		});
		sendB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sendProc();
			}			
		});

		//  추가0: 대화명 바꾸기
		changeNameTF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changeNameProc();
			}			
		});
		changeNameB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changeNameProc();
			}			
		});
	}// 생성자 종료
	
	

	void changeNameProc(){
		JOptionPane.showMessageDialog(null, "대화명을 바꿉니다");
	}

	void connProc() {
		// JOptionPane.showMessageDialog(null, "서버에 접속합니다");
		try {
			s = new Socket( connTF.getText(), 1234);
			out = s.getOutputStream();
			in = new BufferedReader( new InputStreamReader( s.getInputStream() ));
			start();	// run()
		} catch(Exception ex) {
			ta.setText("접속실패 : " + ex.getMessage());
		}
	} // connProc ends
	
	public void run() {
		while(s.isConnected()) {
			try {
				String msg = in.readLine();
				ta.append(msg + "\n");
			} catch(Exception ex) {
				
			}
		}
	}

	void sendProc() {
		// JOptionPane.showMessageDialog(null, "메세지를 전송합니다");
		try {
			String msg = sendTF.getText() + "\n";
			out.write(msg.getBytes());
			sendTF.setText("");
		} catch (Exception ex) {
			ta.append("메세지 전송 실패 : " + ex.getMessage());
		}
	}// sendProc ends
	
	
	
	public static void main(String [] args ) {
		new ChatClient();
	}
	
}// ChatClient ends
			
			

	
		
