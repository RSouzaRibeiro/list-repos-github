package com.rafaelsouza.listreposgithub.view

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rafaelsouza.listreposgithub.R
import com.rafaelsouza.listreposgithub.repository.model.GitHubRepos
import kotlinx.android.synthetic.main.item_repos.view.*

class ListReposAdapter(val context: Context, val repos: List<GitHubRepos>) :
    RecyclerView.Adapter<ListReposAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_repos, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
       return repos.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var repo = repos[position]

        holder.itemView.txtNameRepo.text = repo.name.toUpperCase()
        holder.itemView.txtOwnerUserName.text = repo.owner?.login

        holder.itemView.setOnClickListener {
            val intent = Intent(context, DetailsRepoActivity::class.java)
            intent.putExtra(DetailsRepoActivity.USER_ID, repo.id)
            context.startActivity(intent)


        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}