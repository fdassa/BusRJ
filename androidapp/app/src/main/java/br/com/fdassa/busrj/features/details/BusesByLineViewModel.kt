package br.com.fdassa.busrj.features.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.fdassa.busrj.network.BusRepository
import br.com.fdassa.busrj.network.MutableLiveResource
import br.com.fdassa.busrj.network.getLiveData
import br.com.fdassa.busrj.network.launchResource
import br.com.fdassa.busrj.network.models.Bus

class BusesByLineViewModel(
    private val repository: BusRepository
): ViewModel() {

    private val _busesByLine = MutableLiveResource<List<Bus>>()
    val busesByLine by getLiveData(_busesByLine)

    fun getBusesByLine(busLineId: String) {
        viewModelScope.launchResource(_busesByLine) {
            repository.getBusesByLine(busLineId)
        }
    }
}