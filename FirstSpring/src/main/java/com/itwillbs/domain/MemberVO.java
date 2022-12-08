package com.itwillbs.domain;


import java.sql.Timestamp;

import lombok.Data;

@Data
public class MemberVO {
	// VO(Value Object)
	   /* VO(Value Object) 
    - DTO의 역할과 유사하지만 다른 개념!
    - VO는 특정 데이터를 저장해서 읽을 수만 있게 만들어 줌!, DTO는 전달하는 게 주 목적!
	    */
	
	   // VO (Value Object)
	   // 값을 저장하는 객체의 의미, DTO의 역할과 굉장히 유사함 하지만 DTO랑 VO는 다른 개념
	   // VO : 값을 저장하는 것에 좀 더 목적(리드온리 느낌)
	   // DTO : 데이터를 저장하여 전달 시키는 것에 좀 더 목적이 있음
	
	private String userid;
	private String userpw;
	private String username;
	private String useremail;
	
	private Timestamp regdate;
	private Timestamp updatedate;
	
}
