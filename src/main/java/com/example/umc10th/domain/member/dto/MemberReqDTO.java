package com.example.umc10th.domain.member.dto;

import lombok.Getter;

public class MemberReqDTO {
    //public static class 방법
    @Getter
    public static class RequestBodyClass{
        private String id;
        private String name;
        private Long pwd;
    }


}
