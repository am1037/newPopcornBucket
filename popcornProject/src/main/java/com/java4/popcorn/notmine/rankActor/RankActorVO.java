package com.java4.popcorn.notmine.rankActor;

import lombok.Data;

@Data
public class RankActorVO {
    private int actorId;
    private String actorName;
    private Integer actorLike;
    private Integer actorHits;
    private Integer actorPopularity;
}
