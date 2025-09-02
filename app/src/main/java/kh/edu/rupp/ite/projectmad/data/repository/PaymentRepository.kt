package kh.edu.rupp.ite.projectmad.data.repository

import kh.edu.rupp.ite.projectmad.data.api.service.ApiService
import kh.edu.rupp.ite.projectmad.data.model.PaymentMethodsResponse
import kh.edu.rupp.ite.projectmad.data.model.DeleteCreditCardRequest
import kh.edu.rupp.ite.projectmad.data.model.DeleteCreditCardResponse
import kh.edu.rupp.ite.projectmad.data.model.ApiState
import kh.edu.rupp.ite.projectmad.data.model.State

class PaymentRepository(private val apiService: ApiService) {
    
    suspend fun getPaymentMethods(): ApiState<PaymentMethodsResponse> {
        return try {
            val response = apiService.getPaymentMethods()
            if (response.success) {
                ApiState.success(response.data!!)
            } else {
                ApiState.error(response.message)
            }
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
            val request = DeleteCreditCardRequest(cardId, userId, subscriptionId)
            val response = apiService.deleteCreditCard(request)
            if (response.success) {
                ApiState.success(response.data!!)
            } else {
                ApiState.error(response.message)
            }
        } catch (e: Exception) {
            ApiState.error("Failed to delete credit card: ${e.message}")
        }
    }
}