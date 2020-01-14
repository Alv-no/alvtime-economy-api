package no.alv.alvtimeeconomyapi.mock

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

data class Fakturagrunnlag(
        val kunde: String
)

@RestController
@RequestMapping("/fakturering")
class MessageController {
    @RequestMapping("/fakturagrunnlag")
    fun message(): Fakturagrunnlag {
        return Fakturagrunnlag("Kunde")
    }
}