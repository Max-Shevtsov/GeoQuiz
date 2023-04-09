package com.example.geuquiz

import android.support.annotation.StringRes

data class Question(@StringRes val textResId: Int, val answer: Boolean, var userAnswer: Boolean? = null)
