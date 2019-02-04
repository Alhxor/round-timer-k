package net.xorium.roundtimerk

import android.content.Context
import android.media.MediaPlayer
import android.os.CountDownTimer
import android.widget.Button
import java.sql.Time

private const val TICK: Long = 10 // ms

/* Turns a button into a button with timer */
fun add(context: Context, b: Button, duration: Long) {
    val bt = ButtonTimer(context, b, duration)

    b.setOnClickListener {
        if (bt.isClicked)
            bt.stop()
        else
            bt.click()
    }
}

class ButtonTimer (context: Context, private val btn: Button, startTime: Long)
    : CountDownTimer(startTime, TICK) {
    var isClicked: Boolean = false
        private set
    private val btnDefaultText: String = btn.text.toString()
    private val time: Time = Time(startTime)
    private val beep: MediaPlayer = MediaPlayer.create(context, R.raw.beep)

    fun click() {
        super.start()
        beep.start()
        isClicked = true
    }

    fun stop() {
        super.cancel()
        isClicked = false
        btn.text = btnDefaultText
    }

    override fun onFinish() {
        this.click() // keep restarting until cancelled
    }

    override fun onTick(msUntilFinished: Long) {
        time.time = msUntilFinished
        val ms = msUntilFinished % 1000 / 100
        btn.setText("${time.toString().substring(3)}.${ms.toString()}")
    }
}
