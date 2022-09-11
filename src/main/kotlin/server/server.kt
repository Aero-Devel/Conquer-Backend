package server

import calendar.http4k.server.endpoints.DeleteAccount
import calendar.http4k.server.endpoints.PostAccount
import calendar.http4k.server.endpoints.GetToken
import org.http4k.core.HttpHandler
import org.http4k.core.then
import org.http4k.filter.DebuggingFilters.PrintRequest
import org.http4k.routing.routes
import org.http4k.server.SunHttp
import org.http4k.server.asServer



object AccountServiceServer {
    operator fun invoke(): HttpHandler = routes(
        DeleteAccount()
        ,	PostAccount()
        ,	GetToken()
    )
}

fun main() {

    val printingApp: HttpHandler = PrintRequest().then(AccountServiceServer())

    val server = printingApp.asServer(SunHttp(9000)).start()

    println("Server started on " + server.port())
}


