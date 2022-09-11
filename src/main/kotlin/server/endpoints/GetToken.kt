package calendar.http4k.server.endpoints

import com.example.LoginLens
import com.example.util.Token
import dev.forkhandles.result4k.Success
import org.http4k.core.Method
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status
import org.http4k.routing.RoutingHttpHandler
import org.http4k.routing.bind
import java.time.LocalDateTime

/**
 * Login
 * Validates user and returns a token which is required for the user to access the rest of the
 * service.
 */
fun GetToken(): RoutingHttpHandler {
	return "/token" bind Method.GET to { req: Request ->
        when (LoginLens(req)) {
            is Success -> Response(Status.OK).body(Token("abcdefg", LocalDateTime.now()).toJson())
            else -> Response(Status.BAD_REQUEST)
        }
	}
}


