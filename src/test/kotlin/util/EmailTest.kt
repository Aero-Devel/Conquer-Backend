package util

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class EmailTest{



    @Test
    fun validateEmail_requires_at() {
        val invalidEmail = ""
        assertFalse(validateEmail(invalidEmail))
    }

    @Test
    fun validateEmail_requires_tld() {
        val invalidEmail = "@com"
        assertFalse(validateEmail(invalidEmail))
    }

    @Test
    fun validEmailShouldPass() {
        val validEmail = "example@gmail.com"
        assertTrue(validateEmail(validEmail))
    }

    /**
     * requirements :
     *  A-Z , a-z , 0 - 9
     *   allowed special characters : !#$%&'*+-/=?^_`{|}~
     *   . is allowed as long as it's not the first or the last character
     *   recipient name stops at @
     *   64 characters maximum
     */
    @Test
    fun recipentName_nodots() {
        val invalidEmail1 = ".example@gmail.com"
        assertFalse(validateEmail(invalidEmail1))
        val invalidEmail2 = "example.@gmail.com"
        assertFalse(validateEmail(invalidEmail2))
    }

    @Test
    fun recipentName_legalLength() {
        assertFalse(validateEmail("a".repeat(65) + "@gmail.com"))
    }

    @Test
    fun recipentName_legalChars() {
        assertFalse(validateEmail("«lol" + "@gmail.com"))
    }

    @Test
    fun domainName_max255Chars() {
        val invalidEmail = "example@" + "l".repeat(256) + ".com"
        assertFalse(validateEmail(invalidEmail))
    }

    @Test
    fun domainName_legalChars() {
        val invalidEmail = "example@" + "l!asd" + ".com"
        assertFalse(validateEmail(invalidEmail))
    }
}