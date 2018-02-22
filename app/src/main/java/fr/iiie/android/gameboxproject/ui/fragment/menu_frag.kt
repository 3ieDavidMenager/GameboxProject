package fr.iiie.android.gameboxproject.ui.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import fr.iiie.android.gameboxproject.R
import kotlinx.android.synthetic.main.menu.*

class menu_frag : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view_ = inflater.inflate(R.layout.menu, container, false)
        val button = view_.findViewById<Button>(R.id.start_button)
        val context = activity
        return view_
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        start_button.setOnClickListener {
            (activity as menu_interface).onStartClicked()
        }
    }

    interface menu_interface{
        fun onStartClicked()
    }
}