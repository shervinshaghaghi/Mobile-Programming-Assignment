package com.example.mobileprogrammingassignment;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.mobileprogrammingassignment.model.Article;

import java.util.List;
public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder> {

    private final List<Article> articles; // List of articles to be displayed

    // Constructor to initialize the articles list
    public ArticleAdapter(List<Article> articles) {
        this.articles = articles;
    }

    @Override
    public ArticleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Inflate the item_article.xml layout for each item in the RecyclerView
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_article, parent, false);
        return new ArticleViewHolder(view); // Create and return the ViewHolder
    }

    @Override
    public void onBindViewHolder(ArticleViewHolder holder, int position) {
        // Get the current article from the list
        Article article = articles.get(position);

        // Set the article texts into TextViews
        holder.titleTextView.setText(article.getTitle());
        holder.descriptionTextView.setText(article.getDescription());
        // add image!?
        RequestOptions requestOptions = new RequestOptions();
        Glide.with(holder.thumbnailImageView.getContext())
                .load(article.getUrlToImage())
                .apply(requestOptions.fitCenter())
                .apply(requestOptions.transform(new RoundedCorners(10)))
                .placeholder(android.R.drawable.ic_menu_camera)
                .error(android.R.drawable.stat_notify_error)
                .into(holder.thumbnailImageView);

//        holder.titleTextView.setText(article.getContent()); //
    }

    @Override
    public int getItemCount() {
        return articles.size(); // Return the total number of articles
    }

    // ViewHolder class to hold references to the views for each item
    public static class ArticleViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView;
        TextView descriptionTextView;
        TextView contentTextView;
        ImageView thumbnailImageView;


        public ArticleViewHolder(View itemView) {
            super(itemView);
            // Bind the views from the item layout
            titleTextView = itemView.findViewById(R.id.articleTitle); // Reference to Title TextView
            descriptionTextView = itemView.findViewById(R.id.articleDescription); // Reference to Description TextView
            thumbnailImageView = itemView.findViewById(R.id.articleImage);

//            titleTextView = itemView.findViewById(R.id.articleTitle); // Reference to Content TextView
        }
    }
}

