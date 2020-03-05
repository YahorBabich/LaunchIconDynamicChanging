package com.wsc.dynamic

import android.content.ComponentName
import android.content.pm.PackageManager
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity

import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {

    enum class ICON_COLOUR { RED, GREEN, BLUE }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        red.setOnClickListener { setIcon(ICON_COLOUR.RED) }
        green.setOnClickListener { setIcon(ICON_COLOUR.GREEN) }
        blue.setOnClickListener { setIcon(ICON_COLOUR.BLUE) }
    }

    private fun setIcon(targetColour: ICON_COLOUR) {
        for (value in ICON_COLOUR.values()) {
            val action = if (value == targetColour) {
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED
            } else {
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED
            }
            packageManager.setComponentEnabledSetting(
                ComponentName(
                    BuildConfig.APPLICATION_ID,
                    "${BuildConfig.APPLICATION_ID}.${value.name}"
                ),
                action, PackageManager.DONT_KILL_APP
            )
        }
    }

}
