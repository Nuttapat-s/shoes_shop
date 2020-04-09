package v;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import m.CustomerDB;
import m.CustomerManager;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SearchCustomer extends JFrame
{

	private JPanel contentPane;
	private JTextField textField_search;
	ArrayList<CustomerDB> list;
	private JTable table;
	private JButton btnSelect;
	SelectCustomerI xSelectCustomerI;

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
					SearchCustomer frame = new SearchCustomer();
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
	public SearchCustomer()
	{
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 787, 365);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 94, 751, 221);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		textField_search = new JTextField();
		textField_search.setBounds(32, 39, 121, 20);
		contentPane.add(textField_search);
		textField_search.setColumns(10);
		
		JButton btnsearch = new JButton("Search");
		btnsearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				search();
			}
		});
		btnsearch.setBounds(175, 38, 138, 23);
		contentPane.add(btnsearch);
		
		btnSelect = new JButton("select");
		btnSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(table.getSelectedRow()> 0)  // getselectedrow ถ้าเป็นศูนย์ก็กลับไปหน้าเดิมไม่ได้
				{
					JOptionPane.showMessageDialog(SearchCustomer.this, "Please select row!!");
					return;
				}
				
				if(xSelectCustomerI != null)   //@@@@@@
				{
					if(list != null)
					{
						xSelectCustomerI.select(list.get(table.getSelectedRow()));
						
						setVisible(false);
					}
					
				}
			}
		});
		btnSelect.setBounds(530, 38, 89, 23);
		contentPane.add(btnSelect);
		
		load();
	}
	
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
	public void search()
	{
		list = CustomerManager.searchCustomer(textField_search.getText());
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
	public void setSelectcustomerI (SelectCustomerI x)  //@@@@@ดูตาม ให้ไปเรียกฟังชั่นย้อนกลับ ให้fromแม่ส่งpointerที่ชี้ไปยังฟังชั่น
	{
		xSelectCustomerI =x;
	}

}

interface SelectCustomerI    //เรื้่องDesign pattern
{
	public void select(CustomerDB x);
}


