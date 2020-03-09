package com.rodrigotristany.mrjeff.internal.extensions

import android.animation.ObjectAnimator
import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.DecelerateInterpolator
import android.view.animation.Interpolator
import android.view.animation.LinearInterpolator
import android.widget.ProgressBar

fun ViewGroup.inflate(resource: Int) = LayoutInflater.from(this.context).inflate(resource, this, false)

fun ProgressBar.animateTo(progressTo: Int, startDelay: Long) {
    val multiplicator = if(progressTo < 0) -1 else 1

    val animation = ObjectAnimator.ofInt(
        this,
        if(progressTo < 0) "secondaryProgress" else  "progress",
        if(progressTo < 0) this.secondaryProgress else this.progress,
        progressTo * multiplicator
    )

    animation.duration = 500
    animation.interpolator = LinearInterpolator()
    animation.startDelay = startDelay
    animation.start()
}