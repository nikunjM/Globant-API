package TestApp.RestTest.Model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ErrorMsg {
	private String errorMsg;
	private int errorCode;

	public ErrorMsg()
	{
		
	}
	 
	public ErrorMsg(String erroMsg,int errorCode)
	{
		super();
		this.errorMsg=erroMsg;
		this.errorCode=errorCode;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
}

