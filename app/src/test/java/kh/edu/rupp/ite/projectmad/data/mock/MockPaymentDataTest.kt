package kh.edu.rupp.ite.projectmad.data.mock

import org.junit.Assert.*
import org.junit.Test
import kh.edu.rupp.ite.projectmad.data.model.CreditCard
import kh.edu.rupp.ite.projectmad.data.model.Subscription

class MockPaymentDataTest {

    @Test
    fun testMockCreditCards() {
        val creditCards = MockPaymentData.getMockCreditCards()
        
        assertNotNull(creditCards)
        assertEquals(3, creditCards.size)
        
        // Test first card
        val firstCard = creditCards[0]
        assertEquals("card_001", firstCard.id)
        assertEquals("4532123456781234", firstCard.cardNumber)
        assertEquals("**** **** **** 1234", firstCard.maskedCardNumber)
        assertEquals("John Doe", firstCard.cardHolderName)
        assertEquals("VISA", firstCard.cardType)
        assertTrue(firstCard.isDefault)
    }

    @Test
    fun testMockSubscriptions() {
        val subscriptions = MockPaymentData.getMockSubscriptions()
        
        assertNotNull(subscriptions)
        assertEquals(3, subscriptions.size)
        
        // Test first subscription
        val firstSub = subscriptions[0]
        assertEquals("sub_001", firstSub.id)
        assertEquals("Copilot Premium", firstSub.planName)
        assertEquals(19.99, firstSub.price, 0.01)
        assertEquals("card_001", firstSub.paymentMethodId)
        assertEquals("ACTIVE", firstSub.status)
    }

    @Test
    fun testPaymentMethodsResponse() {
        val response = MockPaymentData.getMockPaymentMethodsResponse()
        
        assertNotNull(response)
        assertEquals(3, response.creditCards.size)
        assertEquals(3, response.subscriptions.size)
    }

    @Test
    fun testDeleteSuccessResponse() {
        val affectedSubs = listOf("Copilot Premium", "Food Delivery Premium")
        val response = MockPaymentData.getMockDeleteSuccessResponse(affectedSubs)
        
        assertTrue(response.success)
        assertEquals("Credit card deleted successfully", response.message)
        assertEquals(2, response.affectedSubscriptions.size)
        assertTrue(response.affectedSubscriptions.contains("Copilot Premium"))
    }

    @Test
    fun testCreditCardMasking() {
        val card = CreditCard(
            id = "test_001",
            cardNumber = "4532123456789876",
            cardHolderName = "Test User",
            expiryMonth = "12",
            expiryYear = "25",
            cardType = "VISA",
            isDefault = false
        )
        
        assertEquals("**** **** **** 9876", card.maskedCardNumber)
    }

    @Test
    fun testSubscriptionLinkedToCreditCard() {
        val creditCards = MockPaymentData.getMockCreditCards()
        val subscriptions = MockPaymentData.getMockSubscriptions()
        
        // Find subscriptions linked to card_001
        val card001Subscriptions = subscriptions.filter { it.paymentMethodId == "card_001" }
        assertEquals(2, card001Subscriptions.size)
        
        // Verify subscription names
        val subscriptionNames = card001Subscriptions.map { it.planName }
        assertTrue(subscriptionNames.contains("Copilot Premium"))
        assertTrue(subscriptionNames.contains("Food Delivery Premium"))
    }
}