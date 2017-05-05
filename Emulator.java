import java.awt.BorderLayout;
import java.awt.Color;
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
	static ChronoTimer9000 ct;

	public JPanel contentPane;
	public JTextField textField;
	public JTextField textField_1;
	public JTextField textField_2;
	public JTextField textField_3;
	public JTextField textField_4;
	public JTextField textField_5;
	public JTextField textField_6;
	public JTextField textField_7;
	public JTextArea eventLog;
	public JTextArea printer;
	public JCheckBox cbxTrig1;
	public JCheckBox cbxTrig2;
	public JCheckBox cbxTrig3;
	public JCheckBox cbxTrig4;
	public JCheckBox cbxTrig5;
	public JCheckBox cbxTrig6;
	public JCheckBox cbxTrig7;
	public JCheckBox cbxTrig8;

	private String runner = "";
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Emulator frame = new Emulator();
					frame.setVisible(true);
					ct = new ChronoTimer9000(frame);
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
		setBounds(100, 100, 1000, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(2, 2));

		JPanel panel = new JPanel();
		contentPane.add(panel);
		contentPane.add(new JPanel());
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
				ct.newRun();
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
		
		cbxTrig1 = new JCheckBox("1 Enabled");
		cbxTrig1.setEnabled(false);
		cbxTrig1.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				ct.toggle(1);
			}
			
		});
		triggers.add(cbxTrig1);
		
		cbxTrig3 = new JCheckBox("3 Enabled");
		cbxTrig3.setEnabled(false);
		cbxTrig3.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				ct.toggle(3);
			}
			
		});
		triggers.add(cbxTrig3);
		
		cbxTrig5 = new JCheckBox("5 Enabled");
		cbxTrig5.setEnabled(false);
		cbxTrig5.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				ct.toggle(5);
			}
			
		});
		triggers.add(cbxTrig5);
		
		cbxTrig7 = new JCheckBox("7 Enabled");
		cbxTrig7.setEnabled(false);
		cbxTrig7.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				ct.toggle(7);
			}
			
		});
		triggers.add(cbxTrig7);
		
		cbxTrig2 = new JCheckBox("2 Enabled");
		cbxTrig2.setEnabled(false);
		cbxTrig2.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				ct.toggle(2);
			}
			
		});
		triggers.add(cbxTrig2);
		
		cbxTrig4 = new JCheckBox("4 Enabled");
		cbxTrig4.setEnabled(false);
		cbxTrig4.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				ct.toggle(4);
			}
			
		});
		triggers.add(cbxTrig4);
		
		cbxTrig6 = new JCheckBox("6 Enabled");
		cbxTrig6.setEnabled(false);
		cbxTrig6.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				ct.toggle(6);
			}
			
		});
		triggers.add(cbxTrig6);
		
		cbxTrig8 = new JCheckBox("8 Enabled");
		cbxTrig8.setEnabled(false);
		cbxTrig8.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				ct.toggle(8);
			}
			
		});
		triggers.add(cbxTrig8);
		
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
		textField_7.setEditable(false);
		textField_7.setColumns(1);
		
		JPanel arrows = new JPanel();
		panel.add(arrows);
		arrows.setLayout(new BorderLayout(0, 0));
		
		JButton button = new JButton("^");
		arrows.add(button, BorderLayout.NORTH);
		
		JButton btnV = new JButton("v");
		arrows.add(btnV, BorderLayout.SOUTH);
		
		JButton button_1 = new JButton("<");
		button_1.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				ct.setScreen(0);
			}
			
		});
		arrows.add(button_1, BorderLayout.WEST);
		
		
		JButton button_2 = new JButton(">");
		button_2.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				ct.setScreen(1);
				
			}
			
		});
		arrows.add(button_2, BorderLayout.EAST);
		
		
		eventLog = new JTextArea("Screen:\n", 3, 0);
		eventLog.setLineWrap(true);
		eventLog.setEditable(false);
		eventLog.setBackground(Color.BLACK);
		eventLog.setDisabledTextColor(Color.GREEN);
		eventLog.setEnabled(false);
		JScrollPane scrollPane = new JScrollPane(eventLog);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		contentPane.add(scrollPane);
		
		JPanel numPad = new JPanel();
		panel.add(numPad);
		numPad.setLayout(new GridLayout(4,3));
		
		JButton btnNum1 = new JButton("1");
		btnNum1.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				eventLog.setText(eventLog.getText()+"1");
				runner += "1";
			}
			
		});
		numPad.add(btnNum1);
		
		JButton btnNum2 = new JButton("2");
		btnNum2.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				eventLog.setText(eventLog.getText()+"2");
				runner += "2";
			}
			
		});
		numPad.add(btnNum2);
		
		JButton btnNum3 = new JButton("3");
		btnNum3.addActionListener(new ActionListener(){


			@Override
			public void actionPerformed(ActionEvent e) {
				eventLog.setText(eventLog.getText()+"3");
				runner += "3";
			}
			
		});
		numPad.add(btnNum3);
		
		JButton btnNum4 = new JButton("4");
		btnNum4.addActionListener(new ActionListener(){


			@Override
			public void actionPerformed(ActionEvent e) {
				eventLog.setText(eventLog.getText()+"4");
				runner += "4";
			}
			});
		numPad.add(btnNum4);
		
		JButton btnNum5 = new JButton("5");
		btnNum5.addActionListener(new ActionListener(){


			@Override
			public void actionPerformed(ActionEvent e) {
				eventLog.setText(eventLog.getText()+"5");
				runner += "5";
			}
			});
		numPad.add(btnNum5);
		
		JButton btnNum6 = new JButton("6");
		btnNum6.addActionListener(new ActionListener(){


			@Override
			public void actionPerformed(ActionEvent e) {
				eventLog.setText(eventLog.getText()+"6");
				runner += "6";
			}
			});
		numPad.add(btnNum6);
		
		JButton btnNum7 = new JButton("7");
		btnNum7.addActionListener(new ActionListener(){


			@Override
			public void actionPerformed(ActionEvent e) {
				eventLog.setText(eventLog.getText()+"7");
				runner += "7";
			}
			});
		numPad.add(btnNum7);
		
		JButton btnNum8 = new JButton("8");
		btnNum8.addActionListener(new ActionListener(){


			@Override
			public void actionPerformed(ActionEvent e) {
				eventLog.setText(eventLog.getText()+"8");
				runner += "8";
			}
			});
		numPad.add(btnNum8);
		
		JButton btnNum9 = new JButton("9");
		btnNum9.addActionListener(new ActionListener(){


			@Override
			public void actionPerformed(ActionEvent e) {
				eventLog.setText(eventLog.getText()+"9");
				runner += "9";
			}
			});
		numPad.add(btnNum9);
		
		JButton btnAstrx = new JButton("*");
		btnAstrx.addActionListener(new ActionListener(){


			@Override
			public void actionPerformed(ActionEvent e) {
				eventLog.setText(eventLog.getText()+"*");
				ct.endRun();
			}
			});
		numPad.add(btnAstrx);
		
		JButton btnNum0 = new JButton("0");
		btnNum0.addActionListener(new ActionListener(){


			@Override
			public void actionPerformed(ActionEvent e) {
				eventLog.setText(eventLog.getText()+"0");
				runner += "0";
			}
			});
		numPad.add(btnNum0);
		
		JButton brnPnd = new JButton("#");
		brnPnd.addActionListener(new ActionListener(){


			@Override
			public void actionPerformed(ActionEvent e) {
				eventLog.setText(eventLog.getText()+"\n");
				if (runner != "")
				{
					ct.addRacer(runner);
					runner = "";
				}
			}
		});
		numPad.add(brnPnd);
		
		printer = new JTextArea("Printer:\n", 3, 0);
		printer.setLineWrap(false);
		printer.setEditable(false);
		JScrollPane scrollPane_1 = new JScrollPane(printer);
		scrollPane_1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		contentPane.add(scrollPane_1);
		
		JPanel Channels = new JPanel();
		//contentPane.add(Channels, BorderLayout.SOUTH);
		Channels.setLayout(new GridLayout(2, 4, 0, 0));
		
		String[] sensors = {"none", "button", "trip", "photogate"};
		JList Ch1 = new JList(sensors);
		Ch1.addListSelectionListener(new ListSelectionListener(){

			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				
			}
			
		});
		//Channels.add(Ch1);
		
		JList Ch2 = new JList(sensors);
		//Channels.add(Ch2);
		
		JList Ch3 = new JList(sensors);
		//Channels.add(Ch3);
		
		JList Ch4 = new JList(sensors);
		//Channels.add(Ch4);
		
		JList Ch5 = new JList(sensors);
		//Channels.add(Ch5);
		
		JList Ch6 = new JList(sensors);
		//Channels.add(Ch6);
		
		JList Ch7 = new JList(sensors);
		//Channels.add(Ch7);
		
		JList Ch8 = new JList(sensors);
		//Channels.add(Ch8);
	}

}
