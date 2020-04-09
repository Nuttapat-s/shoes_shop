package m;

import java.io.ObjectInputStream.GetField;
import java.sql.*;
import java.sql.Connection;
import java.util.ArrayList;

import common.Globaldata;

public class CustomerManager
{
	public static ArrayList<CustomerDB> getAllCustomer()
	{
		ArrayList<CustomerDB> list = new ArrayList<CustomerDB>(); // ต้องประกาศตัวแปร

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
			String query = "SELECT * FROM customer2";

			// create the java statement
			Statement st = conn.createStatement();

			// execute the query, and get a java resultset
			ResultSet rs = st.executeQuery(query);

			// iterate through the java resultset
			while (rs.next())
			{
				int id = rs.getInt("id");
				String firstName = rs.getString("name");
				String lastName = rs.getString("surname");
				String phone = rs.getString("phone");

				CustomerDB cc = new CustomerDB(id, firstName, lastName, phone);
				list.add(cc); // ให้customerตัวใหม่แอดลงlist
				// print the results
				System.out.format("%s, %s, %s, %s \n", id, firstName, lastName, phone);
			}
			st.close();
		} catch (Exception e)
		{
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}

		return list;
	}

	public static void saveNewCustomer(CustomerDB x) // ก้อมาเกือบหมดจาก getallcus
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

			String query = "INSERT INTO customer2 VALUE(0, '" + x.name + "', '" + x.surname + "', '" + x.phone + "')"; // 0
																														// คือ
																														// auto
																														// increment
																														// ของ
																														// id
			Statement st = conn.createStatement();
			st.executeUpdate(query); // แก้ตัวนี้

			st.close();
		} catch (Exception e)
		{
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
	}

	public static void editCustomer(CustomerDB x) // ก้อมาเกือบหมดจาก save
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

			String query = "UPDATE customer2 SET name = '" + x.name + "', surname = '" + x.surname + "', phone = '"
					+ x.phone + "' WHERE id = " + x.id + " "; // edite user step+++++
			Statement st = conn.createStatement();
			st.executeUpdate(query); // แก้ตัวนี้

			st.close();
		} catch (Exception e)
		{
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
	}

	public static void deleteCustomer(CustomerDB x) // ก้อมาเกือบหมดจาก save
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

			String query = "DELETE  FROM customer2 WHERE id = " + x.id + " "; 
			Statement st = conn.createStatement();
			st.executeUpdate(query); // แก้ตัวนี้

			st.close();
		} catch (Exception e)
		{
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
	}
	
	public static ArrayList<CustomerDB> searchCustomer(String s)
	{
		ArrayList<CustomerDB> list = new ArrayList<CustomerDB>(); // ต้องประกาศตัวแปร

		try
		{
			
			String myDriver = "org.gjt.mm.mysql.Driver";
			String myUrl = "jdbc:mysql://" + Globaldata.DATABASE_LOCATION + ":" + Globaldata.DATABASE_PORT + "/"
					+ Globaldata.DATABASE_DATABASE_NAME; // localhost to database loca ,, test to globalta.databasename
			Class.forName(myDriver);
			Connection conn = DriverManager.getConnection(myUrl, Globaldata.DATABASE_USERNAME,
					Globaldata.DATABASE_PASSWORD);

			String query = "SELECT * FROM customer2 WHERE name LIKE '"+s+"' OR surname LiKE '"+s+"' ";
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);
			while (rs.next())
			{
				int id = rs.getInt("id");
				String firstName = rs.getString("name");
				String lastName = rs.getString("surname");
				String phone = rs.getString("phone");

				CustomerDB cc = new CustomerDB(id, firstName, lastName, phone);
				list.add(cc); // ให้customerตัวใหม่แอดลงlist
				// print the results
				System.out.format("%s, %s, %s, %s \n", id, firstName, lastName, phone);
			}
			st.close();
		} catch (Exception e)
		{
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}

		return list;
	}

	
	public static void main(String[] args)
	{
		ArrayList<CustomerDB> ll = CustomerManager.getAllCustomer();
		System.out.println(ll.size());
	}

}
