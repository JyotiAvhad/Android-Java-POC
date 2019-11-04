package com.jkim.shrutsangam.api;

import com.jkim.shrutsangam.api.modal.AddMemberFormResponse;
import com.jkim.shrutsangam.api.modal.AdminDashboardResponse;
import com.jkim.shrutsangam.api.modal.AdminGetBookNoResponse;
import com.jkim.shrutsangam.api.modal.AdminGetMemberCodeResponse;
import com.jkim.shrutsangam.api.modal.AdminLoginResponse;
import com.jkim.shrutsangam.api.modal.AdminMemberListResponse;
import com.jkim.shrutsangam.api.modal.BookIssueFormResponse;
import com.jkim.shrutsangam.api.modal.BookIssueListResponse;
import com.jkim.shrutsangam.api.modal.BookListResponse;
import com.jkim.shrutsangam.api.modal.BookListSearchResponse;
import com.jkim.shrutsangam.api.modal.BookReceiveListResponse;
import com.jkim.shrutsangam.api.modal.BookReceiveResponse;
import com.jkim.shrutsangam.api.modal.BookUoloadResponse;
import com.jkim.shrutsangam.api.modal.DashBoardResponse;
import com.jkim.shrutsangam.api.modal.ImageDeleteResponse;
import com.jkim.shrutsangam.api.modal.ImageListResponse;
import com.jkim.shrutsangam.api.modal.LoginResponse;
import com.jkim.shrutsangam.api.modal.OutstandingListResponse;
import com.jkim.shrutsangam.api.modal.PendingListResponse;
import com.jkim.shrutsangam.api.modal.SubmitBoolEnquiryFormResponse;
import com.jkim.shrutsangam.api.modal.UsageReportListResponse;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface ApiInterface {
    //region 'Librarian API'
    @FormUrlEncoded
    @POST("login")
    Call<LoginResponse> login(@Field("username") String username,
    @Field("password") String password);


    @FormUrlEncoded
    @POST("dashboard")
    Call<DashBoardResponse> dashBoard(@Field("BhandarId") String BhandarId);

    @Multipart
    @POST("book_image_upload")
    Call<BookUoloadResponse> bookUpload(@Part("user_id") RequestBody user_id,
                                        @Part("title") RequestBody title,
                                        @Part MultipartBody.Part bookImage);



    @FormUrlEncoded
    @POST("book_issue_list")
    Call<BookIssueListResponse> getBookIssueList(@Field("BhandarId") String bhandarId);

    @FormUrlEncoded
    @POST("book_recive_list")
    Call<BookReceiveListResponse> getBookReceiveList(@Field("BhandarId") String bhandarId);

    @FormUrlEncoded
    @POST("book_list")
    Call<BookListResponse> getBookList(@Field("BhandarId") String bhandarId);

    @FormUrlEncoded
    @POST("image_list")
    Call<ImageListResponse> getImageList(@Field("BhandarId") String bhandarId);

    @FormUrlEncoded
    @POST("book_recive")
    Call<BookReceiveResponse> bookReceive(@Field("book_issueid") String book_issueid,
                                          @Field("BhandarId") String BhandarId,
                                          @Field("lib_admin_id") String lib_admin_id);

    @FormUrlEncoded
    @POST("book_image_delete")
    Call<ImageDeleteResponse> imageDelete(@Field("user_id") String user_id,
                                          @Field("image_id") String image_id);

    @FormUrlEncoded
    @POST("book_issue_form")
    Call<BookIssueFormResponse> submitBookIssueForm(@Field("mberId") String mberId,
                                                    @Field("mcode") String mcode,
                                                    @Field("book_id") String book_id,
                                                    @Field("book_no") String book_no,
                                                    @Field("IssueDate") String IssueDate,
                                                    @Field("ReceiveDate") String ReceiveDate,
                                                    @Field("BhandarId") String BhandarId,
                                                    @Field("Remark") String Remark);


    @FormUrlEncoded
    @POST("member_add_form")
    Call<AddMemberFormResponse> submitaddMemberForm(@Field("membercode") String membercode,
                                                    @Field("membername") String membername,
                                                    @Field("memberaddress") String memberaddress,
                                                    @Field("membercity") String membercity,
                                                    @Field("memberstate") String memberstate,
                                                    @Field("memberpincode") String memberpincode,
                                                    @Field("memberphone") String memberphone,
                                                    @Field("membermobile") String membermobile,
                                                    @Field("memberemail") String memberemail,
                                                    @Field("memberdob") String memberdob,
                                                    @Field("memberreferance") String memberreferance,
                                                    @Field("member_category") String member_category,
                                                    @Field("member_join_date") String member_join_date,
                                                    @Field("member_end_date") String member_end_date,
                                                    @Field("member_status") String member_status,
                                                    @Field("BhandarId") String BhandarId);

    @FormUrlEncoded
    @POST("member_list")
    Call<AdminMemberListResponse> getMemberList(@Field("BhandarId") String bhandarId);

    @FormUrlEncoded
    @POST("outstanding")
    Call<OutstandingListResponse> getOutstanding(@Field("BhandarId") String bhandarId);

    @FormUrlEncoded
    @POST("outstanding")
    Call<PendingListResponse> getPending(@Field("BhandarId") String bhandarId);


    @FormUrlEncoded
    @POST("usage_report")
    Call<UsageReportListResponse> getUsageReport(@Field("BhandarId") String bhandarId);

    @FormUrlEncoded
    @POST("book_inquiry_form")
    Call<SubmitBoolEnquiryFormResponse> submitBookEnquiry(@Field("mberId") String mberId,
                                                          @Field("mcode") String mcode,
                                                          @Field("BhandarId") String BhandarId,
                                                          @Field("book_name") String book_name);


    //endregion

    //region 'Admin API'
    @FormUrlEncoded
    @POST("admin_login")
    Call<AdminLoginResponse> adminLogin(@Field("uname") String uname,
                                        @Field("pwd") String pwd);

    @FormUrlEncoded
    @POST("admin_dashboard")
    Call<AdminDashboardResponse> getAdminDashBoard(@Field("admin_id") String admin_id);

    @FormUrlEncoded
    @POST("get_data_member_code")
    Call<AdminGetMemberCodeResponse> getMemberCode(@Field("membercode") String membercode,
                                                   @Field("BhandarId") String BhandarId);

    @FormUrlEncoded
    @POST("get_data_book_no")
    Call<AdminGetBookNoResponse> getBookNo(@Field("book_no") String book_no,
                                           @Field("BhandarId") String BhandarId);

    @FormUrlEncoded
    @POST("book_search")
    Call<BookListResponse> getSearchBook(@Field("search_field") String field,
                                           @Field("search_value") String value,
                                           @Field("BhandarId") String BhandarId);

    @FormUrlEncoded
    @POST("usage_report_filter")
    Call<UsageReportListResponse> getSearchUsesReport(@Field("startdate") String startDate,
                                         @Field("enddate") String endDate,
                                         @Field("BhandarId") String BhandarId);

    //endregion

}