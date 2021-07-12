package br.com.fdassa.busrj.network

import br.com.fdassa.busrj.network.models.Bus
import br.com.fdassa.busrj.network.models.BusLine


class BusRepository(
    private val busApi: BusApi,
    private val busLocalStorage: BusLocalStorage
) {

    suspend fun getBusesByLine(busLineId: String): List<Bus> {
        return busApi.getBuses(
            query = "refLinha==$busLineId"
        )
    }

    suspend fun searchBusLine(searchQuery: String): List<BusLine> {
        return busApi.getBusLines(
            query = "name~=$searchQuery"
        )
    }

    fun getFavorites(): List<BusLine> = busLocalStorage.getFavoriteList()

    fun isFavorite(busLine: BusLine): Boolean = busLine in getFavorites()

    fun saveFavorite(busLine: BusLine) {
        val newFavoriteList = getFavorites().toMutableList()
        newFavoriteList.add(busLine)
        busLocalStorage.saveFavoriteList(newFavoriteList)
    }

    fun removeFavorite(busLine: BusLine) {
        val newFavoriteList = getFavorites().toMutableList()
        newFavoriteList.remove(busLine)
        busLocalStorage.saveFavoriteList(newFavoriteList)
    }
}