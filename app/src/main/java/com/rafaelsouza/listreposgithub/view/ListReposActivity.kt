package com.rafaelsouza.listreposgithub.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.SearchView

import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rafaelsouza.listreposgithub.BaseApplication
import com.rafaelsouza.listreposgithub.R
import com.rafaelsouza.listreposgithub.repository.model.GitHubRepos
import com.rafaelsouza.listreposgithub.viewmodel.ListReposViewModel
import com.rafaelsouza.listreposgithub.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.activity_list_repos.*
import javax.inject.Inject

class ListReposActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory<ListReposViewModel>
    var viewModel: ListReposViewModel? = null
    var adapter: ListReposAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_repos)
        (application as BaseApplication).graph.inject(this)
        viewModel =
            ViewModelProviders.of(this, viewModelFactory).get(ListReposViewModel::class.java)
        doBinds()
        getRepos()
    }

    private fun doBinds() {
        viewModel?.repos?.observe(this, Observer {listRepos ->
            initRecycleView(listRepos)
        })

        viewModel?.progress?.observe(this, Observer {
            if (it!!) {
                progressBar.visibility = View.VISIBLE
            } else {
                progressBar.visibility = View.GONE
            }
        })

        viewModel?.error?.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        })
    }


    private fun getRepos() {
        viewModel?.listRepos()
    }

    private fun initRecycleView(result: ArrayList<GitHubRepos>) {
        var layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = layoutManager
        adapter = ListReposAdapter(this, result)
        recyclerView.adapter = adapter
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                val lastPosition = layoutManager.findLastVisibleItemPosition()
                if (lastPosition >= (recyclerView.adapter?.itemCount?.minus(3)!!)) {

                }
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_search, menu)
        val itemSearch = menu?.findItem(R.id.menuSearch)
        val searchView = itemSearch?.actionView as SearchView
        searchView.queryHint = getString(R.string.hint_search)
        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(search: String?): Boolean {
                if(!search.isNullOrEmpty()){
                    viewModel?.listSearchRepos(search)
                }
                return false
            }

            override fun onQueryTextChange(search: String?): Boolean {
                if(!search.isNullOrEmpty()){
                    viewModel?.listSearchRepos(search)
                }
                return false
            }

        })

        searchView.setOnCloseListener {
            getRepos()
            false
        }

        return super.onCreateOptionsMenu(menu)
    }
}
