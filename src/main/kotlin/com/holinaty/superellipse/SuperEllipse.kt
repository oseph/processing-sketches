@file:JvmName("run-SuperEllipse")

package com.holinaty.superellipse

import processing.core.PApplet
import processing.core.PConstants
import kotlin.math.sign

class SuperEllipse : PApplet() {

    var b = 250
    var a = 250
    var n = 1f
    var m = 1f

    override fun settings() {
        size(600,600)
    }

    override fun setup() {
        noStroke()
    }

    override fun draw() {
        background(80)
        beginShape()
        translate(width/2f,height/2f)
        var angle = 0f
        n = map(mouseX.toFloat(), 0f, width.toFloat(), 0f,10f)
        m = map(mouseY.toFloat(), 0f, width.toFloat(), 0f,10f)
        n = constrain(n, 0.000001f, 10f)
        m = constrain(m, 0.000001f, 10f)
        while(angle < PConstants.TWO_PI) {
            var na = 2.0/n
            var nb = 2.0/m
            var x = pow(abs(cos(angle)), na.toFloat()) * a * sign(cos(angle))
            var y = pow(abs(sin(angle)), nb.toFloat()) * b * sign(sin(angle))
            vertex(x,y)
            angle += 0.01f
        }
        endShape(PConstants.CLOSE)
    }

    override fun mouseClicked() {}

}

fun main(args: Array<String>) {
    val processingArgs: Array<String> = arrayOf("SpaceColonization")
    val superEllipse = SuperEllipse()
    PApplet.runSketch(processingArgs, superEllipse)
}