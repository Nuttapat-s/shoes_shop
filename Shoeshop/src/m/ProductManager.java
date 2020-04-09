package m;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.*;
import javax.imageio.ImageIO;



import common.Globaldata;

public class ProductManager
{
	public static ArrayList<ProductDB> getAllProduct()
	{
		ArrayList<ProductDB> list = new ArrayList<ProductDB>(); // ต้องประกาศตัวแปร

		try
		{
			// create our mysql database connection
			String myDriver = "org.gjt.mm.mysql.Driver";
			String myUrl = "jdbc:mysql://" + Globaldata.DATABASE_LOCATION + ":" + Globaldata.DATABASE_PORT + "/"
					+ Globaldata.DATABASE_DATABASE_NAME; // localhost to database loca ,, test to globalta.databasename
			Class.forName(myDriver);
			Connection conn = DriverManager.getConnection(myUrl, Globaldata.DATABASE_USERNAME,
					Globaldata.DATABASE_PASSWORD);

			// our SQL SELECT query.
			// if you only need a few columns, specify them by name instead of using "*"
			String query = "SELECT * FROM product";

			// create the java statement
			Statement st = conn.createStatement();

			// execute the query, and get a java resultset
			ResultSet rs = st.executeQuery(query);

			// iterate through the java resultset
			while (rs.next())
			{
				// product_id product_name price_per_unit product_description product_image

				int id = rs.getInt("product_id");
				String pname = rs.getString("product_name");
				double price = rs.getDouble("price_per_unit");
				String dres = rs.getString("product_description");
				byte[] img_byte = rs.getBytes("product_image"); // ตัวนี้ต้องทำเยอะตามด้านล่าง
				BufferedImage bufferedimg = null;
				if(img_byte ==null || img_byte.length ==0)
				{
					
				}else
				{
					
					ByteArrayInputStream bais = new ByteArrayInputStream(img_byte);
					bufferedimg = ImageIO.read(bais);
					bais.close();
				}
				
				ProductDB cc = new ProductDB(id, pname, price, dres, bufferedimg);
				list.add(cc); // ให้customerตัวใหม่แอดลงlist
				// print the results

			}
			st.close();
		} catch (Exception e)
		{
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}

		return list;
	}
	
	public static ArrayList<ProductDB> searchProduct(String s)
	{
		ArrayList<ProductDB> list = new ArrayList<ProductDB>(); // ต้องประกาศตัวแปร

		try
		{
			// create our mysql database connection
			String myDriver = "org.gjt.mm.mysql.Driver";
			String myUrl = "jdbc:mysql://" + Globaldata.DATABASE_LOCATION + ":" + Globaldata.DATABASE_PORT + "/"
					+ Globaldata.DATABASE_DATABASE_NAME; // localhost to database loca ,, test to globalta.databasename
			Class.forName(myDriver);
			Connection conn = DriverManager.getConnection(myUrl, Globaldata.DATABASE_USERNAME,
					Globaldata.DATABASE_PASSWORD);

			// our SQL SELECT query.
			// if you only need a few columns, specify them by name instead of using "*"
			String query = "SELECT * FROM product WHERE product_name LIKE '"+s+"' OR product_description LIKE '"+s+"' ";

			// create the java statement
			Statement st = conn.createStatement();

			// execute the query, and get a java resultset
			ResultSet rs = st.executeQuery(query);

			// iterate through the java resultset
			while (rs.next())
			{
				// product_id product_name price_per_unit product_description product_image

				int id = rs.getInt("product_id");
				String pname = rs.getString("product_name");
				double price = rs.getDouble("price_per_unit");
				String dres = rs.getString("product_description");
				byte[] img_byte = rs.getBytes("product_image"); // ตัวนี้ต้องทำเยอะตามด้านล่าง
				BufferedImage bufferedimg = null;
				if(img_byte ==null || img_byte.length ==0)
				{
					
				}else
				{
					
					ByteArrayInputStream bais = new ByteArrayInputStream(img_byte);
					bufferedimg = ImageIO.read(bais);
					bais.close();
				}
				
				ProductDB cc = new ProductDB(id, pname, price, dres, bufferedimg);
				list.add(cc); // ให้customerตัวใหม่แอดลงlist
				// print the results

			}
			st.close();
		} catch (Exception e)
		{
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}

		return list;
	}
	public static void saveProduct(ProductDB x)
	{

		try
		{
			// create our mysql database connection
			String myDriver = "org.gjt.mm.mysql.Driver";
			String myUrl = "jdbc:mysql://" + Globaldata.DATABASE_LOCATION + ":" + Globaldata.DATABASE_PORT + "/"
					+ Globaldata.DATABASE_DATABASE_NAME; // localhost to database loca ,, test to globalta.databasename
			Class.forName(myDriver);
			Connection conn = DriverManager.getConnection(myUrl, Globaldata.DATABASE_USERNAME,
					Globaldata.DATABASE_PASSWORD);

			String query = "INSERT INTO product VALUE(? , ? , ? , ? , ? )";
// คือ autoincrement ของ id
			PreparedStatement st =conn.prepareStatement(query);
			st.setInt(1, 0);
			st.setString(2, x.product_name);
			st.setDouble(3, x.price_per_unit);
			st.setString(4, x.product_description);
			
			if(x.product_image != null)
			{
				ByteArrayOutputStream outStream = new ByteArrayOutputStream();
				ImageIO.write(x.product_image,"png",outStream);
				byte[] buffer =outStream.toByteArray();
				st.setBytes(5,buffer);
				outStream.close();
			}else
			{
				byte[] buffer =new byte[0];
				st.setBytes(5,buffer);
			}
			
			
			st.executeUpdate(); // แก้ตัวนี้
			
			
			
			st.close();
		} catch (Exception e)
		{
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
	}

}
