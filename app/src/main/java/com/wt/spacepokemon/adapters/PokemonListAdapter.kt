package com.wt.spacepokemon.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.wt.spacepokemon.R
import com.wt.spacepokemon.data.PokemonInSpace
import com.wt.spacepokemon.databinding.ListItemPokemonBinding

/**
 *  author : William Tsai
 *  date : 2022/3/17
 *  description : adapter for pokemon list
 */
class PokemonListAdapter : RecyclerView.Adapter<PokemonListAdapter.ViewHolder>() {
    private val contentList = arrayListOf<PokemonInSpace>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ListItemPokemonBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = contentList[position]
        holder.apply {
            tvName.text = item.name
            tvNum.text = item.num
            Glide.with(ivPokemon).load(item.imgUrl).into(ivPokemon)
            itemView.setOnClickListener {
                //jump to detail page
                val bundle = bundleOf("id" to item.id)
                it.findNavController()
                    .navigate(R.id.action_pokemonListFragment_to_pokemonDetailFragment, bundle)

            }
        }
    }

    override fun getItemCount(): Int = contentList.size

    inner class ViewHolder(binding: ListItemPokemonBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val ivPokemon: ImageView = binding.ivPokemon
        val tvName: TextView = binding.tvName
        val tvNum: TextView = binding.tvNum
    }

    /**
     * set data and update ui
     */
    fun setData(list: List<PokemonInSpace>) {
        contentList.clear()
        contentList.addAll(list)
        notifyDataSetChanged()
    }
}