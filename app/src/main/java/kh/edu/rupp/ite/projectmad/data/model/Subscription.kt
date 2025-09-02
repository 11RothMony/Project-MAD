package kh.edu.rupp.ite.projectmad.data.model

import com.google.gson.annotations.SerializedName

data class Subscription(
    @SerializedName("id")
    val id: String,
    @SerializedName("planName")
    val planName: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("price")
    val price: Double,
    @SerializedName("currency")
    val currency: String = "USD",
    @SerializedName("billingCycle")
    val billingCycle: String, // MONTHLY, YEARLY
    @SerializedName("status")
    val status: String, // ACTIVE, PAUSED, CANCELLED
    @SerializedName("paymentMethodId")
    val paymentMethodId: String,
    @SerializedName("nextBillingDate")
    val nextBillingDate: String,
    @SerializedName("createdDate")
    val createdDate: String
)