package net.product.db;

import java.util.Date;

public class ProductQnaBean {
	
	private int q_num;
	private String id;
	private int p_num;
	private String subject;
	private String content;
	private int re_result;
	private String reply;
	private Date reg_date;
	private Date re_reg_date;
	private int re_ref;
	private int re_lev;
	
	
	
	public int getRe_ref() {
		return re_ref;
	}
	public void setRe_ref(int re_ref) {
		this.re_ref = re_ref;
	}
	public int getRe_lev() {
		return re_lev;
	}
	public void setRe_lev(int re_lev) {
		this.re_lev = re_lev;
	}
	public int getQ_num() {
		return q_num;
	}
	public void setQ_num(int q_num) {
		this.q_num = q_num;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getP_num() {
		return p_num;
	}
	public void setP_num(int p_num) {
		this.p_num = p_num;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getRe_result() {
		return re_result;
	}
	public void setRe_result(int re_result) {
		this.re_result = re_result;
	}
	public String getReply() {
		return reply;
	}
	public void setReply(String reply) {
		this.reply = reply;
	}
	public Date getReg_date() {
		return reg_date;
	}
	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}
	public Date getRe_reg_date() {
		return re_reg_date;
	}
	public void setRe_reg_date(Date re_reg_date) {
		this.re_reg_date = re_reg_date;
	}
	@Override
	public String toString() {
		return "ProductQnaBean [q_num=" + q_num + ", id=" + id + ", p_num=" + p_num + ", subject=" + subject
				+ ", content=" + content + ", re_result=" + re_result + ", reply=" + reply + ", reg_date=" + reg_date
				+ ", re_reg_date=" + re_reg_date + "]";
	}
	
	
	
	}
