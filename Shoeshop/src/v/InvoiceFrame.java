package v;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import org.omg.CORBA.PUBLIC_MEMBER;

import m.CompanyInfoDB;
import m.CompanyInfoManager;
import m.CustomerDB;
import m.ProductDB;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JComponent;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import m.*;

public class InvoiceFrame extends JFrame
{

	private JPanel contentPane;
	public JPanel panel;
	public JTable table;
	CustomerDB xCustomerDB;
	private JLabel label_customer_info;
	public JScrollPane scrollPane;

	ArrayList<InvoiceDetail> detailList;

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
					InvoiceFrame frame = new InvoiceFrame();
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
	public InvoiceFrame()
	{
		addComponentListener(new ComponentAdapter()
		{
			@Override
			public void componentResized(ComponentEvent e)
			{
				panel.setBounds(0, 60, getWidth() - 20, getHeight() - 100);
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Dimension screeenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double width = screeenSize.getWidth();
		double height = screeenSize.getHeight();

		setBounds(0, 0, 1000, (int) height);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setForeground(Color.GRAY);
		panel.setBounds(0, 61, getWidth() - 20, getHeight() - 100);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel label = new JLabel(
				"\u0E43\u0E1A\u0E40\u0E2A\u0E23\u0E47\u0E08\u0E23\u0E31\u0E1A\u0E40\u0E07\u0E34\u0E19");
		label.setFont(new Font("Tahoma", Font.PLAIN, 32));
		label.setBounds(272, 11, 221, 71);
		panel.add(label);

		JLabel label_copany_info = new JLabel("New label");
		label_copany_info.setBounds(10, 94, 600, 14);
		panel.add(label_copany_info);

		label_customer_info = new JLabel("New label");
		label_customer_info.setBounds(99, 119, 654, 14);
		panel.add(label_customer_info);

		JLabel label_detail = new JLabel("\u0E23\u0E32\u0E22\u0E25\u0E30\u0E40\u0E2D\u0E35\u0E22\u0E14");
		label_detail.setFont(new Font("Tahoma", Font.BOLD, 15));
		label_detail.setBounds(10, 144, 84, 26);
		panel.add(label_detail);

		JLabel label_date = new JLabel("\u0E27\u0E31\u0E19\u0E17\u0E35\u0E48");
		label_date.setBounds(482, 48, 162, 14);
		panel.add(label_date);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 168, 542, 532);
		panel.add(scrollPane);

		table = new JTable();
		table.setFont(new Font("AngsanaUPC", Font.PLAIN, 17));
		scrollPane.setViewportView(table);

		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setBounds(675, 722, 46, 14);
		panel.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setBounds(675, 750, 46, 14);
		panel.add(lblNewLabel_2);

		JButton btnSelectCustomer = new JButton("select Customer");
		btnSelectCustomer.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				SearchCustomer cc = new SearchCustomer();
				cc.setSelectcustomerI(new SelectCustomerI()
				{

					@Override
					public void select(CustomerDB x)
					{
						xCustomerDB = x;
						String s = x.name + " " + x.surname + " ( " + x.phone + ") (id" + x.id + ") ";
						label_customer_info.setText(s);

					}
				});
				cc.setVisible(true); // visible คือให้มันโผล่มาบนจอ
			}
		});
		btnSelectCustomer.setBounds(10, 11, 148, 23);
		contentPane.add(btnSelectCustomer);

		JButton btnSelectProduct = new JButton("Select Product");
		btnSelectProduct.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				SearchProduct ss = new SearchProduct();
				ss.setSelectproduct(new SelectProductI()
				{

					@Override
					public void select(ProductDB x)
					{
						setDetail(x);

					}
				});
				ss.setVisible(true);
			}
		});
		btnSelectProduct.setBounds(185, 11, 148, 23);
		contentPane.add(btnSelectProduct);

		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(xCustomerDB == null )
				{
					///////////เขียนดักเอง
				}
				InvoiceManager.saveInvoice(xCustomerDB, detailList);
			}
		});
		btnSave.setBounds(376, 11, 89, 23);
		contentPane.add(btnSave);

		CompanyInfoDB x = CompanyInfoManager.getCompanyInfo(); // ++++++++++++++++++++++++++++++++++++++++++++++++++++ให้เอาข้อมูลไปใส่ในlabel
		String CompanyInfo = x.company_name + " ที่อยู่ " + x.address + " โทร. " + x.phone + " email " + x.email;
		label_copany_info.setText(CompanyInfo);
		label_date.setText(new SimpleDateFormat().format(Calendar.getInstance().getTime()).toString()); // Calendar.getInstance()
																										// getinstanceคือดึงเวลาในปัจจุบยัน

		JLabel label_1 = new JLabel("\u0E44\u0E14\u0E49\u0E23\u0E31\u0E1A\u0E40\u0E07\u0E34\u0E19\u0E08\u0E32\u0E01");
		label_1.setBounds(20, 119, 69, 14);
		panel.add(label_1);

		JButton btnPrint = new JButton("Print");
		btnPrint.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				PrinterJob job = PrinterJob.getPrinterJob();
				job.setPrintable(new InvoicePrint(InvoiceFrame.this));
				boolean doPrint = job.printDialog();
				if (doPrint)
				{
					try
					{
						job.print();
					} catch (PrinterException ee)
					{

					}
				}
			}
		});
		btnPrint.setBounds(491, 11, 89, 23);
		contentPane.add(btnPrint);

		detailList = new ArrayList<InvoiceDetail>();
	}

	public void setDetail(ProductDB xProduct) // +++++สร้างmethod stedetail
	{
		InvoiceDetail x = new InvoiceDetail();
		x.no = detailList.size() + 1;
		x.price_per_unit = xProduct.price_per_unit;
		x.productName = xProduct.product_name;
		x.qty = 1;
		x.totalPrice = x.price_per_unit * x.qty;
		x.product = xProduct;

		detailList.add(x);

		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("no");
		model.addColumn("image"); // ________________________การเพิ่งตารางในcolum_________
		model.addColumn("productName");
		model.addColumn("qty");
		model.addColumn("price_per_unit");
		model.addColumn("totalPrice");

		for (InvoiceDetail c : detailList) // ________________________การเพิ่งตารางในcolum_________
		{
			model.addRow(new Object[]
			{ c.no, c.product.product_image, c.productName, c.qty, c.price_per_unit, c.totalPrice });
		}
		table.setModel(model);

		table.getColumn("image").setCellRenderer(new InvoiceDetailTableRenderer()); // ________________________การเพิ่งตารางในcolum_________
		// table.getColumn("qty").set ***** แก้QTY

		for (int i = 0; i < table.getRowCount(); i++)
		{
			table.setRowHeight(i, 120);
		}
	}
}



class InvoiceDetailTableRenderer extends DefaultTableCellRenderer implements TableCellRenderer
{
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int col)
	{
		ImageComponent i = new ImageComponent();
		i.image = (BufferedImage) value;
		return i;
	}

}

class ImageComponent extends JComponent
{
	public BufferedImage image;

	public void paint(Graphics g)
	{
		if (image != null)
		{
			g.drawImage(image, 0, 0, 120, 120, 0, 0, image.getWidth(), image.getHeight(), this);
		}
	}
}

class InvoicePrint implements Printable
{
	InvoiceFrame xframe;

	public InvoicePrint(InvoiceFrame frame)
	{
		xframe = frame;
	}

	@Override
	public int print(Graphics g, PageFormat pf, int page) throws PrinterException
	{
		if (page > 0)
		{
			return NO_SUCH_PAGE;
		}

		Graphics2D g2d = (Graphics2D) g;
		g2d.translate(pf.getImageableX(), pf.getImageableY());

		for (int i = 0; i < xframe.panel.getComponentCount(); i++)
		{
			Component c = xframe.panel.getComponent(i);
			if (c instanceof JLabel)
			{
				JLabel l = (JLabel) c;
				g2d.setFont(l.getFont());
				g2d.drawString(l.getText(), (int) (l.getLocation().getX()),
						(int) (l.getLocation().getY() + l.getHeight()));
			}
		}

		int x = 10;
		int y = (int) (xframe.scrollPane.getLocation().getY()) + 40;
		for (int i = 0; i < xframe.table.getColumnCount(); i++)
		{
			g2d.setFont(xframe.table.getFont());
			g2d.drawString(xframe.table.getColumnName(i), x, y);

			x += xframe.table.getColumn(xframe.table.getColumnName(i)).getWidth();
		}

		y += 40;
		for (int j = 0; j < xframe.table.getRowCount(); j++)
		{
			x = 10;
			for (int i = 0; i < xframe.table.getColumnCount(); i++)
			{
				if(xframe.table.getColumnName(i).equals("image"))
				{
					BufferedImage image = (BufferedImage)xframe.table.getValueAt(j, i);
					g.drawImage(image, x, y-10, x+120, y+120-40, 0, 0, image.getWidth(), image.getHeight(), null);
				}else
				{
				g2d.setFont(xframe.table.getFont());
				g2d.drawString(""+ xframe.table.getValueAt(j, i), x, y);
				}
				x += xframe.table.getColumn(xframe.table.getColumnName(i)).getWidth();
			}
			y += xframe.table.getRowHeight(j);
		}
		return PAGE_EXISTS;
	}

}
