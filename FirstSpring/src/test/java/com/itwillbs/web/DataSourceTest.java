package com.itwillbs.web;

import java.sql.Connection;
import java.sql.SQLException;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

//@RunWith(SpringJUnit4ClassRunner.class)
// => Run 실행하다  With ~와 같이
//	  이 파일을 실행할 때 스프링JUnit4으로 실행하겠다.(스프링테스트 동작) 


//@ContextConfiguration(
//		locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml" } // ctrl+space
//			)
// => 이 파일을 실행할 때 필요한 설정파일(root-context.xml)을 등록 


// 이 DataSourceTest{}를 스프링JUnit4으로 실행할 때 root-context.xml파일에 있는 동작내용들을 들고오겠다.
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml" } // ctrl+space
		)
public class DataSourceTest {

	// 디비연결정보(DataSource) 필요함 -> 의존관계
//	BeanFactory factory = new XmlBeanFactory(root-context.xml); // 원래 이렇게 썼었는데 이제는 이것도 안쓸거다
	
	// @Inject : 의존성 주입(DI)을 수행 가능하다.
	
	// root-context.xml 파일에 DriverManagerDataSource타입의 객체 dataSource가 하단 ds에 들어간 것
	//	private DataSource ds2 = new DriverManagerDataSource(); => 업캐스팅(자동형변환)
	// 원래라면 이렇게 써서 객체를 불러와야하지만 @Inject를 쓰면 아래처럼 저렇게만 작성해도된다.
	
//	@Inject
//	private DriverManagerDataSource ds1; => 객체를 직접 생성해서 강한 결합이 됨

	
//	@Inject : 주입하겠다.
//	@Autowired 는 Inject 대신 써도 가능
	@Autowired 
	private DataSource ds; // 데이터 소스 객체를 받아오는 코드
		// 비어있는 객체인 상태에서
		// 얘를 실행할 때 @Inject가 있으면 실제로 주입할 만한 객체를 root-context.xml한테 찾아간다. 
		// 거기서 불러올 데이터 소스의 코드(DataSource 형태의 타입)가 존재하면 이 파일로 들고와서 인젝트로 쓴 코드에 주입해줌
		// => 객체가 완성?생성된다.
	
	@Test  // @Test를 통해 서버 없이 실행가능하게 한다.
	public void DataSource테스트() {
//		System.out.println(" 실행! ");
		// 디비연결 테스트
		System.out.println(ds); // 동작한다면 일반적인 객체의 모습이 보일거고 아니라면 null이 뜰거다.
		
		
		// 위에서 데이터 소스 받아오는 처리를 다 했고 ds에 값이 담긴걸 또 확인했으니
		// ds객체를 try~catch구문을써서 디비연결처리를 실행해보자.		
		try {
			Connection con = ds.getConnection();
			System.out.println(" 디비연결 성공!");
			System.out.println(con);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
