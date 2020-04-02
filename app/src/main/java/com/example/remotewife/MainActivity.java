package com.example.remotewife;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.net.URL;

import static com.example.remotewife.СreationOf_URL.generateURL;
import static com.example.remotewife.СreationOf_URL.getResponseFromURL;

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
       URL generated = generateURL() ;
       
       new JobRequestTask().execute(generateURL());
    }
    
    class JobRequestTask extends AsyncTask<URL, Void, String> {
        
        /**
         * Override this method to perform a computation on a background thread. The
         * specified parameters are the parameters passed to {@link #execute}
         * by the caller of this task.
         * <p>
         * This will normally run on a background thread. But to better
         * support testing frameworks, it is recommended that this also tolerates
         * direct execution on the foreground thread, as part of the {@link #execute} call.
         * <p>
         * This method can call {@link #publishProgress} to publish updates
         * on the UI thread.
         *
         * @param urls The parameters of the task.
         * @return A result, defined by the subclass of this task.
         * @see #onPreExecute()
         * @see #onPostExecute
         * @see #publishProgress
         */
        @Override
        protected String doInBackground(URL... urls) {
            URL generatedURL = generateURL();
            String response = null;
            try {
                response = getResponseFromURL(generatedURL);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return response;
        }
    
       
        @Override
        protected void onPostExecute(String response){
            jobText.setText(response);
        }
    }
}
