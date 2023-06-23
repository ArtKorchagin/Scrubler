package com.artkorchagin.scrubler.android

import com.artkorchagin.scrubler.common.App
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.MaterialTheme
import com.artkorchagin.scrubler.common.DriverFactory
import com.artkorchagin.scrubler.common.ScrublerSdk

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                App(ScrublerSdk(DriverFactory(this)))
            }
        }
    }
}