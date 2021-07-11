package br.com.fdassa.busrj.network

import br.com.fdassa.busrj.network.models.Bus
import br.com.fdassa.busrj.network.models.BusLine
import retrofit2.http.GET
import retrofit2.http.Query

interface BusApi {

    @GET("/v2/entities?type=Onibus")
    suspend fun getBuses(
        @Query("options") options: String = "keyValues",
        @Query("limit") limit: Int = 100,
        @Query("q") query: String,
    ): List<Bus>

    @GET("/v2/entities?type=Linha")
    suspend fun getBusLines(
        @Query("options") options: String = "keyValues",
        @Query("limit") limit: Int = 100,
        @Query("q") query: String,
    ): List<BusLine>
}