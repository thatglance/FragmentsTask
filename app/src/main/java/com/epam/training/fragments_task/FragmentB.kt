package com.epam.training.fragments_task

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_b.*
import kotlinx.android.synthetic.main.fragment_b.view.*

/**
 * Fragment, that shows number of clicks on the button of [FragmentA].
 *
 * @author Alexandra Makovskaya
 */
class FragmentB : Fragment() {
    private var hostActivity : MainActivity? = null

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        if (context is MainActivity) {
            hostActivity = context
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_b, container, false)

        view.clicks_number.text = arguments?.get(CLICKS_COUNTER_KEY).toString()

        return view
    }

    fun setClicksNumber(clicks: Int) {
        clicks_number?.text = clicks.toString()
    }

    companion object {
        const val TAG = "FRAGMENT_B_TAG"
        private const val CLICKS_COUNTER_KEY = "CLICKS_COUNTER"
        fun newInstance(clicks : Int) = FragmentB().apply {
            arguments = Bundle().apply {
                putInt(CLICKS_COUNTER_KEY, clicks)
            }
        }
    }
}