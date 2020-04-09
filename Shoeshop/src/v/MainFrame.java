package v;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainFrame extends JFrame
{

	private JPanel contentPane;

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
					 try {											//set look and feel step+++
				            // Set System L&F
				        UIManager.setLookAndFeel(
				            UIManager.getSystemLookAndFeelClassName());
				    } 
				    catch (UnsupportedLookAndFeelException e) {
				       // handle exception
				    }
				    catch (ClassNotFoundException e) {
				       // handle exception
				    }
				    catch (InstantiationException e) {
				       // handle exception
				    }
				    catch (IllegalAccessException e) {
				       // handle exception
				    }												//set look and feel step ===
					MainFrame frame = new MainFrame();
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
	public MainFrame()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 562, 378);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnCustomer = new JButton("Customer");
		btnCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CustomerFrame f =new CustomerFrame();
				f.setVisible(true);  //ให้ไปเปิดหน้า customer frame+++++++++++++
			}
		});
		btnCustomer.setBounds(20, 24, 89, 23);
		contentPane.add(btnCustomer);
		
		JButton btnProduct = new JButton("Product");
		btnProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Product_Frame f =new Product_Frame();
				f.setVisible(true); 
			}
		});
		btnProduct.setBounds(20, 58, 89, 23);
		contentPane.add(btnProduct);
		
		JButton btnUser = new JButton("User");
		btnUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserFrame f =new UserFrame();
				f.setVisible(true); 
			}
		});
		btnUser.setBounds(20, 92, 89, 23);
		contentPane.add(btnUser);
		
		JButton btnInvoice = new JButton("Invoice");
		btnInvoice.setBounds(20, 126, 89, 23);
		contentPane.add(btnInvoice);
	}
}
