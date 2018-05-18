package com.njstyle.jobschedulertest.common

import android.util.Log

/**
 * Created by njstyle on 17. 11. 30.
 */
object LogMgr {
    fun getTag(customTag: String = "", isShowThreadName: Boolean = false): String {
        val stringBuilder = StringBuilder()
        var stackTrace: StackTraceElement? = null
        var simpleClassName = ""

        for (i in 5..7) {
            stackTrace = Thread.currentThread().stackTrace[i]
            simpleClassName = stackTrace.className.substringAfterLast(".").substringBefore("$")
            if (simpleClassName != this::class.java.simpleName) break
        }

        if (customTag.isNotEmpty()) {
            stringBuilder.append("[$customTag] ")
        }

        if (isShowThreadName) {
            stringBuilder.append("${Thread.currentThread().name}: ")
        }

        stringBuilder.append("$simpleClassName > " +
                "${stackTrace?.methodName}:${stackTrace?.lineNumber}")

        return stringBuilder.toString()
    }

    fun isDebug() = true

    fun d(tag: String, msg: String) {
        if (isDebug()) Log.d(getTag(tag), msg)
    }

    fun d(msg: String = "called") {
        if (isDebug()) Log.d(getTag(), msg)
    }

    fun dt(msg: String = "called") {
        if (isDebug()) Log.d(getTag(isShowThreadName = true), msg)
    }
}