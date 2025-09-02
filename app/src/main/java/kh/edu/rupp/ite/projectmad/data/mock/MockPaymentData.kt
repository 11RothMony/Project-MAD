package kh.edu.rupp.ite.projectmad.data.mock

import kh.edu.rupp.ite.projectmad.data.model.CreditCard
import kh.edu.rupp.ite.projectmad.data.model.Subscription
import kh.edu.rupp.ite.projectmad.data.model.PaymentMethodsResponse
import kh.edu.rupp.ite.projectmad.data.model.DeleteCreditCardResponse

object MockPaymentData {

    fun getMockCreditCards(): List<CreditCard> {
        return listOf(
            CreditCard(
                id = "card_001",
                cardNumber = "4532123456781234",
                cardHolderName = "John Doe",
                expiryMonth = "12",
                expiryYear = "25",
                cardType = "VISA",
                isDefault = true
            ),
            CreditCard(
                id = "card_002",
                cardNumber = "5555123456789999",
                cardHolderName = "Jane Smith",
                expiryMonth = "08",
                expiryYear = "26",
                cardType = "MASTERCARD",
                isDefault = false
            ),
            CreditCard(
                id = "card_003",
                cardNumber = "3782123456789012",
                cardHolderName = "Bob Johnson",
                expiryMonth = "03",
                expiryYear = "27",
                cardType = "AMEX",
                isDefault = false
            )
        )
    }

    fun getMockSubscriptions(): List<Subscription> {
        return listOf(
            Subscription(
                id = "sub_001",
                planName = "Copilot Premium",
                description = "Advanced AI coding assistance with unlimited completions",
                price = 19.99,
                currency = "$",
                billingCycle = "MONTHLY",
                status = "ACTIVE",
                paymentMethodId = "card_001",
                nextBillingDate = "2024-02-15",
                createdDate = "2024-01-15"
            ),
            Subscription(
                id = "sub_002",
                planName = "GitHub Pro",
                description = "Professional GitHub features for individuals",
                price = 4.00,
                currency = "$",
                billingCycle = "MONTHLY",
                status = "ACTIVE",
                paymentMethodId = "card_002",
                nextBillingDate = "2024-02-10",
                createdDate = "2024-01-10"
            ),
            Subscription(
                id = "sub_003",
                planName = "Food Delivery Premium",
                description = "Free delivery and exclusive discounts",
                price = 9.99,
                currency = "$",
                billingCycle = "MONTHLY",
                status = "PAUSED",
                paymentMethodId = "card_001",
                nextBillingDate = "2024-02-20",
                createdDate = "2023-12-20"
            )
        )
    }

    fun getMockPaymentMethodsResponse(): PaymentMethodsResponse {
        return PaymentMethodsResponse(
            creditCards = getMockCreditCards(),
            subscriptions = getMockSubscriptions()
        )
    }

    fun getMockDeleteSuccessResponse(affectedSubscriptions: List<String> = emptyList()): DeleteCreditCardResponse {
        return DeleteCreditCardResponse(
            success = true,
            message = "Credit card deleted successfully",
            affectedSubscriptions = affectedSubscriptions
        )
    }

    fun getMockDeleteErrorResponse(): DeleteCreditCardResponse {
        return DeleteCreditCardResponse(
            success = false,
            message = "Failed to delete credit card. Please try again.",
            affectedSubscriptions = emptyList()
        )
    }
}