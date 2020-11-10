package no.alv.alvtimeeconomyapi.unieconomy

import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.*
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.UriComponentsBuilder
import java.lang.IllegalStateException


class UniEconomyServiceGateway {
    /**
     * GET https://test.unieconomy.no/api/biz/customers?expand=Info&filter=info.name eq 'Jo A I Rivelsrud'&select=id

    Result:
    [
    {
    "CustomValues": {},
    "ID": 30,
    "Deleted": false,
    "BusinessRelationID": 129,
    "Info": {
    "CustomValues": {},
    "ID": 129
    }
    }
    ]
     * */
    fun getCustomerIdByName(name: String): List<Customer>? {
        val restTemplate = RestTemplate()

        val headers = HttpHeaders()
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE)

        val builder = UriComponentsBuilder.fromHttpUrl("https://test.unieconomy.no/api/biz/customers")
                .queryParam("expand", "true")
                .queryParam("filter", "info.name eq '${name}'")
                .queryParam("select", "id")

        val entity: HttpEntity<*> = HttpEntity<Any>(headers)

        val response = restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.GET,
                entity,
                typeRef<List<Customer>>())
        if (response.statusCode == HttpStatus.ACCEPTED) {
            return response.body
        }

        throw IllegalStateException()
    }

    /**
     * POST https://test.unieconomy.no/api/biz/invoices

    Request:
    {
    "InvoiceDate": "2019-10-08",
    "PaymentDueDate": "2019-10-22",
    "OurReference": "Jo Are Ingvaldsen Rivelsrud",
    "CustomerID": 30,
    "CustomerName": "Jo A I Rivelsrud",
    "Items": [
    {
    "ProductID": 1,
    "ItemText": "Weber 114",
    "Unit": "stk",
    "NumberOfItems": 1,
    "PriceExVat": 36000,
    "AccountID": 283,
    "SumVat": 9000,
    "_isDirty": true,
    "SumVatCurrency": 9000,
    "_createguid": "b46d1b85-b7c0-4495-8bad-6054f514edc2",
    "VatTypeID": 11,
    "PriceIncVat": 45000,
    "PriceExVatCurrency": 36000,
    "PriceIncVatCurrency": 45000,
    "VatPercent": 25,
    "SumTotalExVat": 36000,
    "SumTotalIncVat": 45000,
    "SumTotalExVatCurrency": 36000,
    "SumTotalIncVatCurrency": 45000,
    "SortIndex": 1
    }
    ],
    "PaymentInfoTypeID": 5,
    "DistributionPlanID": 5
    }
     *
     * */

    fun postCustomerInvoice(invoice: Invoice)  {
        val restTemplate = RestTemplate()

        val headers = HttpHeaders()
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE)

        val builder = UriComponentsBuilder.fromHttpUrl("https://test.unieconomy.no/api/biz/invoices")

        val entity: HttpEntity<*> = HttpEntity<Any>(invoice,headers)

        val response = restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.POST,
                entity,
                typeRef<List<Customer>>())
        if (response.statusCode != HttpStatus.ACCEPTED) {
            throw IllegalStateException()
        }
    }
}

inline fun <reified T : Any> typeRef(): ParameterizedTypeReference<T> = object : ParameterizedTypeReference<T>() {}
