package net.order.db;

import java.sql.Date;

public class OrderBean {

	private int b_num;
	private String id;
	private String o_name;
	private String o_phone;
	private String o_email;
	private Date b_date;
	private int p_num;
	private int b_count;
	private int point;
	private int d_cost;
	private int d_result;
	private String d_name;
	private String d_phone;
	private String d_address;
	private String d_address2;
	private String d_message;
	private int d_postcode;
	private int payment;
	public int getB_num() {
		return b_num;
	}
	public void setB_num(int b_num) {
		this.b_num = b_num;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getO_name() {
		return o_name;
	}
	public void setO_name(String o_name) {
		this.o_name = o_name;
	}
	public String getO_phone() {
		return o_phone;
	}
	public void setO_phone(String o_phone) {
		this.o_phone = o_phone;
	}
	public String getO_email() {
		return o_email;
	}
	public void setO_email(String o_email) {
		this.o_email = o_email;
	}
	public Date getB_date() {
		return b_date;
	}
	public void setB_date(Date b_date) {
		this.b_date = b_date;
	}
	public int getP_num() {
		return p_num;
	}
	public void setP_num(int p_num) {
		this.p_num = p_num;
	}
	public int getB_count() {
		return b_count;
	}
	public void setB_count(int b_count) {
		this.b_count = b_count;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	public int getD_cost() {
		return d_cost;
	}
	public void setD_cost(int d_cost) {
		this.d_cost = d_cost;
	}
	public int getD_result() {
		return d_result;
	}
	public void setD_result(int d_result) {
		this.d_result = d_result;
	}
	public String getD_name() {
		return d_name;
	}
	public void setD_name(String d_name) {
		this.d_name = d_name;
	}
	public String getD_phone() {
		return d_phone;
	}
	public void setD_phone(String d_phone) {
		this.d_phone = d_phone;
	}
	public String getD_address() {
		return d_address;
	}
	public void setD_address(String d_address) {
		this.d_address = d_address;
	}
	public String getD_address2() {
		return d_address2;
	}
	public void setD_address2(String d_address2) {
		this.d_address2 = d_address2;
	}
	public String getD_message() {
		return d_message;
	}
	public void setD_message(String d_message) {
		this.d_message = d_message;
	}
	public int getPayment() {
		return payment;
	}
	public void setPayment(int payment) {
		this.payment = payment;
	}
	public int getD_postcode() {
		return d_postcode;
	}
	public void setD_postcode(int d_postcode) {
		this.d_postcode = d_postcode;
	}
	@Override
	public String toString() {
		return "orderBean [b_num=" + b_num + ", id=" + id + ", o_name=" + o_name + ", o_phone=" + o_phone + ", o_email="
				+ o_email + ", b_date=" + b_date + ", p_num=" + p_num + ", b_count=" + b_count + ", point=" + point
				+ ", d_cost=" + d_cost + ", d_result=" + d_result + ", d_name=" + d_name + ", d_phone=" + d_phone
				+ ", d_address=" + d_address + ", d_postcode =" + d_postcode  + ", d_address2=" + d_address2 + ", d_message=" + d_message + ", payment="
				+ payment + "]";
	}
	
	
	
}
