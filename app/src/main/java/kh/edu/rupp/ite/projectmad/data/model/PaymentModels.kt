package kh.edu.rupp.ite.projectmad.data.model

data class PaymentMethodsResponse(
    val creditCards: List<CreditCard>,
    val subscriptions: List<Subscription>
)

data class DeleteCreditCardRequest(
    val cardId: String,
    val userId: String,
    val subscriptionId: String? = null
)

data class DeleteCreditCardResponse(
    val success: Boolean,
    val message: String,
    val affectedSubscriptions: List<String> = emptyList()
)