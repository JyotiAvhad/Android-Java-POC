package com.jkim.shrutsangam.api.modal;

public class MemberCode {

    public String memCode;

    public MemberCode(String memCode) {
        this.memCode = memCode;
    }

    @Override
    public String toString() {
        return memCode;
    }

}
