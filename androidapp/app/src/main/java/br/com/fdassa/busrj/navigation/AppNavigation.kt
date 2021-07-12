package br.com.fdassa.busrj.navigation

import android.content.Context
import br.com.fdassa.busrj.features.details.BusesByLineActivity
import br.com.fdassa.busrj.features.favorites.FavoritesActivity
import br.com.fdassa.busrj.features.search.SearchBusLineActivity
import br.com.fdassa.busrj.network.models.BusLine
import br.com.fdassa.busrj.utils.startActivity

class AppNavigation {

    companion object {
        const val EXTRA_BUS_LINE = "EXTRA_BUS_LINE"
    }

    fun navigateToBusesByLine(context: Context, busLine: BusLine) {
        context.startActivity<BusesByLineActivity> {
            putExtra(EXTRA_BUS_LINE, busLine)
        }
    }

    fun navigateToSearchBusLine(context: Context) {
        context.startActivity<SearchBusLineActivity>()
    }

    fun navigateToFavorites(context: Context) {
        context.startActivity<FavoritesActivity>()
    }
}