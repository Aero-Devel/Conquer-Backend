package util

data class Email(val hej: String)


fun validateEmail(string: String): Boolean {
    if (string.isEmpty()) {
        return false
    }

    // example@gmail.com => com
    val topLevelDomain = string.takeLastWhile { it != '.' }
    val beforeTopLevelDomain = string.dropLastWhile { it != '.' }

    // example@gmail.com => gmail
    val domain =
        beforeTopLevelDomain.dropWhile { it != '@' }
            .drop(1)
            .dropLast(1) // removes . and @

    // example@gmail.com => example
    val recipient = string.takeWhile { it != '@' }


    return string.contains("@")
            && topLevelDomain.isNotBlank()
            && topLevelDomain.isNotEmpty()
            && topLevelDomain != string
            && validDomain(domain)
            && validEmailRecipient(recipient);
}

private fun validEmailRecipient(recipient: String): Boolean {

    val charactersValid = { str: String ->

        str.fold(true) { acc: Boolean, c: Char ->
            acc && (c.isLetterOrDigit() || "!#\$%&'*+-/=?^_`{|}~".contains(c))
        }
    }

    return recipient.isNotEmpty()
            && recipient.isNotBlank()
            && charactersValid(recipient)
            && recipient.first() != '.'
            && recipient.last() != '.'
            && recipient.length <= 64
}

private fun validDomain(domain: String): Boolean {
    val onlyLegalChars = domain.fold(true) { acc: Boolean, c: Char -> acc && c.isLetterOrDigit() || "-.".contains(c) }

    return onlyLegalChars
            && domain.length <= 255

}