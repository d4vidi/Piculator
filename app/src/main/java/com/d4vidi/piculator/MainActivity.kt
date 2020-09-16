package com.d4vidi.piculator

import android.app.Activity
import android.os.Bundle
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.test.espresso.IdlingResource
import androidx.test.espresso.idling.CountingIdlingResource
import java.lang.ref.WeakReference
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.atomic.AtomicBoolean

class PiculatorIdlingResource : IdlingResource {
    private var registeredCallback: IdlingResource.ResourceCallback? = null
    private val isIdling = AtomicBoolean(true)

    override fun getName() = "PiculatorIdlingResource"

    override fun isIdleNow() = isIdling.get()

    override fun registerIdleTransitionCallback(callback: IdlingResource.ResourceCallback?) {
        this.registeredCallback = callback
    }

    fun latch() {
        isIdling.set(false)
    }

    fun release() {
        isIdling.set(true)
        this.registeredCallback?.onTransitionToIdle()
    }
}

class MainActivity : Activity() {

    private var idlingResource: CountingIdlingResource? = null
    private var customIdlingResource: PiculatorIdlingResource? = null
    private lateinit var threadExec: ExecutorService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val txtPiResultWR = WeakReference(findViewById<TextView?>(R.id.txtPiResult))
        val progressPiCalcWR = WeakReference(findViewById<ProgressBar?>(R.id.progressPiCalc))

        this.threadExec = Executors.newFixedThreadPool(3)

        val btnTriggerPi = findViewById<Button>(R.id.btnTriggerPi)
        btnTriggerPi!!.setOnClickListener {
            txtPiResultWR.get()!!.visibility = INVISIBLE
            progressPiCalcWR.get()!!.visibility = VISIBLE

btnTriggerPi.post {
    Thread.sleep(10000)
    progressPiCalcWR.get()?.visibility = INVISIBLE
    txtPiResultWR.get()?.visibility = VISIBLE
}

//            this.threadExec.execute {
//                idlingResource?.increment()
//                customIdlingResource?.latch()
//
//                val result = Piculator().calculate(100000000).toString()
//                txtPiResultWR.get()?.post {
//                    progressPiCalcWR.get()?.visibility = INVISIBLE
//
//                    txtPiResultWR.get()?.text = result
//                    txtPiResultWR.get()?.visibility = VISIBLE
//
//                    idlingResource?.decrement()
//                    customIdlingResource?.release()
//                }
//            }
        }
    }

    fun getTestIdlingResource(): IdlingResource {
        if (idlingResource == null) {
            idlingResource = CountingIdlingResource("Piculator-idling-resource")
        }
        return idlingResource!!
    }

    fun getIdlingResource(): IdlingResource {
        if (customIdlingResource == null) {
            customIdlingResource = PiculatorIdlingResource()
        }
        return customIdlingResource!!
    }
}
