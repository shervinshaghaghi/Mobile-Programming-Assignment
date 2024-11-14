package com.example.mobileprogrammingassignment;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileprogrammingassignment.api.NewsApiService;
import com.example.mobileprogrammingassignment.model.Article;
import com.example.mobileprogrammingassignment.model.NewsResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String BASE_URL = "https://newsapi.org/"; // Base URL for NewsAPI
    private static final String API_KEY = "1dd3d066e3d9434aaf9771b7fa4402e5"; // Replace with your actual API key

//    private static final String BASE_URL = "https://sanger.dia.fi.upm.es/pmd-task/"; // Base URL for NewsAPI, https://sanger.dia.fi.upm.es/pmd-task/
//    private static final String API_KEY = "???"; // Replace with your actual API key

    private RecyclerView recyclerView; // RecyclerView to display articles
    private ArticleAdapter articleAdapter; // Adapter for RecyclerView

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Initialize RecyclerView
        recyclerView = findViewById(R.id.articleList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this)); // Set LayoutManager (vertical list)

        // add dividers between RecyclerView items
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        Drawable articleDivider = ContextCompat.getDrawable(this, R.drawable.article_divider);
        if (articleDivider != null) {
            dividerItemDecoration.setDrawable(articleDivider);
        }
        recyclerView.addItemDecoration(dividerItemDecoration);

        // Initialize Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)  // Set the base URL of the News API
                .addConverterFactory(GsonConverterFactory.create())  // Add Gson converter to parse JSON
                .build();

        // Create the API service
        NewsApiService apiService = retrofit.create(NewsApiService.class);

        // Example query term to search articles
        String query = "technology";

        // Make the API call
        Call<NewsResponse> call = apiService.getArticles(query, API_KEY);

        // Enqueue the call to make it asynchronous
        call.enqueue(new Callback<NewsResponse>() {
            @Override
            public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
                if (response.isSuccessful()) {
                    // Extract articles from the response
                    List<Article> articles = response.body().getArticles();

                    // Initialize the Adapter and set it to the RecyclerView
                    articleAdapter = new ArticleAdapter(articles); // Pass articles to the adapter
                    recyclerView.setAdapter(articleAdapter); // Set the adapter
                } else {
                    // Handle the error response (e.g., invalid API key, network issues)
                    Log.e("API Error", "Error response: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<NewsResponse> call, Throwable t) {
                // Handle failure (e.g., no internet connection)
                Log.e("API Failure", "Request failed", t);
            }
        });
    }
}
