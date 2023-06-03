package com.java4.popcorn.controllers.alarm.subclasses;

import lombok.Data;

@Data
public class AlarmVO {
    String movie; //이 영화를 알립니다
    Integer num; //이 숫자도 알립니다
    String lineId; //이 아이디로 알립니다
}
