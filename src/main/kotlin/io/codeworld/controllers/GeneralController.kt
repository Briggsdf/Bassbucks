package io.codeworld.controllers
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Produces
import io.micronaut.security.annotation.Secured
import io.micronaut.security.rules.SecurityRule
import java.security.Principal


@Controller("/healthcheck")
@Produces(MediaType.APPLICATION_JSON)
class GeneralController {


    /**
     * Method handling HTTP GET requests for users. The returned object will be sent
     * to the client as "application/json" media type.
     *
     * @return A User JSON response containing a list of users.
     */
    @Get("/")
    @Secured(SecurityRule.IS_ANONYMOUS)
    fun doHealthCheck() = HealthResponse()

}

data class HealthResponse(var message: String = "ok")
