package fr.iiie.android.gameboxproject.ui.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import fr.iiie.android.gameboxproject.R
import kotlinx.android.synthetic.main.game_select.*

class level_select_frag : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view_ = inflater.inflate(R.layout.game_select, container, false)
        return view_
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        btn_tic_tac_toe.setOnClickListener {
            (activity as level_select_inter).onTicTacToeClicked()
        }
    }


    interface level_select_inter {
        fun onTicTacToeClicked()
    }
}