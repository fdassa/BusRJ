package br.com.fdassa.busrj.features.favorites

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.fdassa.busrj.navigation.AppNavigation
import br.com.fdassa.busrj.network.BusRepository
import br.com.fdassa.busrj.network.MutableLiveResource
import br.com.fdassa.busrj.network.getLiveData
import br.com.fdassa.busrj.network.launchResource
import br.com.fdassa.busrj.network.models.BusLine

class FavoritesViewModel(
    private val repository: BusRepository,
    private val navigation: AppNavigation
) : ViewModel() {

    fun getFavorites() = repository.getFavorites()

    fun navigateToBusesByLine(context: Context, busLine: BusLine) {
        navigation.navigateToBusesByLine(context, busLine)
    }
}