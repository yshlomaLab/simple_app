package com.intelity.test.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 * Define Base Activity to create abstract methods.
 */
abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayout())
    }

    abstract fun getLayout() : Int

}