package com.noahjutz.dice

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
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
        val random = Random()

        fun randDie(max: Int) : Int {
            return random.nextInt(max) + 1
        }
        when(randDie(dieSides)) {
            1 -> icon_die.setImageResource(R.drawable.ic_dice_1)
            2 -> icon_die.setImageResource(R.drawable.ic_dice_2)
            3 -> icon_die.setImageResource(R.drawable.ic_dice_3)
            4 -> icon_die.setImageResource(R.drawable.ic_dice_4)
            5 -> icon_die.setImageResource(R.drawable.ic_dice_5)
            6 -> icon_die.setImageResource(R.drawable.ic_dice_6)
        }
    }

}