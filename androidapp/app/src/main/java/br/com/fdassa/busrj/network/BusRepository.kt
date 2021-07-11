package br.com.fdassa.busrj.network

import br.com.fdassa.busrj.network.models.Bus
import br.com.fdassa.busrj.network.models.BusLine


class BusRepository(
    private val busApi: BusApi
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
}