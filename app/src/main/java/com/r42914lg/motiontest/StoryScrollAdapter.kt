package com.r42914lg.motiontest

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.r42914lg.motiontest.databinding.ScrollItemBinding

class StoryScrollAdapter(private val listener: StoryScrollListener) : RecyclerView.Adapter<StoryScrollAdapter.ViewHolder>() {

    private val items = ArrayList<Int>()

    interface StoryScrollListener {
        fun onClickedStoryScroll(model: Int, view: View)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setItems(items: List<Int>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ScrollItemBinding = ScrollItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    class ViewHolder(private val itemBinding: ScrollItemBinding,
                     private val listener: StoryScrollListener)
        : RecyclerView.ViewHolder(itemBinding.root), View.OnClickListener {

        private var model: Int = -1

        init {
            itemBinding.root.setOnClickListener(this)
        }

        fun bind(item: Int) = with(itemBinding) {
            model = item

            title.text = "Item with id #$item"
            root.transitionName = "transition_id_$item"
        }

        override fun onClick(view: View?) {
            listener.onClickedStoryScroll(model, itemBinding.root)
        }
    }
}