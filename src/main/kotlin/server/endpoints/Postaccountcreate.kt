package calendar.http4k.server.endpoints

import org.http4k.core.Method
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status
import org.http4k.routing.RoutingHttpHandler
import org.http4k.routing.bind

/**
 * Create Account
 * Handles all manipulations of account
 * Response:
 * 	application/json
 * 		200 Success!
 * 		400 Bad Request
 * 		409 Account Already exists!
 */
fun PostAccount(): RoutingHttpHandler {
	return "/account" bind Method.POST to { req: Request ->
		Response(Status.OK)
	}
}
