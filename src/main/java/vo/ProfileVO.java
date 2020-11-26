package vo;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class ProfileVO {
	MultipartFile file;
	private int board_idx, user_idx, like_num, reply_num;
	private String img, content, area, id;
	private Boolean isLike;
	private List replys;
	
	public ProfileVO() {
		
	}
	
	
	public ProfileVO(int board_idx, int user_idx, String id, String img, String content, String area, int like_num, int reply_num) {
		this.board_idx = board_idx;
		this.user_idx = user_idx;
		this.id = id;
		this.img = img;
		this.content = content;
		this.area = area;
		this.like_num = like_num;
		this.reply_num = reply_num;
	}
	
	
	public int getReply_num() {
		return reply_num;
	}


	public void setReply_num(int reply_num) {
		this.reply_num = reply_num;
	}


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List getReplys() {
		return replys;
	}

	public void setReplys(List replys) {
		this.replys = replys;
	}


	public Boolean getIsLike() {
		return isLike;
	}
	
	public void setIsLike(Boolean isLike) {
		this.isLike = isLike;
	}

	public int getLike_num() {
		return like_num;
	}

	public void setLike_num(int like_num) {
		this.like_num = like_num;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	public int getBoard_idx() {
		return board_idx;
	}

	public void setBoard_idx(int board_idx) {
		this.board_idx = board_idx;
	}

	public int getUser_idx() {
		return user_idx;
	}

	public void setUser_idx(int user_idx) {
		this.user_idx = user_idx;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}
	
}
