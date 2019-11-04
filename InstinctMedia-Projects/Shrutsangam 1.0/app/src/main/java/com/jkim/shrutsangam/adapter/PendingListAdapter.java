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
import com.jkim.shrutsangam.api.modal.PendingListResponse;

import java.util.List;

public class PendingListAdapter extends RecyclerView.Adapter<PendingListAdapter.OutstandingListHolder> {
    private List<PendingListResponse.listDataResponse> listDataResponses;
    private Context context;

    public PendingListAdapter(List<PendingListResponse.listDataResponse> listDataResponses, Context context) {
        this.listDataResponses = listDataResponses;
        this.context = context;
    }

    @Override
    public PendingListAdapter.OutstandingListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PendingListAdapter.OutstandingListHolder(LayoutInflater.from(context).inflate(R.layout.row_outstanding_list, parent, false));
    }

    @Override
    public void onBindViewHolder(final PendingListAdapter.OutstandingListHolder holder, final int position) {
        holder.txtMemberName.setText(Html.fromHtml("<b>Member Name: </b>" + listDataResponses.get(position).getMemberName()));
        holder.txtName.setText(Html.fromHtml("<b>Name: </b>" + listDataResponses.get(position).getName()));
        holder.txtOverDueday.setText(Html.fromHtml("<b>OverDueDay: </b>" + listDataResponses.get(position).getOverDueDay()));
        holder.txtBookNo.setText(Html.fromHtml("<b>Book No: </b>" + listDataResponses.get(position).getBook_no()));
//        holder.txtMemberCode.setText(Html.fromHtml("<b>Member Code: </b>" + listDataResponses.get(position).getMemberName()));
//        holder.txtMemberMobileNo.setText(Html.fromHtml("<b>Mobile No: </b>" + listDataResponses.get(position).getBook_no()));
//        holder.txtBookName.setText(Html.fromHtml("<b>Book No: </b>" + listDataResponses.get(position).getBook_no()));
//        holder.txtBookIssueDate.setText(Html.fromHtml("<b>Book No: </b>" + listDataResponses.get(position).getBook_no()));

        if((position%2) != 0){
            holder.cardview.setCardBackgroundColor(context.getResources().getColor(R.color.pink));
            holder.txtOverDueday.setTextColor(context.getResources().getColor(R.color.colorWhite));
            holder.txtBookNo.setTextColor(context.getResources().getColor(R.color.colorWhite));
            holder.txtName.setTextColor(context.getResources().getColor(R.color.colorWhite));
            holder.txtMemberName.setTextColor(context.getResources().getColor(R.color.colorWhite));
        }else{
            holder.cardview.setCardBackgroundColor(context.getResources().getColor(R.color.greeen));
            holder.txtOverDueday.setTextColor(context.getResources().getColor(R.color.colorPrimaryDark));
            holder.txtBookNo.setTextColor(context.getResources().getColor(R.color.colorPrimaryDark));
            holder.txtName.setTextColor(context.getResources().getColor(R.color.colorPrimaryDark));
            holder.txtMemberName.setTextColor(context.getResources().getColor(R.color.colorPrimaryDark));
        }

    }

    @Override
    public int getItemCount() {
        return listDataResponses.size();
    }


    public class OutstandingListHolder extends RecyclerView.ViewHolder {
        private TextView txtMemberName, txtName, txtOverDueday,txtBookNo,txtMemberCode,txtMemberMobileNo,txtBookName,txtBookIssueDate;
        CardView cardview;

        public OutstandingListHolder(View itemView) {
            super(itemView);
            txtMemberName = itemView.findViewById(R.id.txt_member_name);
            txtName = itemView.findViewById(R.id.txt_name);
            txtOverDueday = itemView.findViewById(R.id.txt_over_dueday);
            txtBookNo = itemView.findViewById(R.id.txt_book_no);
            txtMemberCode = itemView.findViewById(R.id.txt_member_code);
            txtMemberMobileNo = itemView.findViewById(R.id.txt_mobile_no);
            txtBookName = itemView.findViewById(R.id.txt_book_name);
            txtBookIssueDate = itemView.findViewById(R.id.txt_issue_date);
            cardview = itemView.findViewById(R.id.card_book_issue_list);

        }

    }
}
