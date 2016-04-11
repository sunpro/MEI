package com.mei.util;

public class Labels {

	private int _id;
	private String label;
	private String date;
	private String l1,l2,l3,l4,l5;
	private String jilu;

	/**
	 * @return the l1
	 */
	public String getL1() {
		return l1;
	}

	/**
	 * @param l1 the l1 to set
	 */
	public void setL1(String l1) {
		this.l1 = l1;
	}

	/**
	 * @return the l2
	 */
	public String getL2() {
		return l2;
	}

	/**
	 * @param l2 the l2 to set
	 */
	public void setL2(String l2) {
		this.l2 = l2;
	}

	/**
	 * @return the l3
	 */
	public String getL3() {
		return l3;
	}

	/**
	 * @param l3 the l3 to set
	 */
	public void setL3(String l3) {
		this.l3 = l3;
	}

	/**
	 * @return the l4
	 */
	public String getL4() {
		return l4;
	}

	/**
	 * @param l4 the l4 to set
	 */
	public void setL4(String l4) {
		this.l4 = l4;
	}

	/**
	 * @return the l5
	 */
	public String getL5() {
		return l5;
	}

	/**
	 * @param l5 the l5 to set
	 */
	public void setL5(String l5) {
		this.l5 = l5;
	}

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
	
	public String toString() {
		String result = 
				getLabel() + "\t" + getDate() + "\n"
				+ "机器人类型:" + getL1() + "\n"
				+ "发球方式:" + getL2() + "\n" 
				+ "接球区域大小:" + getL3() + "\n"
				+ "回球情况:" + getL4() + "\n"
				+ "回球方式:" + getL5() + "\n"
				+ "随记:\n" + getJilu() + "\n\n";
		return result;
	}

	/**
	 * @return the jilu
	 */
	public String getJilu() {
		return jilu;
	}

	/**
	 * @param jilu the jilu to set
	 */
	public void setJilu(String jilu) {
		this.jilu = jilu;
	}
}
