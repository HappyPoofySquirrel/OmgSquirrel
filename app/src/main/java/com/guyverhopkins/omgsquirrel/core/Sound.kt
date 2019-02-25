package com.guyverhopkins.omgsquirrel.core

class Sound(val pathName: String) {
    val fileName: String
    var id: Int? = null

    init {
        val splitPath = pathName.split("/".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        this.fileName = splitPath[splitPath.size - 1]
    }
}