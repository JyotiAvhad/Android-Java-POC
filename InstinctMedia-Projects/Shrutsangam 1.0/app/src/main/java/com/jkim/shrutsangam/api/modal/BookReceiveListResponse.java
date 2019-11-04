package com.jkim.shrutsangam.api.modal;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BookReceiveListResponse {
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
        private String book_id;
        @SerializedName("book_issueno")
        private String book_issueno;
        @SerializedName("IssueId")
        private String IssueId;
        @SerializedName("book_no")
        private String book_no;
        @SerializedName("bhandar_id")
        private String bhandar_id;
        @SerializedName("Language")
        private String Language;
        @SerializedName("mberId")
        private String mberId;
        @SerializedName("MemberCode")
        private String MemberCode;
        @SerializedName("MemberName")
        private String MemberName;
        @SerializedName("MemberEmail")
        private String MemberEmail;
        @SerializedName("MemberMobile")
        private String MemberMobile;
        @SerializedName("IssueDate")
        private String IssueDate;
        @SerializedName("ReceiveDate")
        private String ReceiveDate;
        @SerializedName("condition")
        private String condition;
        @SerializedName("BookPlacementCupb")
        private String BookPlacementCupb;
        @SerializedName("BookPlacementshelf")
        private String BookPlacementshelf;
        @SerializedName("status")
        private String status;
        @SerializedName("bookinfo_id")
        private String bookinfo_id;
        @SerializedName("name")
        private String name;
        @SerializedName("BookTopic")
        private String BookTopic;
        @SerializedName("Remarks")
        private String Remarks;
        @SerializedName("bookImage")
        private String bookImage;
        @SerializedName("InsertedBy")
        private String InsertedBy;
        @SerializedName("InsertedDate")
        private String InsertedDate;
        @SerializedName("UpdatedBy")
        private String UpdatedBy;
        @SerializedName("UpdatedDate")
        private String UpdatedDate;

        public String getBook_id() {
            return book_id;
        }

        public void setBook_id(String book_id) {
            this.book_id = book_id;
        }

        public String getIssueId() {
            return IssueId;
        }

        public String getBook_issueno() {
            return book_issueno;
        }

        public void setBook_issueno(String book_issueno) {
            this.book_issueno = book_issueno;
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

        public String getBhandar_id() {
            return bhandar_id;
        }

        public void setBhandar_id(String bhandar_id) {
            this.bhandar_id = bhandar_id;
        }

        public String getLanguage() {
            return Language;
        }

        public void setLanguage(String language) {
            Language = language;
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

        public String getCondition() {
            return condition;
        }

        public void setCondition(String condition) {
            this.condition = condition;
        }

        public String getBookPlacementCupb() {
            return BookPlacementCupb;
        }

        public void setBookPlacementCupb(String bookPlacementCupb) {
            BookPlacementCupb = bookPlacementCupb;
        }

        public String getBookPlacementshelf() {
            return BookPlacementshelf;
        }

        public void setBookPlacementshelf(String bookPlacementshelf) {
            BookPlacementshelf = bookPlacementshelf;
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

        public String getBookTopic() {
            return BookTopic;
        }

        public void setBookTopic(String bookTopic) {
            BookTopic = bookTopic;
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

        public String getInsertedBy() {
            return InsertedBy;
        }

        public void setInsertedBy(String insertedBy) {
            InsertedBy = insertedBy;
        }

        public String getInsertedDate() {
            return InsertedDate;
        }

        public void setInsertedDate(String insertedDate) {
            InsertedDate = insertedDate;
        }

        public String getUpdatedBy() {
            return UpdatedBy;
        }

        public void setUpdatedBy(String updatedBy) {
            UpdatedBy = updatedBy;
        }

        public String getUpdatedDate() {
            return UpdatedDate;
        }

        public void setUpdatedDate(String updatedDate) {
            UpdatedDate = updatedDate;
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
