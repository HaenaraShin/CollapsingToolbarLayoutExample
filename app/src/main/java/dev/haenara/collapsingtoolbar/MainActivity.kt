package dev.haenara.collapsingtoolbar

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.ViewGroup
import android.widget.SearchView
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

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = (menu.findItem(R.id.action_search).actionView as SearchView).apply {
            setSearchableInfo(searchManager.getSearchableInfo(componentName))
        }

        searchView.setOnClickListener {
            collapse()
        }

        searchView.queryHint = "검색어를 입력해보세요"

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                Snackbar.make(searchView, query ?: "", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                collapse()
                Toast.makeText(this@MainActivity, newText ?: "", Toast.LENGTH_SHORT).show()
                return true
            }
        })
        return true
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
