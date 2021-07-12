package br.com.fdassa.busrj.features.home

import android.content.Context
import androidx.lifecycle.ViewModel
import br.com.fdassa.busrj.navigation.AppNavigation

class HomeViewModel(
    private val navigation: AppNavigation
) : ViewModel() {

    fun navigateToSearchBusLine(context: Context) {
        navigation.navigateToSearchBusLine(context)
    }
}