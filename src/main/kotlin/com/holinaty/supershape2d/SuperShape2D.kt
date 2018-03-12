package com.holinaty.supershape2d

import processing.core.PApplet
import processing.core.PConstants

class SuperShape2DApplet : PApplet() {

    val COLS = 5
    val ROWS = 5
    var superShapes = ArrayList<SuperShape2D>()

    override fun settings() {
        size(600,600)
    }

    override fun setup() {
        noStroke()
        for (x in 0..COLS) {
            for (y in 0..ROWS) {
                superShapes.add(SuperShape2D(x*120f,y*120f,40f))
            }
        }
    }

    override fun draw() {
        background(80)
        for (s in superShapes) {
            s.update()
            s.draw()
        }
    }

    inner class SuperShape2D(var x: Float, var y: Float, var radius: Float) {

        var b = 1f
        var a = 1f
        var m = floor(random(1f,21f))
        var n1 = 10f
        var n2 = 5f
        var n3 = 5f
        var id = random(50000f).toInt()
        var rotAng = 0.0f
        val rotAngDir = if (random(1f) > 0.5f) 1f else -1f

        fun draw() {
            val total = 360
            val increment = PConstants.TWO_PI / total
            var angle = 0f
            rotAng += increment

            fill(255)
            pushMatrix()
            translate(x, y)
            rotate(rotAng*rotAngDir)
            beginShape()
            while (angle < PConstants.TWO_PI) {
                val r = superShape2D(angle)
                val x = radius * r * PApplet.cos(angle)
                val y = radius * r * PApplet.sin(angle)
                vertex(x, y)
                angle += increment
            }
            endShape(PConstants.CLOSE)
            popMatrix()
        }

        fun update() {
            n1 = map(noise(frameCount*0.05f+id),0f, 1f, 1f,12f)
            n2 = map(noise(frameCount*0.05f+id+9000),0f, 1f, 0f,12f)
            n3 = n2
        }

        private fun superShape2D(theta: Float): Float {
            var part1 = (1f / a) * cos(theta * m / 4f)
            part1 = abs(part1)
            part1 = pow(part1, n2)

            var part2 = (1f / b) * sin(theta * m / 4f)
            part2 = abs(part2)
            part2 = pow(part2, n3)

            val part3 = pow(part1 + part2, 1f / n1)

            if (part3 == 0f) {
                return 0f
            }
            return (1 / part3)
        }

    }

}