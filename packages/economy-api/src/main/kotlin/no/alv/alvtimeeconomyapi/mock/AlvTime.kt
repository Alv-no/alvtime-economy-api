package no.alv.alvtimeeconomyapi.mock

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

data class AlvTimeApiData(
        val customer: String
)

@RestController
@RequestMapping("/invoice")
class AlvTimeApi {

    @RequestMapping("/")
    fun customer(): AlvTimeApiData {
        return AlvTimeApiData("customer")
    }
}