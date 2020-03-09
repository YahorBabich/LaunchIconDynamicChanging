package com.wsc.dynamic

import android.content.ComponentName
import android.content.Intent
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.wsc.dynamic.BuildConfig.ENABLE_SCRENNNING
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    enum class ICON_COLOUR { RED, GREEN, BLUE }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val isDebug = BuildConfig.DEBUG
        val isDebuggable = 0 != applicationInfo.flags and ApplicationInfo.FLAG_DEBUGGABLE
        val isScreening = ENABLE_SCRENNNING

        if (!isDebug) {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_SECURE,
                WindowManager.LayoutParams.FLAG_SECURE
            )
        }

        setContentView(R.layout.activity_main)

        info.text = "debug: $isDebug debuggable $isDebuggable isScreening $isScreening"

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

    fun onClick(view: View) {
        startActivity(Intent(this, SecondActivity::class.java))
    }
}
