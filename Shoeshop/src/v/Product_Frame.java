package v;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.DefaultTableModel;

import m.CustomerDB;
import m.CustomerManager;
import m.ProductDB;
import m.ProductManager;

import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;

import java.awt.Color;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Product_Frame extends JFrame
{

	private JPanel contentPane;
	private JTextField textField_id;
	private JTextField textField_name;
	private JTextField textField_price_per_unit;
	private JTextField textField_dest;
	//private final ImagePanel imagePanel = new ImagePanel();

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
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());  //look and feel
					
					Product_Frame frame = new Product_Frame();
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
	public Product_Frame()
	{
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);  //แก้เป็นhide
		setBounds(100, 100, 776, 487);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(17, 49, 385, 353);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(table.getSelectedRowCount()<1)return;
				int index=table.getSelectedRow();
				BufferedImage img = list.get(index).product_image;
				if(img != null)
				{
					imagePanel.setImage(img);
				}
			}
		});
		scrollPane.setViewportView(table);
		
		JLabel lblProductId = new JLabel("product id");
		lblProductId.setBounds(412, 49, 112, 14);
		contentPane.add(lblProductId);
		
		JLabel lblProductName = new JLabel("product name");
		lblProductName.setBounds(412, 74, 104, 14);
		contentPane.add(lblProductName);
		
		JLabel lblPricePerUnit = new JLabel("price per unit");
		lblPricePerUnit.setBounds(412, 99, 104, 14);
		contentPane.add(lblPricePerUnit);
		
		JLabel lblProductDestcription = new JLabel("product destcription");
		lblProductDestcription.setBounds(412, 124, 112, 14);
		contentPane.add(lblProductDestcription);
		
		textField_id = new JTextField();
		textField_id.setEditable(false);
		textField_id.setColumns(10);
		textField_id.setBackground(Color.ORANGE);
		textField_id.setBounds(534, 49, 216, 20);
		contentPane.add(textField_id);
		
		textField_name = new JTextField();
		textField_name.setColumns(10);
		textField_name.setBounds(534, 71, 216, 20);
		contentPane.add(textField_name);
		
		textField_price_per_unit = new JTextField();
		textField_price_per_unit.setColumns(10);
		textField_price_per_unit.setBounds(534, 96, 216, 20);
		contentPane.add(textField_price_per_unit);
		
		textField_dest = new JTextField();
		textField_dest.setColumns(10);
		textField_dest.setBounds(534, 121, 216, 20);
		contentPane.add(textField_dest);
		
		JButton button_save = new JButton("SAVE NEW");
		button_save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(!textField_price_per_unit.getText().trim().matches("[-+]?\\d*\\.?\\d+"))  //เช็คถ้าไม่ใช่ตัวเลขให้ไปแก้
				{
					JOptionPane.showMessageDialog(Product_Frame.this, "number Only!!");
					textField_price_per_unit.requestFocus();
					textField_price_per_unit.selectAll();
				}
				ProductDB x=new ProductDB();
				x.product_id=0;
				x.product_name = textField_name.getText().trim();
				x.product_description=textField_dest.getText().trim();
				x.price_per_unit=Double.parseDouble(textField_price_per_unit.getText().trim());
				x.product_image=(BufferedImage)imagePanel.getImage();
				
				ProductManager.saveProduct(x);
				load();
				textField_id.setText("");
				textField_name.setText("");
				textField_dest.setText("");
				textField_price_per_unit.setText("");

				JOptionPane.showMessageDialog(Product_Frame.this, "finish!!");

			}
		});
		button_save.setBounds(438, 413, 89, 23);
		contentPane.add(button_save);
		
		JButton button_edit = new JButton("EDIT");
		button_edit.setBounds(551, 413, 89, 23);
		contentPane.add(button_edit);
		
		JButton button_delete = new JButton("DELETE");
		button_delete.setBounds(661, 413, 89, 23);
		contentPane.add(button_delete);
		
		imagePanel = new ImagePanel((Image) null); ///******************************
		imagePanel.setBounds(427, 200, 323, 202);
		contentPane.add(imagePanel);
		
		JButton btnBrowseImg = new JButton("browse img");
		btnBrowseImg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc= new JFileChooser();      ///คำสั่งเปิดไฟล์ในเครื่อง
				fc.addChoosableFileFilter(new OpenFileFilter("jpeg","Photo in JPEG format") );
				fc.addChoosableFileFilter(new OpenFileFilter("jpg","Photo in JPEG format") );
				fc.addChoosableFileFilter(new OpenFileFilter("png","PNG image") );
				fc.addChoosableFileFilter(new OpenFileFilter("svg","Scalable Vector Graphic") );
				fc.setAcceptAllFileFilterUsed(false);
				int returnVal =fc.showOpenDialog(Product_Frame.this);
				if(returnVal == JFileChooser.APPROVE_OPTION )
				{
					File f = fc.getSelectedFile();
					try
					{
					BufferedImage bimg = ImageIO.read(f);
					imagePanel.setImage(bimg);
					}catch (IOException e1)
					{
						e1.printStackTrace();
					}
				}
			}
		});
		btnBrowseImg.setBounds(544, 152, 89, 37);
		contentPane.add(btnBrowseImg);
		
		load();
	}
	ArrayList<ProductDB> list;
	private JTable table;
	private ImagePanel imagePanel;
	//product_id product_name price_per_unit product_description product_image
	public void load()
	{
		list = ProductManager.getAllProduct();
		DefaultTableModel model = new DefaultTableModel(); // ต้องcast
		
		model.addColumn("product_id");
		model.addColumn("product_name");
		model.addColumn("price_per_unit");
		model.addColumn("product_description");
			for (ProductDB c : list) // เขียนแบบวนลูปตามทุกตัวที่อยู่ในlist
		{
			model.addRow(new Object[]
			{ c.product_id, c.product_name, c.price_per_unit, c.product_description });
		}

		table.setModel(model);
	}
}
class OpenFileFilter extends FileFilter {

    String description = "";
    String fileExt = "";

    public OpenFileFilter(String extension) {
        fileExt = extension;
    }

    public OpenFileFilter(String extension, String typeDescription) {
        fileExt = extension;
        this.description = typeDescription;
    }

    @Override
    public boolean accept(File f) {
        if (f.isDirectory())
            return true;
        return (f.getName().toLowerCase().endsWith(fileExt));
    }

    @Override
    public String getDescription() {
        return description;
    }
}
