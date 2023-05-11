package com.java4.popcorn.notmine.bbs;

import lombok.Data;

import java.sql.Date;

@Data
public class BbsVO {
    private int bbs_Id;
    private String bbs_Title;
    private String bbs_Content;
    private String bbs_WriterId;
    private Date bbs_Create;
    private Date bbs_Update;
    private Date bbs_Delete;
    private String bbs_IsDeleted;
    private int bbs_ViewCnt;
    private int bbs_CateNum;
}
