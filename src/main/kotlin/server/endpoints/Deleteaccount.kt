package calendar.http4k.server.endpoints

import org.http4k.core.Method
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status
import org.http4k.routing.RoutingHttpHandler
import org.http4k.routing.bind

/**
 * Delete Account
 * Deletes an account from the service.
 * Response:
 * 	application/json
 * 		200 OK
 * 		404 Not Found
 */
fun DeleteAccount(): RoutingHttpHandler {
	return "/account" bind Method.DELETE to { req: Request ->
		Response(Status.OK)
	}
}


