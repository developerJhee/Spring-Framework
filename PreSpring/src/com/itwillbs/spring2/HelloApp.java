package com.itwillbs.spring2;

public class HelloApp {

	public static void main(String[] args) {
		// MessageBean - sayHello() 실행

		// MessageBean객체 생성 => why? 필요하니까!
		// MessageBean객체 의존하고있다.(없으면 안됨)
		// HelloApp - MessageBean
		// 의존관계

		// MessageBean객체 생성
		//		MessageBean mb = new MessageBean(); //강한결합
		//		mb.sayHello("홍길동");
		
		// MessageBeanKr - sayHello() 호출
		MessageBeanKr beanKR = new MessageBeanKr();
		beanKR.sayHello("홍길동2");
		
		// 약한 결합으로 호출하기!
		MessageBean mb = new MessageBeanKr();
		// => 업캐스팅
		// => 업캐스팅 했으니까 오버라이딩메서드 호출할 수 있다.
		mb.sayHello("홍길동(업캐스팅)");
		
		MessageBean mb2 = new MessageBeanEn();
		mb2.sayHello("Hong");
		
	}

}
