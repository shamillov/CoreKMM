package com.shamilov.core


class Greeting {
    fun greeting(): String {
        return "Hello, ${Platform().platform}!"
    }
}
