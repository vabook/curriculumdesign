package top.vabook.domain;

public class StoreEm {
	 public int emCount;

	 public String emNo;

	 public String emName;

	 public String emStatus;

	public StoreEm() {

	}

	public StoreEm(int count, String emNo, String emName, String emStatus) {
		this.emCount = count;
		this.emNo = emNo;
		this.emName = emName;
		this.emStatus = emStatus;
	}

	public int getEmCount() {
		return emCount;
	}

	public void setEmCount(int emCount) {
		this.emCount = emCount;
	}

	public String getEmNo() {
		return emNo;
	}

	public void setEmNo(String emNo) {
		this.emNo = emNo;
	}

	public String getEmName() {
		return emName;
	}

	public void setEmName(String emName) {
		this.emName = emName;
	}

	public String getEmStatus() {
		return emStatus;
	}

	public void setEmStatus(String emStatus) {
		this.emStatus = emStatus;
	}

	//取出对象值,并转为数组对象
	public String[] covertArray() {
		String[] store = {emNo,emName,emStatus,emCount+""};
		return store;
		
	}

}
