package com.openbank.marvel.presentation.screen.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.openbank.marvel.databinding.ViewHolderCharacterBinding
import com.openbank.marvel.domain.MarvelCharacter

class CharactersAdapter(
    private val characterClicked: (id: Int) -> Unit
) : ListAdapter<MarvelCharacter, CharactersAdapter.CharacterVH>(
    object : DiffUtil.ItemCallback<MarvelCharacter>() {
        override fun areItemsTheSame(oldItem: MarvelCharacter, newItem: MarvelCharacter): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: MarvelCharacter,
            newItem: MarvelCharacter
        ): Boolean {
            return oldItem == newItem
        }
    }
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterVH {
        val binding = ViewHolderCharacterBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return CharacterVH(binding)
    }

    override fun onBindViewHolder(holder: CharacterVH, position: Int) {
        holder.bind(getItem(position))
    }

    inner class CharacterVH(
        private val binding: ViewHolderCharacterBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        private var id: Int = 0

        init {
            binding.root.setOnClickListener { characterClicked(id) }
        }

        fun bind(character: MarvelCharacter) {
            id = character.id

            binding.thumbnail.load(character.thumbnailPath)
            binding.name.text = character.name
        }
    }

}