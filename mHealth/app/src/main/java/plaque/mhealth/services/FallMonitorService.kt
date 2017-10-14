package plaque.mhealth.services

import android.app.Service
import android.content.Context
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.IBinder
import plaque.mhealth.ui.activities.FallDetectedActivity
import plaque.mhealth.ui.login.LoginActivity

/**
 * Created by szymon on 06.10.17.
 */
class FallMonitorService: Service(), SensorEventListener {

    private lateinit var  sensorManager: SensorManager
    private lateinit var accelerometer: Sensor
    private var fallTime: Long = 0
    private var currTime: Long = 0
    private var firstConditionMet: Boolean = false
    private var secondConditionMet: Boolean = false
    private var fallDetected: Boolean = false
    private val fallThreshold: Float = 9.0f
    private val stillThreshold: Float = 0.2f

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {

    }

    override fun onSensorChanged(sensorEvent: SensorEvent?) {
        val sensor: Sensor? = sensorEvent?.sensor

        if(sensor?.type == Sensor.TYPE_ACCELEROMETER){
            val x: Float = sensorEvent.values[0]
            val y: Float = sensorEvent.values[1]
            val z: Float = sensorEvent.values[2]

            val acceleration = Math.abs(Math.sqrt(Math.pow(x.toDouble(), 2.0) + Math.pow(y.toDouble(), 2.0)
                        + Math.pow(z.toDouble(), 2.0)) - 9.8)


            currTime = System.currentTimeMillis()

            checkFallCondition(acceleration)
            bigAccelerationForTooLong()
            checkStillCondition(acceleration)
            checkFallDetectedConditions()
            fallDetectedFalse()


        }
    }

    private fun checkFallCondition(acceleration: Double){
        if(acceleration > fallThreshold){
            firstConditionMet = true
            fallTime = System.currentTimeMillis()
        }
    }

    private fun fallDetectedFalse(){
        if((currTime - fallTime > 10000)){
            fallDetected = false
        }
    }

    private fun checkStillCondition(acceleration: Double){
        if(firstConditionMet && (acceleration < stillThreshold) && (currTime - fallTime > 500)){
            secondConditionMet = true
        }
    }

    private fun bigAccelerationForTooLong(){
        if(firstConditionMet && (currTime - fallTime > 1000)){
            firstConditionMet = false
            secondConditionMet = false
        }
    }

    private fun checkFallDetectedConditions(){
        if(firstConditionMet && secondConditionMet && !fallDetected){
            fallDetected = true
            showWarning()
        }
    }

    private fun showWarning() {
        System.out.println("Fall detected")
        val intent = Intent(this, FallDetectedActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL)


        return Service.START_STICKY
    }

    override fun onBind(p0: Intent?): IBinder = null!!

}