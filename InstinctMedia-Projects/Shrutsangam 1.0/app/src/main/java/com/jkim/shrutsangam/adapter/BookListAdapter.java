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
import com.jkim.shrutsangam.api.modal.BookListResponse;

import java.util.List;

public class BookListAdapter extends RecyclerView.Adapter<BookListAdapter.BookListHolder> {
    private List<BookListResponse.listDataResponse> listDataResponses;
    private Context context;

    public BookListAdapter(List<BookListResponse.listDataResponse> listDataResponses, Context context) {
        this.listDataResponses = listDataResponses;
        this.context = context;
    }

    @Override
    public BookListAdapter.BookListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BookListAdapter.BookListHolder(LayoutInflater.from(context).inflate(R.layout.row_book_list, parent, false));
    }

    @Override
    public void onBindViewHolder(final BookListAdapter.BookListHolder holder, final int position) {

        holder.txtBookName.setText(Html.fromHtml("<b>Book Name : </b>" + listDataResponses.get(position).getName()));
        holder.txtBookNumber.setText(Html.fromHtml("<b>Book Number : </b>" + listDataResponses.get(position).getBook_no()));
        holder.txtBookLanguage.setText(Html.fromHtml("<b>Language : </b>" + listDataResponses.get(position).getLanguage()));
        holder.txtBookAuthor.setText(Html.fromHtml("<b>Book Author : </b>" + listDataResponses.get(position).getAuthorId()));
        holder.txtBookPublisher.setText(Html.fromHtml("<b>Publisher : </b>" + listDataResponses.get(position).getPublisherId()));
        holder.txtPrice.setText(Html.fromHtml("<b>Price : </b>" + listDataResponses.get(position).getPrice()));
        holder.txtAutherId.setText(Html.fromHtml("<b>Author Id : </b>" + listDataResponses.get(position).getAuthorId()));

        if((position%2) != 0){
            holder.cardview.setCardBackgroundColor(context.getResources().getColor(R.color.pink));
            holder.txtBookName.setTextColor(context.getResources().getColor(R.color.colorWhite));
            holder.txtBookNumber.setTextColor(context.getResources().getColor(R.color.colorWhite));
            holder.txtBookLanguage.setTextColor(context.getResources().getColor(R.color.colorWhite));
            holder.txtBookAuthor.setTextColor(context.getResources().getColor(R.color.colorWhite));
            holder.txtBookPublisher.setTextColor(context.getResources().getColor(R.color.colorWhite));
            holder.txtPrice.setTextColor(context.getResources().getColor(R.color.colorWhite));
            holder.txtAutherId.setTextColor(context.getResources().getColor(R.color.colorWhite));
        }else{
            holder.cardview.setCardBackgroundColor(context.getResources().getColor(R.color.greeen));
            holder.txtBookName.setTextColor(context.getResources().getColor(R.color.colorPrimaryDark));
            holder.txtBookNumber.setTextColor(context.getResources().getColor(R.color.colorPrimaryDark));
            holder.txtBookLanguage.setTextColor(context.getResources().getColor(R.color.colorPrimaryDark));
            holder.txtBookAuthor.setTextColor(context.getResources().getColor(R.color.colorPrimaryDark));
            holder.txtBookPublisher.setTextColor(context.getResources().getColor(R.color.colorPrimaryDark));
            holder.txtPrice.setTextColor(context.getResources().getColor(R.color.colorPrimaryDark));
            holder.txtAutherId.setTextColor(context.getResources().getColor(R.color.colorPrimaryDark));
        }


    }

    @Override
    public int getItemCount() {
        return listDataResponses.size();
    }


    public class BookListHolder extends RecyclerView.ViewHolder {
        private TextView txtBookName, txtPrice, txtAutherId;
        private TextView txtBookNumber , txtBookLanguage , txtBookAuthor , txtBookPublisher;
        CardView cardview;

        public BookListHolder(View itemView) {
            super(itemView);
            txtBookName = itemView.findViewById(R.id.txt_book_name);
            txtPrice = itemView.findViewById(R.id.txt_price);
            txtAutherId = itemView.findViewById(R.id.txt_auther_id);
            txtBookNumber = itemView.findViewById(R.id.txt_book_number);
            txtBookLanguage = itemView.findViewById(R.id.txt_book_language);
            txtBookAuthor = itemView.findViewById(R.id.txt_book_Author);
            txtBookPublisher = itemView.findViewById(R.id.txt_book_Publisher);
            cardview = itemView.findViewById(R.id.card_book_issue_list);

        }
    }
}
