package com.example.eighthprac;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class Task1Worker extends Worker {

    public Task1Worker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        // Выполняем код для задачи 1
        // ...
        return Result.success();
    }
}