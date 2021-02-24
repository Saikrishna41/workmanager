package com.tp.myappworkmanager.utils

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.tp.myappworkmanager.LOG_TAG
import java.lang.Exception

class CompressingWorker(ctx : Context, params : WorkerParameters) : Worker(ctx, params) {

    override fun doWork(): Result {

        try {

            for (i in 0..800) {

                Log.d(LOG_TAG, "Compressing   work $i")
            }

            return Result.success()

        } catch (e: Exception) {

            return Result.failure()
        }
    }

}