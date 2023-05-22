package com.java4.popcorn.api.account.kakao;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class KakaoTokenInfoResponse {
    long id;
    int expires_in;//sec;;
    int appId;
}
