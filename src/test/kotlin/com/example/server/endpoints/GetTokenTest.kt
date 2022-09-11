package com.example.server.endpoints

import calendar.http4k.server.endpoints.GetToken
import com.example.Login
import com.example.util.TokenLens
import com.natpryce.hamkrest.Matcher
import com.natpryce.hamkrest.assertion.assertThat
import dev.forkhandles.result4k.Success
import org.http4k.contract.openapi.OpenAPIJackson.asJsonObject
import org.http4k.core.*
import org.http4k.hamkrest.hasBody

import org.http4k.server.asServer
import org.junit.jupiter.api.Test
import org.http4k.hamkrest.hasStatus
import org.http4k.server.SunHttp
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach

internal class GetTokenKtTest {
    private val server = GetToken().asServer(SunHttp(9000))
    private val url = "http://localhost:${server.port()}/login"
    fun throughGetToken(request: Request): Response = GetToken().invoke(request)

    @BeforeEach
    fun setup() {
        server.start()
    }

    @AfterEach
    fun teardown() {
        server.stop()
    }

    data class BadLoginForm(val email: String, val pw: String)

    val validAccount = Login("example@gmail.com","abc123ABC!.")
    val validRequest = Request(Method.GET, "/token").body(validAccount.toJson())
    val invalidAccount = Login("example@gmail.com","")
    private fun assertGetTokenResponse(request: Request, matcher: Matcher<Response>) : Unit = assertThat(throughGetToken(request), matcher)

    @Test
    fun postlogin_follows_format() {
        val body: String = validAccount.asJsonObject().toPrettyString()
        val request = Request(Method.GET, "/token").body(body)
        val response = throughGetToken(request)
        assertThat(response, hasStatus(Status.OK))
    }
    @Test
    fun postLogin_returns_token(){
        val response = throughGetToken(validRequest)
        val lensResult = TokenLens(response)
        assertTrue(lensResult is Success)
    }

    @Test
    fun postLogin_nonexistingAccount_returns401(){
        val response = throughGetToken(validRequest)
        assertThat(response, hasStatus(Status.NOT_FOUND))
        val shouldExplanation = response.body
    }

    //data class Email(val fullEmailAddr: String)

    @Test
    fun postlogin_returns_invalidformat() {

        val body = BadLoginForm("abc", "dfg").asJsonObject().toPrettyString()
        val request = Request(Method.POST, url).body(body)
        val response = throughGetToken(request)

    }

    // should require form
    // test mail validator and pass validator
    // should require new account
    // should respond differently depending on if existing account or bad information
    // https://www.http4k.org/guide/reference/json/
}
