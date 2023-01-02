package com.itwillbs.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FileUploadController {
	
	private static final String FILE_PATH="c:\\spring";
	private static final Logger mylog = LoggerFactory.getLogger(FileUploadController.class);
	
	// 파일업로드 뷰
	//http://localhost:8080/upload
	@RequestMapping(value = "/upload", method = RequestMethod.GET)
	public void uploadForm() {
		mylog.debug(" upload.jsp 페이지로 이동");
	}
	
	
	// 파일업로드 처리
		@RequestMapping(value = "/upload",method = RequestMethod.POST)
		public ModelAndView uploadPro(MultipartHttpServletRequest multi) throws Exception {
			
		// 한글처리
		multi.setCharacterEncoding("UTF-8");			
		
		// 전달정보 저장(파일여러개저장 => Map)
		Map map = new HashMap(); // Map을 사용하면 어떠한 형태의 객체든 저장할 수 있다.
		
		// 전달된 파라미터의 이름들을 모두 저장
		Enumeration enu = multi.getParameterNames();
					// ﻿Enumeration: LiST형태로 저장해서 사용할 수 있음 while문에 적합
		mylog.debug(enu+"");
		while(enu.hasMoreElements()) {
			String name = (String) enu.nextElement(); // 다운캐스팅
			//mylog.debug(name);
			String value = multi.getParameter(name);
			map.put(name, value);
		}
		
		mylog.debug(map+"");
		// 파일정보 제외한 파라미터 정보 모두 저장
		////////////////////////////////////////////////////////////
		// 파일정보 처리
		//fileProcess(multi);
		List<String> fileList = fileProcess(multi);
		map.put("fileList", fileList);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("result");
		mav.addObject("map", map); // map객체를 뷰에 전달
		
		return mav;
	} // uploadPro
		
	// 파일업로드 시 전달되는 파일정보 처리 메서드	
		// => Post안에 들어가있어도 상관없으나 복잡해지니까 일단 따로 빼둔다.
	private List<String> fileProcess(MultipartHttpServletRequest multi) throws Exception{
		mylog.debug("업로드 파일 처리 시작");
		
		// 업로드 파일명을 저장하는 리스트
		List<String> fileList = new ArrayList<String>();
		
		
		// 전달받은 파라미터(파일) 정보 저장 => only이름만
		Iterator<String> fileNames
					= multi.getFileNames();
		
		while(fileNames.hasNext()) { // =hasMoreElements() 
							// 커서를 (옮겼을 때)내렸을 때 다음정보가 있으면 그 정보를 들고오자.
			String fileName = fileNames.next();
			//mylog.debug(fileName);
			
			// 전달받은 파일의 정보를 저장(데이터)
			MultipartFile mFile= multi.getFile(fileName);
			// 전달받은 파일의 이름정보를 저장
			String oFileName = mFile.getOriginalFilename();
			
			fileList.add(oFileName);
			
			// 업로드 처리 C:\\spring
			File file = new File(FILE_PATH+"\\"+fileName);
			
			if(mFile.getSize() != 0) { // 업로드된 파일정보가 있으면
				if(!file.exists()) { // 기존에 업로드된 폴더가 존재여부
					if(file.getParentFile().mkdir()) {
						// 부모역할을 하는 폴더 생성 후 
						file.createNewFile();
						// 임시파일을 생성
					}
				}
			}
			
			//file.createNewFile(); => 위에 조건문에 넣어서 이부분은 주석처리
			mFile.transferTo(new File(FILE_PATH+"\\"+oFileName)); // 파라미터 형태로 변환
			// => 임시 저장파일의 정보를 업로드된 실제 파일로 변경
		}
		
		mylog.debug("업로드 파일 처리 끝");
		return fileList;
	}
	
	// 다운로드
	@RequestMapping(value = "/download", method = RequestMethod.GET)
	public void download(@RequestParam("fileName") String fileName, 
						HttpServletResponse response) throws Exception {
		
		// 파일 출력 통로를 생성
		OutputStream out = response.getOutputStream();
		
		// 다운로드 파일의 위치 (== 업로드 파일의 위치)
		String downFile = FILE_PATH + "\\" + fileName;
		mylog.debug(downFile);
		
		// 다운로드
		File file = new File(downFile);
		
		response.setHeader("Cache-control", "no-cache");
		response.addHeader("Content-disposition", "attachment; fileName="+fileName);
		// -> 모든 파일이 다운로드 형태로 처리
		
		FileInputStream fis = new FileInputStream(file);
		byte[] buffer = new byte[1024*8]; // 배열생성, 8KB
		
		
		while(true) {
			int count = fis.read(buffer);
			if(count == -1) break; // 파일의 끝
			
			out.write(buffer, 0, count);
		}
		
		out.flush(); // 버퍼의 빈공간을 공백을 채우기
		
		fis.close();
		out.close();
		
//		int count = 0;
//		while( (count = fis.read(buffer)) != -1 ) {
//			out.write(buffer, 0, count);
//		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
