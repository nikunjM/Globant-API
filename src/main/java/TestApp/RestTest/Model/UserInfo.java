package TestApp.RestTest.Model;

import java.util.ArrayList;
import java.util.List;

public class UserInfo {
	private int UserId;
	private String Username;
	private String Password;
	private String Enable;
	private String City;
	private String State;
	private String Gender;
	private String Profession;
	private String GivenName;
	private int Count;
	private String createdOn;
	private String CreatedBy;
	private String UpdateOn;
	private String UpdatedBy;

	public String getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}
	public String getCreatedBy() {
		return CreatedBy;
	}
	public void setCreatedBy(String createdBy) {
		CreatedBy = createdBy;
	}
	public String getUpdateOn() {
		return UpdateOn;
	}
	public void setUpdateOn(String updateOn) {
		UpdateOn = updateOn;
	}
	public String getUpdatedBy() {
		return UpdatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		UpdatedBy = updatedBy;
	}
	public int getCount() {
		return Count;
	}
	public void setCount(int count) {
		Count = count;
	}
	public UserInfo()
	{
		
	}
	private List<ReturnResponse> errorist=new ArrayList<>();
	
	public List<ReturnResponse> getErrorist() {
		return errorist;
	}
	public void setErrorist(List<ReturnResponse> errorist) {
		this.errorist = errorist;
	}
	
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

	public String getCity() {
		return City;
	}

	public void setCity(String city) {
		City = city;
	}

	public String getState() {
		return State;
	}

	public void setState(String state) {
		State = state;
	}

	public String getGender() {
		return Gender;
	}

	public void setGender(String gender) {
		Gender = gender;
	}

	public String getProfession() {
		return Profession;
	}

	public void setProfession(String profession) {
		Profession = profession;
	}

	public String getGivenName() {
		return GivenName;
	}

	public void setGivenName(String givenName) {
		GivenName = givenName;
	}

}
