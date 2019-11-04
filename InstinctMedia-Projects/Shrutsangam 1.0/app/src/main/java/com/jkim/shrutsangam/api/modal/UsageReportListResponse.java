package com.jkim.shrutsangam.api.modal;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UsageReportListResponse {
    @SerializedName("status")
    private String status;
    @SerializedName("message")
    private String message;
    @SerializedName("data")
    private List<Datum> data;


    public class Datum {
        @SerializedName("listdata")
        public List<listDataResponse> listdata;

        public List<listDataResponse> getListdata() {
            return listdata;
        }

        public void setListdata(List<listDataResponse> listdata) {
            this.listdata = listdata;
        }
    }

    public class listDataResponse {
        @SerializedName("mberId")
        private String mberId;
        @SerializedName("MemberCode")
        private String MemberCode;
        @SerializedName("MemberName")
        private String MemberName;
        @SerializedName("MemberMobile")
        private String MemberMobile;
        @SerializedName("MemberEmail")
        private String MemberEmail;
        @SerializedName("Issuebooks")
        private String Issuebooks;
        @SerializedName("Receivebooks")
        private String Receivebooks;

        public String getMberId() {
            return mberId;
        }

        public void setMberId(String mberId) {
            this.mberId = mberId;
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

        public String getMemberEmail() {
            return MemberEmail;
        }

        public void setMemberEmail(String memberEmail) {
            MemberEmail = memberEmail;
        }

        public String getIssuebooks() {
            return Issuebooks;
        }

        public void setIssuebooks(String issuebooks) {
            Issuebooks = issuebooks;
        }

        public String getReceivebooks() {
            return Receivebooks;
        }

        public void setReceivebooks(String receivebooks) {
            Receivebooks = receivebooks;
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
