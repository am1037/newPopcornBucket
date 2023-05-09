package com.java4.popcorn.bbsComment;

import lombok.Data;

import java.sql.Date;

@Data
public class BbsCommentVO {
    private int bCommentId;
    private String bCommentContent;
    private String bCommentWriterId;
    private Date bCommentCreate;
    private Date bCommentUpdate;
    private Date bCommentDelete;
    private String bCommentIsDeleted;
}
