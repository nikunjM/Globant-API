package TestApp.RestTest.Model;

public class FileList {
	private String filename;
	private String fileSize;
	private String fileType;
	
	private String fileParentPath;
	
	
	public String getFileParentPath() {
		return fileParentPath;
	}
	public void setFileParentPath(String fileParentPath) {
		this.fileParentPath = fileParentPath;
	}
	
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String  getFileSize() {
		return fileSize;
	}
	public void setFileSize(String string) {
		this.fileSize = string;
	}
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

}
