package br.com.fdassa.busrj.features.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.fdassa.busrj.R
import br.com.fdassa.busrj.network.models.BusLine

class SearchBusLineAdapter(
    private val onItemClick: (BusLine) -> Unit
) : RecyclerView.Adapter<SearchBusItem>() {

    private val busLineList: MutableList<BusLine> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = SearchBusItem(
        LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_bus_line, parent, false),
        onItemClick
    )

    override fun onBindViewHolder(holder: SearchBusItem, position: Int) {
        holder.bind(busLineList[position])
    }

    override fun getItemCount(): Int = busLineList.size

    fun loadBusLineList(newBusLineList: List<BusLine>) {
        busLineList.clear()
        busLineList.addAll(newBusLineList)
        notifyDataSetChanged()
    }
}