package com.ankit.stopwatch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var timeTextView: TextView
    private lateinit var startButton: Button
    private lateinit var stopButton: Button
    private lateinit var resetButton: Button

    private var timeSeconds = 0L
    private var isRunning = false

    private val handler = Handler()
    private val runnable = object : Runnable {
        override fun run() {
            timeSeconds++
            updateTime()
            handler.postDelayed(this, 1000)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize UI components
        timeTextView = findViewById(R.id.timeTextView)
        startButton = findViewById(R.id.startButton)
        stopButton = findViewById(R.id.stopButton)
        resetButton = findViewById(R.id.resetButton)

        // Set up button click listeners
        startButton.setOnClickListener { startTimer() }
        stopButton.setOnClickListener { stopTimer() }
        resetButton.setOnClickListener { resetTimer() }
    }

    private fun startTimer() {
        if (!isRunning) {
            handler.post(runnable)
            isRunning = true
        }
    }

    private fun stopTimer() {
        if (isRunning) {
            handler.removeCallbacks(runnable)
            isRunning = false
        }
    }

    private fun resetTimer() {
        stopTimer()
        timeSeconds = 0
        updateTime()
    }

    private fun updateTime() {
        val minutes = timeSeconds / 60
        val seconds = timeSeconds % 60
        timeTextView.text = String.format("%02d:%02d", minutes, seconds)
    }
}