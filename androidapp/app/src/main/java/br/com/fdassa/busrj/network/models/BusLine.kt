package br.com.fdassa.busrj.network.models

import com.squareup.moshi.Json
import java.io.Serializable

data class BusLine(
    @field:Json(name = "id") val id: String,
    @field:Json(name = "type") val type: Type,
    @field:Json(name = "name") val name: String
): Serializable
