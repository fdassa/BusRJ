package br.com.fdassa.busrj.network.models

import com.squareup.moshi.Json

data class Bus(
    @field:Json(name = "id") val id: String,
    @field:Json(name = "type") val type: Type,
    @field:Json(name = "datahora") val date: String,
    @field:Json(name = "latitude") val latitude: Double,
    @field:Json(name = "longitude") val longitude: Double,
    @field:Json(name = "refLinha") val busLineId: String,
)