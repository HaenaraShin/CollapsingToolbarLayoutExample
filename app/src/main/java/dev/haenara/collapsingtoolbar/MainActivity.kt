package dev.haenara.collapsingtoolbar

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        fab2.hide()

        fab.setOnClickListener {
//            Snackbar.make(it, "Replace with your own action", Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show()
            hide()
            fab2.show()
        }

        fab2.setOnClickListener {
            show()
            fab2.hide()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.appbar_actions, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_search -> {
                collapse()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun hide() {
        app_bar.layoutParams =
            (app_bar.layoutParams as (CoordinatorLayout.LayoutParams)).also {
                it.height = 0
            }
    }

    fun show() {
        app_bar.layoutParams =
            (app_bar.layoutParams as (CoordinatorLayout.LayoutParams)).also {
                it.height = ViewGroup.LayoutParams.WRAP_CONTENT
            }
    }

    fun collapse() {
        app_bar.setExpanded(false, true)
    }

    fun expand() {
        app_bar.setExpanded(true, true)
    }
}

class Foo : Fragment() {
    fun bar() {
        (activity as AppCompatActivity).supportActionBar
    }
}
