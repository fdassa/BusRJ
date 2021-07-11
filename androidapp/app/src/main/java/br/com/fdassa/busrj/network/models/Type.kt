package br.com.fdassa.busrj.network.models

import com.squareup.moshi.Json

enum class Type(val value: String) {
    @field:Json(name = "Onibus") BUS("Onibus"),
    @field:Json(name = "Linha") BUS_LINE("Linha")
}