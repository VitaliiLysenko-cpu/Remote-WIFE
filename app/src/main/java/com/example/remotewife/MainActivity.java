package com.example.remotewife;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.remotewife.polo.Job;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView jobText;
    private Button jobChoice;
    
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
        jobText.setText("");
        Server.getInstance()
                .getJSONApi()
                .getJobWithActivity()
                .enqueue(new Callback<Job>() {
                    @Override
                    public void onResponse(@NonNull Call<Job> call, @NonNull Response<Job> response) {
                        Job job = response.body();
                        
                        jobText.append(job.getActivity() + "\n");
                        jobText.append(job.getAccessibility() + "\n");
                        jobText.append(job.getType() + "\n");
                        jobText.append(job.getParticipants() + "\n");
                        jobText.append(job.getPrice() + "\n");
                        jobText.append(job.getLink() + "\n");
                        jobText.append(job.getKey() + "\n");
                        
                    }
                    
                    /**
                     * Invoked when a network exception occurred talking to the server or when an unexpected
                     * exception occurred creating the request or processing the response.
                     *
                     * @param call
                     * @param t
                     */
                    public void onFailure(Call<Job> call, Throwable t) {
                        jobText.append("Error occurred while getting request!");
                        t.printStackTrace();
                        
                    }
                    
                    
                });
        
    }
}
