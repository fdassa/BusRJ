package br.com.fdassa.busrj.features.search

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import br.com.fdassa.busrj.R
import br.com.fdassa.busrj.databinding.ItemBusLineBinding
import br.com.fdassa.busrj.network.models.BusLine

class SearchBusItem(
    view: View,
    private val onItemClick: (BusLine) -> Unit
) : RecyclerView.ViewHolder(view) {

    private val binding = ItemBusLineBinding.bind(itemView)

    fun bind(item: BusLine) {
        binding.tvLine.text = itemView.context.getString(R.string.line, item.name)
        binding.root.setOnClickListener {
            onItemClick.invoke(item)
        }
    }
}