package com.holinaty.superellipse

import processing.core.PApplet
import processing.core.PConstants
import kotlin.math.sign

class SuperellipseApplet : PApplet() {

    inner class Superellipse(val x: Float, val y: Float, val radius: Float) {
        var b = radius + random(10f)
        var a = radius + random(10f)
        var n = random(0.1f,1f)
        var m = random(1f,20f)
        var id = random(10000f).toInt()
        var speed = random(0.003f,0.015f)
        var col = color(0)

        fun draw() {
            fill(col)
            beginShape()
            pushMatrix()
            translate(x,y)
            var angle = 0f
            while(angle < PConstants.TWO_PI) {
                var na = 2.0/n
                var nb = 2.0/m
                var xx = pow(abs(cos(angle)), na.toFloat()) * a * sign(cos(angle))
                var yy = pow(abs(sin(angle)), nb.toFloat()) * b * sign(sin(angle))
                vertex(xx,yy)
                angle += 0.1f
            }
            endShape(PConstants.CLOSE)
            popMatrix()
        }

        fun update() {
            col = color(
                    sin(id+frameCount*(speed+0.0011f))*127f+127f,
                    sin(id+frameCount*(speed+0.0062f))*127f+127f,
                    sin(id+frameCount*(speed+0.0082f))*127f+127f
            )
            n = map(noise(id+frameCount*speed), 0f,1f, 0f,4f)
            m = map(noise((id*2)+frameCount*speed), 0f,1f, 0f,4f)
        }
    }

    val COLS = 5
    val ROWS = 5
    var superEllipses = ArrayList<Superellipse>()

    override fun settings() {
        size(900,900)
    }

    override fun setup() {
        noStroke()
        val spacing = width / COLS.toFloat()
        val radius = width/COLS.toFloat()*0.36f
        for (x in 0..COLS) {
            for (y in 0..ROWS){
                superEllipses.add(Superellipse(x*spacing,y*spacing, radius ))
            }
        }
    }

    override fun draw() {
        background(80)
        fill(255)
        for (s in superEllipses) {
            s.update()
            s.draw()
        }
    }

    override fun mouseClicked() {}

}

