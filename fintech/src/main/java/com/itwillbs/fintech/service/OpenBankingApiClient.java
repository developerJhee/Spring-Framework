package com.itwillbs.fintech.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.itwillbs.fintech.vo.AccountSearchRequestVO;
import com.itwillbs.fintech.vo.AccountSearchResponseVO;
import com.itwillbs.fintech.vo.RequestTokenVO;
import com.itwillbs.fintech.vo.ResponseTokenVO;
import com.itwillbs.fintech.vo.UserInfoRequestVO;
import com.itwillbs.fintech.vo.UserInfoResponseVO;

@Service
public class OpenBankingApiClient {
	private String client_id="22408c9f-6f62-4fca-aed8-f0d70d015a19";
	private String client_secret="01080e02-38c6-4335-8a74-06cd33465320";
	private String redirect_uri="http://localhost:8080/fintech/callback";
	private String grant_type="authorization_code";
	//http 헤더 정보 관리 클래스
	private HttpHeaders httpHeaders;
	// REST방식 API요청
	private RestTemplate restTemplate;
	
	//헤더에 토큰 추가 메서드 정의
		public HttpHeaders setHeaderAccessToken(String access_token) {
			httpHeaders.add("Authorization", "Bearer "+access_token);
			return httpHeaders;
		}
	
	//토큰발급 요청 메서드
	public ResponseTokenVO requestToken(RequestTokenVO requestTokenVO) {
//		HTTP URL https://testapi.openbanking.or.kr/oauth/2.0/token
//	 	HTTP Method POST
//		Content-Type application/x-www-form-urlencoded; charset=UTF-8
		
		// http header 지정
		httpHeaders=new HttpHeaders();
		httpHeaders.add("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
		
		// requestTokenVO code => 저장
		requestTokenVO.setClient_id(client_id);
		requestTokenVO.setClient_secret(client_secret);
		requestTokenVO.setRedirect_uri(redirect_uri);
		requestTokenVO.setGrant_type(grant_type);
		// application/x-www-form-urlencoded; charset=UTF-8 객체저장 불가능
		// 파라미터 저장 해서 들고감
		MultiValueMap<String, String> parameters
		=new LinkedMultiValueMap<String, String>();
		parameters.add("code", requestTokenVO.getCode());
		parameters.add("client_id", requestTokenVO.getClient_id());
		parameters.add("client_secret", requestTokenVO.getClient_secret());
		parameters.add("redirect_uri", requestTokenVO.getRedirect_uri());
		parameters.add("grant_type", requestTokenVO.getGrant_type());
		
//		httpHeaders,parameters 담아서 감 =>HttpEntity
		HttpEntity<MultiValueMap<String, String>> param=
new HttpEntity<MultiValueMap<String,String>>(parameters,httpHeaders);
		
		String requestUrl="https://testapi.openbanking.or.kr/oauth/2.0/token";
			
		restTemplate=new RestTemplate();
		
		return restTemplate.exchange(requestUrl, 
				HttpMethod.POST,param,ResponseTokenVO.class).getBody();
	}
	
	
	// 위에는 POST방식인데 POST방식할 때는 위에처럼
	// GET방식으로 할 때는 아래처럼하기
	
	
	//사용자 정보 조회
		public UserInfoResponseVO findUser(UserInfoRequestVO userInfoRequestVO) {
			// http header 지정
			httpHeaders=new HttpHeaders();
			restTemplate=new RestTemplate();
			
			String requestUrl="https://testapi.openbanking.or.kr/v2.0/user/me"; // p.44
		
			
//			httpHeaders(token) 담아서 감 =>HttpEntity
			HttpEntity<String> param=
	new HttpEntity<String>(setHeaderAccessToken(userInfoRequestVO.getAccess_token()));
			
			// user_seq_no 파라미터를 UriComponentsdp 담아서감
			// https://testapi.openbanking.or.kr/v2.0/user/me?user_seq_no=11111
			UriComponents uriBuilder= UriComponentsBuilder.fromHttpUrl(requestUrl)
					.queryParam("user_seq_no", userInfoRequestVO.getUser_seq_no())
					.build();
			
			
			return restTemplate.exchange(uriBuilder.toString(), 
					HttpMethod.GET,param,UserInfoResponseVO.class).getBody();
		}
	
	
	//등록계좌 조회
		public AccountSearchResponseVO findAccount(AccountSearchRequestVO accountSearchRequestVO) {
			// http header 지정
			httpHeaders=new HttpHeaders();
			restTemplate=new RestTemplate();
			
			String requestUrl="https://testapi.openbanking.or.kr/v2.0/account/list"; // p.48
		
			
//			httpHeaders(token) 담아서 감 =>HttpEntity
			HttpEntity<String> param=
	new HttpEntity<String>(setHeaderAccessToken(accountSearchRequestVO.getAccess_token())); // 토큰은 보이면 안됨
			
			// user_seq_no,  include_cancel_yn, sort_order 파라미터를 UriComponents에 담아서 감
			// https://testapi.openbanking.or.kr/v2.0/account/list?user_seq_no=11111
			UriComponents uriBuilder= UriComponentsBuilder.fromHttpUrl(requestUrl)
					.queryParam("user_seq_no", accountSearchRequestVO.getUser_seq_no())
					.queryParam("include_cancel_yn", accountSearchRequestVO.getInclude_cancel_yn())
					.queryParam("sort_order", accountSearchRequestVO.getSort_order())
					.build();
			
			
			return restTemplate.exchange(uriBuilder.toString(), 
					HttpMethod.GET,param,AccountSearchResponseVO.class).getBody();
		}
}
