package com.noahjutz.dice

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.SeekBar
import kotlinx.android.synthetic.main.activity_main.*
import java.util.Random


class MainActivity : AppCompatActivity() {

    var dieSides = 4

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        text_sides.text = resources.getString(R.string.seekbar_progress, dieSides)

        seekbar_sides.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener {
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                dieSides = (seekbar_sides.progress+2)
                text_sides.text = resources.getString(R.string.seekbar_progress, dieSides)
            }
        })
    }

    fun dieRoll(@Suppress("UNUSED_PARAMETER")view: View) {

        // Roll die effect
        icon_die.animate().setDuration(1000).rotationBy(360f)
        icon_die.animate().setDuration(500).translationYBy(-72f)
        Handler().postDelayed({
            icon_die.animate().setDuration(500).translationYBy(72f)
        }, 500)

        // Random number generation
        val random = Random()
        fun randDie(max: Int) : Int {
            return random.nextInt(max) + 1
        }

        // Die icon selection
        Handler().postDelayed({
            when(randDie(dieSides)) {
                1 -> icon_die.setImageResource(R.drawable.ic_dice_1)
                2 -> icon_die.setImageResource(R.drawable.ic_dice_2)
                3 -> icon_die.setImageResource(R.drawable.ic_dice_3)
                4 -> icon_die.setImageResource(R.drawable.ic_dice_4)
                5 -> icon_die.setImageResource(R.drawable.ic_dice_5)
                6 -> icon_die.setImageResource(R.drawable.ic_dice_6)
            }
        }, 1000)

        // Disable button for 30s
        button_roll.isEnabled = false

        // Re-enable button
        Handler().postDelayed({
            button_roll.isEnabled = true
        }, 1000)
    }

}