package com.example.myfluttermoduleapp.helper

import com.google.gson.annotations.SerializedName

data class FlutterRouteInfo(
    @SerializedName("key")
    val key: Int,
    @SerializedName("index")
    val index: Int,
    @SerializedName("url")
    val url: String) {


    override fun equals(other: Any?): Boolean {
        return if (other is FlutterRouteInfo) {
            other.key == key && other.index == index && url == url
        } else {
            false
        }
    }

    override fun hashCode(): Int {
        return super.hashCode()
    }
}