package com.bis.instagrarn;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import common.Common;
import service.ProfileService;
import service.UserService;
import vo.ProfileVO;
import vo.UserVO;

@Controller
public class HomeController {
	
	@Autowired //Spring으로 부터 application정보를 자동으로 받는다
	ServletContext application;
	
	@Autowired
	UserService userService;
	
	@Autowired
	ProfileService profileService;
	
	@Autowired
	HttpServletRequest request;
	
	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public String main(Model model) {
		int user_idx = 1;
		List<ProfileVO> list = profileService.select_post(user_idx, 0);
		model.addAttribute("loadlist", list);
		return Common.Board.VIEW_PATH + "main.jsp";
	}
	
	@RequestMapping(value = "/loadpost", method = RequestMethod.GET)
	@ResponseBody
	public List<ProfileVO> loadpost(Model model, @RequestParam(value="page", defaultValue="1")int page) {
		int user_idx = 1;
		List<ProfileVO> list = profileService.select_post(user_idx, page);
		
		return list;
	}
	
	@RequestMapping(value = "/clickLike", method = RequestMethod.GET)
	@ResponseBody
	public int clickLike(Model model, int board_idx) {
		int res = profileService.clicked_like(board_idx);
		return res;
	}
	
	@RequestMapping(value = "/clickUnLike", method = RequestMethod.GET)
	@ResponseBody
	public int clickUnLike(Model model, int board_idx) {
		int res = profileService.unclicked_like(board_idx);
		return res;
	}
	
	@RequestMapping(value = {"/", "/loginpage"})
	public String loginpage() {
		
		return Common.User.VIEW_PATH + "login.jsp";
	}
	
	@RequestMapping("/first")
	@ResponseBody
	public String first() {
		Cookie[] cookies = request.getCookies();
		String user_id_info = "";
		if(cookies == null) {
			System.out.println("로그인 된 정보 없음");
		}else {
			for (Cookie cookie : cookies) {
				if("rememberSession".equals(cookie.getName())) {
					System.out.println("쿠키이름 : " +cookie.getName() + "값 : "+ cookie.getValue());
					HttpSession session = request.getSession();
					UserVO session_info = (UserVO) session.getAttribute(cookie.getValue());
					
					user_id_info = session_info.getId();
					
				}
			}
		}
		System.out.println(user_id_info);
		return user_id_info;
	}
	
	@RequestMapping(value = "/login")
	public String login(UserVO vo, HttpServletResponse response) {
		
		UserVO login_vo = userService.signin(vo);
		if( login_vo != null ) {
			int idx = login_vo.getIdx();
			String fullname = login_vo.getFullname();
			String id = login_vo.getId();
			System.out.println(fullname+"님 로그인 성공");
			
			HttpSession session = request.getSession();
			Common com = new Common();
			
			String sessionKey = com.sessonKey();
			
			session.setAttribute(sessionKey, login_vo);
			System.out.println("session : " + session.getAttribute(sessionKey));
			
			Cookie cookie= new Cookie("rememberSession", sessionKey);
			cookie.setPath("/");
			cookie.setMaxAge(60*60*24*7);//일주일간 가게
			response.addCookie(cookie);
			System.out.println("cookie : " + cookie.getValue());
			
		} else {
			System.out.println("로그인 실패");
		}
		
		return "redirect:main";
	}
	
	@RequestMapping(value="/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("id");
		System.out.println(session.getAttribute("id"));
		
		return Common.User.VIEW_PATH + "login.jsp";
	}
	
	@RequestMapping(value =  "/signuppage")
	public String signuppage() {
		return Common.User.VIEW_PATH + "signup.jsp";
	}
	
	@RequestMapping(value = "/signup")
	public String signup(UserVO vo) {
		System.out.println(vo.getFullname());
		int res = userService.signup(vo);
		return Common.Board.VIEW_PATH + "main.jsp";
	}
	
}
