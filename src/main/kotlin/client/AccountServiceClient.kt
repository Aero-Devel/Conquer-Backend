package calendar.http4k.client

import org.http4k.core.HttpHandler
import org.http4k.core.Method
import org.http4k.core.Request
import org.http4k.core.then

class AccountServiceClient(
	private val httpHandler: HttpHandler
) {
	/**
	 * Delete Account
	 * Deletes an account from the service.
	 * Response:
	 * 	application/json
	 * 		200 OK
	 * 		404 Not Found
	 */
	fun deleteaccount() {
		val httpReq = Request(Method.DELETE, "/account")
		val response = httpHandler(httpReq)

		response
	}

	/**
	 * Create Account
	 * Handles all manipulations of account
	 * Response:
	 * 	application/json
	 * 		200 Success!
	 * 		400 Bad Request
	 * 		409 Account Already exists!
	 */
	fun postaccountcreate() {
		val httpReq = Request(Method.POST, "/account")
		val response = httpHandler(httpReq)

		response
	}

	/**
	 * Login
	 * Validates user and returns a token which is required for the user to access the rest of the
	 * service.
	 */
	fun postlogin() {
		val httpReq = Request(Method.POST, "/login")
		val response = httpHandler(httpReq)

		response
	}
}
