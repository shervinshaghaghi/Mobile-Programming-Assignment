package com.example.mobileprogrammingassignment;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;

public class ArticleDetailsActivity extends AppCompatActivity {

    public static final String imageParam = "urlToImage";
    public static final String titleParam = "articleTitle";
    public static final String urlParam = "articleUrl";
    public static final String descriptionParam = "articleDescription";
    public static final String bodyParam = "articleBody";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_article_details);

        // Set padding for system insets?
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.articleDetailsLayout), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });

        Intent articleIntent = getIntent();
        String urlToImage = articleIntent.getStringExtra(imageParam);
        String title = articleIntent.getStringExtra(titleParam);
        String url = articleIntent.getStringExtra(urlParam);
        String description = articleIntent.getStringExtra(descriptionParam);
        String body = articleIntent.getStringExtra(bodyParam);

        setArticleDetails(urlToImage, title, url, description, body);
    }

    private void setArticleDetails(String urlToImage,
                                   String title,
                                   String url,
                                   String description,
                                   String body) {
        ImageView imageView = findViewById(R.id.articleDetailsImage);
        RequestOptions requestOptions = new RequestOptions();
        Glide.with(imageView.getContext())
                .load(urlToImage)
                .apply(requestOptions.fitCenter())
                .apply(requestOptions.transform(new RoundedCorners(15)))
                .placeholder(android.R.drawable.ic_menu_camera)
                .error(android.R.drawable.stat_notify_error)
                .into(imageView);

        TextView titleView = findViewById(R.id.articleDetailsTitle);
        TextView urlView = findViewById(R.id.articleUrl);
        TextView descriptionView = findViewById(R.id.articleDetailsDescription);
        TextView bodyView = findViewById(R.id.articleDetailsBody);

        titleView.setText(title);
        urlView.setText(url);
        descriptionView.setText(description);
        bodyView.setText(body);
    }
}