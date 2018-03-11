package com.holinaty.treegenerator

import processing.core.*
import processing.core.PApplet

class Leaf(val pa: PApplet) {

    companion object {
        var heightOffset = 100
        fun setHeightOffset(offset: Float) {
            heightOffset = offset.toInt()
        }
    }

    var pos = PVector()
    var reached = false

    init {
        pos.x = pa.random(50f, pa.width.toFloat()-50f)
        pos.y = pa.random(50f,pa.height.toFloat()- heightOffset)
    }

    fun show() {
        pa.noStroke()
        pa.ellipse(pos.x,pos.y, 1f,1f)
    }



}