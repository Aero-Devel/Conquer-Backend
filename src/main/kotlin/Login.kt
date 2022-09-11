package com.example

import org.http4k.contract.openapi.OpenAPIJackson.auto
import org.http4k.core.Body
import org.http4k.format.Jackson.asJsonObject
import org.http4k.lens.asResult

data class Login(val mail : String, val password : String): ToJson

val LoginLens = Body.auto<Login>().toLens().asResult()


interface ToJson {
    fun toJson(): String = this.asJsonObject().toPrettyString()
}

