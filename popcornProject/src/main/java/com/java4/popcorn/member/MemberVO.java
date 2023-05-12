package com.java4.popcorn.member;

import lombok.Data;

@Data
public class MemberVO {
	private String member_id;
	private String member_pw;
	private String member_name;
	private String member_knickname;
	private String member_tel;
	private String member_email;
	private Integer member_level;
	private Integer member_fav;
	private Integer member_view;
	private String member_img;
	
}
