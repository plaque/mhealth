package plaque.mhealth.services

import android.app.Service
import android.content.Context
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.IBinder

/**
 * Created by szymon on 06.10.17.
 */
class FallMonitorService: Service(), SensorEventListener {

    private lateinit var  sensorManager: SensorManager
    private lateinit var accelerometer: Sensor
    private var lastUpdate: Long = 0

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {

    }

    override fun onSensorChanged(sensorEvent: SensorEvent?) {
        val sensor: Sensor? = sensorEvent?.sensor

        if(sensor?.type == Sensor.TYPE_ACCELEROMETER){
            val x: Float = sensorEvent.values[0]
            val y: Float = sensorEvent.values[1]
            val z: Float = sensorEvent.values[2]

            val curTime: Long = System.currentTimeMillis()

            val acceleration = Math.sqrt(Math.pow(x.toDouble(), 2.0) + Math.pow(y.toDouble(), 2.0)
                        + Math.pow(z.toDouble(), 2.0))

                System.out.println(acceleration)

        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL)


        return Service.START_STICKY
    }

    override fun onBind(p0: Intent?): IBinder = null!!

}