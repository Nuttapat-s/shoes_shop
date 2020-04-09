package v;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import m.CompanyInfoDB;
import m.CompanyInfoManager;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CompanyInfoFrame extends JFrame
{

	private JPanel contentPane;
	private JTextField textField_com_name;
	private JTextField textField_address;
	private JTextField textField_phone;
	private JTextField textField_email;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					CompanyInfoFrame frame = new CompanyInfoFrame();
					frame.setVisible(true);
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CompanyInfoFrame()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField_com_name = new JTextField();
		textField_com_name.setBounds(168, 26, 215, 20);
		contentPane.add(textField_com_name);
		textField_com_name.setColumns(10);
		
		JLabel lblCompanyName = new JLabel("Company Name");
		lblCompanyName.setBounds(42, 29, 101, 14);
		contentPane.add(lblCompanyName);
		
		textField_address = new JTextField();
		textField_address.setColumns(10);
		textField_address.setBounds(168, 56, 215, 20);
		contentPane.add(textField_address);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setBounds(42, 59, 101, 14);
		contentPane.add(lblAddress);
		
		textField_phone = new JTextField();
		textField_phone.setColumns(10);
		textField_phone.setBounds(168, 87, 215, 20);
		contentPane.add(textField_phone);
		
		JLabel lblPhone = new JLabel("Phone");
		lblPhone.setBounds(42, 90, 101, 14);
		contentPane.add(lblPhone);
		
		textField_email = new JTextField();
		textField_email.setColumns(10);
		textField_email.setBounds(168, 118, 215, 20);
		contentPane.add(textField_email);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(42, 121, 101, 14);
		contentPane.add(lblEmail);
		
		JButton btnSave = new JButton("SAVE");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				xCompanyInfoDB.company_name =textField_com_name.getText();
				xCompanyInfoDB.address =textField_address.getText();
				xCompanyInfoDB.phone =textField_phone.getText();
				xCompanyInfoDB.email =textField_email.getText();
				
				CompanyInfoManager.edit(xCompanyInfoDB);  //call edit cominfomana class
				
			}
		});
		btnSave.setBounds(168, 174, 89, 23);
		contentPane.add(btnSave);
		
		loadData();
	}
	CompanyInfoDB xCompanyInfoDB;
	private void loadData()
	{
		xCompanyInfoDB = CompanyInfoManager.getCompanyInfo();
		textField_com_name.setText(xCompanyInfoDB.company_name);
		textField_address.setText(xCompanyInfoDB.address);
		textField_phone.setText(xCompanyInfoDB.phone);
		textField_email.setText(xCompanyInfoDB.email);
		

	}

}
