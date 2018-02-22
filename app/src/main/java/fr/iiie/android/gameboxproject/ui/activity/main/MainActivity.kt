package fr.iiie.android.gameboxproject.ui.activity.main

import android.content.IntentFilter
import android.graphics.Point
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.design.widget.Snackbar
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.view.View

import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

import butterknife.BindView
import butterknife.ButterKnife
import fr.iiie.android.gameboxproject.R
import fr.iiie.android.gameboxproject.bus.SnackEvent
import fr.iiie.android.gameboxproject.bus.SwitchFragmentEvent
import fr.iiie.android.gameboxproject.data.app.App
import fr.iiie.android.gameboxproject.business.main.MainController
import fr.iiie.android.gameboxproject.ui.fragment.level_select_frag
import fr.iiie.android.gameboxproject.ui.fragment.menu_frag
import fr.iiie.android.gameboxproject.ui.fragment.sample.SampleFragment
import fr.iiie.android.gameboxproject.ui.fragment.tic_tac_frag
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.game_select.*
import kotlinx.android.synthetic.main.menu.*

class MainActivity : AppCompatActivity() , menu_frag.menu_interface, level_select_frag.level_select_inter {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val frag_man = supportFragmentManager
        frag_man.beginTransaction().replace(R.id.activity_main_container, menu_frag()).commit()
        toolbar_name.text =""

    }

    override fun onStartClicked() {
        if(player_name.text.isEmpty()) {
            noname_msg.text = "Please enter your name first"
        }
        else {

            supportFragmentManager.beginTransaction().replace(R.id.activity_main_container, level_select_frag()).commit()

            toolbar_name.text = "Hello " + player_name.text
        }
    }

    override fun onTicTacToeClicked() {
        supportFragmentManager.beginTransaction().replace(R.id.activity_main_container, tic_tac_frag()).commit()
    }

}

