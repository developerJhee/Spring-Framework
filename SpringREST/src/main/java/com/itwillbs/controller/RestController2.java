package com.itwillbs.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.print.DocFlavor.READER;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.itwillbs.domain.RestVO;

// @RestController : 클래스 안에 있는 모든 메서드는 @ResponseBody이 적용되어 있는 형태
@RestController
public class RestController2 {
	private static final Logger mylog = LoggerFactory.getLogger(RestController2.class);
	
	// http://localhost:8080/test3
	@RequestMapping("/test3")
	public String test3() throws Exception{
		mylog.debug("test3()");
		
		return "ITWILL";
	}
	
	// http://localhost:8080/test4
	@RequestMapping("/test4")
	public int test4() throws Exception{
		mylog.debug("test4()");
		
		return 1000;
	}
	
	// http://localhost:8080/test5
	@RequestMapping("/test5")
	public RestVO test5() throws Exception{
		mylog.debug("test5()");
		
		// RestVO 리턴하려면 RestVO객체를 생성해야지
		RestVO vo = new RestVO();
		vo.setNo(1);
		vo.setId("itwill");
		vo.setPw("1234");
		vo.setTel("010-1234-1234");
		
		return vo;
	}
	
	// http://localhost:8080/test6
	@RequestMapping("/test6")
	public List<RestVO> test6() throws Exception{
		mylog.debug("test6()");
		
		List<RestVO> restList = new ArrayList<RestVO>();
		for(int i=0;i<10;i++) {
			RestVO vo = new RestVO();
			vo.setNo(i);
			vo.setId("itwill"+i);
			vo.setPw("1234"+i);
			vo.setTel("010-1234-123"+i);
			restList.add(vo);
		}
		
		return restList;
	}
	
	// http://localhost:8080/test7
	@RequestMapping("/test7")
	public Map<Integer, RestVO> test7() throws Exception{
		mylog.debug("test7()");
		
		Map<Integer, RestVO> map 
				= new HashMap<Integer, RestVO>();
		
		for(int i=0;i<10;i++) {
			RestVO vo = new RestVO();
			vo.setNo(i);
			vo.setId("itwill"+i);
			vo.setPw("1234"+i);
			vo.setTel("010-1234-123"+i);
			map.put(i, vo);
		}
		return map;
	}
	
	// test8?num=100
	// http://localhost:8080/test8?num=100
	// http://localhost:8080/test8/100 - PathVariable
	// @RequestMapping("/test8")
	@RequestMapping("/test8/{num}")
	public int test8(@PathVariable("num") int num/*@RequestParam("num") int num*/) throws Exception{
		
		return num;
	}	
	
	@RequestMapping(value = "/restPost",method = RequestMethod.POST)
	public void restTest1(@RequestBody RestVO vo) throws Exception{
		// @RequestBody : JSON타입의 데이터를 저장 -> 자동으로 VO객체 저장
		mylog.debug(" restTest1() @@@@@@@@@@@ ");
		mylog.debug(vo+"");
		mylog.debug(" 연결되어있는 view페이지가 없음! (상태정보를 전달)");
		
	}
	
	// http://localhost:8080/status1
		@RequestMapping(value = "/status1")
		public ResponseEntity<Void> testStatus() {

			// return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}

		// http://localhost:8080/status2
		@RequestMapping(value = "/status2")
		public ResponseEntity<RestVO> testStatus2() {
			RestVO vo = new RestVO();
			vo.setId("itwill");
			vo.setNo(200);
			vo.setPw("1234");
			vo.setTel("010-1234-1234");
			// 서비스 정보 받아옴
			
			if(vo == null) {
				return new ResponseEntity<RestVO>(vo,HttpStatus.NOT_FOUND);
			}else {
				return new ResponseEntity<RestVO>(vo,HttpStatus.OK);	
			}
	
		}

	}


















