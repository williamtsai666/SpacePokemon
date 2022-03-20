package com.wt.spacepokemon.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.wt.spacepokemon.R
import com.wt.spacepokemon.SpaceCraftFragment
import com.wt.spacepokemon.data.Craft
import com.wt.spacepokemon.databinding.ListItemCraftBinding

/**
 *  author : William Tsai
 *  date : 2022/3/17
 *  description : adapter for craft list
 */
class SpaceCraftAdapter : RecyclerView.Adapter<SpaceCraftAdapter.ViewHolder>() {
    private val contentList = arrayListOf<Craft>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ListItemCraftBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = contentList[position]
        holder.craftName.text = item.name
        holder.itemView.setOnClickListener {
            //jump to craft page
            val bundle = bundleOf("craftName" to item.name)
            it.findNavController()
                .navigate(R.id.action_spaceCraftFragment_to_pokemonListFragment, bundle)
        }
    }

    override fun getItemCount(): Int = contentList.size

    inner class ViewHolder(binding: ListItemCraftBinding) : RecyclerView.ViewHolder(binding.root) {
        val craftImage: ImageView = binding.ivCraft
        val craftName: TextView = binding.tvCraftName
    }

    /**
     * set data and update ui
     */
    fun setData(list: List<Craft>) {
        contentList.clear()
        contentList.addAll(list)
        notifyDataSetChanged()
    }
}