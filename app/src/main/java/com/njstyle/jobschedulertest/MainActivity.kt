package com.njstyle.jobschedulertest

import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.njstyle.jobschedulertest.job.ConnectivityJob
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    val jobScheduler by lazy { getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val jobInfo = JobInfo.Builder(0, ComponentName(applicationContext, ConnectivityJob::class.java))
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
                .setPersisted(true)
                .build()

        jobScheduler.schedule(jobInfo)
    }
}
