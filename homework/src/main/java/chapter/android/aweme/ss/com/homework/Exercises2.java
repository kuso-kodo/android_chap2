package chapter.android.aweme.ss.com.homework;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * 作业2：一个抖音笔试题：统计页面所有view的个数
 * Tips：ViewGroup有两个API
 * {@link android.view.ViewGroup #getChildAt(int) #getChildCount()}
 * 用一个TextView展示出来
 */
public class Exercises2 extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise2);
        TextView textView = findViewById(R.id.textViewResult);
        textView.setText(String.valueOf(getAllChildViewCount(findViewById(R.id.layoutExercise2))));
    }

    public int getAllChildViewCount(View view) {
        if (view != null) {
            int i = 0;
            if (view instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup)view;
                int k = viewGroup.getChildCount();
                for (int j = 0; j < k; j++)
                    i += getAllChildViewCount(viewGroup.getChildAt(j));
                return i;
            }
            return 1;
        }
        // View is null
        return -1;
    }
}
