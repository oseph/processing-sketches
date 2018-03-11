@file:JvmName("RunSuperellipse")
package com.holinaty.superellipse

import processing.core.PApplet

fun main(args: Array<String>) {
    val processingArgs: Array<String> = arrayOf("Superellipse")
    val superEllipse = Superellipse()
    PApplet.runSketch(processingArgs, superEllipse)
}
