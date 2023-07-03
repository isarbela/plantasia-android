package com.example.plantasia.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.res.TypedArrayUtils.getText
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.plantasia.R
import com.example.plantasia.repository.Plant

class PlantListAdapter: ListAdapter<Plant, PlantListAdapter.PlantViewHolder>(PlantsComparator()) {

    var onItemClick : ( (Plant) -> Unit)? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlantViewHolder {
        return PlantViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: PlantViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current.name, current.common_name, current.age)
        holder.itemView.setOnClickListener {
            onItemClick?.invoke(current)
        }
    }

    class PlantViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameTextView: TextView = itemView.findViewById(R.id.name_edittext)
        private val speciesTextView: TextView = itemView.findViewById(R.id.species_edittext)
        private val ageTextView: TextView = itemView.findViewById(R.id.age_edittext)

        fun bind(name: String?, species: String?, age: Int?) {
            nameTextView.text = name
            speciesTextView.text = species
            ageTextView.text = age.toString()
        }

        companion object {
            fun create(parent: ViewGroup): PlantViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.recyclerview_item, parent, false)
                return PlantViewHolder(view)
            }
        }
    }

    class PlantsComparator : DiffUtil.ItemCallback<Plant>() {
        override fun areItemsTheSame(oldItem: Plant, newItem: Plant): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Plant, newItem: Plant): Boolean {
            return oldItem.id == newItem.id
        }
    }
}