package com.nomad.printboard.exceptions;

import lombok.Getter;

@Getter
public enum ErrorCodes {

    GENERAL(10, "알 수 없는 문제"),
    MODEL_ITEM_NOT_FOUND(21, "항목을 찾을 수 없습니다."),
    SECURITY_USER_NOT_FOUND(31, "사용자가 존재하지 않습니다.");

    private int code;
    private String description;

    ErrorCodes(int code, String description) {
        this.code = code;
        this.description = description;
    }
}
