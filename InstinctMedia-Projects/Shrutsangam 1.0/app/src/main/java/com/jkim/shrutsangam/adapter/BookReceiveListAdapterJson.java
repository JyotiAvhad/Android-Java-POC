package com.jkim.shrutsangam.adapter;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.jkim.shrutsangam.R;

public class BookReceiveListAdapterJson extends RecyclerView.Adapter<BookReceiveListAdapterJson.BookReceiveListHolder> {
    private JsonArray listDataResponses;
    private Context context;

    public BookReceiveListAdapterJson(JsonArray listDataResponses, Context context) {
        this.listDataResponses = listDataResponses;
        this.context = context;
    }

    @Override
    public BookReceiveListAdapterJson.BookReceiveListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BookReceiveListAdapterJson.BookReceiveListHolder(LayoutInflater.from(context).inflate(R.layout.row_book_receive_list, parent, false));
    }

    @Override
    public void onBindViewHolder(final BookReceiveListAdapterJson.BookReceiveListHolder holder, final int position) {

        JsonObject jobj = listDataResponses.get(position).getAsJsonObject();
        holder.txtBookName.setText(Html.fromHtml("<b>Book Name: </b>" + String.valueOf(jobj.get("name")).replace("\"", "")));
        holder.txtDate.setText(Html.fromHtml("<b>Issue Date: </b>" + String.valueOf(jobj.get("IssueDate")).replace("\"", "")));
//        holder.txtReceive.setText(Html.fromHtml("<b>Book Name: </b>" + listDataResponses.get(position).getName()));
        holder.txtMemberName.setText(Html.fromHtml("<b>Member Name: </b>" + String.valueOf(jobj.get("MemberName")).replace("\"", "")));
        holder.txtBookNo.setText(Html.fromHtml("<b>Book No. </b>" + String.valueOf(jobj.get("book_no")).replace("\"", "")));
        holder.txtIssueNo.setText(Html.fromHtml("<b>Issue No: </b>" + String.valueOf(jobj.get("book_issueno")).replace("\"", "")));
        holder.txtReceivedDate.setText(Html.fromHtml("<b>Received Date: </b>" + String.valueOf(jobj.get("ReceiveDate")).replace("\"", "")));
        holder.txtMemberCode.setText(Html.fromHtml("<b>Member Code: </b>" + String.valueOf(jobj.get("MemberCode")).replace("\"", "")));

        if ((position % 2) != 0) {
            holder.cardBookIssueList.setCardBackgroundColor(context.getResources().getColor(R.color.pink));
            holder.txtBookName.setTextColor(context.getResources().getColor(R.color.colorWhite));
            holder.txtDate.setTextColor(context.getResources().getColor(R.color.colorWhite));
            holder.txtMemberName.setTextColor(context.getResources().getColor(R.color.colorWhite));
            holder.txtBookNo.setTextColor(context.getResources().getColor(R.color.colorWhite));
            holder.txtIssueNo.setTextColor(context.getResources().getColor(R.color.colorWhite));
            holder.txtReceivedDate.setTextColor(context.getResources().getColor(R.color.colorWhite));
            holder.txtMemberCode.setTextColor(context.getResources().getColor(R.color.colorWhite));
        } else {
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
        private TextView txtBookName, txtDate, txtReceive, txtMemberName, txtBookNo, txtIssueNo, txtCode, txtMemberCode, txtReceivedDate;
        private CardView cardBookIssueList;

        public BookReceiveListHolder(View itemView) {
            super(itemView);

            txtBookNo = itemView.findViewById(R.id.txt_book_no);
            txtBookName = itemView.findViewById(R.id.txt_book_name);
            txtDate = itemView.findViewById(R.id.txt_date);
            txtDate.setVisibility(View.GONE);
            txtReceive = itemView.findViewById(R.id.txt_receive);
            txtMemberName = itemView.findViewById(R.id.txt_member_name);
            txtMemberCode = itemView.findViewById(R.id.txt_member_code);
            txtIssueNo = itemView.findViewById(R.id.txt_issue_no);
            txtReceivedDate = itemView.findViewById(R.id.txt_received_date);
            cardBookIssueList = itemView.findViewById(R.id.card_book_issue_list);
        }

    }
}
