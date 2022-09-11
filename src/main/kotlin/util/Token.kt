package com.example.util

import com.example.ToJson
import org.http4k.contract.openapi.OpenAPIJackson.auto
import org.http4k.core.Body
import org.http4k.lens.asResult
import java.time.LocalDateTime

data class Token(val id : String, val expires : LocalDateTime) : ToJson

val TokenLens = Body.auto<Token>().toLens().asResult()


