package com.njstyle.jobschedulertest.job

import android.app.job.JobInfo
import android.app.job.JobParameters
import android.app.job.JobScheduler
import android.app.job.JobService
import android.content.ComponentName
import android.content.Context
import android.net.*
import android.net.wifi.WifiManager
import com.njstyle.jobschedulertest.common.LogMgr

class ConnectivityJob: JobService() {
    private val connectivityManager
            by lazy { applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager }
    private val wifiManager by lazy { getSystemService(Context.WIFI_SERVICE) as WifiManager }
    private val networkCallback: ConnectivityManager.NetworkCallback by lazy {
        object : ConnectivityManager.NetworkCallback() {
            override fun onCapabilitiesChanged(network: Network?, networkCapabilities: NetworkCapabilities?) {
                super.onCapabilitiesChanged(network, networkCapabilities)

                print("onCapabilitiesChanged")
            }

            override fun onLinkPropertiesChanged(network: Network?, linkProperties: LinkProperties?) {
                super.onLinkPropertiesChanged(network, linkProperties)

                print("onLink")
            }

//            override fun onAvailable(network: Network?) {
//                super.onAvailable(network)
//
//                print("onAvailable")
//            }

            override fun onLost(network: Network?) {
                super.onLost(network)

                print("onLost")
            }

            override fun onUnavailable() {
                super.onUnavailable()

                print("onUnavailable")
            }

            override fun onLosing(network: Network?, maxMsToLive: Int) {
                super.onLosing(network, maxMsToLive)

                print("onLosing")
            }
        }
    }

    companion object {
        fun connectivityCheckJobSchedule(context: Context) {
            val jobScheduler = context.applicationContext.getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler

            val jobInfo = JobInfo.Builder(0, ComponentName(context.applicationContext, ConnectivityJob::class.java))
                    .setMinimumLatency(1000)
                    .setOverrideDeadline(2000)
                    .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
                    .setPersisted(true)
                    .build()

            jobScheduler.schedule(jobInfo)
        }
    }

    private fun print(functionName: String) {
        connectivityManager.activeNetworkInfo?.let{
            if (it.type == ConnectivityManager.TYPE_WIFI) {
                LogMgr.d("$functionName -> ssid: ${wifiManager.connectionInfo.ssid}")
            }

            LogMgr.d("$functionName -> type: ${it.type}")
            LogMgr.d("$functionName -> isConnected: ${it.isConnected}")
            LogMgr.d("$functionName -> subtypeName: ${it.subtypeName}")
            LogMgr.d("$functionName -> typeName: ${it.typeName}")

            LogMgr.d(" ")
        }
    }

    override fun onStopJob(jobParameters: JobParameters?): Boolean {
        LogMgr.d()
//        connectivityManager.unregisterNetworkCallback(networkCallback)

        return true
    }

    override fun onStartJob(jobParameters: JobParameters?): Boolean {
        LogMgr.d("$this $networkCallback")
//        print("onStartJob")
        connectivityManager.registerNetworkCallback(NetworkRequest.Builder().build(), networkCallback)

        return true
    }
}