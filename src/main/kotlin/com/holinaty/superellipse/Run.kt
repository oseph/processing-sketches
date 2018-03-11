@file:JvmName("Run")
package com.holinaty.superellipse

import processing.core.PApplet

fun main(args: Array<String>) {
    val processingArgs: Array<String> = arrayOf("Superellipse")
    val superEllipse = SuperEllipse()
    PApplet.runSketch(processingArgs, superEllipse)
}
