package com.example.mobileprogrammingassignment;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileprogrammingassignment.model.Article;

import java.util.List;
public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder> {

    private List<Article> articles; // List of articles to be displayed

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

        // Set the article title into the TextView
        holder.titleTextView.setText(article.getTitle()); // Use the article's title here
    }

    @Override
    public int getItemCount() {
        return articles.size(); // Return the total number of articles
    }

    // ViewHolder class to hold references to the views for each item
    public static class ArticleViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView;

        public ArticleViewHolder(View itemView) {
            super(itemView);
            // Bind the views from the item layout
            titleTextView = itemView.findViewById(R.id.articleTitle); // Reference to TextView
        }
    }
}

