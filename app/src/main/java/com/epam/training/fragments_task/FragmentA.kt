package com.epam.training.fragments_task

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_a.view.*

/**
 * Fragment with a button, that notifies [MainActivity] about button clicks.
 *
 * @author Alexandra Makovskaya
 */
class FragmentA : Fragment() {

    private var hostActivity : MainActivity? = null

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        if (context is MainActivity) {
            hostActivity = context
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_a, container, false)
        view.button.setOnClickListener {
            hostActivity?.onFragmentAButtonClicked()
        }

        return view
    }
}