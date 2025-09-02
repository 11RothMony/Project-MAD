package kh.edu.rupp.ite.projectmad.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kh.edu.rupp.ite.projectmad.data.api.client.ApiClient
import kh.edu.rupp.ite.projectmad.data.model.ApiState
import kh.edu.rupp.ite.projectmad.data.model.CreditCard
import kh.edu.rupp.ite.projectmad.data.model.Subscription
import kh.edu.rupp.ite.projectmad.data.model.PaymentMethodsResponse
import kh.edu.rupp.ite.projectmad.data.model.DeleteCreditCardResponse
import kh.edu.rupp.ite.projectmad.data.repository.PaymentRepository
import kotlinx.coroutines.launch

class PaymentViewModel : ViewModel() {

    private val paymentRepository = PaymentRepository(ApiClient.get().apiService)
    
    private val _paymentMethods = MutableLiveData<ApiState<PaymentMethodsResponse>>()
    val paymentMethods: LiveData<ApiState<PaymentMethodsResponse>> = _paymentMethods

    private val _deleteCreditCardResult = MutableLiveData<ApiState<DeleteCreditCardResponse>>()
    val deleteCreditCardResult: LiveData<ApiState<DeleteCreditCardResponse>> = _deleteCreditCardResult

    private val _creditCards = MutableLiveData<List<CreditCard>>()
    val creditCards: LiveData<List<CreditCard>> = _creditCards

    private val _subscriptions = MutableLiveData<List<Subscription>>()
    val subscriptions: LiveData<List<Subscription>> = _subscriptions

    fun loadPaymentMethods() {
        viewModelScope.launch {
            _paymentMethods.value = ApiState.loading()
            _paymentMethods.value = paymentRepository.getPaymentMethods()
        }
    }

    fun deleteCreditCard(cardId: String, userId: String, subscriptionId: String? = null) {
        viewModelScope.launch {
            _deleteCreditCardResult.value = ApiState.loading()
            val result = paymentRepository.deleteCreditCard(cardId, userId, subscriptionId)
            _deleteCreditCardResult.value = result
            
            // If deletion was successful, refresh the payment methods
            if (result.state == kh.edu.rupp.ite.projectmad.data.model.State.Success) {
                loadPaymentMethods()
            }
        }
    }

    fun updatePaymentMethodsData(paymentMethodsResponse: PaymentMethodsResponse) {
        _creditCards.value = paymentMethodsResponse.creditCards
        _subscriptions.value = paymentMethodsResponse.subscriptions
    }

    fun getSubscriptionsByCardId(cardId: String): List<Subscription> {
        return _subscriptions.value?.filter { it.paymentMethodId == cardId } ?: emptyList()
    }
}