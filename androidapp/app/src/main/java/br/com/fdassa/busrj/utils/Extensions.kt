package br.com.fdassa.busrj.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory


fun Context.bitmapDescriptorFromVector(vectorResId: Int): BitmapDescriptor? {
    return ContextCompat.getDrawable(this, vectorResId)?.let {
        it.setBounds(0, 0, it.intrinsicWidth, it.intrinsicHeight)
        val bitmap = Bitmap.createBitmap(it.intrinsicWidth, it.intrinsicHeight, Bitmap.Config.ARGB_8888)
        it.draw(Canvas(bitmap))
        BitmapDescriptorFactory.fromBitmap(bitmap)
    }
}

fun View.hide() {
    visibility = GONE
}

fun View.show() {
    visibility = VISIBLE
}

inline fun <reified T : Activity> Context.startActivity(noinline extras: (Intent.() -> Unit)? = null) {
    startActivity(Intent(this, T::class.java).also {
        extras?.invoke(it)
    })
}