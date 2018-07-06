package com.jzq.workmanager.demo;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.jzq.workmanager.demo.workmanager.JWorker;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

import androidx.work.Constraints;
import androidx.work.ExistingWorkPolicy;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkContinuation;
import androidx.work.WorkManager;
import androidx.work.Worker;

public class MainActivity extends AppCompatActivity {

    private static final String ALARM_CLOCK = "alarm_clock";
    /**
     * Worker：指定我们需要执行的任务。 WorkManager API包含一个抽象的Worker类，我们需要继承这个类并且在这里执
     * 行工作。

     WorkRequest：代表一个单独的任务。一个WorkRequest 对象指定哪个 Woker 类应该执行该任务，而且，我们还可以向
     WorkRequest 对象添加详细信息，指定任务运行的环境等。每个 WorkRequest 都有一个自动生成的唯一ID，我们可以使
     用该ID来执行诸如取消排队的任务或获取任务状态等内容。 WorkRequest 是一个抽象类，在代码中，我们需要使用它的直
     接子类，OneTimeWorkRequest 或 PeriodicWorkRequest.。

     WorkRequest.Builder：用于创建WorkRequest对象的辅助类，同样，我们要使用它的一个子类，OneTimeWorkRequest
     .Builder 和PeriodicWorkRequest.Builder 。

     Constraints：指定任务在何时运行（例如，“仅在连接到网络时”）。我们可以通过Constraints.Builder 来创建
     Constraints对象，并在创建WorkRequest之前，将 Constraints 对象传递给 WorkRequest.Builder。

     WorkManager：将WorkRequest入队和管理WorkRequest。我们要将WorkRequest对象传递给 WorkManager ，
     WorkManager 以这样的方式调度任务，以便分散系统资源的负载，同时遵守我们指定的约束条件。

     WorkStatus：包含有关特定任务的信息。WorkManager 为每个 WorkRequest 对象提供一个LiveData，LiveData持有
     一个WorkStatus对象，通过观察LiveData，我们可以确定任务的当前状态，并在任务完成后获取返回的任何值。
     */
    /**
     * Worker
     * WorkManager
     * WorkRequest:OneTimeWorkRequest 或 PeriodicWorkRequest
     * Constraints
     * WorkStatus
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void startAlarmClick(View view) {
        uuid = requestWork(10);
    }


    private UUID uuid;

    private UUID requestWork(long duration) {
        WorkManager workManager = WorkManager.getInstance();
        if (null != workManager) {
            OneTimeWorkRequest request = new OneTimeWorkRequest.Builder(JWorker.class)
                    .setInitialDelay(duration, TimeUnit.SECONDS)
                    .addTag("image_manipulation_work")
                    .build();
            workManager.enqueue(request);
            return request.getId();
        }
        return null;
    }

    //
    public void cancelWorkById() {
        WorkManager workManager = WorkManager.getInstance();
        if (null != workManager && null != uuid) {
            workManager.cancelWorkById(uuid);
        }
    }

}
