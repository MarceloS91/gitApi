package com.example.githubapi.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.githubapi.Model.Repositorio
import com.example.githubapi.databinding.ResLayoutBinding

class Adapter (
    private val listRepository: List<Repositorio>,
    private val onClicked: (Repositorio) -> Unit) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val rootBinding = ResLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RepositoryViewHolder(rootBinding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {
            is RepositoryViewHolder -> {
                holder.bind(listRepository[position], onClicked)
            }
        }
    }

    override fun getItemCount(): Int {
        return listRepository.size
    }

    class RepositoryViewHolder constructor(
        itemView: ResLayoutBinding
    ) : RecyclerView.ViewHolder(itemView.root) {
        private val repositoryName = itemView.name
        private val repositoryLanguage = itemView.language

        fun bind(repositorio: Repositorio,onClicked: (Repositorio) -> Unit) {
            repositoryName.text = repositorio.nome
            repositoryLanguage.text = repositorio.linguagem

            itemView.setOnClickListener {
                onClicked(repositorio)

            }
        }
    }
}

