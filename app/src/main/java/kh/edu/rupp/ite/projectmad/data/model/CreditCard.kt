package kh.edu.rupp.ite.projectmad.data.model

import com.google.gson.annotations.SerializedName

data class CreditCard(
    @SerializedName("id")
    val id: String,
    @SerializedName("cardNumber")
    val cardNumber: String,
    @SerializedName("cardHolderName")
    val cardHolderName: String,
    @SerializedName("expiryMonth")
    val expiryMonth: String,
    @SerializedName("expiryYear")
    val expiryYear: String,
    @SerializedName("cardType")
    val cardType: String, // VISA, MASTERCARD, etc.
    @SerializedName("isDefault")
    val isDefault: Boolean = false
) {
    // Mask the card number for display (show only last 4 digits)
    val maskedCardNumber: String
        get() = "**** **** **** ${cardNumber.takeLast(4)}"
}