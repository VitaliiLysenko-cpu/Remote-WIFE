package com.example.remotewife;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.net.URL;
import java.util.StringTokenizer;

import static com.example.remotewife.СreationOf_URL.generateURL;
import static com.example.remotewife.СreationOf_URL.getResponseFromURL;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView  jobText;
    private Button  jobChoice;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        jobChoice = findViewById(R.id.job_choice_button);
        jobText = findViewById(R.id.job_text);
        jobChoice.setOnClickListener(this);
    }
    
    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
          URL generatedURL =  generateURL();
        String response = null;
        try {
            response = getResponseFromURL(generatedURL);
        } catch (IOException e) {
            e.printStackTrace();
        }
        jobText.setText(response);
    }
}
