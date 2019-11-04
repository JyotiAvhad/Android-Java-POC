package com.jkim.shrutsangam.api.modal;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


import java.util.List;

public class BookIssueListResponse {
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
        @SerializedName("book_id")
        @Expose
        private String book_id;
        @SerializedName("IssueId")
        @Expose
        private String IssueId;
        @SerializedName("book_no")
        @Expose
        private String book_no;
        @SerializedName("book_issueno")
        @Expose
        private String book_issueno;
        @SerializedName("bhandar_id")
        @Expose
        private String bhandar_id;
        @SerializedName("OverDueDay")
        @Expose
        private String OverDueDay;
        @SerializedName("Remark")
        @Expose
        private String Remark;
        @SerializedName("mberId")
        @Expose
        private String mberId;
        @SerializedName("MemberCode")
        @Expose
        private String MemberCode;
        @SerializedName("MemberName")
        @Expose
        private String MemberName;
        @SerializedName("MemberEmail")
        @Expose
        private String MemberEmail;
        @SerializedName("MemberMobile")
        @Expose
        private String MemberMobile;
        @SerializedName("IssueDate")
        @Expose
        private String IssueDate;
        @SerializedName("ReceiveDate")
        @Expose
        private String ReceiveDate;
        @SerializedName("status")
        @Expose
        private String status;
        @SerializedName("bookinfo_id")
        @Expose
        private String bookinfo_id;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("Remarks")
        @Expose
        private String Remarks;
        @SerializedName("bookImage")
        @Expose
        private String bookImage;

        public String getBook_id() {
            return book_id;
        }

        public void setBook_id(String book_id) {
            this.book_id = book_id;
        }

        public String getIssueId() {
            return IssueId;
        }

        public void setIssueId(String issueId) {
            IssueId = issueId;
        }

        public String getBook_no() {
            return book_no;
        }

        public void setBook_no(String book_no) {
            this.book_no = book_no;
        }

        public String getBook_issueno() {
            return book_issueno;
        }

        public void setBook_issueno(String book_issueno) {
            this.book_issueno = book_issueno;
        }

        public String getBhandar_id() {
            return bhandar_id;
        }

        public void setBhandar_id(String bhandar_id) {
            this.bhandar_id = bhandar_id;
        }

        public String getOverDueDay() {
            return OverDueDay;
        }

        public void setOverDueDay(String overDueDay) {
            OverDueDay = overDueDay;
        }

        public String getRemark() {
            return Remark;
        }

        public void setRemark(String remark) {
            Remark = remark;
        }

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

        public String getMemberEmail() {
            return MemberEmail;
        }

        public void setMemberEmail(String memberEmail) {
            MemberEmail = memberEmail;
        }

        public String getMemberMobile() {
            return MemberMobile;
        }

        public void setMemberMobile(String memberMobile) {
            MemberMobile = memberMobile;
        }

        public String getIssueDate() {
            return IssueDate;
        }

        public void setIssueDate(String issueDate) {
            IssueDate = issueDate;
        }

        public String getReceiveDate() {
            return ReceiveDate;
        }

        public void setReceiveDate(String receiveDate) {
            ReceiveDate = receiveDate;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getBookinfo_id() {
            return bookinfo_id;
        }

        public void setBookinfo_id(String bookinfo_id) {
            this.bookinfo_id = bookinfo_id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getRemarks() {
            return Remarks;
        }

        public void setRemarks(String remarks) {
            Remarks = remarks;
        }

        public String getBookImage() {
            return bookImage;
        }

        public void setBookImage(String bookImage) {
            this.bookImage = bookImage;
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
