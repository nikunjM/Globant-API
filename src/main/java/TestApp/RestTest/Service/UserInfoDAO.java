package TestApp.RestTest.Service;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import javax.ws.rs.core.UriInfo;
import java.io.File;
import java.security.MessageDigest;

import TestApp.RestTest.Database.*;
import TestApp.RestTest.Exceptions.DataNotFoundException;
import TestApp.RestTest.Model.*;
import TestApp.RestTest.resource.UserInfoResource;

public class UserInfoDAO {

	public UserInfo login(String username, String password, UriInfo uriInfo) {
		System.out.println("Username" + username + "passoword" + password);
		String sql = "SELECT * FROM users WHERE username = ? and password= ? and Enable=1";
		UserInfo user = null;
		Connection c = null;
		try {
			c = ConnectionHelper.getConnection();
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, getHash(password));
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				user = processRowLogin(rs, uriInfo);
				String uri = uriInfo.getBaseUriBuilder().path(UserInfoResource.class).path(user.getUsername()).build().toString();
				UpdateToken(user);// will update all the active token and create
				createToken(user);// will create new token
				// call a class to add and store token number
			} else {
				UserInfo luser = new UserInfo();
				ReturnResponse lerr = new ReturnResponse();
				lerr.setErrorId("1");
				lerr.setErrorResponse("username password not found");
				String uri = uriInfo.getBaseUriBuilder().path(UserInfoResource.class).path("Login").build().toString();
				lerr.setRedirectURL(uri);// to main paeg
				lerr.setEx(null);
				List<ReturnResponse> lerrList = new ArrayList<>();
				lerrList.add(lerr);
				luser.setErrorist(lerrList);
				user = luser;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			ConnectionHelper.close(c);
		}
		return user;
	}

	public UserInfo create(String username, String password, UriInfo uriInfo) {
		System.out.println("Username" + username + "passoword" + password);
		String sql = "SELECT * FROM users WHERE username = ?";
		UserInfo user = null;
		Connection c = null;
		try {
			c = ConnectionHelper.getConnection();
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				UserInfo luser = new UserInfo();
				ReturnResponse lerr = new ReturnResponse();
				lerr.setErrorId("1");
				lerr.setErrorResponse("username already exists");
				String uri = uriInfo.getBaseUriBuilder().path(UserInfoResource.class).path("Create").build().toString();
				lerr.setRedirectURL(uri);// to main page
				lerr.setEx(null);
				List<ReturnResponse> lerrList = new ArrayList<>();
				lerrList.add(lerr);
				luser.setErrorist(lerrList);
				user = luser;
			}else
			{
				createUser(username,getHash(password));
				user=login(username,getHash(password));// will create new token
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			ConnectionHelper.close(c);
		}
		return user;
	}

	public List<UserInfo> findAll(String city, String state, String Groupby, String profession,int start,int size) {
		List<UserInfo> list = new ArrayList<UserInfo>();
		Connection c = null;
		StringBuilder stringBuilder = new StringBuilder();
		String extraParam = "Select city,gender,Givenname,Profession,state,userid,username,Createdon,CreatedBy,UpdateOn,UpdatedBy";
		if (city != null) {
			stringBuilder.append(" city=" + "'" + city + "'");
		}
		if (state != null) {
			if (city != null) {
				stringBuilder.append(" and");
			}
			stringBuilder.append(" state=" + "'" + state + "'");
		}
		if (profession != null) {
			if (city != null || state != null) {
				stringBuilder.append(" and");
			}
			stringBuilder.append(" profession=" + "'" + profession + "'");
		}
		if (Groupby != null) {
			if (!Groupby.matches("(?i)city|gender|Givenname|Profession|state"))
			// if(true)
			{
				System.out.println(
						"-----------------------------------------------------andar ja-----------------------------------------------------");
				throw new DataNotFoundException(
						"Either you cant group by using this columns or values doesnt exists,Please check and try again");
			}
			extraParam += " ,COUNT(" + Groupby + ") AS " + Groupby + "Col";
			stringBuilder.append(" Group By " + Groupby);
		}
		String whereCondition=" where ";
		if(city==null && state==null && profession==null)
		{
			whereCondition="";
		}
		String sql = extraParam + " FROM users"+ whereCondition+ stringBuilder.toString();
		System.out.println("QUery" + sql);
		try {
			c = ConnectionHelper.getConnection();
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(sql);
			boolean rscheck=false; 
			if (!rs.next()) {
				// ResultSet is empty
				rscheck=true;
				System.out.println(
						"-----------------------------------------------------andar ja Resultset-----------------------------------------------------");
				throw new DataNotFoundException("Data not found");
			}
			if(!rscheck)
			{
				System.out.println("chcking rs");
				rs.beforeFirst();
			}
			while(rs.next()) {
				list.add(processRow(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			ConnectionHelper.close(c);
		}
		if(start>=0 && size>0)
		{
			if(start+size >list.size()) return new ArrayList<UserInfo>();
			return list.subList(start, start+size);
		}
		return list;
	}

	public List<FileList> returnFilePath(String path) {
		List<FileList> lfilelist=new ArrayList<FileList>();
		try{
			File folder = new File(path);
			File[] listOfFiles = folder.listFiles();
		    for (int i = 0; i < listOfFiles.length; i++) {
		      if (listOfFiles[i].isFile()) {
		    	FileList lfile=new FileList();
		        System.out.println("File " + listOfFiles[i].getName());
		        lfile.setFilename(listOfFiles[i].getName());
		        lfile.setFileSize(listOfFiles[i].length()+" bytes ");
		        lfile.setFileType("File");
		        lfilelist.add(lfile);
		      } 
		      else 
		    	  if (listOfFiles[i].isDirectory()) 
		      {
		    	FileList lfile=new FileList();
		        System.out.println("Directory " + listOfFiles[i].getName());
		        lfile.setFilename(listOfFiles[i].getName());
		        lfile.setFileSize(listOfFiles[i].length()+" bytes");
		        lfile.setFileType("Directory");
		        lfilelist.add(lfile);
		      }
		 }
			} catch (Exception e) {
		e.printStackTrace();
	}
		if(lfilelist.isEmpty())
		{
				System.out.println("-----------------------------------------------------andar ja Resultset-----------------------------------------------------");
				throw new DataNotFoundException("Path has not files, or its a wrong path");
		}
		    return lfilelist;
	}
	void UpdateToken(UserInfo user) {
		String sql = "Update tokenmanagement  SET Status=0 where user_id=?";
		Connection c = null;
		try {
			c = ConnectionHelper.getConnection();
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, user.getUserId());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			ConnectionHelper.close(c);
		}
	}
	void createUser(String username,String password)
	{
		System.out.println("inside create user");
		//String sql = "insert into users (UserName, password,Enable,City,State,Gender,Profession,GivenName,CreatedBy,UpdatedBy) VALUES (?,?)";
		String sql = "insert into users (UserName, password) VALUES (?,?)";
		Connection c = null;
		try {
			c = ConnectionHelper.getConnection();
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			ConnectionHelper.close(c);
		}
	}
	void createToken(UserInfo user) {
		String sql = "insert into tokenmanagement (User_Id, ExpTime, StartTime, TokenGenrated, Status) VALUES (?,?,?,?,1)";
		Connection c = null;
		try {
			c = ConnectionHelper.getConnection();
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, user.getUserId());
			ps.setLong(2, user.getErrorist().get(0).getExpEndTime());
			ps.setLong(3, user.getErrorist().get(0).getExpStartTime());
			ps.setString(4, user.getErrorist().get(0).getTokennumber());
			ps.executeUpdate();
			System.out.println("Insert opration done");
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			ConnectionHelper.close(c);
		}
	}
	public UserInfo login(String username, String password)
	{
		System.out.println("Isnide login thing");
		String sql = "SELECT * FROM users WHERE username = ? and password= ? and Enable=1";
		UserInfo user = null;
		Connection c = null;
		try {
			c = ConnectionHelper.getConnection();
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				user = processRow(rs);
			}
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			} finally {
				ConnectionHelper.close(c);
			}
			return user;
	}
	
	public static String generateRandomChars(String candidateChars, int length) {
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			sb.append(candidateChars.charAt(random.nextInt(candidateChars.length())));
		}

		return sb.toString();
	}

	protected UserInfo processRowLogin(ResultSet rs, UriInfo uriInfo) throws SQLException {
		Date date = new Date();
		long todaystime = date.getTime();
		long addedTime = todaystime + 1800000;
		UserInfo user = new UserInfo();
		user.setUserId(rs.getInt("UserId"));
		user.setGivenName(rs.getString("GivenName"));
		user.setUsername(rs.getString("UserName"));
		user.setCity(rs.getString("City"));
		user.setState(rs.getString("State"));
		user.setGender(rs.getString("Gender"));
		user.setProfession(rs.getString("Profession"));
		user.setUsername(rs.getString("UserName"));
		user.setCreatedOn(rs.getString("CreatedOn"));
		user.setCreatedBy(rs.getString("CreatedBy"));
		user.setUpdateOn(rs.getString("UpdateOn"));
		user.setUpdatedBy(rs.getString("UpdatedBy"));
		String uri = uriInfo.getBaseUriBuilder().path(UserInfoResource.class).path(rs.getString("UserName")).build().toString();
		
		ReturnResponse lerr = new ReturnResponse();
		lerr.setRedirectURL(uri);// to main paeg
		lerr.setEx(null);
		lerr.setTokennumber(generateRandomChars("ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890", 17));
		lerr.setExpStartTime(todaystime);
		lerr.setExpEndTime(addedTime);// added 30 mins
		List<ReturnResponse> lerrList = new ArrayList<>();
		lerrList.add(lerr);
		user.setErrorist(lerrList);

		
		return user;
	}

	protected UserInfo processRow(ResultSet rs) throws SQLException {
		UserInfo user = new UserInfo();
		user.setUserId(rs.getInt("UserId"));
		user.setGivenName(rs.getString("GivenName"));
		user.setUsername(rs.getString("UserName"));
		user.setCity(rs.getString("City"));
		user.setState(rs.getString("State"));
		user.setGender(rs.getString("Gender"));
		user.setProfession(rs.getString("Profession"));
		user.setCreatedOn(rs.getString("CreatedOn"));
		user.setCreatedBy(rs.getString("CreatedBy"));
		user.setUpdateOn(rs.getString("UpdateOn"));
		user.setUpdatedBy(rs.getString("UpdatedBy"));

		ResultSetMetaData rsMetaData = rs.getMetaData();
		if (rsMetaData.getColumnCount() == 12)
			user.setCount(rs.getInt(12));
		return user;
	}

	public String CheckConnection() {
		Connection c = null;
		String RetunrString = "";
		try {
			c = ConnectionHelper.getConnection();
			DatabaseMetaData dmd = c.getMetaData();
			String DatabaseVersion = dmd.getDatabaseProductVersion();
			String ProductName = dmd.getDatabaseProductName();
			RetunrString = ProductName + "---------------------" + DatabaseVersion + "---------------------"
					+ "is running";
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			ConnectionHelper.close(c);
		}
		return RetunrString;
	}
	byte[] digest;
	public String getHash(String stringToHash) {
		
		//hexString is used to store the hash
		StringBuffer hexString = new StringBuffer();
		//get a byte array of the String to be hashed
		byte[] buffer = stringToHash.getBytes();
		try {
			//get and instance of the MD5 MessageDigest
			MessageDigest alg = MessageDigest.getInstance("MD5");
			//Make sure the Digest is clear
			alg.reset();
			//Populate the Digest with the byte array
			alg.update(buffer);
			//Create the MD5 Hash
			digest = alg.digest();
			//Now we need to pull out each char of the byte array into the StringBuffer
		} catch (Exception e) {
			//Need to catch some exceptions
		}
		//And return the hex hash value as a String
		return asHex(digest);
	}

	private String asHex(byte hash[]) {
		StringBuffer buf = new StringBuffer(hash.length * 2);
		int i;
		for (i = 0; i < hash.length; i++) {
			if (((int) hash[i] & 0xff) < 0x10)
				buf.append("0");
			buf.append(Long.toString((int) hash[i] & 0xff, 16));
		}
		return buf.toString();
	}



}
