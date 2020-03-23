package chapter.android.aweme.ss.com.homework;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * 作业1：
 * Logcat在屏幕旋转的时候 #onStop() #onDestroy()会展示出来
 * 但我们的 mLifecycleDisplay 由于生命周期的原因(Tips:执行#onStop()之后，UI界面我们是看不到的)并没有展示
 * 在原有@see Exercises1 基础上如何补全它，让其跟logcat的展示一样?
 * <p>
 * Tips：思考用比Activity的生命周期要长的来存储？  （比如：application、static变量）
 */
public class Exercises1 extends AppCompatActivity {
    private static final String TAG = "name1e5s";
    private static final ArrayList<String> lifeCycleCallbacks = new ArrayList<>();
    private TextView textViewDisplay;

    private void appendCallback(String string) {
        String sb = "Lifecycle Event: " + string;
        Log.d(TAG, sb);
        TextView textView = this.textViewDisplay;
        textView.append(string + "\n");
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise1);

        Button button = findViewById(R.id.resetLog);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textViewDisplay.setText("Lifecycle callbacks:\n");
            }
        });

        this.textViewDisplay = findViewById(R.id.textView_logLifecycle);
        textViewDisplay.setText("Lifecycle callbacks:\n");

        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey("callbacks")) {
                String str = (String)savedInstanceState.get("callbacks");
                this.textViewDisplay.setText(str);
            }
            for (String x : lifeCycleCallbacks) {
                TextView textView = this.textViewDisplay;
                textView.append(x + "\n");
            }
        }
        lifeCycleCallbacks.clear();
        appendCallback("onCreate");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        lifeCycleCallbacks.add("onDestroy");
        appendCallback("onDestroy");
    }

    @Override
    protected void onPause() {
        super.onPause();
        appendCallback("onDestroy");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        appendCallback("onRestart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        appendCallback("onResume");
    }

    @Override
    protected void onStart() {
        super.onStart();
        appendCallback("onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        lifeCycleCallbacks.add("onStop");
        appendCallback("onStop");
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        appendCallback("onSaveInstanceState");
        savedInstanceState.putString("callbacks", this.textViewDisplay.getText().toString());
    }
}
