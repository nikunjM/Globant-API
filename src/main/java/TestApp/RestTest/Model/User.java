package TestApp.RestTest.Model;

import java.util.Date;
import javax.xml.bind.annotation.*;

@XmlRootElement

public class User {
	private int UserId;
	private String Username;
	private String Password;
	private String Enable;
	
	
	public int getUserId() {
		return UserId;
	}
	public void setUserId(int userId) {
		UserId = userId;
	}
	public String getUsername() {
		return Username;
	}
	public void setUsername(String username) {
		Username = username;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public String getEnable() {
		return Enable;
	}
	public void setEnable(String enable) {
		Enable = enable;
	}
}
