package com.mei.util;

public class AllUtil {
	private int _id;
	private String label;
	private String date;
	private int xmm;
	private int ymm;
	private String high;
	private String l1,l2,l3,l4,l5;
	private String jilu;
	public int get_id() {
		return _id;
	}
	public void set_id(int _id) {
		this._id = _id;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getXmm() {
		return xmm;
	}
	public void setXmm(int xmm) {
		this.xmm = xmm;
	}
	public int getYmm() {
		return ymm;
	}
	public void setYmm(int ymm) {
		this.ymm = ymm;
	}
	public String getHigh() {
		return high;
	}
	public void setHigh(String high) {
		this.high = high;
	}
	public String getL1() {
		return l1;
	}
	public void setL1(String l1) {
		this.l1 = l1;
	}
	public String getL2() {
		return l2;
	}
	public void setL2(String l2) {
		this.l2 = l2;
	}
	public String getL3() {
		return l3;
	}
	
	public void setL3(String l3) {
		this.l3 = l3;
	}
	public String getL4() {
		return l4;
	}
	public void setL4(String l4) {
		this.l4 = l4;
	}
	public String getL5() {
		return l5;
	}
	public void setL5(String l5) {
		this.l5 = l5;
	}
	public String getJilu() {
		return jilu;
	}
	public void setJilu(String jilu) {
		this.jilu = jilu;
	}
	
	
	public String toString() {
		String posiStr;
		if(xmm <1000) {
			posiStr = label + "\t" + date + high + "\t( " + xmm + "\t, " + ymm + ")\n "; 
		}else {
			posiStr = label + "\t" + date + high + "\t( " + xmm + ", " + ymm + ")\n ";

		}
		posiStr += "机器人类型:" + getL1() + "\n"
				+ "发球方式:" + getL2() + "\n" 
				+ "接球区域大小:" + getL3() + "\n"
				+ "回球情况:" + getL4() + "\n"
				+ "回球方式:" + getL5() + "\n"
				+ "随记:\n" + getJilu() + "\n\n";
		return posiStr;
		
	}
	
}
