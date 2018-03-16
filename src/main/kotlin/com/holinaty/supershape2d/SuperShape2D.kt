package com.holinaty.supershape2d

import processing.core.PApplet
import processing.core.PConstants


/*
* SuperShape2D
* Use spacebar to generate a new grid.
* Click on individual shapes to generate a new shape.
* */

class SuperShape2DApplet : PApplet() {

    private var COLUMNS = 5
    private var ROWS = 5
    private var superShapes = ArrayList<SuperShape2D>()
    private var bgcol = 0
    private var mmin = 1f
    private var mmax = 21f

    override fun settings() {
        size(600,600)
    }

    override fun setup() {
        noStroke()
        bgcol = color(random(255f))
        for (x in 0..COLUMNS) {
            for (y in 0..ROWS) {
                superShapes.add(SuperShape2D(x*120f,y*120f,40f,mmin, mmax))
            }
        }
    }

    override fun draw() {
        background(bgcol)
        for (s in superShapes) {
            s.update()
            s.draw()
        }
    }

    override fun mousePressed() {
        for (s in superShapes) {
            s.checkClick(mouseX.toFloat(),mouseY.toFloat())
        }
    }

    override fun keyPressed() {
        if (key == ' ') {
            bgcol = color(random(0f,100f))
            superShapes.clear()
            background(bgcol)
            COLUMNS = floor(random(3f,7f))
            ROWS = COLUMNS
            mmax = random(3f,50f)
            var spac = width/COLUMNS.toFloat()
            var spacr = spac*0.3f
            for (x in 0..COLUMNS) {
                for (y in 0..ROWS) {
                    superShapes.add(SuperShape2D(x*spac,y*spac,spacr,mmin, mmax))
                }
            }
        }
    }

    inner class SuperShape2D(var x: Float, var y: Float, var radius: Float, var min: Float, var max: Float) {

        var b = 1f
        var a = 1f
        var m = floor(random(min, max))
        var n1 = 10f
        var n2 = 5f
        var n3 = 5f
        var id = random(50000f).toInt()
        var rotAng = 0.0f
        val rotAngDir = if (random(1f) > 0.5f) 1f else -1f
        var col: Int
        var total: Int

        init {
            col = color(random(150f,255f))
            total = 360
        }

        fun draw() {
            val increment = PConstants.TWO_PI / total
            var angle = 0f
            rotAng += increment
            fill(col)
            pushMatrix()
            translate(x, y)
            rotate(rotAng * rotAngDir)
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
            n1 = map(noise(frameCount * 0.025f + id), 0f, 1f, 0.1f, 5f)
            n2 = map(noise(frameCount * 0.025f + id + 9000), 0f, 1f, -4f, 8f)
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

        fun checkClick(mx: Float, my: Float) {
            if (mousePressed) {
                if (mx > x - radius && mx < x + radius && my > y - radius && my < y + radius) {
                    m = floor(random(min, max))
                    col = getNewColor()
                }
            }
        }

        private fun getNewColor(): Int {
            return when {
                random(1f) > 0.8f -> color(255)
                random(1f) > 0.6f -> color(200)
                random(1f) > 0.4f -> color(150)
                random(1f) > 0.2f -> color(100)
                else -> color(0)
            }
        }
    }
}