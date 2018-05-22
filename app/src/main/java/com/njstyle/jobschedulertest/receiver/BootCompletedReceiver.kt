package com.njstyle.jobschedulertest.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.njstyle.jobschedulertest.common.LogMgr
import com.njstyle.jobschedulertest.job.ConnectivityJob.Companion.connectivityCheckJobSchedule

class BootCompletedReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        LogMgr.d()

        context?.let { connectivityCheckJobSchedule(it) }
    }
}