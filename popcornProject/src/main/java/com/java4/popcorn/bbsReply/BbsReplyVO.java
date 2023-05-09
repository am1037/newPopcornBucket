package com.java4.popcorn.bbsReply;

import lombok.Data;

import java.sql.Date;

@Data
public class BbsReplyVO {
    private int bReplyId;
    private Integer bbsId;
    private String bReplyContent;
    private String bReplyWriterId;
    private Date bReplyCreate;
    private Date bReplyUpdate;
    private Date bReplyDelete;
    private Date bReplyIsDeleted;
    private Integer bReplyOrderNum;
}
