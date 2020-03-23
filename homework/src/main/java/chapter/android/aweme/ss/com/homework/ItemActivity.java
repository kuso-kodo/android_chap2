package chapter.android.aweme.ss.com.homework;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;


public class ItemActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise2);
        TextView textView = findViewById(R.id.textViewResult);
        Bundle bundle = this.getIntent().getExtras();
        if(bundle != null) {
            int pos = bundle.getInt("pos");
            textView.setText("The item you clicked is " + String.valueOf(pos));
        }
    }
}