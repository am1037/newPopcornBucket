package com.java4.popcorn.rankUser;

import lombok.Data;

@Data
public class RankUserVO {
    private String memberId;
    private String memberName;
    private Integer memberLike;
    private Integer memberHits;
    private Integer memberPopularity;
}
