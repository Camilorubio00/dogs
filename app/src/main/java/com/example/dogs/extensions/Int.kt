package com.example.dogs.extensions

private const val DEFAULT_VALUE = 0
const val ZERO = 0
const val ONE = 1

fun Int?.orDefault() = this ?: DEFAULT_VALUE