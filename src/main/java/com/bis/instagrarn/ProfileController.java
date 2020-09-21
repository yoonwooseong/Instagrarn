package com.bis.instagrarn;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import common.Common;
import service.ProfileService;
import vo.ProfileVO;
import vo.UserVO;

@Controller
public class ProfileController {
	
	@Autowired //Spring으로 부터 application정보를 자동으로 받는다
	ServletContext application;
	
	@Autowired
	ProfileService profileService;
	
	@Autowired
	HttpServletRequest request;
	
	@RequestMapping(value = "/addpost", method = RequestMethod.GET)
	public String home() {

		return Common.Profile.VIEW_PATH + "addpost.jsp";
	}
	
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public String main(ProfileVO vo, HttpSession session) throws IOException {

		Cookie[] cookies = request.getCookies();
		int user_idx = 0;

		if(cookies == null) {
			return Common.User.VIEW_PATH + "login.jsp";
		}else {
			for (Cookie cookie : cookies) {
				if("rememberSession".equals(cookie.getName())) {

					session = request.getSession();
					UserVO session_info = (UserVO)session.getAttribute(cookie.getValue());
					
					user_idx = session_info.getIdx();
					vo.setUser_idx(user_idx);
				}
			}
		}
		
		String webPath = "/resources/post/"; //절대경로
		String savePath = application.getRealPath(webPath);
		MultipartFile photo = vo.getFile();

		byte[] data = photo.getBytes();//여러개일경우 대비 아직 구현안함

		String filename = "no_file";

		if(!photo.isEmpty()) {
			filename = photo.getOriginalFilename(); //파일 이름 저장
			File saveFile = new File(savePath, filename);

			if(!saveFile.exists()) {
				saveFile.mkdirs(); 
			}else {
				long time = System.currentTimeMillis();
				filename = String.format("%d_%s", time, filename);
				saveFile = new File(savePath, filename);
			}

			try {

				photo.transferTo(saveFile);
				vo.setImg(filename);
				
				int result = profileService.upload(vo);
				System.out.println(result);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "redirect:profile";
	}
	
	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public String profile(Model model, HttpSession session) {
		
		Cookie[] cookies = request.getCookies();
		int user_idx = 0;
		String user_id = "";
		String user_fullname="";
		if(cookies == null) {
			return Common.User.VIEW_PATH + "login.jsp";
		}else {
			for (Cookie cookie : cookies) {
				if("rememberSession".equals(cookie.getName())) {

					session = request.getSession();
					UserVO session_info = (UserVO)session.getAttribute(cookie.getValue());
					
					user_idx = session_info.getIdx();
					user_id = session_info.getId();
					user_fullname = session_info.getFullname();
				}
			}
		}

		List<ProfileVO> list = profileService.select(user_idx);
		
		model.addAttribute("post_num", list.size());
		model.addAttribute("list", list);
		model.addAttribute("user_id", user_id);
		model.addAttribute("user_fullname", user_fullname);
		
		return Common.Profile.VIEW_PATH + "profile.jsp";
	}
	
}
