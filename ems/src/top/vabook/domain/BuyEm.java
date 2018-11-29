package top.vabook.domain;

public class BuyEm {
	public int count;
	public String date;
	public int cost;
	public String peopleName;
	public String emNo;
	public String emName;
	
	public BuyEm(int count ,String date, int cost,String peopleName,String emNo,String emName) {
		this.count = count;
		this.date = date;
		this.cost = cost;
		this.peopleName = peopleName;
		this.emNo = emNo;
		this.emName = emName;
	}
	
}
