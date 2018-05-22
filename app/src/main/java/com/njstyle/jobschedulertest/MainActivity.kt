package com.njstyle.jobschedulertest

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.njstyle.jobschedulertest.job.ConnectivityJob.Companion.connectivityCheckJobSchedule

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        connectivityCheckJobSchedule(this)
    }
}
