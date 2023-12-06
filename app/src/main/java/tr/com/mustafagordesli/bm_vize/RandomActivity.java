package tr.com.mustafagordesli.bm_vize;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.Random;

public class RandomActivity extends AppCompatActivity {

    Button btnAdd;
    EditText countInput, minInput, maxInput;
    LinearLayout linearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random);
        viewSettings();
    }

    private void viewSettings() {
        btnAdd = (Button) findViewById(R.id.btnAdd);
        countInput = (EditText) findViewById(R.id.inputCount);
        maxInput = (EditText) findViewById(R.id.inputMax);
        minInput = (EditText) findViewById(R.id.inputMin);
        linearLayout = (LinearLayout) findViewById(R.id.linearLayout);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    int count = Integer.parseInt(countInput.getText().toString());
                    for (int i = 0; i < count; i++) {
                        ProgressBar progressBar = createProgress();
                        linearLayout.addView(progressBar);
                    }
                } catch (Exception e) {
                    Toast.makeText(RandomActivity.this, "Bir hata oluştu", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    private ProgressBar createProgress() {
        int min = Integer.parseInt(minInput.getText().toString());
        int max = Integer.parseInt(maxInput.getText().toString());
        int minValue = min + new Random().nextInt(max - min);
        int maxValue = minValue + new Random().nextInt(max - minValue + 1);

        ProgressBar progressBar = new ProgressBar(RandomActivity.this, null, android.R.attr.progressBarStyleHorizontal);

        progressBar.setMax(max);
        progressBar.setProgress(minValue);
        progressBar.setSecondaryProgress(maxValue);
        return progressBar;
    }
}