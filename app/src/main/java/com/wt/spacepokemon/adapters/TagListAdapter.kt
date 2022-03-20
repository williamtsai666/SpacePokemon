package com.wt.spacepokemon.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.wt.spacepokemon.R
import com.wt.spacepokemon.SpaceCraftFragment
import com.wt.spacepokemon.data.Craft
import com.wt.spacepokemon.data.PokemonInSpace
import com.wt.spacepokemon.databinding.ListItemCraftBinding
import com.wt.spacepokemon.databinding.ListItemPokemonBinding
import com.wt.spacepokemon.databinding.ListItemTagBinding

/**
 *  author : William Tsai
 *  date : 2022/3/17
 *  description : adapter for type and weakness list
 */
class TagListAdapter : RecyclerView.Adapter<TagListAdapter.ViewHolder>() {
    private val contentList = arrayListOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ListItemTagBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = contentList[position]
        holder.tagName.text = item
    }

    override fun getItemCount(): Int = contentList.size

    inner class ViewHolder(binding: ListItemTagBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val tagName: TextView = binding.tvTag
    }

    fun setData(list: List<String>) {
        contentList.clear()
        contentList.addAll(list)
        notifyDataSetChanged()
    }
}