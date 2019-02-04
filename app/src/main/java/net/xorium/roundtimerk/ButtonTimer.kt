package net.xorium.roundtimerk

import android.content.Context
import android.media.MediaPlayer
import android.os.CountDownTimer
import android.widget.Button
import java.sql.Time

private const val TICK: Long = 10 // ms

/* Turns a button into a button with timer */
fun add(context: Context, b: Button, duration: Long) {
    val buttonTimer = ButtonTimer(context, b, duration)

    b.setOnClickListener {
        if (buttonTimer.isClicked)
            buttonTimer.stop()
        else
            buttonTimer.click()
    }
}

class ButtonTimer (context: Context, private val button: Button, startTime: Long)
    : CountDownTimer(startTime, TICK) {
    var isClicked: Boolean = false
        private set
    private var soundEnabled: Boolean = false
    private val buttonDefaultText: String = button.text.toString()
    private val time: Time = Time(startTime)
    private val beepSound: MediaPlayer = MediaPlayer.create(context, R.raw.beep)

    fun click() {
        super.start()
        if (soundEnabled)
            beepSound.start()
        isClicked = true
    }

    fun stop() {
        super.cancel()
        isClicked = false
        soundEnabled = false
        button.text = buttonDefaultText
    }

    override fun onFinish() {
        soundEnabled = true
        this.click() // keep restarting until cancelled
    }

    override fun onTick(msUntilFinished: Long) {
        time.time = msUntilFinished
        val ms = msUntilFinished % 1000 / 100
        val timestr = time.toString().substring(3)
        button.setText("$timestr.$ms")
    }
}
