package com.noahjutz.dice

import android.media.MediaPlayer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.SeekBar
import kotlinx.android.synthetic.main.activity_main.*
import java.util.Random


class MainActivity : AppCompatActivity() {

    private lateinit var mp: MediaPlayer
    var dieSides = 4

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Display initial side count
        text_sides.text = resources.getString(R.string.seekbar_progress, dieSides)



        // Seekbar sides listener
        seekbar_sides.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener {
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                // Update dieSides variable and display it
                dieSides = (seekbar_sides.progress+2)
                text_sides.text = resources.getString(R.string.seekbar_progress, dieSides)

                // Reset icon
                icon_die.setImageResource(R.drawable.ic_dice_blank)
            }
        })
    }

    fun dieRoll(@Suppress("UNUSED_PARAMETER")view: View) {

        // Reset icon
        icon_die.setImageResource(R.drawable.ic_dice_blank)

        // Sound effect
        mp = MediaPlayer.create(this, R.raw.roll_sound)
        mp.start()

        // Roll die effect
        icon_die.animate().setDuration(400).rotationBy(180f)

        // Disable button
        button_roll.isEnabled = false

        // Random number generation
        val random = Random()
        fun randDie(max: Int): Int {
            return random.nextInt(max) + 1
        }

        // Die icon selection
        Handler().postDelayed({
            when (randDie(dieSides)) {
                1 -> icon_die.setImageResource(R.drawable.ic_dice_1)
                2 -> icon_die.setImageResource(R.drawable.ic_dice_2)
                3 -> icon_die.setImageResource(R.drawable.ic_dice_3)
                4 -> icon_die.setImageResource(R.drawable.ic_dice_4)
                5 -> icon_die.setImageResource(R.drawable.ic_dice_5)
                6 -> icon_die.setImageResource(R.drawable.ic_dice_6)
            }

            // Re-enable button
            button_roll.isEnabled = true

            // Release mp
            mp.release()
        }, 400)
    }

    override fun onDestroy () {
        super.onDestroy ()
        mp.release ()
    }

}