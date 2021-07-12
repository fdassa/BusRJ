package br.com.fdassa.busrj.features.search

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.fdassa.busrj.navigation.AppNavigation
import br.com.fdassa.busrj.network.BusRepository
import br.com.fdassa.busrj.network.MutableLiveResource
import br.com.fdassa.busrj.network.getLiveData
import br.com.fdassa.busrj.network.launchResource
import br.com.fdassa.busrj.network.models.BusLine

class SearchBusLineViewModel(
    private val repository: BusRepository,
    private val navigation: AppNavigation
) : ViewModel() {

    private val _searchBusLine = MutableLiveResource<List<BusLine>>()
    val searchBusLine by getLiveData(_searchBusLine)

    private var lastQuery: String = ""

    fun searchBusLine(query: String) {
        lastQuery = query
        viewModelScope.launchResource(_searchBusLine) {
            repository.searchBusLine(query)
        }
    }

    fun retrySearch() = searchBusLine(lastQuery)

    fun navigateToBusesByLine(context: Context, busLine: BusLine) {
        navigation.navigateToBusesByLine(context, busLine)
    }
}