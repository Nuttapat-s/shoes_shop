package m;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.*;

import common.Globaldata;

public class UserManager
{
	public static ArrayList<UserDB> getAllUser()
	{
		ArrayList<UserDB> list = new ArrayList<UserDB>(); // ต้องประกาศตัวแปร

		try
		{
			// create our mysql database connection
			String myDriver = "org.gjt.mm.mysql.Driver";
			String myUrl = "jdbc:mysql://" + Globaldata.DATABASE_LOCATION + ":" + Globaldata.DATABASE_PORT + "/"
					+ Globaldata.DATABASE_DATABASE_NAME; // localhost to database loca ,, test to globalta.databasename
			Class.forName(myDriver);
			Connection conn = DriverManager.getConnection(myUrl, Globaldata.DATABASE_USERNAME,
					Globaldata.DATABASE_PASSWORD);

			String query = "SELECT * FROM users";
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);
			while (rs.next())
			{
				int id = rs.getInt("id");
				String username = rs.getString("username");
				String password = rs.getString("password");
				String usertype = rs.getString("usertype");

				UserDB cc = new UserDB(id, username, password, usertype);
				list.add(cc);

			}
			st.close();
		} catch (Exception e)
		{
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}

		return list;
	}

	public static void saveNewUser(UserDB x) // ก้อมาเกือบหมดจาก getallcus
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

			String query = "INSERT INTO users VALUE(0, '" + x.username + "', '" + x.password + "', '" + x.usertype
					+ "')";
			Statement st = conn.createStatement();
			st.executeUpdate(query);

			st.close();
		} catch (Exception e)
		{
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
	}

	public static boolean checkLogin(String username, String password)
	{
//		try
//		{
//			String myDriver = "org.gjt.mm.mysql.Driver";
//			String myUrl = "jdbc:mysql://" + Globaldata.DATABASE_LOCATION + ":" + Globaldata.DATABASE_PORT + "/"
//					+ Globaldata.DATABASE_DATABASE_NAME; // localhost to database loca ,, test to globalta.databasename
//			Class.forName(myDriver);
//			Connection conn = DriverManager.getConnection(myUrl, Globaldata.DATABASE_USERNAME,
//					Globaldata.DATABASE_PASSWORD);
//
//			String query = "SELECT * FROM users WHERE username = '" + username + "' AND password = '" + password + "' ";
//			System.out.println(query);
//			Statement st = conn.createStatement();
//			ResultSet rs = st.executeQuery(query);
//			while (rs.next())
//			{
//				Globaldata.CurrentUser_userID = rs.getInt(1);
//				Globaldata.CurrentUser_username = rs.getString(2);
//				Globaldata.CurrentUser_usertype = rs.getString(4);
//				return true;
//
//			}
//			st.close();
//		} catch (Exception e)
//		{
//			System.err.println("Got an exception! ");
//			System.err.println(e.getMessage());
//		}
//		return false;
//	}
		try       //ใช้prepareed statement ให้มันปลอดภัยขึ้น  ดูในxx
		{
			String myDriver = "org.gjt.mm.mysql.Driver";
			String myUrl = "jdbc:mysql://" + Globaldata.DATABASE_LOCATION + ":" + Globaldata.DATABASE_PORT + "/"
					+ Globaldata.DATABASE_DATABASE_NAME; // localhost to database loca ,, test to globalta.databasename
			Class.forName(myDriver);
			Connection conn = DriverManager.getConnection(myUrl, Globaldata.DATABASE_USERNAME,
					Globaldata.DATABASE_PASSWORD);

			String query = "SELECT * FROM users WHERE username = ? AND password = ? ";     //xx
			System.out.println(query);
			PreparedStatement st = conn.prepareStatement(query);
			st.setString(1, username);
			st.setString(2,password);
			
			
			
			ResultSet rs = st.executeQuery();
			while (rs.next())
			{
				Globaldata.CurrentUser_userID = rs.getInt(1);
				Globaldata.CurrentUser_username = rs.getString(2);
				Globaldata.CurrentUser_usertype = rs.getString(4);
				return true;

			}
			st.close();
		} catch (Exception e)
		{
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
		return false;
	}

}
