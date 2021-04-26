package com.chriscalderonh.andersonscodechallenge.common.ui.execution

import io.reactivex.Scheduler

interface ExecutionThread {

    fun schedulerForObserving(): Scheduler

    fun schedulerForSubscribing(): Scheduler

}