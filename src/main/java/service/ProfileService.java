package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.ProfileDAO;
import vo.ProfileVO;

@Service("profileService")
public class ProfileService {
	@Autowired
	ProfileDAO profile_dao;

	public int upload(ProfileVO vo) {
		int res = profile_dao.insert(vo);
		return res;
	}

	public List<ProfileVO> select(int user_idx) { 
		List<ProfileVO> list = profile_dao.select(user_idx);
		return list;
	}
	
	public List<ProfileVO> select_post(int user_idx, int page) {
		List<ProfileVO> list = profile_dao.select_post(user_idx, page);
		return list;
	}
	
	public List<Integer> select_like(int user_idx) {
		List<Integer> list = profile_dao.select_like(user_idx);
		return list;
	}
	
	public int clicked_like(int board_idx, int user_idx) {
		int res = profile_dao.clicked_like(board_idx);
		int res2 = profile_dao.clicked_like_DB(board_idx, user_idx);
		//여기에 알람DB에 추가
		return res;
	}
	
	public int unclicked_like(int board_idx, int user_idx) {
		int res = profile_dao.unclicked_like(board_idx);
		int res2 = profile_dao.clicked_unlike_DB(board_idx, user_idx);
		return res;
	}

	

}
