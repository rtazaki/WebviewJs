package com.example.benchmark

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.annotation.LayoutRes
import androidx.benchmark.junit4.BenchmarkRule
import androidx.benchmark.junit4.measureRepeated
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.webviewjs.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Benchmark, which will execute on an Android device.
 *
 * The body of [BenchmarkRule.measureRepeated] is measured in a loop, and Studio will
 * output the result. Modify your code to see how it affects performance.
 */
@RunWith(AndroidJUnit4::class)
class ExampleBenchmark {

    @get:Rule
    val benchmarkRule = BenchmarkRule()

    @Test
    fun log() {
        benchmarkRule.measureRepeated {
            Log.d("LogBenchmark", "the cost of writing this log method will be measured")
        }
    }

    // https://developer.android.com/studio/profile/benchmark#write-benchmark
    @Test
    fun simpleViewInflate() {
        // ApplicationProvider.getApplicationContext()だとエラー
        val context = ApplicationProvider.getApplicationContext<Context>()
        val inflater = LayoutInflater.from(context)
        val root = FrameLayout(context)

        // keepRunningは使えない
        benchmarkRule.measureRepeated {
            inflater.inflate(R.layout.activity_main, root, false)
        }
    }

    // hello-benchmarkより
    private val inflater = LayoutInflater.from(ApplicationProvider.getApplicationContext<Context>())
    private fun inflate(@LayoutRes resource: Int) = inflater.inflate(resource, null, false)

    @Test
    fun mainActivityInflation() = benchmarkRule.measureRepeated {
        inflate(R.layout.activity_main)
    }
}