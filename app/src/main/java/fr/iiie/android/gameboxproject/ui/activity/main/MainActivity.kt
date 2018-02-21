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
    private var mainController: MainController? = null

    @BindView(R.id.drawer_container)
    var drawerLayout: DrawerLayout? = null

    @BindView(R.id.drawer_navigation_view)
    var navigationView: NavigationView? = null

    @BindView(R.id.activity_main_container)
    var containerView: View? = null

    @BindView(R.id.activity_main_toolbar)
    var actionBar: Toolbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ButterKnife.bind(this)

        val screenSizePoint = Point()
        windowManager.defaultDisplay.getSize(screenSizePoint)
        App.setScreenSize(screenSizePoint)
        mainController = MainController()
        setSupportActionBar(actionBar)

      /*  val drawerToggle = object : ActionBarDrawerToggle(this, drawerLayout, actionBar, R.string.openDrawer, R.string.closeDrawer) {

            override fun onDrawerOpened(drawerView: View) {
                    super.onDrawerOpened(drawerView)

            }

            override fun onDrawerClosed(drawerView: View) {
                    super.onDrawerClosed(drawerView)

            }

        }
        drawerLayout!!.addDrawerListener(drawerToggle) // NPE ici !!
        drawerToggle.syncState()

        navigationView!!.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.drawer_menu_item_close -> drawerLayout!!.closeDrawer(GravityCompat.START)
                else -> App.getAppBus().post(SnackEvent("You clicked : " + menuItem.title))
            }

            true
        }*/

        if (supportFragmentManager.findFragmentByTag("sample") == null) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.activity_main_container, SampleFragment(), "sample")
                    .commit()
        }
    }

    override fun onResume() {
        super.onResume()
        mainController!!.resume()
        registerReceiver(App.getNetworkBroadcastReceiver(), IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"))
        if (!App.getAppBus().isRegistered(this)) {
            App.getAppBus().register(this)
        }
    }

    override fun onPause() {
        if (App.getAppBus().isRegistered(this)) {
            App.getAppBus().unregister(this)
        }
        unregisterReceiver(App.getNetworkBroadcastReceiver())
        mainController!!.pause()
        super.onPause()
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onSwitchFragmentEvent(switchFragmentEvent: SwitchFragmentEvent) {
        if (switchFragmentEvent.isToCleanBackstack!!) {
            cleanBackstack()
        }
        if (switchFragmentEvent.fragment != null) {
            val tag = (switchFragmentEvent.fragment as Any).javaClass.getName()

            val transaction = supportFragmentManager.beginTransaction()

            if (switchFragmentEvent.direction != null) {
                if (switchFragmentEvent.direction == SwitchFragmentEvent.Direction.VERTICAL) {
                    transaction.setCustomAnimations(R.anim.slide_in, R.anim.slide_out, R.anim.slide_in, R.anim.slide_out)
                } else if (switchFragmentEvent.direction == SwitchFragmentEvent.Direction.HORIZONTAL) {
                    transaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left, R.anim.slide_in_left, R.anim.slide_out_right)
                } else {
                    transaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out, R.anim.fade_in, R.anim.fade_out)
                }
            }

            if (switchFragmentEvent.isFragmentReplaced!!) {
                transaction.replace(R.id.activity_main_container, switchFragmentEvent.fragment, tag)
            } else {
                transaction.add(R.id.activity_main_container, switchFragmentEvent.fragment, tag)
            }

            if (switchFragmentEvent.isAddedToBackStack!!) {
                transaction.addToBackStack(null)
            }

            transaction.commit()
        } else {
            supportFragmentManager.popBackStackImmediate()
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onSnackEvent(event: SnackEvent) {
        Snackbar.make(containerView!!, event.message, Snackbar.LENGTH_SHORT).show()
    }

    private fun cleanBackstack() {
        supportFragmentManager.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }
}
