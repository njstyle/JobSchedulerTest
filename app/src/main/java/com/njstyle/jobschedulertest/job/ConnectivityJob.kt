package com.njstyle.jobschedulertest.job

import android.app.job.JobParameters
import android.app.job.JobService
import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
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

                LogMgr.d()
            }
        }
    }

    private fun print(functionName: String) {
        val networkInfo = connectivityManager.activeNetworkInfo

        if (networkInfo.type == ConnectivityManager.TYPE_WIFI) {
            LogMgr.d("$functionName -> ssid: ${wifiManager.connectionInfo.ssid}")

        }

        LogMgr.d("$functionName -> type: ${networkInfo.type}")
        LogMgr.d("$functionName -> isConnected: ${networkInfo.isConnected}")
        LogMgr.d("$functionName -> subtypeName: ${networkInfo.subtypeName}")
        LogMgr.d("$functionName -> typeName: ${networkInfo.typeName}")
    }

    override fun onStopJob(jobParameters: JobParameters?): Boolean {
        LogMgr.d()
//        connectivityManager.unregisterNetworkCallback(networkCallback)

        return true
    }

    override fun onStartJob(jobParameters: JobParameters?): Boolean {
        LogMgr.d()
        print("onStartJob")
//        connectivityManager.registerNetworkCallback(NetworkRequest.Builder().build(), networkCallback)

        return true
    }
}