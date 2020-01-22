package no.alv.alvtimeeconomyapi.unieconomy

data class Customer(
        val BusinessRelationID: Int,
        val CustomValues: CustomValues,
        val Deleted: Boolean,
        val ID: Int,
        val Info: Info
)

class CustomValues(
)

data class Info(
        val CustomValues: CustomValuesX,
        val ID: Int
)

class CustomValuesX(
)

data class Invoice(
    val CustomerID: Int,
    val CustomerName: String,
    val DistributionPlanID: Int,
    val InvoiceDate: String,
    val Items: List<Item>,
    val OurReference: String,
    val PaymentDueDate: String,
    val PaymentInfoTypeID: Int
)

data class Item(
    val AccountID: Int,
    val ItemText: String,
    val NumberOfItems: Int,
    val PriceExVat: Int,
    val PriceExVatCurrency: Int,
    val PriceIncVat: Int,
    val PriceIncVatCurrency: Int,
    val ProductID: Int,
    val SortIndex: Int,
    val SumTotalExVat: Int,
    val SumTotalExVatCurrency: Int,
    val SumTotalIncVat: Int,
    val SumTotalIncVatCurrency: Int,
    val SumVat: Int,
    val SumVatCurrency: Int,
    val Unit: String,
    val VatPercent: Int,
    val VatTypeID: Int,
    val _createguid: String,
    val _isDirty: Boolean
)