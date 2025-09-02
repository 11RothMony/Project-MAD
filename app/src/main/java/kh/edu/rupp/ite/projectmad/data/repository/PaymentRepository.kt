package kh.edu.rupp.ite.projectmad.data.repository

import kh.edu.rupp.ite.projectmad.data.api.service.ApiService
import kh.edu.rupp.ite.projectmad.data.model.PaymentMethodsResponse
import kh.edu.rupp.ite.projectmad.data.model.DeleteCreditCardRequest
import kh.edu.rupp.ite.projectmad.data.model.DeleteCreditCardResponse
import kh.edu.rupp.ite.projectmad.data.model.ApiState
import kh.edu.rupp.ite.projectmad.data.model.State
import kh.edu.rupp.ite.projectmad.data.mock.MockPaymentData
import kotlinx.coroutines.delay

class PaymentRepository(private val apiService: ApiService) {
    
    suspend fun getPaymentMethods(): ApiState<PaymentMethodsResponse> {
        return try {
            // Simulate network delay
            delay(1000)
            
            // For now, use mock data since the API might not be available
            // In production, you would uncomment the line below and use the actual API
            // val response = apiService.getPaymentMethods()
            
            // Using mock data for demonstration
            val mockResponse = MockPaymentData.getMockPaymentMethodsResponse()
            ApiState.success(mockResponse)
            
        } catch (e: Exception) {
            ApiState.error("Failed to load payment methods: ${e.message}")
        }
    }

    suspend fun deleteCreditCard(
        cardId: String,
        userId: String,
        subscriptionId: String? = null
    ): ApiState<DeleteCreditCardResponse> {
        return try {
            // Simulate network delay
            delay(1500)
            
            // For now, use mock data since the API might not be available
            // In production, you would uncomment the lines below and use the actual API
            // val request = DeleteCreditCardRequest(cardId, userId, subscriptionId)
            // val response = apiService.deleteCreditCard(request)
            
            // Using mock data for demonstration
            // Check if the card is being used by subscriptions
            val mockSubscriptions = MockPaymentData.getMockSubscriptions()
            val affectedSubscriptions = mockSubscriptions
                .filter { it.paymentMethodId == cardId }
                .map { it.planName }
            
            val mockResponse = MockPaymentData.getMockDeleteSuccessResponse(affectedSubscriptions)
            ApiState.success(mockResponse)
            
        } catch (e: Exception) {
            ApiState.error("Failed to delete credit card: ${e.message}")
        }
    }
}