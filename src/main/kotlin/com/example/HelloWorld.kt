package com.example

import org.http4k.core.HttpHandler
import org.http4k.core.Method.GET
import org.http4k.core.Response
import org.http4k.core.Status.Companion.OK
import org.http4k.core.Request
import org.http4k.routing.bind
import org.http4k.routing.routes
import org.http4k.routing.webJars
import org.http4k.server.SunHttp
import org.http4k.server.asServer

// fun HelloWorld(): HttpHandler {
//     return routes("/" bind GET to { Response(OK).body("hello world!") })
// }

fun main() {
    // mix the WebJars routing into your app...
    val app = routes(
        "/" bind GET to { req: Request -> Response(OK) },
        webJars()
    )

    app.asServer(SunHttp(8080)).start()

    // then browse to: http://localhost:8080/webjars/swagger-ui/3.44.0/index.html
}
