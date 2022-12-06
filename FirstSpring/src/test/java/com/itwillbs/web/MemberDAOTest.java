package com.itwillbs.web;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.itwillbs.domain.MemberVO;
import com.itwillbs.persistence.MemberDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
      locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"}
      )

public class MemberDAOTest {
   
   // DAO 객체 필요함
   @Inject 
   // MemberDAO 객체 주입중 근데 sqlSession을 주입하면 안될까? 
   // 우리 MVC에서도 M의 역할을 하는 Action에서 sql구문을 작성하지 않으니까 
   // 이것도 똑같은거다 거기서 메서드만 불러왔듯이 여기서도 메서드만 불러올거다.
   private MemberDAO dao;
   // MemberDAO dao = new MemberDAO(); 의미
   
//   @Test
   public void 시간정보조회하기() {
      System.out.println(dao.getServerTime());
   }
   
   // 회원정보 저장(Create)
   @Test        // Test파일이다보니까 해당 메서드를 실행시킬 수 있는 준비과정이 필요하다. => @Test
   public void createUser() {	
	  
	   // 회원정보 생성(사용자 입력 정보)
	   MemberVO vo = new MemberVO();
	   vo.setUserid("itwill02");
	   vo.setUserpw("122");
	   vo.setUsername("사용자02");
	   vo.setUseremail("itwill02@admin.com");
	   
	   dao.insertMember(vo); // 문제가 있다. vo가 없다. 임의로 만든다.
   
   }
   
//   // 특정 회원의 모든 정보를 조회
//   @Test
//   public void getUserInfo() {
//	   
////	   dao.getMember("admin");	  // 이상태로는 호출만 한 거지 출력은 안한상태
//	   	MemberVO vo = dao.getMember("admin");
//	   	System.out.println(" Test : " + vo);
//   }
//   
//   
//// 로그인 처리 
//	@Test
//	public void 로그인() {
//		
//		MemberVO vo = dao.loginMember("admin", "1234");
//		
//		if(vo != null) {
//			System.out.println(" 로그인 성공! ");
//		}else {
//			System.out.println(" 로그인 실패! ");			
//		}
//		
//		
//		MemberVO inputVo = new MemberVO();
//		inputVo.setUserid("admin");
//		inputVo.setUserpw("1234");
//		
//		MemberVO vo2 = dao.loginMember(inputVo);
//		
//		if(vo2 != null) {
//			System.out.println(" 로그인 성공! ");
//		}else {
//			System.out.println(" 로그인 실패! ");			
//		}
//		
//		
//	}
}





