package com.jkim.shrutsangam.adapter;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.jkim.shrutsangam.R;
import com.jkim.shrutsangam.api.modal.AdminMemberListResponse;
import com.jkim.shrutsangam.api.modal.BookListResponse;

import java.util.List;

public class AdminMemberListAdapter extends RecyclerView.Adapter<AdminMemberListAdapter.AdminMemberListHolder> {
    private List<AdminMemberListResponse.listDataResponse> listDataResponses;
    private Context context;

    public AdminMemberListAdapter(List<AdminMemberListResponse.listDataResponse> listDataResponses, Context context) {
        this.listDataResponses = listDataResponses;
        this.context = context;
    }

    @Override
    public AdminMemberListAdapter.AdminMemberListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new AdminMemberListAdapter.AdminMemberListHolder(LayoutInflater.from(context).inflate(R.layout.row_member_list, parent, false));
    }

    @Override
    public void onBindViewHolder(final AdminMemberListAdapter.AdminMemberListHolder holder, final int position) {

        holder.txtMemberName.setText(Html.fromHtml("<b>Member Name: </b>" + listDataResponses.get(position).getMemberName()));
        holder.txtMemberCode.setText(Html.fromHtml("<b>Code: </b>" + listDataResponses.get(position).getMemberCode()));
        holder.txtMemberCity.setText(Html.fromHtml("<b>City: </b>" + listDataResponses.get(position).getMemberCity()));
        holder.txtMemberCategory.setText(Html.fromHtml("<b>Member Category: </b>" + listDataResponses.get(position).getMemberCategory()));
        holder.txtMemberJoinDate.setText(Html.fromHtml("<b>Join Date: </b>" + listDataResponses.get(position).getMemberDateofJoining()));
        holder.txtMemberEndDate.setText(Html.fromHtml("<b>End Date: </b>" + listDataResponses.get(position).getMemberDateofEnd()));
        holder.txtMobileNo.setText(Html.fromHtml("<b>Mobile Number: </b>" + listDataResponses.get(position).getMemberMobile()));

        if((position%2) != 0){
            holder.cardview.setCardBackgroundColor(context.getResources().getColor(R.color.pink));
            holder.txtMemberName.setTextColor(context.getResources().getColor(R.color.colorWhite));
            holder.txtMemberCode.setTextColor(context.getResources().getColor(R.color.colorWhite));
            holder.txtMemberCity.setTextColor(context.getResources().getColor(R.color.colorWhite));
            holder.txtMemberCategory.setTextColor(context.getResources().getColor(R.color.colorWhite));
            holder.txtMemberJoinDate.setTextColor(context.getResources().getColor(R.color.colorWhite));
            holder.txtMemberEndDate.setTextColor(context.getResources().getColor(R.color.colorWhite));
            holder.txtMobileNo.setTextColor(context.getResources().getColor(R.color.colorWhite));
        }else{
            holder.cardview.setCardBackgroundColor(context.getResources().getColor(R.color.greeen));
            holder.txtMemberName.setTextColor(context.getResources().getColor(R.color.colorPrimaryDark));
            holder.txtMemberCode.setTextColor(context.getResources().getColor(R.color.colorPrimaryDark));
            holder.txtMemberCity.setTextColor(context.getResources().getColor(R.color.colorPrimaryDark));
            holder.txtMemberCategory.setTextColor(context.getResources().getColor(R.color.colorPrimaryDark));
            holder.txtMemberJoinDate.setTextColor(context.getResources().getColor(R.color.colorPrimaryDark));
            holder.txtMemberEndDate.setTextColor(context.getResources().getColor(R.color.colorPrimaryDark));
            holder.txtMobileNo.setTextColor(context.getResources().getColor(R.color.colorPrimaryDark));
        }

    }

    @Override
    public int getItemCount() {
        return listDataResponses.size();
    }


    public class AdminMemberListHolder extends RecyclerView.ViewHolder {
        private TextView txtMemberName, txtMemberCode, txtMemberCity, txtMemberCategory, txtMemberJoinDate, txtMemberEndDate , txtMobileNo;
        CardView cardview;

        public AdminMemberListHolder(View itemView) {
            super(itemView);
            txtMemberName = itemView.findViewById(R.id.txt_member_name);
            txtMemberCode = itemView.findViewById(R.id.txt_member_code);
            txtMemberCity = itemView.findViewById(R.id.txt_member_city);
            txtMemberCategory = itemView.findViewById(R.id.txt_member_category);
            txtMemberJoinDate = itemView.findViewById(R.id.txt_member_join_date);
            txtMemberEndDate = itemView.findViewById(R.id.txt_member_end_date);
            txtMobileNo = itemView.findViewById(R.id.txt_mobile_no);
            cardview = itemView.findViewById(R.id.card_book_issue_list);

        }

    }
}

