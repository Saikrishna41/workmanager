package com.tp.myappworkmanager.utils

import android.content.Context
import android.util.Log
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.tp.myappworkmanager.Globals.Companion.KEY_COUNT_VALUE
import com.tp.myappworkmanager.LOG_TAG
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

class UploadWorker(ctx: Context, params: WorkerParameters) : Worker(ctx, params) {
    companion object {

        const val KEY_WORKER = "key_worker"
    }

    override fun doWork(): Result {

        try {

            val count = inputData.getInt(KEY_COUNT_VALUE, 0)
            for (i in 0..count) {

                Log.d(LOG_TAG, "Upload work $i")
            }
//            val time = SimpleDateFormat("dd/mm/yyyy hh:mm:ss")
//
//            val currDate = time.format(Date())
//            val outputData = Data.Builder().putString(KEY_WORKER, currDate).build()

            val time = SimpleDateFormat("dd/mm/yyyy hh:mm:ss", Locale.US)

            val currDate = time.format(Date())

            val outputData = Data.Builder().putString(KEY_WORKER, currDate.toString()).build()

            return Result.success(outputData)

        } catch (e: Exception) {

            return Result.failure()
        }
    }

}