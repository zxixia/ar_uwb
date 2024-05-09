package com.google.ar.core.examples.kotlin.algo

class Sphere(var mX: Double,  var mY: Double, var mZ: Double, var mR: Double) {
    fun distanceTo(x: Double, y: Double, z: Double): Double {
        return Math.sqrt((mX - x) * (mX - x) + (mY - y) * (mY - y) + (mZ - z) * (mZ - z)) - mR
    }

    override fun toString(): String {
        return "$mX, $mY, $mZ, $mZ"
    }
}