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
import com.jkim.shrutsangam.api.modal.BookReceiveListResponse;
import com.jkim.shrutsangam.utils.interfaces.OnBookIssueListClick;

import java.util.List;

public class BookReceiveListAdapter extends RecyclerView.Adapter<BookReceiveListAdapter.BookReceiveListHolder> {
    private List<BookReceiveListResponse.listDataResponse> listDataResponses;
    private Context context;

    public BookReceiveListAdapter(List<BookReceiveListResponse.listDataResponse> listDataResponses, Context context) {
        this.listDataResponses = listDataResponses;
        this.context = context;
    }

    @Override
    public BookReceiveListAdapter.BookReceiveListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BookReceiveListAdapter.BookReceiveListHolder(LayoutInflater.from(context).inflate(R.layout.row_book_receive_list, parent, false));
    }

    @Override
    public void onBindViewHolder(final BookReceiveListAdapter.BookReceiveListHolder holder, final int position) {

        holder.txtBookName.setText(Html.fromHtml("<b>Book Name: </b>" + listDataResponses.get(position).getName()));
        holder.txtDate.setText(Html.fromHtml("<b>Issue Date: </b>" + listDataResponses.get(position).getIssueDate()));
//        holder.txtReceive.setText(Html.fromHtml("<b>Book Name: </b>" + listDataResponses.get(position).getName()));
        holder.txtMemberName.setText(Html.fromHtml("<b>Member Name: </b>" + listDataResponses.get(position).getMemberName()));
        holder.txtBookNo.setText(Html.fromHtml("<b>Book No. </b>" + listDataResponses.get(position).getBook_no()));
        holder.txtIssueNo.setText(Html.fromHtml("<b>Issue No: </b>" + listDataResponses.get(position).getBook_issueno()));
        holder.txtReceivedDate.setText(Html.fromHtml("<b>Received Date: </b>" + listDataResponses.get(position).getReceiveDate()));
        holder.txtMemberCode.setText(Html.fromHtml("<b>Member Code: </b>" + listDataResponses.get(position).getMemberCode()));
        if((position%2) != 0){
            holder.cardBookIssueList.setCardBackgroundColor(context.getResources().getColor(R.color.pink));
            holder.txtBookName.setTextColor(context.getResources().getColor(R.color.colorWhite));
            holder.txtDate.setTextColor(context.getResources().getColor(R.color.colorWhite));
            holder.txtMemberName.setTextColor(context.getResources().getColor(R.color.colorWhite));
            holder.txtBookNo.setTextColor(context.getResources().getColor(R.color.colorWhite));
            holder.txtIssueNo.setTextColor(context.getResources().getColor(R.color.colorWhite));
            holder.txtReceivedDate.setTextColor(context.getResources().getColor(R.color.colorWhite));
            holder.txtMemberCode.setTextColor(context.getResources().getColor(R.color.colorWhite));
        }else{
            holder.cardBookIssueList.setCardBackgroundColor(context.getResources().getColor(R.color.greeen));
            holder.txtBookName.setTextColor(context.getResources().getColor(R.color.colorPrimaryDark));
            holder.txtDate.setTextColor(context.getResources().getColor(R.color.colorPrimaryDark));
            holder.txtMemberName.setTextColor(context.getResources().getColor(R.color.colorPrimaryDark));
            holder.txtBookNo.setTextColor(context.getResources().getColor(R.color.colorPrimaryDark));
            holder.txtIssueNo.setTextColor(context.getResources().getColor(R.color.colorPrimaryDark));
            holder.txtReceivedDate.setTextColor(context.getResources().getColor(R.color.colorPrimaryDark));
            holder.txtMemberCode.setTextColor(context.getResources().getColor(R.color.colorPrimaryDark));
        }

    }

    @Override
    public int getItemCount() {
        return listDataResponses.size();
    }


    public class BookReceiveListHolder extends RecyclerView.ViewHolder {
        private TextView txtBookName, txtDate, txtReceive, txtMemberName, txtBookNo, txtIssueNo, txtCode,txtMemberCode,txtReceivedDate;
        private CardView cardBookIssueList;

        public BookReceiveListHolder(View itemView) {
            super(itemView);

            txtBookNo = itemView.findViewById(R.id.txt_book_no);
            txtBookName = itemView.findViewById(R.id.txt_book_name);
            txtDate = itemView.findViewById(R.id.txt_date);
            txtDate.setVisibility(View.INVISIBLE);
            txtReceive = itemView.findViewById(R.id.txt_receive);
            txtMemberName = itemView.findViewById(R.id.txt_member_name);
            txtMemberCode = itemView.findViewById(R.id.txt_member_code);
            txtIssueNo = itemView.findViewById(R.id.txt_issue_no);
            txtReceivedDate = itemView.findViewById(R.id.txt_received_date);
            cardBookIssueList = itemView.findViewById(R.id.card_book_issue_list);
        }

    }
}
