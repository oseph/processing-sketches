package com.holinaty.superellipse

import processing.core.PApplet
import processing.core.PConstants
import kotlin.math.sign

class Superellipse : PApplet() {

    var b = 250f
    var a = 250f
    var n = 1f
    var m = 12f

    override fun settings() {
        size(600,600)
    }

    override fun setup() {
        noStroke()
    }

    override fun draw() {
        background(80)
        fill(255)
        drawSuperellipse(width/2f, height/2f)
    }


    fun drawSuperellipse(x: Float, y: Float) {
        beginShape()
        translate(x,y)
        var angle = 0f
        n = map(mouseX.toFloat(), 0f, width.toFloat(), 0f,10f)
        m = map(mouseY.toFloat(), 0f, width.toFloat(), 0f,10f)
        n = constrain(n, 0.000001f, 10f)
        m = constrain(m, 0.000001f, 10f)
        while(angle < PConstants.TWO_PI) {
            var na = 2.0/n
            var nb = 2.0/m
            var xx = pow(abs(cos(angle)), na.toFloat()) * a * sign(cos(angle))
            var yy = pow(abs(sin(angle)), nb.toFloat()) * b * sign(sin(angle))
            vertex(xx,yy)
            angle += 0.01f
        }
        endShape(PConstants.CLOSE)
    }

    override fun mouseClicked() {}

}

