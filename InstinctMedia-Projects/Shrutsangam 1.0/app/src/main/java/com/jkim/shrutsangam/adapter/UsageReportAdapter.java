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
import com.jkim.shrutsangam.api.modal.OutstandingListResponse;
import com.jkim.shrutsangam.api.modal.UsageReportListResponse;

import java.util.List;

public class UsageReportAdapter extends RecyclerView.Adapter<UsageReportAdapter.UsageReportHolder> {
    private List<UsageReportListResponse.listDataResponse> listDataResponses;
    private Context context;

    public UsageReportAdapter(List<UsageReportListResponse.listDataResponse> listDataResponses, Context context) {
        this.listDataResponses = listDataResponses;
        this.context = context;
    }

    @Override
    public UsageReportAdapter.UsageReportHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new UsageReportAdapter.UsageReportHolder(LayoutInflater.from(context).inflate(R.layout.row_usage_report, parent, false));
    }

    @Override
    public void onBindViewHolder(final UsageReportAdapter.UsageReportHolder holder, final int position) {
        holder.txtMemberName.setText(Html.fromHtml("<b>Member Name: </b>" + listDataResponses.get(position).getMemberName()));
        holder.txtMemberCode.setText(Html.fromHtml("<b>Member Code: </b>" + listDataResponses.get(position).getMemberCode()));
        holder.txtMemberNumber.setText(Html.fromHtml("<b>Member Number: </b>" + listDataResponses.get(position).getMemberMobile()));
        holder.txtMemberEmail.setText(Html.fromHtml("<b>Member Email: </b>" + listDataResponses.get(position).getMemberEmail()));
        holder.txtIssueBook.setText(Html.fromHtml("<b>Issue Book: </b>" + listDataResponses.get(position).getIssuebooks()));
        holder.txtReceiveBook.setText(Html.fromHtml("<b>Receive Book: </b>" + listDataResponses.get(position).getReceivebooks()));
        if((position%2) != 0){
            holder.cardview.setCardBackgroundColor(context.getResources().getColor(R.color.pink));
            holder.txtMemberName.setTextColor(context.getResources().getColor(R.color.colorWhite));
            holder.txtMemberCode.setTextColor(context.getResources().getColor(R.color.colorWhite));
            holder.txtMemberNumber.setTextColor(context.getResources().getColor(R.color.colorWhite));
            holder.txtMemberEmail.setTextColor(context.getResources().getColor(R.color.colorWhite));
            holder.txtIssueBook.setTextColor(context.getResources().getColor(R.color.colorWhite));
            holder.txtReceiveBook.setTextColor(context.getResources().getColor(R.color.colorWhite));
        }else{
            holder.cardview.setCardBackgroundColor(context.getResources().getColor(R.color.greeen));
            holder.txtMemberName.setTextColor(context.getResources().getColor(R.color.colorPrimaryDark));
            holder.txtMemberCode.setTextColor(context.getResources().getColor(R.color.colorPrimaryDark));
            holder.txtMemberNumber.setTextColor(context.getResources().getColor(R.color.colorPrimaryDark));
            holder.txtMemberEmail.setTextColor(context.getResources().getColor(R.color.colorPrimaryDark));
            holder.txtIssueBook.setTextColor(context.getResources().getColor(R.color.colorPrimaryDark));
            holder.txtReceiveBook.setTextColor(context.getResources().getColor(R.color.colorPrimaryDark));
        }

    }

    @Override
    public int getItemCount() {
        return listDataResponses.size();
    }


    public class UsageReportHolder extends RecyclerView.ViewHolder {
        private TextView txtMemberName, txtMemberCode, txtMemberNumber, txtMemberEmail, txtIssueBook, txtReceiveBook;
        CardView cardview;

        public UsageReportHolder(View itemView) {
            super(itemView);
            txtMemberName = itemView.findViewById(R.id.txt_member_name);
            txtMemberCode = itemView.findViewById(R.id.txt_member_code);
            txtMemberNumber = itemView.findViewById(R.id.txt_member_number);
            txtMemberEmail = itemView.findViewById(R.id.txt_member_email);
            txtIssueBook = itemView.findViewById(R.id.txt_issue_book);
            txtReceiveBook = itemView.findViewById(R.id.txt_receive_book);
            cardview = itemView.findViewById(R.id.card_book_issue_list);

        }

    }
}
