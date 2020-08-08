package com.codingblocks.hardwaresensors

import android.hardware.Sensor
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.content.getSystemService
import java.lang.UnsupportedOperationException

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sensorManager = getSystemService<SensorManager>()
        if (sensorManager == null) {
            Toast.makeText(this, "Could not get sensors", Toast.LENGTH_SHORT).show()
            finish()
        } else {
            val sensors = sensorManager.getSensorList(Sensor.TYPE_ALL)
            sensors.forEach {
                Log.d("HWSENS", """
                    ${it.name}  |  ${it.stringType}  |  ${it.vendor}
                """.trimIndent())
            }
        }


    }
}