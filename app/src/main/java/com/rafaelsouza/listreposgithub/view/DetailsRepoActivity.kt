package com.rafaelsouza.listreposgithub.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.rafaelsouza.listreposgithub.BaseApplication
import com.rafaelsouza.listreposgithub.R
import com.rafaelsouza.listreposgithub.repository.model.GitHubRepos
import com.rafaelsouza.listreposgithub.viewmodel.DetailsRepoViewModel
import com.rafaelsouza.listreposgithub.viewmodel.ListReposViewModel
import com.rafaelsouza.listreposgithub.viewmodel.ViewModelFactory
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_details_repo.*
import javax.inject.Inject

class DetailsRepoActivity : AppCompatActivity() {

    companion object {
        var USER_ID = "userId"
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory<DetailsRepoViewModel>
    var viewModel: DetailsRepoViewModel? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_repo)
        (application as BaseApplication).graph.inject(this)
        viewModel =
            ViewModelProviders.of(this, viewModelFactory).get(DetailsRepoViewModel::class.java)
        initToolbar()
        doBinds()
        intent.extras.get(USER_ID)?.let {
            viewModel?.getRepoById(it.toString())
        }
    }

    private fun doBinds() {
        viewModel?.repo?.observe(this, Observer { repository ->

            initView(repository)

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

    private fun initToolbar() {
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun initView(repo: GitHubRepos) {
        txtNameRepo.text = repo.name
        txtNameUser.text = repo.owner?.login
        txtDescription.text = repo.description
        setPoster(repo.owner!!.avatar_url)
    }

    private fun setPoster(imagePath: String) {
        Picasso.get()
            .load( imagePath)
            .resize(320, 230)
            .networkPolicy(NetworkPolicy.NO_STORE)
            .centerCrop()
            .into(profile_image)

    }
}
