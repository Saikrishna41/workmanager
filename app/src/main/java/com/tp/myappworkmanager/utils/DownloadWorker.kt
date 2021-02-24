package com.tp.myappworkmanager.utils

import android.content.Context
import android.util.Log
import androidx.work.ListenableWorker
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.tp.myappworkmanager.LOG_TAG
import java.lang.Exception

class DownloadWorker(ctx: Context, params: WorkerParameters) : Worker(ctx, params) {

    override fun doWork(): ListenableWorker.Result {

        try {

            for (i in 0..5000) {

                Log.d(LOG_TAG, "Downloading  work $i")
            }

            return ListenableWorker.Result.success()

        } catch (e: Exception) {

            return ListenableWorker.Result.failure()
        }
    }

}