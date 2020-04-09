package v;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import m.UserManager;

import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.Window.Type;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class LoginFrame extends JFrame
{

	private JPanel contentPane;
	private JTextField textField_username;
	private JPasswordField passwordField;

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
					LoginFrame frame = new LoginFrame();
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
	public LoginFrame()
	{
		setTitle("ShoesShop Login");
		setType(Type.UTILITY);
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null); // à«çµµÃ§¡ÅÒ§¨Í
		// setLocation(arg0,arg1);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		textField_username = new JTextField();
		textField_username.setBounds(214, 43, 125, 20);
		contentPane.add(textField_username);
		textField_username.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				if(arg0.getKeyCode() == KeyEvent.VK_ENTER)
				{
					check();
				}
			}
		});
		passwordField.setBounds(214, 94, 125, 20);
		contentPane.add(passwordField);

		JLabel lblNewLabel = new JLabel("username");
		lblNewLabel.setBounds(56, 46, 46, 14);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("password");
		lblNewLabel_1.setBounds(56, 97, 46, 14);
		contentPane.add(lblNewLabel_1);

		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				check();
			}
		});
		btnLogin.setBounds(137, 171, 89, 23);
		contentPane.add(btnLogin);

		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				System.exit(0); // ãËé¡´»Ô´
			}
		});
		btnExit.setBounds(249, 171, 89, 23);
		contentPane.add(btnExit);
	}
	
	public void check() 
	{
		if(UserManager.checkLogin(textField_username.getText(), new String(passwordField.getPassword()) ))
		{
			MainFrame f=new MainFrame();
			f.setVisible(true);
			
			LoginFrame.this.setVisible(false);
		}else
		{
			JOptionPane.showMessageDialog(LoginFrame.this, "username or password incorrect!!");
		}	
	}
}
