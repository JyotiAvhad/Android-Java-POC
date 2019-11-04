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
import com.jkim.shrutsangam.api.modal.BookIssueListResponse;
import com.jkim.shrutsangam.utils.interfaces.OnBookIssueListClick;

import java.util.List;

public class BookIssueListAdapter extends RecyclerView.Adapter<BookIssueListAdapter.BookIssueListHolder> {
    private List<BookIssueListResponse.listDataResponse> listDataResponses;
    private Context context;
    private OnBookIssueListClick onBookIssueListClick;

    public BookIssueListAdapter(List<BookIssueListResponse.listDataResponse> listDataResponses, Context context, OnBookIssueListClick onBookIssueListClick) {
        this.listDataResponses = listDataResponses;
        this.context = context;
        this.onBookIssueListClick = onBookIssueListClick;
    }

    @Override
    public BookIssueListAdapter.BookIssueListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BookIssueListAdapter.BookIssueListHolder(LayoutInflater.from(context).inflate(R.layout.row_book_issue_list_new, parent, false));
    }

    @Override
    public void onBindViewHolder(final BookIssueListAdapter.BookIssueListHolder holder, final int position) {

        holder.txtBookName.setText(Html.fromHtml("<b>Book Name: </b>" + listDataResponses.get(position).getName()));
        holder.txtDate.setText(Html.fromHtml("<b>Issue Date: </b>" + listDataResponses.get(position).getIssueDate()));
//        holder.txtReceive.setText(Html.fromHtml("<b>Book Name: </b>" + listDataResponses.get(position).getName()));
        holder.txtMemberName.setText(Html.fromHtml("<b>Member Name: </b>" + listDataResponses.get(position).getMemberName()));
        holder.txtBookNo.setText(Html.fromHtml("<b>Book No. </b>" + listDataResponses.get(position).getBook_no()));
        holder.txtIssueNo.setText(Html.fromHtml("<b>Issue No: </b>" + listDataResponses.get(position).getBook_issueno()));
        holder.txtOverdueDays.setText(Html.fromHtml("<b>OverDue Days: </b>" + listDataResponses.get(position).getOverDueDay()));
        holder.txtMemberCode.setText(Html.fromHtml("<b>Member Code: </b>" + listDataResponses.get(position).getMemberCode()));
        holder.cardBookIssueList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBookIssueListClick.onBookIssueListClick(listDataResponses.get(position).getIssueId(), listDataResponses.get(position).getBhandar_id());
            }
        });

        if((position%2) != 0){
            holder.cardBookIssueList.setCardBackgroundColor(context.getResources().getColor(R.color.pink));
            holder.txtBookName.setTextColor(context.getResources().getColor(R.color.colorWhite));
            holder.txtDate.setTextColor(context.getResources().getColor(R.color.colorWhite));
            holder.txtMemberName.setTextColor(context.getResources().getColor(R.color.colorWhite));
            holder.txtBookNo.setTextColor(context.getResources().getColor(R.color.colorWhite));
            holder.txtIssueNo.setTextColor(context.getResources().getColor(R.color.colorWhite));
            holder.txtOverdueDays.setTextColor(context.getResources().getColor(R.color.colorWhite));
            holder.txtMemberCode.setTextColor(context.getResources().getColor(R.color.colorWhite));
        }else{
            holder.cardBookIssueList.setCardBackgroundColor(context.getResources().getColor(R.color.greeen));
            holder.txtBookName.setTextColor(context.getResources().getColor(R.color.colorPrimaryDark));
            holder.txtDate.setTextColor(context.getResources().getColor(R.color.colorPrimaryDark));
            holder.txtMemberName.setTextColor(context.getResources().getColor(R.color.colorPrimaryDark));
            holder.txtBookNo.setTextColor(context.getResources().getColor(R.color.colorPrimaryDark));
            holder.txtIssueNo.setTextColor(context.getResources().getColor(R.color.colorPrimaryDark));
            holder.txtOverdueDays.setTextColor(context.getResources().getColor(R.color.colorPrimaryDark));
            holder.txtMemberCode.setTextColor(context.getResources().getColor(R.color.colorPrimaryDark));
        }

    }

    @Override
    public int getItemCount() {
        return listDataResponses.size();
    }


    public class BookIssueListHolder extends RecyclerView.ViewHolder {
        private TextView txtBookName, txtDate, txtReceive, txtMemberName,txtMemberCode, txtBookNo, txtIssueNo, txtOverdueDays;
        private CardView cardBookIssueList;

        public BookIssueListHolder(View itemView) {
            super(itemView);
            txtBookNo = itemView.findViewById(R.id.txt_book_no);
            txtBookName = itemView.findViewById(R.id.txt_book_name);
            txtDate = itemView.findViewById(R.id.txt_date);
            txtReceive = itemView.findViewById(R.id.txt_receive);
            txtMemberName = itemView.findViewById(R.id.txt_member_name);
            txtMemberCode = itemView.findViewById(R.id.txt_member_code);
            txtIssueNo = itemView.findViewById(R.id.txt_issue_no);
            txtOverdueDays = itemView.findViewById(R.id.txt_overdue_days);
            cardBookIssueList = itemView.findViewById(R.id.card_book_issue_list);
        }

    }
}
