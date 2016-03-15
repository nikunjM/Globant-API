package TestApp.RestTest.Model;

public class ReturnResponse {

	private String Tokennumber;
	private long ExpEndTime;
	private long ExpStartTime;

	private String ErrorId;
	private String ErrorResponse;
	private Exception ex;
	private String RedirectURL;
	
	public String getTokennumber() {
		return Tokennumber;
	}
	public void setTokennumber(String tokennumber) {
		Tokennumber = tokennumber;
	}
	public long getExpEndTime() {
		return ExpEndTime;
	}
	public void setExpEndTime(long expEndTime) {
		ExpEndTime = expEndTime;
	}
	public long getExpStartTime() {
		return ExpStartTime;
	}
	public void setExpStartTime(long l) {
		ExpStartTime = l;
	}
	public String getErrorId() {
		return ErrorId;
	}
	public void setErrorId(String errorId) {
		ErrorId = errorId;
	}
	public String getErrorResponse() {
		return ErrorResponse;
	}
	public void setErrorResponse(String errorResponse) {
		ErrorResponse = errorResponse;
	}
	public Exception getEx() {
		return ex;
	}
	public void setEx(Exception ex) {
		this.ex = ex;
	}
	public String getRedirectURL() {
		return RedirectURL;
	}
	public void setRedirectURL(String redirectURL) {
		RedirectURL = redirectURL;
	}
    
}
