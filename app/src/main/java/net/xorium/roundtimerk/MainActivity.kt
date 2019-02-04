package net.xorium.roundtimerk

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val res = resources

        add(this, findViewById(R.id.button1),
            res.getInteger(R.integer._00_30).toLong())
        add(this, findViewById(R.id.button2),
            res.getInteger(R.integer._01_00).toLong())
        add(this, findViewById(R.id.button3),
            res.getInteger(R.integer._01_30).toLong())
        add(this, findViewById(R.id.button4),
            res.getInteger(R.integer._02_00).toLong())
        add(this, findViewById(R.id.button5),
            res.getInteger(R.integer._03_00).toLong())
    }
}
