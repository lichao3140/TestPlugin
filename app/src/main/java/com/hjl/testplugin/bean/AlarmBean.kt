package com.hjl.storage.bean

import android.graphics.drawable.Drawable

class AlarmBean(var type: String, var drawColor: Drawable) {

    init {
        drawColor.setBounds(0, 0, drawColor.minimumWidth, drawColor.minimumHeight)
    }
}