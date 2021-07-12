package br.com.fdassa.busrj.network

import br.com.fdassa.busrj.network.models.BusLine
import com.orhanobut.hawk.Hawk

class BusLocalStorage() {
    private companion object {
        const val FAVORITES_KEY = "FAVORITES_KEY"
    }

    fun getFavoriteList(): List<BusLine> = Hawk.get(FAVORITES_KEY) ?: emptyList()

    fun saveFavoriteList(favoriteList: List<BusLine>) = Hawk.put(FAVORITES_KEY, favoriteList)
}