@file:JvmName("runSuperShape2D")

package com.holinaty.supershape2d

import processing.core.PApplet
import processing.core.PConstants

class SuperShape2D : PApplet() {

    var b = 1f
    var a = 1f
    var m = 12f

    var n1 = 10f
    var n2 = 5f
    var n3 = 5f

    override fun settings() {
        size(600,600)
    }

    override fun setup() {

    }

    override fun draw() {
        background(80)
        fill(255)
        translate(width/2f, height/2f)

        val radius = 200f
        val total = 500f
        val increment = PConstants.TWO_PI / total
        var angle = 0f
//        m = map(mouseX.toFloat(),0f, width.toFloat(), 0f,10f)
//        a = map(mouseY.toFloat(),0f, width.toFloat(), 0f,10f)

        beginShape()
        while (angle < PConstants.TWO_PI) {
            val r = supershape2D(angle)
            val x = radius * r * PApplet.cos(angle)
            val y = radius * r * PApplet.sin(angle)
            vertex(x, y)
            angle += increment
        }
        endShape(PConstants.CLOSE)
    }

    fun supershape2D(theta: Float): Float {
        var part1 = (1f / a) * cos(theta * m / 4f)
        part1 = abs(part1)
        part1 = pow(part1, n2)

        var part2 = (1f / b) * sin(theta * m / 4f)
        part2 = abs(part2)
        part2 = pow(part2, n3)

        var part3 = pow(part1 + part2, 1f / n1)

        if (part3 == 0f) {
            return 0f
        }
        return (1 / part3)
    }

    override fun mouseClicked() {}

}

fun main(args: Array<String>) {
    val processingArgs: Array<String> = arrayOf("SuperShape2D")
    val superShape2D = SuperShape2D()
    PApplet.runSketch(processingArgs, superShape2D)

}
