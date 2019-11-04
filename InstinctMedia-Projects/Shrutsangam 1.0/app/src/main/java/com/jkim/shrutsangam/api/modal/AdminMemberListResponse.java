package com.jkim.shrutsangam.api.modal;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AdminMemberListResponse {
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
        @Expose
        private String mberId;
        @SerializedName("MemberCode")
        @Expose
        private String MemberCode;
        @SerializedName("MemberName")
        @Expose
        private String MemberName;
        @SerializedName("MemberAddress")
        @Expose
        private String MemberAddress;
        @SerializedName("MemberCity")
        @Expose
        private String MemberCity;
        @SerializedName("MemberState")
        @Expose
        private String MemberState;
        @SerializedName("MemberPincode")
        @Expose
        private String MemberPincode;
        @SerializedName("MemberPhone")
        @Expose
        private String MemberPhone;
        @SerializedName("MemberMobile")
        @Expose
        private String MemberMobile;
        @SerializedName("MemberEmail")
        @Expose
        private String MemberEmail;
        @SerializedName("MemberDateofBirth")
        @Expose
        private String MemberDateofBirth;
        @SerializedName("Memberreference")
        @Expose
        private String Memberreference;
        @SerializedName("MemberDateofJoining")
        @Expose
        private String MemberDateofJoining;
        @SerializedName("MemberDateofEnd")
        @Expose
        private String MemberDateofEnd;
        @SerializedName("MemberCategory")
        @Expose
        private String MemberCategory;
        @SerializedName("IsActive")
        @Expose
        private String IsActive;

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

        public String getMemberAddress() {
            return MemberAddress;
        }

        public void setMemberAddress(String memberAddress) {
            MemberAddress = memberAddress;
        }

        public String getMemberCity() {
            return MemberCity;
        }

        public void setMemberCity(String memberCity) {
            MemberCity = memberCity;
        }

        public String getMemberState() {
            return MemberState;
        }

        public void setMemberState(String memberState) {
            MemberState = memberState;
        }

        public String getMemberPincode() {
            return MemberPincode;
        }

        public void setMemberPincode(String memberPincode) {
            MemberPincode = memberPincode;
        }

        public String getMemberPhone() {
            return MemberPhone;
        }

        public void setMemberPhone(String memberPhone) {
            MemberPhone = memberPhone;
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

        public String getMemberDateofBirth() {
            return MemberDateofBirth;
        }

        public void setMemberDateofBirth(String memberDateofBirth) {
            MemberDateofBirth = memberDateofBirth;
        }

        public String getMemberreference() {
            return Memberreference;
        }

        public void setMemberreference(String memberreference) {
            Memberreference = memberreference;
        }

        public String getMemberDateofJoining() {
            return MemberDateofJoining;
        }

        public void setMemberDateofJoining(String memberDateofJoining) {
            MemberDateofJoining = memberDateofJoining;
        }

        public String getMemberDateofEnd() {
            return MemberDateofEnd;
        }

        public void setMemberDateofEnd(String memberDateofEnd) {
            MemberDateofEnd = memberDateofEnd;
        }

        public String getMemberCategory() {
            return MemberCategory;
        }

        public void setMemberCategory(String memberCategory) {
            MemberCategory = memberCategory;
        }

        public String getIsActive() {
            return IsActive;
        }

        public void setIsActive(String isActive) {
            IsActive = isActive;
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
