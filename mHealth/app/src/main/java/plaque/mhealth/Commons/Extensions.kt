package plaque.mhealth.Commons

import android.app.Activity
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.squareup.picasso.Picasso
import org.jetbrains.anko.find
import plaque.mhealth.R
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by szymon on 13.09.17.
 */
fun ViewGroup.inflate(layoutId: Int, attachToRoot: Boolean = false): View
        = LayoutInflater.from(context).inflate(layoutId, this, attachToRoot)


fun ImageView.loadImg(imageUrl: String) {
    if (TextUtils.isEmpty(imageUrl)) {
        Picasso.with(context).load(R.mipmap.ic_launcher).into(this)
    } else {
        Picasso.with(context).load(imageUrl).into(this)
    }
}
inline fun View.snackbar(message: String){
    val activity = context
    if(activity is Activity) Snackbar.make(activity.find(android.R.id.content), message, Snackbar.LENGTH_LONG).show()
    else throw IllegalStateException("View needs to be attached to an Activity.")
}

fun Calendar.getFullDate(): String {
    val format1: SimpleDateFormat = SimpleDateFormat("dd-MM-yyyy hh:mm")
    return format1.format(this.time)
}