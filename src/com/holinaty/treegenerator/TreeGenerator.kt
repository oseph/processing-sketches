package com.holinaty.treegenerator

import processing.core.PApplet

class SpaceColonizedTree : PApplet() {

    val w = 800
    val h = 800
    var tree: Tree? = null // init in setup()
    val treesize = 800
    var bgCol = 60
    var max_dist = 100
    var min_dist = 20

    override fun settings() {
        size(w,h)
    }

    override fun setup() {
        tree = Tree(this, treesize)
    }

    override fun draw() {
        translate(0f,-35f)
        background(bgCol)
        tree?.grow()
        tree?.show(false)
    }

    override fun mouseClicked() {
        /* Mouse clicks will make a new tree by reinitializing the
         * current tree instance with new randomly rolled values */
        bgCol = random(0f,255f).toInt()
        max_dist = random(50f,100f).toInt()
        min_dist = random(10f,20f).toInt()
        Branch.strokeWeight = random(5f,20f)
        Branch.col = if (bgCol > 127) 0 else 255
        Branch.len = random(2f,6f)
        tree?.rollNewTree()
    }
}

fun main(args: Array<String>) {
    val processingArgs: Array<String> = arrayOf("SpaceColonization")
    val spaceColonizedTree = SpaceColonizedTree()
    PApplet.runSketch(processingArgs, spaceColonizedTree)
}