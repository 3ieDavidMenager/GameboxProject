package fr.iiie.android.gameboxproject.ui.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import fr.iiie.android.gameboxproject.R

class tic_tac_frag : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view_ = inflater.inflate(R.layout.tic_tac_toe, container, false)
        return view_
    }



}