package util

import com.example.util.Token
import com.example.util.TokenLens
import dev.forkhandles.result4k.Success
import dev.forkhandles.result4k.get
import org.http4k.core.HttpMessage
import org.http4k.core.Method
import org.http4k.core.Request
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

import java.time.LocalDateTime
import java.time.Month

internal class TokenTest {

    @Test
    fun toJson() {
        val expires = LocalDateTime.of(2000, Month.JANUARY, 1, 0, 0)
        val id = "exampleID123"
        val token = Token(id, expires)
        val req = Request(Method.POST, "").body(token.toJson())

        val result = TokenLens(req)
        assertTrue(result is Success && result.get().id == id && result.get().expires == expires)
    }
}