package com.example.moviereviews;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    DBHandler handler;
    TextView id;
    EditText name;
    EditText review;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        handler = new DBHandler(this, null, null, 1);
        id = findViewById(R.id.idLabel);
        name = findViewById(R.id.nameText);
        review = findViewById(R.id.reviewText);
    }

    public void addMovie(View v){
        handler.addMovie(name.getText().toString(), review.getText().toString());
        name.setText("");
        review.setText("");
        id.setText("Movie Added");
    }

    public void findMovie(View v){
        Movie movie = handler.findMovie(name.getText().toString());
        if(movie != null){
            id.setText("ID: " + String.valueOf(movie.getId()));
            review.setText(String.valueOf(movie.getReview()));
        }
        else{
            id.setText("Movie NOT found");
        }
    }

    public void deleteMovie(View v){
        boolean result = handler.deleteMovie(name.getText().toString());
        if(result){
            id.setText("Movie deleted");
            name.setText("");
            review.setText("");
        }
        else{
            id.setText("Movie NOT found");
        }
    }
}
