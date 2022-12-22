package com.itwillbs.domain;

public class Criteria {
	//	페이징처리에 필요한 정보를 저장 (페이지 시작정보, 사이즈)
	private int page; // 시작페이지 정보
	private int perPageNum; // 페이지 개수
	
	
	// 페이징처리 정보가 없을 경우 - 기본값
	public Criteria() {
		this.page = 1; // 시작페이지 정보
		this.perPageNum = 10; // 페이지 개수
	}
	
	/**
	 * 페이지 정보 초기화 :
	 * 페이지정보 (음수일 때 1페이지 정보)
	 */
	// 페이지정보를 그냥 쓸 수 있냐?
	// 정수 형태의 정보들만 들어오는 게 아니다. 음수가 들어올 수도 있다.
	public void setPage(int page) {
		if(page <= 0) { // 음수일때
			this.page = 1;
			return; // 음수는 여기서 함수 끝내기 => return 안 넣으면 아래 this.page = page;도 실행이 돼서 결국음 음수로 끝나버림..
			//return 대신에 if else() 넣어도 되지만 return형태가 더 많이 쓰임
		}
		// 양수일 때는 그대로 넣을 거다.
		this.page = page;
	}
	
	
	/**
	 * 페이지 정보 초기화 :
	 * 페이지 사이즈 정보(음수, 페이지수가 100이상 => 기본값 10)
	 */
	public void setPerPageNum(int perPageNum) {
		if(perPageNum <= 0 || perPageNum >= 100) {
			this.perPageNum = 10;
			return;
		}
		// 위 조건(if)이 아니면 전달받은 값의 형태로 아래에 값이 바로 입력됨
		this.perPageNum = perPageNum;
	}
	
	
	public int getPage() {
		return page;
	}
	
	// #{pageStart}안에 정보전달
	public int getPageStart() {
		// page = (page - 1) * 10;
		return (this.page-1)*perPageNum;
	}
	// #{perPageNum} 안에 정보전달
	public int getPerPageNum() {
		return perPageNum;
	}
	

	// alt shift s + s
	@Override
	public String toString() {
		return "Criteria [page=" + page + ", perPageNum=" + perPageNum + "]";
	}
}
