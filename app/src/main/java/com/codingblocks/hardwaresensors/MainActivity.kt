package com.codingblocks.hardwaresensors

import android.graphics.Color
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.getSystemService
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity(), SensorEventListener {

    lateinit var sensorManager: SensorManager
    lateinit var proxSensor: Sensor
    lateinit var accelSensor: Sensor

    val colors = arrayOf(Color.RED, Color.GREEN, Color.BLUE, Color.CYAN, Color.MAGENTA, Color.YELLOW)

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}

    override fun onSensorChanged(event: SensorEvent?) {
//        if (event!!.values[0] > 0) {
//            flProxIndicator.setBackgroundColor(colors[Random.nextInt(6)])
//        }
        Log.d("HWSENS", """
            ----
            ax = ${event!!.values[0]}
            ay = ${event!!.values[1]}
            az = ${event!!.values[2]}
            ----
        """.trimIndent())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sensorManager = getSystemService<SensorManager>()!!
        proxSensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY)
        accelSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

    }


    override fun onResume() {
        super.onResume()
        sensorManager.registerListener(
                this, accelSensor, 1000 * 1000
        )
    }

    override fun onPause() {
        sensorManager.unregisterListener(this)
        super.onPause()
    }
}