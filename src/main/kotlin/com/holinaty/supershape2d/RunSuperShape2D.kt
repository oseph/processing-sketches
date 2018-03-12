@file:JvmName("runSuperShape2D")
package com.holinaty.supershape2d

import processing.core.PApplet

fun main(args: Array<String>) {
    val processingArgs: Array<String> = arrayOf("SuperShape2D")
    val superShape2DApplet = SuperShape2DApplet()
    PApplet.runSketch(processingArgs, superShape2DApplet)

}