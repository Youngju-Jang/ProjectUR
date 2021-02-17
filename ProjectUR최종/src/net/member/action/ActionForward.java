package net.member.action;

public class ActionForward {
	// 페이지를 이동할때 필요한 정보를 저장하는 객체 
	// 1. 이동할 주소
	// 2. 이동할 방식 ( response, forward )
	
	// 이동 주소
	private String path;
	// 이동 방식
	// - true : sendRedirect 방식 이동
	// - false : forward 방식 이동
	private boolean isRedirect;
	
	// alt shift s + r
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public boolean isRedirect() { //get()
		return isRedirect;
	}
	public void setRedirect(boolean isRedirect) {
		this.isRedirect = isRedirect;
	}
	

}
