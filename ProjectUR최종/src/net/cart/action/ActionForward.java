package net.cart.action;

public class ActionForward {

	//1.외부접근 못하도록 이동할 페이지와 이동할 방식 멤버변수 생성
	private String path;
	private boolean isRedirect;
		// true면 sendRedirect방식으로 이동
		//사용처: 주소와 화면의 전환이 동시에 일어날때 (가상주소 -> 가상주소로 )
		// false면  forward방식으로 이동
		//사용처: 주소는 그대로인데 화면이 바뀔때 (가상주소에서 jsp보여줌)
	//2. getter setter생성
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public boolean isRedirect() {
		return isRedirect;
	}
	public void setRedirect(boolean isRedirect) {
		this.isRedirect = isRedirect;
	}
/*	
	//3.toString
	@Override
	public String toString() {
		return "ActionForward [path=" + path + ", isRedirect=" + isRedirect + "]";
	}*/
}