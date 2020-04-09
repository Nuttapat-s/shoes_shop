package m;

public class UserDB
{
	public int id;
	public String username;
	public String password;
	public String usertype;

	public UserDB()
	{
	}

	public UserDB(int xid, String xusername, String xpassword, String xusertype)
	{
		id = xid;
		username=xusername;
		password=xpassword;
		usertype=xusertype;
	}
}
