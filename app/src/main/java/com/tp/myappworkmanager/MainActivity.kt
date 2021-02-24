package com.tp.myappworkmanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.work.*
import com.tp.myappworkmanager.Globals.Companion.KEY_COUNT_VALUE
import com.tp.myappworkmanager.databinding.ActivityMainBinding
import com.tp.myappworkmanager.utils.CompressingWorker
import com.tp.myappworkmanager.utils.DownloadWorker
import com.tp.myappworkmanager.utils.FilteringWorker
import com.tp.myappworkmanager.utils.UploadWorker
import com.tp.myappworkmanager.utils.UploadWorker.Companion.KEY_WORKER
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btn.setOnClickListener {

            setPeriodicWorkRequest()
        }
    }

    private fun setUpRequest() {

        val data: Data = Data.Builder()
            .putInt(KEY_COUNT_VALUE, 125).build()
        val parallelWorks = mutableListOf<OneTimeWorkRequest>()


        val workManager = WorkManager.getInstance(applicationContext)

        val constraints =
            Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()


        val uploadRequest =
            OneTimeWorkRequest.Builder(UploadWorker::class.java).setConstraints(constraints)
                .setInputData(data).build()

        val filteringRequest = OneTimeWorkRequest.Builder(FilteringWorker::class.java).build()

        val downloadingRequest = OneTimeWorkRequest.Builder(DownloadWorker::class.java).build()

        parallelWorks.add(downloadingRequest)

        parallelWorks.add(filteringRequest)


        val compressingWorkRequest =
            OneTimeWorkRequest.Builder(CompressingWorker::class.java).build()


//        workManager.beginWith(filteringRequest).then(compressingWorkRequest).then(uploadRequest)
//            .enqueue()

        workManager.beginWith(parallelWorks).then(compressingWorkRequest).then(uploadRequest)
            .enqueue()

        workManager.getWorkInfoByIdLiveData(uploadRequest.id).observe(this, Observer {

            Log.d(LOG_TAG, "Status : ${it.state.toString()}")
            if (it.state.isFinished) {
                val message: String? = data.getString(KEY_WORKER)
                Toast.makeText(this, message.toString(), Toast.LENGTH_LONG).show()
                binding.tv.setText(it.outputData.toString())

            }
        })
    }

    private fun setPeriodicWorkRequest() {

        val periodicWorkRequest = PeriodicWorkRequest.Builder(DownloadWorker::class.java,16,TimeUnit.MINUTES).build()

        val workManager = WorkManager.getInstance(applicationContext)

        workManager.enqueue(periodicWorkRequest)
    }
}