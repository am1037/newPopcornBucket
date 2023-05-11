package com.java4.popcorn.notmine.userSimilarity;

import lombok.Data;

@Data
public class UserSimilarityVO {
    private String userId;
    private String memberId;
    private Integer similarity;
}
