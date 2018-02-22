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
import fr.iiie.android.gameboxproject.ui.fragment.sample.SampleFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }
}
