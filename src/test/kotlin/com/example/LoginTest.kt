package com.example

import dev.forkhandles.result4k.Success
import dev.forkhandles.result4k.get
import org.http4k.core.Method
import org.http4k.core.Request
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class LoginTest{

@Test
fun marshallTooOnlyLoginform(){
    val login = Login("hello","world")
    val jsonLoginForm = login.toJson()
    val message = Request(Method.POST,"").body(jsonLoginForm)


    when (val eLoginForm = LoginLens(message)){
        is Success -> assertEquals(eLoginForm.get(), login)
        else -> assertTrue(false,"Lens failure when marshalling")
    }
}


}