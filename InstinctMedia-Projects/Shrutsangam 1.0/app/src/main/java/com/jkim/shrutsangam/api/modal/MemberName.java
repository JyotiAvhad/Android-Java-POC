package com.jkim.shrutsangam.api.modal;

public class MemberName {
    public String memName;

    public MemberName(String memName) {
        this.memName = memName;
    }

    @Override
    public String toString() {
        return memName;
    }
}

