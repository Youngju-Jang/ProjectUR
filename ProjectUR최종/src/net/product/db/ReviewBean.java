package net.product.db;

import java.util.Date;

public class ReviewBean {
	
	private int r_num;
	private int p_num;
	private String id;
	private String r_content;
	private double r_star;
	private Date r_date;
	
	public int getR_num() {
		return r_num;
	}
	public void setR_num(int r_num) {
		this.r_num = r_num;
	}
	public int getP_num() {
		return p_num;
	}
	public void setP_num(int p_num) {
		this.p_num = p_num;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getR_content() {
		return r_content;
	}
	public void setR_content(String r_content) {
		this.r_content = r_content;
	}
	public double getR_star() {
		return r_star;
	}
	public void setR_star(double r_star) {
		this.r_star = r_star;
	}
	public Date getR_date() {
		return r_date;
	}
	public void setR_date(Date r_date) {
		this.r_date = r_date;
	}
	@Override
	public String toString() {
		return "ReviewBean [r_num=" + r_num + ", p_num=" + p_num + ", id=" + id + ", r_content=" + r_content
				+ ", r_star=" + r_star + ", r_date=" + r_date + "]";
	}
	
	

}
