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
import javax.swing.SwingConstants;

import java.awt.GridLayout;
import javax.swing.JTextArea;
import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.JScrollPane;

public class Emulator extends JFrame {
    
	static ChronoTimer9000 ct; //to run the emulator we need a chronotimer and a directory
	

	public JPanel contentPane; //list of fields to initialize
	
	// Labels for the trigger buttons
	public JTextField textField_0;
	public JTextField textField_1;
	public JTextField textField_2;
	public JTextField textField_3;
	public JTextField textField_4;
	public JTextField textField_5;
	public JTextField textField_6;
	public JTextField textField_7;
	
	// Text areas for the main output and printer
	public JScrollPane scrollpane;
	public JTextArea eventLog;
	public JTextArea printer;
	
	// Checkboxes to enable/disable the triggers
	public JCheckBox cbxTrig1;
	public JCheckBox cbxTrig2;
	public JCheckBox cbxTrig3;
	public JCheckBox cbxTrig4;
	public JCheckBox cbxTrig5;
	public JCheckBox cbxTrig6;
	public JCheckBox cbxTrig7;
	public JCheckBox cbxTrig8;
	
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
	public Emulator() { //The GUI element
		
		// Types of sensors
		String[] sensors = {"none", "button", "trip", "photogate"};
		
		//main window
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(2, 2));

		JPanel panel = new JPanel();
		contentPane.add(panel);
		
		//holds the power, function, swap, and printpower buttons
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
		
		//panel that contains the start, finish, and enable disable for each channel
		JPanel triggers = new JPanel();
		panel.add(triggers);
		triggers.setLayout(new GridLayout(6, 4, 0, 0));
		
		/*Setup trigger controls*/
		
		textField_0 = new JTextField();
		triggers.add(textField_0);
		textField_0.setEditable(false);
		textField_0.setText("1");
		textField_0.setColumns(1);
		
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
		
		JButton btnStart1 = new JButton("Start1 ");
		btnStart1.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				ct.trigger(1);
			}
			
		});
		triggers.add(btnStart1);
		
		JButton btnStart_1 = new JButton("Start 3");
		btnStart_1.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				ct.trigger(3);
			}
			
		});
		triggers.add(btnStart_1);
		
		JButton btnStart_2 = new JButton("Start 5");
		btnStart_2.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				ct.trigger(5);
			}
			
		});
		triggers.add(btnStart_2);
		
		JButton btnStart_3 = new JButton("Start 7");
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
		
		JButton btnStart = new JButton("Finish 2");
		btnStart.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				ct.trigger(2);
			}
			
		});
		triggers.add(btnStart);
		
		JButton btnFinish = new JButton("Finish 4");
		btnFinish.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				ct.trigger(4);
			}
			
		});
		triggers.add(btnFinish);
		
		JButton btnFinish_1 = new JButton("Finish 6");
		btnFinish_1.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				ct.trigger(6);
			}
			
		});
		triggers.add(btnFinish_1);
		
		JButton btnFinish_2 = new JButton("Finish 8");
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
		
		/*Setup arrows*/
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
		
		/*Create Channel inputs - Allows user to select type of sensor*/
		
		JPanel Channels = new JPanel();
		contentPane.add(Channels, BorderLayout.SOUTH);
		Channels.setLayout(new GridLayout(4, 4, 0, 0));
		
		JList Ch1 = new JList(sensors);
		Ch1.setSelectedValue(sensors[0], false);
		Ch1.setName("Ch1");
		Ch1.addListSelectionListener(new ListSelectionListener(){

			@Override
			public void valueChanged(ListSelectionEvent e) {
				if(!e.getValueIsAdjusting()){
					if(Ch1.getSelectedIndex()==0){
						ct.writeln(Ch1.getName()+" "+"Sensor disconnected.");
					}
					else{
						ct.writeln(Ch1.getName()+" "+Ch1.getSelectedValue()+" connected.");
					}
				}
			}
			
		});
		Channels.add(Ch1);
		
		JList Ch3 = new JList(sensors);
		Ch3.setSelectedValue(sensors[0], false);
		Ch3.setName("Ch3");
		Ch3.addListSelectionListener(new ListSelectionListener(){

			@Override
			public void valueChanged(ListSelectionEvent e) {
				if(!e.getValueIsAdjusting()){
					if(Ch3.getSelectedIndex()==0){
						ct.writeln(Ch3.getName()+" "+"Sensor disconnected.");
					}
					else{
						ct.writeln(Ch3.getName()+" "+Ch3.getSelectedValue()+" connected.");
					}
				}
			}
			
		});
		Channels.add(Ch3);
		
		JList Ch5 = new JList(sensors);
		Ch5.setSelectedValue(sensors[0], false);
		Ch5.setName("Ch5");
		Ch5.addListSelectionListener(new ListSelectionListener(){

			@Override
			public void valueChanged(ListSelectionEvent e) {
				if(!e.getValueIsAdjusting()){
					if(Ch5.getSelectedIndex()==0){
						ct.writeln(Ch5.getName()+" "+"Sensor disconnected.");
					}
					else{
						ct.writeln(Ch5.getName()+" "+Ch5.getSelectedValue()+" connected.");
					}
				}
			}
			
		});
		Channels.add(Ch5);
		
		JList Ch7 = new JList(sensors);
		Ch7.setSelectedValue(sensors[0], false);
		Ch7.setName("Ch7");
		Ch7.addListSelectionListener(new ListSelectionListener(){

			@Override
			public void valueChanged(ListSelectionEvent e) {
				if(!e.getValueIsAdjusting()){
					if(Ch7.getSelectedIndex()==0){
						ct.writeln(Ch7.getName()+" "+"Sensor disconnected.");
					}
					else{
						ct.writeln(Ch7.getName()+" "+Ch7.getSelectedValue()+" connected.");
					}
				}
			}
			
		});
		Channels.add(Ch7);
		
		JList Ch2 = new JList(sensors);
		Ch2.setSelectedValue(sensors[0], false);
		Ch2.setName("Ch2");
		Ch2.addListSelectionListener(new ListSelectionListener(){

			@Override
			public void valueChanged(ListSelectionEvent e) {
				if(!e.getValueIsAdjusting()){
					if(Ch2.getSelectedIndex()==0){
						ct.writeln(Ch2.getName()+" "+"Sensor disconnected.");
					}
					else{
						ct.writeln(Ch2.getName()+" "+Ch2.getSelectedValue()+" connected.");
					}
				}
			}
			
		});
		Channels.add(Ch2);
		
		JList Ch4 = new JList(sensors);
		Ch4.setSelectedValue(sensors[0], false);
		Ch4.setName("Ch4");
		Ch4.addListSelectionListener(new ListSelectionListener(){

			@Override
			public void valueChanged(ListSelectionEvent e) {
				if(!e.getValueIsAdjusting()){
					if(Ch4.getSelectedIndex()==0){
						ct.writeln(Ch4.getName()+" "+"Sensor disconnected.");
					}
					else{
						ct.writeln(Ch4.getName()+" "+Ch4.getSelectedValue()+" connected.");
					}
				}
			}
			
		});
		Channels.add(Ch4);
		
		JList Ch6 = new JList(sensors);
		Ch6.setSelectedValue(sensors[0], false);
		Ch6.setName("Ch6");
		Ch6.addListSelectionListener(new ListSelectionListener(){

			@Override
			public void valueChanged(ListSelectionEvent e) {
				if(!e.getValueIsAdjusting()){
					if(Ch6.getSelectedIndex()==0){
						ct.writeln(Ch6.getName()+" "+"Sensor disconnected.");
					}
					else{
						ct.writeln(Ch6.getName()+" "+Ch6.getSelectedValue()+" connected.");
					}
				}
			}
			
		});
		Channels.add(Ch6);
		
		JList Ch8 = new JList(sensors);
		Ch8.setSelectedValue(sensors[0], false);
		Ch8.setName("Ch8");
		Ch8.addListSelectionListener(new ListSelectionListener(){

			@Override
			public void valueChanged(ListSelectionEvent e) {
				if(!e.getValueIsAdjusting()){
					if(Ch8.getSelectedIndex()==0){
						ct.writeln(Ch8.getName()+" "+"Sensor disconnected.");
					}
					else{
						ct.writeln(Ch8.getName()+" "+Ch8.getSelectedValue()+" connected.");
					}
				}
			}
			
		});
		Channels.add(Ch8);
		
		
		//buttons that emulate a signal from a sensor on that channel
		JButton btnCh1Strigg = new JButton("Ch1:trigger");
		btnCh1Strigg.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(!Ch1.isSelectedIndex(0)) ct.trigger(1);
			}
			
		});
		Channels.add(btnCh1Strigg);
		
		JButton btnCh3Strigg = new JButton("Ch3:trigger");
		btnCh3Strigg.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(!Ch3.isSelectedIndex(0)) ct.trigger(3);
			}
			
		});
		Channels.add(btnCh3Strigg);
		
		JButton btnCh5Strigg = new JButton("Ch5:trigger");
		btnCh5Strigg.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(!Ch5.isSelectedIndex(0)) ct.trigger(5);
			}
			
		});
		Channels.add(btnCh5Strigg);
		
		JButton btnCh7Strigg = new JButton("Ch7:trigger");
		btnCh7Strigg.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(!Ch7.isSelectedIndex(0)) ct.trigger(7);
			}
			
		});
		Channels.add(btnCh7Strigg);
		
		JButton btnCh2Strigg = new JButton("Ch2:trigger");
		btnCh2Strigg.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(!Ch2.isSelectedIndex(0)) ct.trigger(2);
			}
			
		});
		Channels.add(btnCh2Strigg);
		
		JButton btnCh4Strigg = new JButton("Ch4:trigger");
		btnCh4Strigg.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(!Ch4.isSelectedIndex(0)) ct.trigger(4);
			}
			
		});
		Channels.add(btnCh4Strigg);
		
		JButton btnCh6Strigg = new JButton("Ch6:trigger");
		btnCh6Strigg.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(!Ch6.isSelectedIndex(0)) ct.trigger(6);
			}
			
		});
		Channels.add(btnCh6Strigg);
		
		JButton btnCh8Strigg = new JButton("Ch8:trigger");
		btnCh8Strigg.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(!Ch8.isSelectedIndex(0)) ct.trigger(8);
			}
			
		});
		Channels.add(btnCh8Strigg);
		
		eventLog = new JTextArea("Screen:\n", 3, 0);
		eventLog.setLineWrap(true);
		eventLog.setEditable(false);
		eventLog.setBackground(Color.BLACK);
		eventLog.setDisabledTextColor(Color.GREEN);
		eventLog.setEnabled(false);
		scrollpane = new JScrollPane(eventLog);
		scrollpane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		contentPane.add(scrollpane);
		
		/*Create a a number pad with buttons*/
		
		JPanel numPad = new JPanel();
		panel.add(numPad);
		numPad.setLayout(new GridLayout(4,3));
		
		JButton btnNum1 = new JButton("1");
		btnNum1.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				ct.inputNumber("1");
			}
			
		});
		numPad.add(btnNum1);
		
		JButton btnNum2 = new JButton("2");
		btnNum2.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				ct.inputNumber("2");
			}
			
		});
		numPad.add(btnNum2);
		
		JButton btnNum3 = new JButton("3");
		btnNum3.addActionListener(new ActionListener(){


			@Override
			public void actionPerformed(ActionEvent e) {
				ct.inputNumber("3");
			}
			
		});
		numPad.add(btnNum3);
		
		JButton btnNum4 = new JButton("4");
		btnNum4.addActionListener(new ActionListener(){


			@Override
			public void actionPerformed(ActionEvent e) {
				ct.inputNumber("4");
			}
			});
		numPad.add(btnNum4);
		
		JButton btnNum5 = new JButton("5");
		btnNum5.addActionListener(new ActionListener(){


			@Override
			public void actionPerformed(ActionEvent e) {
				ct.inputNumber("5");
			}
			});
		numPad.add(btnNum5);
		
		JButton btnNum6 = new JButton("6");
		btnNum6.addActionListener(new ActionListener(){


			@Override
			public void actionPerformed(ActionEvent e) {
				ct.inputNumber("6");
			}
			});
		numPad.add(btnNum6);
		
		JButton btnNum7 = new JButton("7");
		btnNum7.addActionListener(new ActionListener(){


			@Override
			public void actionPerformed(ActionEvent e) {
				ct.inputNumber("7");
			}
			});
		numPad.add(btnNum7);
		
		JButton btnNum8 = new JButton("8");
		btnNum8.addActionListener(new ActionListener(){


			@Override
			public void actionPerformed(ActionEvent e) {
				ct.inputNumber("8");
			}
			});
		numPad.add(btnNum8);
		
		JButton btnNum9 = new JButton("9");
		btnNum9.addActionListener(new ActionListener(){


			@Override
			public void actionPerformed(ActionEvent e) {
				ct.inputNumber("9");
			}
			});
		numPad.add(btnNum9);
		
		JButton btnAstrx = new JButton("*");
		btnAstrx.addActionListener(new ActionListener(){
		

			@Override
			public void actionPerformed(ActionEvent e) {
				ct.writeln("*");
				ct.post();
			}
			});
		numPad.add(btnAstrx);
		
		JButton btnNum0 = new JButton("0");
		btnNum0.addActionListener(new ActionListener(){


			@Override
			public void actionPerformed(ActionEvent e) {
				ct.inputNumber("0");
			}
			});
		numPad.add(btnNum0);
		
		JButton brnPnd = new JButton("#");
		brnPnd.addActionListener(new ActionListener(){


			@Override
			public void actionPerformed(ActionEvent e) {
				ct.enter();
			}
		});
		numPad.add(brnPnd);
		
		printer = new JTextArea("Printer:\n", 3, 0);
		printer.setLineWrap(false);
		printer.setEditable(false);
		JScrollPane scrollPane_1 = new JScrollPane(printer);
		scrollPane_1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		contentPane.add(scrollPane_1);
	}
	
}
