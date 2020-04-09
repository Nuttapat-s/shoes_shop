package v;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import m.CustomerDB;

import m.CustomerManager;
import m.ProductDB;
import m.ProductManager;
import m.UserDB;
import m.UserManager;

import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.ComboBoxEditor;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UserFrame extends JFrame
{

	private JPanel contentPane;
	private JTextField textField_id;
	private JTextField textField_username;
	private JTextField textField_passw;
	private JTable table;

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
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					UserFrame frame = new UserFrame();
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
	public UserFrame()
	{
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 834, 468);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(22, 11, 374, 407);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (table.getSelectedRowCount() < 1)
				{
					return;
				}
				int index = table.getSelectedRow();
				int id = Integer.parseInt(table.getValueAt(index, 0).toString());
				String username = table.getValueAt(index, 1).toString();
				String password = ""+table.getValueAt(index, 2);
				String usertype = ""+table.getValueAt(index, 3);

				textField_id.setText("" + id);
				textField_username.setText("" + username);
				textField_passw.setText("" + password);
				comboBox.setSelectedItem(usertype);
			}
		});
		scrollPane.setViewportView(table);
		
		JLabel label = new JLabel("id");
		label.setBounds(429, 32, 46, 14);
		contentPane.add(label);
		
		JLabel lblUsername = new JLabel("username");
		lblUsername.setBounds(429, 57, 99, 14);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("password");
		lblPassword.setBounds(429, 82, 46, 14);
		contentPane.add(lblPassword);
		
		JLabel lblUsertupe = new JLabel("user type");
		lblUsertupe.setBounds(429, 107, 46, 14);
		contentPane.add(lblUsertupe);
		
		textField_id = new JTextField();
		textField_id.setEditable(false);
		textField_id.setColumns(10);
		textField_id.setBackground(Color.ORANGE);
		textField_id.setBounds(538, 29, 143, 20);
		contentPane.add(textField_id);
		
		textField_username = new JTextField();
		textField_username.setColumns(10);
		textField_username.setBounds(538, 54, 143, 20);
		contentPane.add(textField_username);
		
		textField_passw = new JTextField();
		textField_passw.setColumns(10);
		textField_passw.setBounds(538, 79, 143, 20);
		contentPane.add(textField_passw);
		
		JButton button_save = new JButton("SAVE NEW");
		button_save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserDB x = new UserDB(0, textField_username.getText().trim(), textField_passw.getText().trim(),
						comboBox.getSelectedItem().toString().trim());
				UserManager.saveNewUser(x);
				load();
				textField_id.setText("");
				textField_username.setText("");
				textField_passw.setText("");
				

				JOptionPane.showMessageDialog(UserFrame.this, "finish!!");
			}
		});
		button_save.setBounds(423, 132, 89, 23);
		contentPane.add(button_save);
		
		JButton button_edit = new JButton("EDIT");
		button_edit.setBounds(423, 166, 89, 23);
		contentPane.add(button_edit);
		
		JButton button_delete = new JButton("DELETE");
		button_delete.setBounds(423, 200, 89, 23);
		contentPane.add(button_delete);
		
		comboBox = new JComboBox<String>();
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"admin", "user"}));
		comboBox.setBounds(538, 104, 143, 20);
		contentPane.add(comboBox);
		
		load();
	}
	
	ArrayList<UserDB> list;
	private JComboBox<String> comboBox;
	public void load()
	{
		list = UserManager.getAllUser();
		DefaultTableModel model = new DefaultTableModel(); // ต้องcast
		
		model.addColumn("id");
		model.addColumn("username");
		model.addColumn("password");
		model.addColumn("usertype");
			for (UserDB c : list) // เขียนแบบวนลูปตามทุกตัวที่อยู่ในlist
		{
			model.addRow(new Object[]
			{ c.id, c.username, c.password, c.usertype });
		}

		table.setModel(model);
	}
}
