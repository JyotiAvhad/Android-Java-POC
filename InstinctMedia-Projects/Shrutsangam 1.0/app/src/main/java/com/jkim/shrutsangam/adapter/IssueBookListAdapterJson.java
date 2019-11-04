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
import com.jkim.shrutsangam.utils.interfaces.OnBookIssueListClick;

public class IssueBookListAdapterJson extends RecyclerView.Adapter<IssueBookListAdapterJson.BookIssueListHolder> {
    private JsonArray listDataResponses;
    private Context context;
    private OnBookIssueListClick onBookIssueListClick;

    public IssueBookListAdapterJson(JsonArray listDataResponses, Context context, OnBookIssueListClick onBookIssueListClick) {
        this.listDataResponses = listDataResponses;
        this.context = context;
        this.onBookIssueListClick = onBookIssueListClick;
    }

    @Override
    public IssueBookListAdapterJson.BookIssueListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new IssueBookListAdapterJson.BookIssueListHolder(LayoutInflater.from(context).inflate(R.layout.row_book_issue_list, parent, false));
    }

    @Override
    public void onBindViewHolder(final IssueBookListAdapterJson.BookIssueListHolder holder, final int position) {

        final JsonObject jobj = listDataResponses.get(position).getAsJsonObject();

        holder.txtBookName.setText(Html.fromHtml("<b>Book Name: </b>" + String.valueOf(jobj.get("name")).replace("\"", "")));
        holder.txtDate.setText(Html.fromHtml("<b>Issue Date: </b>" + String.valueOf(jobj.get("IssueDate")).replace("\"", "")));
//        holder.txtReceive.setText(Html.fromHtml("<b>Book Name: </b>" + listDataResponses.get(position).getName()));
        holder.txtMemberName.setText(Html.fromHtml("<b>Name: </b>" + String.valueOf(jobj.get("MemberName")).replace("\"", "")));
        holder.txtBookNo.setText(Html.fromHtml("<b>Book No. </b>" + String.valueOf(jobj.get("book_no")).replace("\"", "")));
        holder.txtIssueNo.setText(Html.fromHtml("<b>Issue No: </b>" + String.valueOf(jobj.get("IssueId")).replace("\"", "")));
        holder.txtCode.setText(Html.fromHtml("<b>Code: </b>" + String.valueOf(jobj.get("MemberCode")).replace("\"", "")));

        holder.cardBookIssueList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBookIssueListClick.onBookIssueListClick(String.valueOf(jobj.get("IssueId")).replace("\"", ""), String.valueOf(jobj.get("bhandar_id")).replace("\"", ""));
            }
        });

        if((position%2) != 0){
            holder.cardBookIssueList.setCardBackgroundColor(context.getResources().getColor(R.color.pink));
            holder.txtBookName.setTextColor(context.getResources().getColor(R.color.colorWhite));
            holder.txtDate.setTextColor(context.getResources().getColor(R.color.colorWhite));
            holder.txtMemberName.setTextColor(context.getResources().getColor(R.color.colorWhite));
            holder.txtBookNo.setTextColor(context.getResources().getColor(R.color.colorWhite));
            holder.txtIssueNo.setTextColor(context.getResources().getColor(R.color.colorWhite));
            holder.txtCode.setTextColor(context.getResources().getColor(R.color.colorWhite));

        }else{
            holder.cardBookIssueList.setCardBackgroundColor(context.getResources().getColor(R.color.greeen));
            holder.txtBookName.setTextColor(context.getResources().getColor(R.color.colorPrimaryDark));
            holder.txtDate.setTextColor(context.getResources().getColor(R.color.colorPrimaryDark));
            holder.txtMemberName.setTextColor(context.getResources().getColor(R.color.colorPrimaryDark));
            holder.txtBookNo.setTextColor(context.getResources().getColor(R.color.colorPrimaryDark));
            holder.txtIssueNo.setTextColor(context.getResources().getColor(R.color.colorPrimaryDark));
            holder.txtCode.setTextColor(context.getResources().getColor(R.color.colorPrimaryDark));
        }

    }

    @Override
    public int getItemCount() {
        return listDataResponses.size();
    }


    public class BookIssueListHolder extends RecyclerView.ViewHolder {
        private TextView txtBookName, txtDate, txtReceive, txtMemberName, txtBookNo, txtIssueNo, txtCode;
        private CardView cardBookIssueList;

        public BookIssueListHolder(View itemView) {
            super(itemView);
            txtBookName = itemView.findViewById(R.id.txt_book_name);
            txtDate = itemView.findViewById(R.id.txt_date);
            txtReceive = itemView.findViewById(R.id.txt_receive);
            txtMemberName = itemView.findViewById(R.id.txt_member_name);
            txtBookNo = itemView.findViewById(R.id.txt_book_no);
            txtIssueNo = itemView.findViewById(R.id.txt_issue_no);
            txtCode = itemView.findViewById(R.id.txt_code);
            cardBookIssueList = itemView.findViewById(R.id.card_book_issue_list);
        }

    }
}
