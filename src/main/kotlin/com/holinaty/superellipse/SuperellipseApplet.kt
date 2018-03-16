package com.holinaty.superellipse

import processing.core.PApplet
import processing.core.PConstants
import kotlin.math.sign

class SuperellipseApplet : PApplet() {

    inner class Superellipse {
        private val x: Float
        private val y: Float
        private val radius: Float

        constructor(x: Float, y: Float, radius: Float) {
            this.x = x
            this.y = y
            this.radius = radius
            this.b = radius + random(10f)
            this.a = radius + random(10f)
            this.n = random(0.1f, 1f)
            this.m = random(1f, 20f)
            this.id = random(10000f).toInt()
            this.speed = random(0.003f, 0.015f)
            this.col = color(0)
        }

        private var b: Float
        private var a: Float
        private var n: Float
        private var m: Float
        private var id: Int
        private var speed: Float
        private var col: Int

        fun draw() {
            fill(col)
            beginShape()
            pushMatrix()
            translate(x,y)
            var angle = 0f
            while(angle < PConstants.TWO_PI) {
                val na = 2.0/n
                val nb = 2.0/m
                val xx = pow(abs(cos(angle)), na.toFloat()) * a * sign(cos(angle))
                val yy = pow(abs(sin(angle)), nb.toFloat()) * b * sign(sin(angle))
                vertex(xx,yy)
                angle += 0.1f
            }
            endShape(PConstants.CLOSE)
            popMatrix()
        }

        fun update() {
            col = color(
                    sin(id+frameCount*(speed+0.0011f))*80f+80f,
                    sin(id+frameCount*(speed+0.0022f))*127f+127f,
                    sin(id+frameCount*(speed+0.0025f))*127f+127f
            )
            n = map(noise(id+frameCount*speed), 0f,1f, 0f,4f)
            m = map(noise((id*2)+frameCount*speed), 0f,1f, 0f,4f)
        }
    }

    private val columns = 5
    private val rows = 5
    private var superEllipses = ArrayList<Superellipse>()

    override fun settings() {
        size(900,900)
    }

    override fun setup() {
        noStroke()
        val spacing = width / columns.toFloat()
        val radius = width/columns.toFloat()*0.36f
        for (x in 0..this.columns) {
            for (y in 0..rows){
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

