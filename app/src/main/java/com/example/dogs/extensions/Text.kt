package com.example.dogs.extensions

import androidx.annotation.StringRes

sealed class Text {
    data class StringValue(val text: String) : Text()
    class StringResource(@StringRes val resId: Int, vararg val arguments: Any) : Text() {
        override fun equals(other: Any?): Boolean {
            return when (other) {
                is StringResource -> other.resId == resId && other.arguments.contentEquals(arguments)
                else -> super.equals(other)
            }
        }

        override fun hashCode(): Int {
            return super.hashCode()
        }
    }
    companion object
}