package com.itwillbs.web;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml" }
		)
public class MybatisTest {
	// < Mybatis를 사용한 디비연결 테스트 >
	
	
	
	// 1. 객체(sqlFactory)가 존재하는지 확인.
	// Mybatis 객체정보(설정정보) 가지고 있는 객체 필요함
	// => sqlSessionFactory 객체를 의존하고있다. DI
	
	@Inject
	private SqlSessionFactory sqlFactory; // root-context.xml에서 만든 FactoryBean을 들고오는 것
										  // 실제로는 Bean사용하는 거지만 약한결합을 주입했기때문에 그냥 팩토리를 불러온다.
	
	@Test
	public void 마이바티스_연결테스트() {
		System.out.println("@@@@@@@@@@sqlFactory : "+ sqlFactory); // sqlFactory 객체 주입완
	
		
	//2. 객체 존재하는지 확인 후 디비연결	
	// 디비연결
	SqlSession session = sqlFactory.openSession();
	System.out.println("@@@@@@@@sqlFactory : " + session);
		
	}
	
	

	
}
