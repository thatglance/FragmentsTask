package com.epam.training.fragments_task

import android.content.res.Configuration
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

/**
 * Main activity of application.
 * In portrait orientation [MainActivity] shows [FragmentA] and replaces it with [FragmentB] at a button press,
 * in landscape orientation [MainActivity] shows [FragmentA] and [FragmentB].
 *
 * @author Alexandra Makovskaya
 */
class MainActivity : AppCompatActivity() {

    var clicksCounter = 0
        private set
    private val orientation
        get() = resources.configuration.orientation
    private var currentFragment = FRAGMENT_A_NAME

    fun onFragmentAButtonClicked() {
        clicksCounter++

        when (orientation) {
            Configuration.ORIENTATION_PORTRAIT -> {
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragment_container, FragmentB.newInstance(clicksCounter))
                    .addToBackStack(null)
                    .commit()
                currentFragment = FRAGMENT_B_NAME
            }
            Configuration.ORIENTATION_LANDSCAPE -> {
                val fragmentB = supportFragmentManager.findFragmentByTag(FragmentB.TAG) as FragmentB?
                fragmentB?.setClicksNumber(clicksCounter)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        savedInstanceState?.apply {
            clicksCounter = getInt(CLICKS_COUNTER_KEY)
            currentFragment = getString(CURRENT_FRAGMENT_KEY) ?: FRAGMENT_A_NAME
        }

        when (orientation) {
            Configuration.ORIENTATION_PORTRAIT -> {
                if (currentFragment == FRAGMENT_A_NAME) {
                    supportFragmentManager.beginTransaction().replace(R.id.fragment_container, FragmentA()).commit()
                } else {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, FragmentB.newInstance(clicksCounter), FragmentB.TAG).commit()
                }
            }
            Configuration.ORIENTATION_LANDSCAPE -> {
                supportFragmentManager.beginTransaction().replace(R.id.fragment_a_container, FragmentA()).commit()
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_b_container, FragmentB.newInstance(clicksCounter), FragmentB.TAG).commit()
            }
        }


    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putInt(CLICKS_COUNTER_KEY, clicksCounter)
        outState?.putString(CURRENT_FRAGMENT_KEY, currentFragment)
    }

    companion object {
        private const val CLICKS_COUNTER_KEY = "CLICKS_COUNTER"
        private const val CURRENT_FRAGMENT_KEY = "CURRENT_FRAGMENT"
        private const val FRAGMENT_A_NAME = "FRAGMENT_A"
        private const val FRAGMENT_B_NAME = "FRAGMENT_B"
    }
}
