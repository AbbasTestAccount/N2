package com.example.n2

import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.StatusPages
import io.ktor.response.respond


val handleException: Application.() -> Unit = {
    install(StatusPages) {
        exception<CustomExceptions> {
            call.respond(ResponseBase(it.status, null, it.description))
        }
        exception<Throwable> {
            it.printStackTrace()
            call.respond(ResponseBase(9999, null, it.message!!))
        }
    }
}


open class CustomExceptions(val status: Int, val description: String) : Exception(description)

class MissingParamsException(param: String) : CustomExceptions(100, "Missing parameter: $param")
class GeneralException(description: String) : CustomExceptions(999, description)