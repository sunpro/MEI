package com.mei.util;

public class Position {

	private int _id;
	private String label;
	private String date;
	private int xmm;
	private int ymm;
	private String high;
	
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
	
	public String toString() {
		String posiStr;
		if(xmm <1000) {
			posiStr = label + "\t" + date + high + "\t( " + xmm + "\t, " + ymm + ")\n "; 
		}else {
			posiStr = label + "\t" + date + high + "\t( " + xmm + ", " + ymm + ")\n "; 

		}
		return posiStr;
		
	}
	public String getHigh() {
		return high;
	}
	public void setHigh(String high) {
		this.high = high;
	}
	/**
	 * @return the _id
	 */
	public int get_id() {
		return _id;
	}
	/**
	 * @param _id the _id to set
	 */
	public void set_id(int _id) {
		this._id = _id;
	}
	
	
}
