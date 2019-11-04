package com.jkim.shrutsangam.api.modal;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BookListResponse {
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
        @SerializedName("id")
        private String id;
        @SerializedName("bhandar_id")
        private String bhandar_id;
        @SerializedName("Language")
        private String Language;
        @SerializedName("book_id")
        private String book_id;
        @SerializedName("condition")
        private String condition;
        @SerializedName("book_no")
        public String book_no;
        @SerializedName("BookPlacementCupb")
        private String BookPlacementCupb;
        @SerializedName("BookPlacementshelf")
        private String BookPlacementshelf;
        @SerializedName("status")
        private String status;
        @SerializedName("bookinfo_id")
        private String bookinfo_id;
        @SerializedName("name")
        public String name;
        @SerializedName("AliasName")
        private String AliasName;
        @SerializedName("BookTopic")
        private String BookTopic;
        @SerializedName("Vol")
        private String Vol;
        @SerializedName("SubVol")
        private String SubVol;
        @SerializedName("Edition")
        private String Edition;
        @SerializedName("Price")
        private String Price;
        @SerializedName("Pages")
        private String Pages;
        @SerializedName("Remarks")
        private String Remarks;
        @SerializedName("bookImage")
        private String bookImage;
        @SerializedName("ISDNNum")
        private String ISDNNum;
        @SerializedName("InsertedBy")
        private String InsertedBy;
        @SerializedName("InsertedDate")
        private String InsertedDate;
        @SerializedName("UpdatedBy")
        private String UpdatedBy;
        @SerializedName("UpdatedDate")
        private String UpdatedDate;
        @SerializedName("time")
        private String time;
        @SerializedName("PublisherId")
        private String PublisherId;
        @SerializedName("AuthorId")
        private String AuthorId;
        @SerializedName("EditorId")
        private String EditorId;
        @SerializedName("TranslaterId")
        private String TranslaterId;
        @SerializedName("TikakarId")
        private String TikakarId;
        @SerializedName("Others")
        private String Others;
        @SerializedName("BookType")
        private String BookType;
        @SerializedName("BookCategory")
        private String BookCategory;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
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

        public String getBook_id() {
            return book_id;
        }

        public void setBook_id(String book_id) {
            this.book_id = book_id;
        }

        public String getCondition() {
            return condition;
        }

        public void setCondition(String condition) {
            this.condition = condition;
        }

        public String getBook_no() {
            return book_no;
        }

        public void setBook_no(String book_no) {
            this.book_no = book_no;
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

        public String getAliasName() {
            return AliasName;
        }

        public void setAliasName(String aliasName) {
            AliasName = aliasName;
        }

        public String getBookTopic() {
            return BookTopic;
        }

        public void setBookTopic(String bookTopic) {
            BookTopic = bookTopic;
        }

        public String getVol() {
            return Vol;
        }

        public void setVol(String vol) {
            Vol = vol;
        }

        public String getSubVol() {
            return SubVol;
        }

        public void setSubVol(String subVol) {
            SubVol = subVol;
        }

        public String getEdition() {
            return Edition;
        }

        public void setEdition(String edition) {
            Edition = edition;
        }

        public String getPrice() {
            return Price;
        }

        public void setPrice(String price) {
            Price = price;
        }

        public String getPages() {
            return Pages;
        }

        public void setPages(String pages) {
            Pages = pages;
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

        public String getISDNNum() {
            return ISDNNum;
        }

        public void setISDNNum(String ISDNNum) {
            this.ISDNNum = ISDNNum;
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

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getPublisherId() {
            return PublisherId;
        }

        public void setPublisherId(String publisherId) {
            PublisherId = publisherId;
        }

        public String getAuthorId() {
            return AuthorId;
        }

        public void setAuthorId(String authorId) {
            AuthorId = authorId;
        }

        public String getEditorId() {
            return EditorId;
        }

        public void setEditorId(String editorId) {
            EditorId = editorId;
        }

        public String getTranslaterId() {
            return TranslaterId;
        }

        public void setTranslaterId(String translaterId) {
            TranslaterId = translaterId;
        }

        public String getTikakarId() {
            return TikakarId;
        }

        public void setTikakarId(String tikakarId) {
            TikakarId = tikakarId;
        }

        public String getOthers() {
            return Others;
        }

        public void setOthers(String others) {
            Others = others;
        }

        public String getBookType() {
            return BookType;
        }

        public void setBookType(String bookType) {
            BookType = bookType;
        }

        public String getBookCategory() {
            return BookCategory;
        }

        public void setBookCategory(String bookCategory) {
            BookCategory = bookCategory;
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

