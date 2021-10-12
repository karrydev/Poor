package com.poor.android.public.util

import android.util.Log

object L {

    private const val VERBOSE = 1

    private const val DEBUG = 1

    private const val INFO = 1

    private const val WARN = 1

    private const val ERROR = 1

    private var level = VERBOSE

    fun v(tag: String, msg: String) {
        if (level <= VERBOSE) {
            Log.v(tag, msg)
        }
    }

    fun d(tag: String, msg: String) {
        if (level <= VERBOSE) {
            Log.d(tag, msg)
        }
    }

    fun i(tag: String, msg: String) {
        if (level <= VERBOSE) {
            Log.i(tag, msg)
        }
    }

    fun w(tag: String, msg: String) {
        if (level <= VERBOSE) {
            Log.w(tag, msg)
        }
    }

    fun e(tag: String, msg: String) {
        if (level <= VERBOSE) {
            Log.e(tag, msg)
        }
    }
}