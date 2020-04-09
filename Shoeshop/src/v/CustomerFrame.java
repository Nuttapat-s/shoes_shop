package v;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import m.CustomerDB;
import m.CustomerManager;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;

import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CustomerFrame extends JFrame
{

	private JPanel contentPane;
	private JTable table;
	private JTextField textField_id;
	private JTextField textField_name;
	private JTextField textField_surname;
	private JTextField textField_phone;
	ArrayList<CustomerDB> list;

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
					
					CustomerFrame frame = new CustomerFrame();
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
	public CustomerFrame() // constructor
	{
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE); // เซ็ตหลังJFRAME แบบนี้ทำให้อีกหน้าหายไปแต่หน้าจอยังไม่ปิด
		setBounds(100, 100, 588, 414);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 307, 353);
		contentPane.add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter()
		{ // แอดmouse click
			@Override
			public void mouseClicked(MouseEvent arg0)
			{
				if (table.getSelectedRowCount() < 1)
				{
					return;
				}
				int index = table.getSelectedRow();
				int id = Integer.parseInt(table.getValueAt(index, 0).toString());
				String name = table.getValueAt(index, 1).toString();
				String surname = table.getValueAt(index, 2).toString();
				String phone = table.getValueAt(index, 3).toString();

				textField_id.setText("" + id);
				textField_name.setText("" + name);
				textField_surname.setText("" + surname);
				textField_phone.setText("" + phone);
			}
		});

		scrollPane.setViewportView(table);
		table.setBackground(Color.white); // สามารถเซ็ตสีแบรคกราวได้หาอ่านในเน็ต

		JLabel lblId = new JLabel("id");
		lblId.setBounds(344, 12, 46, 14);
		contentPane.add(lblId);

		JLabel lblName = new JLabel("name");
		lblName.setBounds(344, 37, 46, 14);
		contentPane.add(lblName);

		JLabel lblName_1 = new JLabel("surname");
		lblName_1.setBounds(344, 62, 46, 14);
		contentPane.add(lblName_1);

		JLabel lblPhone = new JLabel("phone");
		lblPhone.setBounds(344, 87, 46, 14);
		contentPane.add(lblPhone);

		textField_id = new JTextField();
		textField_id.setEditable(false);
		textField_id.setBackground(Color.ORANGE);
		textField_id.setBounds(400, 9, 143, 20);
		contentPane.add(textField_id);
		textField_id.setColumns(10);

		textField_name = new JTextField();
		textField_name.setColumns(10);
		textField_name.setBounds(400, 34, 143, 20);
		contentPane.add(textField_name);

		textField_surname = new JTextField();
		textField_surname.setColumns(10);
		textField_surname.setBounds(400, 59, 143, 20);
		contentPane.add(textField_surname);

		textField_phone = new JTextField();
		textField_phone.setColumns(10);
		textField_phone.setBounds(400, 84, 143, 20);
		contentPane.add(textField_phone);

		JButton btnSaveNew = new JButton("SAVE NEW");
		btnSaveNew.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				CustomerDB x = new CustomerDB(0, textField_name.getText().trim(), textField_surname.getText().trim(),
						textField_phone.getText().trim());
				CustomerManager.saveNewCustomer(x);
				load();
				textField_id.setText("");
				textField_name.setText("");
				textField_surname.setText("");
				textField_phone.setText("");

				JOptionPane.showMessageDialog(CustomerFrame.this, "finish!!");
			}
		});
		btnSaveNew.setBounds(344, 112, 89, 23);
		contentPane.add(btnSaveNew);

		JButton btnEdit = new JButton("EDIT");
		btnEdit.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				CustomerDB x = new CustomerDB(Integer.parseInt(textField_id.getText()), textField_name.getText().trim(),
						textField_surname.getText().trim(), textField_phone.getText().trim());
				CustomerManager.editCustomer(x);
				load();
				textField_id.setText("");
				textField_name.setText("");
				textField_surname.setText("");
				textField_phone.setText("");

				JOptionPane.showMessageDialog(CustomerFrame.this, "finish!!"); // ใส่thisข้างหลังเพราะมันเป็นclassซ้อนclass
																				// ต้องบอกว่าเป็นthis ไหน

			}
		});
		btnEdit.setBounds(344, 146, 89, 23);
		contentPane.add(btnEdit);

		JButton btnDelete = new JButton("DELETE");
		btnDelete.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{ // copy edit ma

				if (JOptionPane.OK_OPTION == JOptionPane.showConfirmDialog(
						CustomerFrame.this, "DO you want to delete?",
						"DELETE?", JOptionPane.OK_CANCEL_OPTION))
				{

					CustomerDB x = new CustomerDB(Integer.parseInt(
							textField_id.getText()),
							textField_name.getText().trim(),
							textField_surname.getText().trim(),
							textField_phone.getText().trim());
					CustomerManager.deleteCustomer(x); // delete
					load();
					textField_id.setText("");
					textField_name.setText("");
					textField_surname.setText("");
					textField_phone.setText("");

					JOptionPane.showMessageDialog(CustomerFrame.this, "finish!!");

				}
				
			}
		});
		btnDelete.setBounds(344, 180, 89, 23);
		contentPane.add(btnDelete);

		load();
	}

	// ArrayList<CustomerDB> list = //เอาไปประกาศไว้ข้างบน
	public void load()
	{
		list = CustomerManager.getAllCustomer();
		DefaultTableModel model = new DefaultTableModel(); // ต้องcast
		model.addColumn("id");
		model.addColumn("name");
		model.addColumn("surname");
		model.addColumn("phone");
			for (CustomerDB c : list) // เขียนแบบวนลูปตามทุกตัวที่อยู่ในlist
		{
			model.addRow(new Object[]
			{ c.id, c.name, c.surname, c.phone });
		}

		table.setModel(model);
	}
}
