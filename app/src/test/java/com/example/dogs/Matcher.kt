package com.example.dogs

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.CoreMatchers.instanceOf

inline fun <reified T> classType(): Class<T> = T::class.java

inline fun <reified T> assertThatIsInstanceOf(actual: Any?) = assertThat(
    actual, instanceOf(classType<T>())
)

fun assertThatEquals(actual: Any?, expected: Any?) = assertThat(actual, equalTo(expected))