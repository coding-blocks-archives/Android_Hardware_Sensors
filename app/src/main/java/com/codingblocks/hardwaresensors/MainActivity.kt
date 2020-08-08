package com.codingblocks.hardwaresensors

import android.graphics.Color
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.getSystemService
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity(), SensorEventListener {

    lateinit var sensorManager: SensorManager
    lateinit var proxSensor: Sensor

    val colors = arrayOf(Color.RED, Color.GREEN, Color.BLUE, Color.CYAN, Color.MAGENTA, Color.YELLOW)

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}

    override fun onSensorChanged(event: SensorEvent?) {
        if (event!!.values[0] > 0) {
            flProxIndicator.setBackgroundColor(colors[Random.nextInt(6)])
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sensorManager = getSystemService<SensorManager>()!!
        proxSensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY)

    }


    override fun onResume() {
        super.onResume()
        sensorManager.registerListener(
                this, proxSensor, 1000 * 1000
        )
    }

    override fun onPause() {
        sensorManager.unregisterListener(this)
        super.onPause()
    }
}