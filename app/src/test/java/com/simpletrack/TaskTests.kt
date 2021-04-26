package com.simpletrack

import org.junit.Test
import org.junit.Assert.assertEquals
import java.lang.Thread.sleep

import Task
import java.util.*

class TaskTests {
    @Test
    fun timeCount_isCorrect() {
        val sleepTime: Long = 1000
        val epsilon: Long = 10
        val task = Task()

        task.startTime()
        sleep(sleepTime)
        val retval = task.stopTime()

        assert(retval >= sleepTime - epsilon && retval <= sleepTime + epsilon)
    }

    @Test
    fun stop_before_start() {
        val task = Task()

        val retval = task.stopTime()

        assertEquals(-1, retval)
    }

    @Test
    fun start_twice() {
        val task = Task()

        task.startTime()
        val firststart = task.start
        sleep(5)
        task.startTime()

        assertEquals(firststart, task.start)
    }

    @Test
    fun stop_twice() {
        val task = Task()

        task.startTime()
        sleep(5)
        task.stopTime()
        val firststop = task.stop

        sleep(10)
        task.stopTime()

        assertEquals(firststop, task.stop)
    }

    @Test
    fun isRunningWhileRunning() {
        val task = Task()
        task.startTime()
        assert(task.running())
    }
    @Test
    fun isRunningWhileStopped() {
        val task = Task()
        task.startTime()
        task.stopTime()
        assert(!task.running())
    }
    @Test
    fun isRunningBeforeStart() {
        val task = Task()
        assert(!task.running())
    }

    @Test
    fun isStoppedWhileRunning() {
        val task = Task()
        task.startTime()
        assert(!task.isStopped())
    }
    @Test
    fun isStoppedWhileStopped() {
        val task = Task()
        task.startTime()
        task.stopTime()
        assert(task.isStopped())
    }
    @Test
    fun isStoppedBeforeStart() {
        val task = Task()
        assert(!task.isStopped())
    }

    @Test
    fun getTimeSuccess() {
        val task = Task(Date(1000), Date(3000))
        assertEquals(2000L, task.getTime())
    }

    @Test
    fun getTimeNotStarted() {
        val task = Task()
        assertEquals(0, task.getTime())
    }

    @Test
    fun getTimeAsStringCorrect() {
        val task = Task(Date(0), Date(8530000))
        assertEquals("02:22:10", task.getTimeAsString())
    }

    @Test
    fun getTimeAsStringThreeDigitHours() {
        val task = Task(Date(0), Date(853000000))
        assertEquals("236:56:40", task.getTimeAsString())
    }

    @Test
    fun getTimeAsStringNotStarted() {
        val task = Task()
        assertEquals("00:00:00", task.getTimeAsString())
    }
}
