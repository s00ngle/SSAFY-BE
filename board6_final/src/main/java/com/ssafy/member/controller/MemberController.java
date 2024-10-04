package com.ssafy.member.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.member.model.MemberDto;
import com.ssafy.member.model.service.MemberService;
import com.ssafy.member.model.service.MemberServiceImpl;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/user")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private MemberService memberService;
	
	public void init() {
		memberService = MemberServiceImpl.getMemberService();
	}
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		
		String path = "";
		if("mvjoin".equals(action)) {
			path = "/user/join.jsp";
			redirect(request, response, path);
		} else if("idcheck".equals(action)) {
			String checkid = request.getParameter("checkid");
			System.out.println("checkid >>>>> " + checkid);
			int cnt = 1;
			try {
				cnt = memberService.idCheck(checkid);
			} catch (Exception e) {
				e.printStackTrace();
				cnt = 1;
			}

			// TODO : 위 결과를 클라이언트로 전송.
			// CSV
//			response.setContentType("text/plain;charset=utf-8");
//			PrintWriter out = response.getWriter();
//			out.print(checkid + "," + cnt);
			
			// JSON
			ObjectMapper mapper = new ObjectMapper();
			Map<String, String> map = new HashMap();
			map.put("checkid", checkid);
			map.put("cnt", cnt + "");
			mapper.writeValue(response.getWriter(), map);
			
		} else if("join".equals(action)) {
			path = join(request, response);
			redirect(request, response, path);
		} else if("mvlogin".equals(action)) {
			path = "/user/login.jsp";
			redirect(request, response, path);
		} else if("login".equals(action)) {
			path = login(request, response);
			forward(request, response, path);
		} else if("logout".equals(action)) {
			path = logout(request, response);
			redirect(request, response, path);
		} else {
			redirect(request, response, path);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		doGet(request, response);
	}
	
	private void forward(HttpServletRequest request, HttpServletResponse response, String path)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher(path);
		dispatcher.forward(request, response);
	}

	private void redirect(HttpServletRequest request, HttpServletResponse response, String path) throws IOException {
		response.sendRedirect(request.getContextPath() + path);
	}
	
	private int idcheck(HttpServletRequest request, HttpServletResponse response) {
		// TODO : 입력한 아이디의 사용여부 체크 (0 : 사용 X, 1 : 사용 O)
		String userId = request.getParameter("userid");
		try {
			return memberService.idCheck(userId);
		} catch (Exception e) {
			e.printStackTrace();
			return 500;
		}
	}
	
	private String join(HttpServletRequest request, HttpServletResponse response) {
		// TODO : 이름, 아이디, 비밀번호, 이메일등 회원정보를 받아 MemberDto로 setting.
		// TODO : 위의 데이터를 이용하여 service의 joinMember() 호출.
		// TODO : 정상 등록 후 로그인 페이지로 이동.
		MemberDto memberDto = new MemberDto();
		memberDto.setUserName(request.getParameter("username"));
		memberDto.setUserId(request.getParameter("userid"));
		memberDto.setUserPwd(request.getParameter("userpwd"));
		memberDto.setEmailId(request.getParameter("emailid"));
		memberDto.setEmailDomain(request.getParameter("emaildomain"));
		try {
			memberService.joinMember(memberDto);
			return "/index.jsp";
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "회원가입 중 에러 발생!!!");
			return "/error/error.jsp";
		}
	}
	
	private String login(HttpServletRequest request, HttpServletResponse response) {
		String userId = request.getParameter("userid");
		String userPwd = request.getParameter("userpwd");
		try {
			MemberDto memberDto = memberService.loginMember(userId, userPwd);
			if(memberDto != null) {
//				session 설정
				HttpSession session = request.getSession();
				session.setAttribute("userinfo", memberDto);
				
//				cookie 설정
				String idsave = request.getParameter("saveid");
				if("ok".equals(idsave)) { //아이디 저장을 체크 했다면.
					Cookie cookie = new Cookie("ssafy_id", userId);
					cookie.setPath(request.getContextPath());
//					크롬의 경우 400일이 최대
//					https://developer.chrome.com/blog/cookie-max-age-expires/
					cookie.setMaxAge(60 * 60 * 24 * 365 * 40); //40년간 저장.
					response.addCookie(cookie);
				} else { //아이디 저장을 해제 했다면.
					Cookie cookies[] = request.getCookies();
					if(cookies != null) {
						for(Cookie cookie : cookies) {
							if("ssafy_id".equals(cookie.getName())) {
								cookie.setMaxAge(0);
								response.addCookie(cookie);
								break;
							}
						}
					}
				}
				return "/index.jsp";
			} else {
				request.setAttribute("msg", "아이디 또는 비밀번호 확인 후 다시 로그인하세요.");
				return "/user/login.jsp";
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "로그인 중 에러 발생!!!");
			return "/error/error.jsp";
		}
	}
	
	private String logout(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
//		session.removeAttribute("userinfo");
		session.invalidate();
		return "";
	}

}
