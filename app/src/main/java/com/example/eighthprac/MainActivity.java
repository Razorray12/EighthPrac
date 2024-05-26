package com.example.eighthprac;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.work.ExistingWorkPolicy;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

//Реализовать запуск 2 задач параллельно.
/*public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        OneTimeWorkRequest task1WorkRequest = new OneTimeWorkRequest.Builder(Task1Worker.class)
                .build();

        OneTimeWorkRequest task2WorkRequest = new OneTimeWorkRequest.Builder(Task2Worker.class)
                .build();

        WorkManager.getInstance(this).enqueueUniqueWork("task1", ExistingWorkPolicy.KEEP, task1WorkRequest);
        WorkManager.getInstance(this).enqueueUniqueWork("task2", ExistingWorkPolicy.KEEP, task2WorkRequest);
    }
}*/



  // Выполнение 3х задач последовательно
public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private static final int TASK_1 = 1;
    private static final int TASK_2 = 2;
    private static final int TASK_3 = 3;

    private Handler mHandler;
    private ImageView mImageView1;
    private ImageView mImageView2;
    private ImageView mImageView3;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mImageView1 = findViewById(R.id.imageView1);
        mImageView2 = findViewById(R.id.imageView2);
        mImageView3 = findViewById(R.id.imageView3);

        mHandler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case TASK_1:
                        Log.d(TAG, "Task 1 completed");
                        mImageView1.setImageResource(R.drawable.image1);
                        startTask2();
                        break;
                    case TASK_2:
                        Log.d(TAG, "Task 2 completed");
                        mImageView2.setImageResource(R.drawable.image2);
                        mImageView2.setVisibility(View.VISIBLE);
                        startTask3();
                        break;
                    case TASK_3:
                        Log.d(TAG, "Task 3 completed");
                        mImageView3.setImageResource(R.drawable.image3);
                        mImageView3.setVisibility(View.VISIBLE);
                        break;
                }
            }
        };

        startTask1();
    }

    private void startTask1() {
        new Thread(() -> {
            // Выполняем код для задачи 1
            // ...
            try {
                Thread.sleep(4000); // Симулируем выполнение задачи 1 в течение 2 секунд
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            mHandler.obtainMessage(TASK_1).sendToTarget();
        }).start();
    }

    private void startTask2() {
        new Thread(() -> {
            // Выполняем код для задачи 2
            // ...
            try {
                Thread.sleep(4000); // Симулируем выполнение задачи 2 в течение 2 секунд
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            mHandler.obtainMessage(TASK_2).sendToTarget();
        }).start();
    }

    private void startTask3() {
        new Thread(() -> {
            // Выполняем код для задачи 3
            // ...
            try {
                Thread.sleep(4000); // Симулируем выполнение задачи 3 в течение 2 секунд
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            mHandler.obtainMessage(TASK_3).sendToTarget();
        }).start();
    }
}


