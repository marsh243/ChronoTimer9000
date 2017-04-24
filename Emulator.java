import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.GridLayout;
import javax.swing.JTextArea;
import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.JScrollPane;

public class Emulator extends JFrame {
	ChronoTimer ct = new ChronoTimer();

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Emulator frame = new Emulator();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Emulator() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel);
		
		JPanel powerfunctions = new JPanel();
		panel.add(powerfunctions);
		powerfunctions.setLayout(new GridLayout(4, 1, 0, 0));
		
		JButton btnPower = new JButton("Power");
		btnPower.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				ct.power();
			}
			
		});
		powerfunctions.add(btnPower);
		
		JButton btnFunction = new JButton("Function");
		btnFunction.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				ct.setMode();
			}
			
		});
		powerfunctions.add(btnFunction);
		
		JButton btnSwap = new JButton("Swap");
		btnSwap.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				ct.swap();
			}
			
		});		
		powerfunctions.add(btnSwap);
		
		JButton btnPrintpower = new JButton("PrintPower");
		btnPrintpower.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				ct.printPower();
			}
			
		});
		powerfunctions.add(btnPrintpower);
		
		JPanel triggers = new JPanel();
		panel.add(triggers);
		triggers.setLayout(new GridLayout(6, 4, 0, 0));
		
		textField = new JTextField();
		triggers.add(textField);
		textField.setEditable(false);
		textField.setText("1");
		textField.setColumns(1);
		
		textField_2 = new JTextField();
		triggers.add(textField_2);
		textField_2.setEditable(false);
		textField_2.setText("3");
		textField_2.setColumns(1);
		
		textField_4 = new JTextField();
		triggers.add(textField_4);
		textField_4.setEditable(false);
		textField_4.setText("5");
		textField_4.setColumns(1);
		
		textField_6 = new JTextField("7");
		triggers.add(textField_6);
		textField_6.setEditable(false);
		textField_6.setColumns(1);
		
		JButton btnStart1 = new JButton("Start1");
		btnStart1.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				ct.trigger(1);
			}
			
		});
		triggers.add(btnStart1);
		
		JButton btnStart_1 = new JButton("Start3");
		btnStart_1.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				ct.trigger(3);
			}
			
		});
		triggers.add(btnStart_1);
		
		JButton btnStart_2 = new JButton("Start5");
		btnStart_2.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				ct.trigger(5);
			}
			
		});
		triggers.add(btnStart_2);
		
		JButton btnStart_3 = new JButton("Start7");
		btnStart_3.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				ct.trigger(7);
			}
			
		});
		triggers.add(btnStart_3);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("1Enabled");
		chckbxNewCheckBox.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				ct.toggle(1);
			}
			
		});
		triggers.add(chckbxNewCheckBox);
		
		JCheckBox chckbxenabled_1 = new JCheckBox("3Enabled");
		chckbxenabled_1.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				ct.toggle(3);
			}
			
		});
		triggers.add(chckbxenabled_1);
		
		JCheckBox chckbxenabled_3 = new JCheckBox("5Enabled");
		chckbxenabled_3.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				ct.toggle(5);
			}
			
		});
		triggers.add(chckbxenabled_3);
		
		JCheckBox chckbxenabled_4 = new JCheckBox("7Enabled");
		chckbxenabled_4.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				ct.toggle(7);
			}
			
		});
		triggers.add(chckbxenabled_4);
		
		JCheckBox chckbxenabled = new JCheckBox("2Enabled");
		chckbxenabled.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				ct.toggle(2);
			}
			
		});
		triggers.add(chckbxenabled);
		
		JCheckBox chckbxenabled_2 = new JCheckBox("4Enabled");
		chckbxenabled_2.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				ct.toggle(4);
			}
			
		});
		triggers.add(chckbxenabled_2);
		
		JCheckBox chckbxNewCheckBox_1 = new JCheckBox("6Enabled");
		chckbxNewCheckBox_1.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				ct.toggle(6);
			}
			
		});
		triggers.add(chckbxNewCheckBox_1);
		
		JCheckBox chckbxenabled_5 = new JCheckBox("8Enabled");
		chckbxenabled_5.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				ct.toggle(8);
			}
			
		});
		triggers.add(chckbxenabled_5);
		
		JButton btnStart = new JButton("Finish2");
		btnStart.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				ct.trigger(2);
			}
			
		});
		triggers.add(btnStart);
		
		JButton btnFinish = new JButton("Finish4");
		btnFinish.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				ct.trigger(4);
			}
			
		});
		triggers.add(btnFinish);
		
		JButton btnFinish_1 = new JButton("Finish6");
		btnFinish_1.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				ct.trigger(6);
			}
			
		});
		triggers.add(btnFinish_1);
		
		JButton btnFinish_2 = new JButton("Finish8");
		btnFinish_2.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				ct.trigger(8);
			}
			
		});
		triggers.add(btnFinish_2);
		
		textField_1 = new JTextField();
		triggers.add(textField_1);
		textField_1.setEditable(false);
		textField_1.setText("2");
		textField_1.setColumns(1);
		
		textField_3 = new JTextField();
		triggers.add(textField_3);
		textField_3.setEditable(false);
		textField_3.setText("4");
		textField_3.setColumns(1);
		
		textField_5 = new JTextField("6");
		triggers.add(textField_5);
		textField_5.setEditable(false);
		textField_5.setColumns(1);
		
		textField_7 = new JTextField();
		triggers.add(textField_7);
		textField_7.setText("8");
		textField_7.setColumns(1);
		
		JPanel arrows = new JPanel();
		panel.add(arrows);
		arrows.setLayout(new BorderLayout(0, 0));
		
		JButton button = new JButton("^");
		arrows.add(button, BorderLayout.NORTH);
		
		JButton btnV = new JButton("v");
		arrows.add(btnV, BorderLayout.SOUTH);
		
		JButton button_1 = new JButton("<");
		arrows.add(button_1, BorderLayout.WEST);
		
		JButton button_2 = new JButton(">");
		arrows.add(button_2, BorderLayout.EAST);
		
		JTextArea textArea = new JTextArea("Event Log:\n", 3, 0);
		textArea.setLineWrap(true);
		textArea.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		panel.add(scrollPane);
		
		JPanel numPad = new JPanel();
		panel.add(numPad);
		numPad.setLayout(new GridLayout(4,3));
		
		JButton button_3 = new JButton("1");
		button_3.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				textArea.setText(textArea.getText()+"1");
			}
			
		});
		numPad.add(button_3);
		
		JButton button_4 = new JButton("2");
		button_4.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				textArea.setText(textArea.getText()+"2");
			}
			
		});
		numPad.add(button_4);
		
		JButton button_5 = new JButton("3");
		button_5.addActionListener(new ActionListener(){


			@Override
			public void actionPerformed(ActionEvent e) {
				textArea.setText(textArea.getText()+"3");
			}
			
		});
		numPad.add(button_5);
		
		JButton button_6 = new JButton("4");
		button_6.addActionListener(new ActionListener(){


			@Override
			public void actionPerformed(ActionEvent e) {
				textArea.setText(textArea.getText()+"4");
			}
			});
		numPad.add(button_6);
		
		JButton button_7 = new JButton("5");
		button_7.addActionListener(new ActionListener(){


			@Override
			public void actionPerformed(ActionEvent e) {
				textArea.setText(textArea.getText()+"5");
			}
			});
		numPad.add(button_7);
		
		JButton button_8 = new JButton("6");
		button_8.addActionListener(new ActionListener(){


			@Override
			public void actionPerformed(ActionEvent e) {
				textArea.setText(textArea.getText()+"6");
			}
			});
		numPad.add(button_8);
		
		JButton button_9 = new JButton("7");
		button_9.addActionListener(new ActionListener(){


			@Override
			public void actionPerformed(ActionEvent e) {
				textArea.setText(textArea.getText()+"7");
			}
			});
		numPad.add(button_9);
		
		JButton button_10 = new JButton("8");
		button_10.addActionListener(new ActionListener(){


			@Override
			public void actionPerformed(ActionEvent e) {
				textArea.setText(textArea.getText()+"8");
			}
			});
		numPad.add(button_10);
		
		JButton button_11 = new JButton("9");
		button_11.addActionListener(new ActionListener(){


			@Override
			public void actionPerformed(ActionEvent e) {
				textArea.setText(textArea.getText()+"9");
			}
			});
		numPad.add(button_11);
		
		JButton button_13 = new JButton("*");
		button_13.addActionListener(new ActionListener(){


			@Override
			public void actionPerformed(ActionEvent e) {
				textArea.setText(textArea.getText()+"*");
			}
			});
		numPad.add(button_13);
		
		JButton button_12 = new JButton("0");
		button_12.addActionListener(new ActionListener(){


			@Override
			public void actionPerformed(ActionEvent e) {
				textArea.setText(textArea.getText()+"0");
			}
			});
		numPad.add(button_12);
		
		JButton button_14 = new JButton("#");
		button_14.addActionListener(new ActionListener(){


			@Override
			public void actionPerformed(ActionEvent e) {
				textArea.setText(textArea.getText()+"\n");
			}
		});
		numPad.add(button_14);
		
		JTextArea textArea_1 = new JTextArea("Printer:", 3, 0);
		textArea_1.setLineWrap(true);
		textArea_1.setEditable(false);
		JScrollPane scrollPane_1 = new JScrollPane(textArea_1);
		scrollPane_1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		panel.add(scrollPane_1);
		
		JPanel Channels = new JPanel();
		contentPane.add(Channels, BorderLayout.SOUTH);
		Channels.setLayout(new GridLayout(2, 4, 0, 0));
		
		String[] sensors = {"none", "button", "trip", "photogate"};
		JList Ch1 = new JList(sensors);
		Ch1.addListSelectionListener(new ListSelectionListener(){

			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				
			}
			
		});
		Channels.add(Ch1);
		
		JList Ch2 = new JList(sensors);
		Channels.add(Ch2);
		
		JList Ch3 = new JList(sensors);
		Channels.add(Ch3);
		
		JList Ch4 = new JList(sensors);
		Channels.add(Ch4);
		
		JList Ch5 = new JList(sensors);
		Channels.add(Ch5);
		
		JList Ch6 = new JList(sensors);
		Channels.add(Ch6);
		
		JList Ch7 = new JList(sensors);
		Channels.add(Ch7);
		
		JList Ch8 = new JList(sensors);
		Channels.add(Ch8);
	}

}
