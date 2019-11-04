package com.jkim.shrutsangam.api.modal;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AdminGetMemberCodeResponse {
    @SerializedName("status")
    private String status;
    @SerializedName("message")
    private String message;
    @SerializedName("data")
    private List<Datum> data;

    public class Datum {
        @SerializedName("MemberCode")
        private String MemberCode;
        @SerializedName("MemberName")
        private String MemberName;
        @SerializedName("MemberMobile")
        private String MemberMobile;
        @SerializedName("MemberId")
        private String MemberId;

        public String getMemberId() {
            return MemberId;
        }

        public void setMemberId(String memberId) {
            MemberId = memberId;
        }

        public String getMemberCode() {
            return MemberCode;
        }

        public void setMemberCode(String memberCode) {
            MemberCode = memberCode;
        }

        public String getMemberName() {
            return MemberName;
        }

        public void setMemberName(String memberName) {
            MemberName = memberName;
        }

        public String getMemberMobile() {
            return MemberMobile;
        }

        public void setMemberMobile(String memberMobile) {
            MemberMobile = memberMobile;
        }
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }
}
