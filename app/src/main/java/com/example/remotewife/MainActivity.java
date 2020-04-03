package com.example.remotewife;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.remotewife.pojo.Task;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView taskText;
    private Button taskChoice;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        taskChoice = findViewById(R.id.task_choice_button);
        taskText = findViewById(R.id.task_text);
        taskChoice.setOnClickListener(this);
    }
    
    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        taskText.setText("");
        NetworkClient.getInstance()
                .getJSONApi()
                .getJobWithActivity()
                .enqueue(new Callback<Task>() {
                    @Override
                    public void onResponse(@NonNull Call<Task> call, @NonNull Response<Task> response) {
                        Task task = response.body();
                        
                        taskText.append(task.getActivity() + "\n");
                        taskText.append(task.getAccessibility() + "\n");
                        taskText.append(task.getType() + "\n");
                        taskText.append(task.getParticipants() + "\n");
                        taskText.append(task.getPrice() + "\n");
                        taskText.append(task.getLink() + "\n");
                        taskText.append(task.getKey() + "\n");
                        
                    }
                    
                    /**
                     * Invoked when a network exception occurred talking to the server or when an unexpected
                     * exception occurred creating the request or processing the response.
                     *
                     * @param call
                     * @param t
                     */
                    public void onFailure(Call<Task> call, Throwable t) {
                        taskText.append("Error occurred while getting request!");
                        t.printStackTrace();
                        
                    }
                    
                    
                });
        
    }
}
