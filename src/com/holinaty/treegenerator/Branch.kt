package com.holinaty.treegenerator

import processing.core.PApplet
import processing.core.PVector

class Branch(val pa: PApplet, val parent: Branch?, val pos: PVector, var dir: PVector) {

    companion object {
        var len = 5f
        var branchCount = 0
        var strokeWeight = 2f;
        var col = 255
        fun countUp() { branchCount++ }

        fun newStrokeWeight(newWeight: Float) {
            strokeWeight = newWeight
        }
    }

    val strokeWeight = pa.random(0.5f,7f)
    var count: Int = 0
    var originalDir = dir.copy()
    //var col = 255
    //var len = 5f

    init {
        //col = pa.color(pa.random(255f))

    }

    fun next(): Branch {
        dir.normalize()
        var nextDir = PVector.mult(dir, len)
        var nextPos = PVector.add(pos, nextDir)
        var nextBranch = Branch(pa, this, nextPos, dir.copy())
        return nextBranch
    }

    fun show() {
        if (parent != null) {
            pa.stroke(col)
            pa.strokeWeight(Companion.strokeWeight)
            pa.line(pos.x, pos.y, parent.pos.x, parent.pos.y)
        }
    }

    fun reset() {
        dir = originalDir
        count = 0
    }

}