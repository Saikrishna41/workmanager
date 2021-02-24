package com.tp.myappworkmanager.utils

import android.content.Context
import android.util.Log
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.tp.myappworkmanager.Globals
import com.tp.myappworkmanager.LOG_TAG
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

class FilteringWorker(ctx : Context, params : WorkerParameters) : Worker(ctx, params) {


    override fun doWork(): Result {

        try {

            for (i in 0..5000) {

                Log.d(LOG_TAG, "Filtering  work $i")
            }

            return Result.success()

        } catch (e: Exception) {

            return Result.failure()
        }
    }

}