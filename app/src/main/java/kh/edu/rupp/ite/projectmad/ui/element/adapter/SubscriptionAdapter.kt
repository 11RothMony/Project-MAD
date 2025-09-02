package kh.edu.rupp.ite.projectmad.ui.element.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kh.edu.rupp.ite.projectmad.R
import kh.edu.rupp.ite.projectmad.data.model.Subscription
import kh.edu.rupp.ite.projectmad.data.model.CreditCard

class SubscriptionAdapter(
    private var subscriptions: List<Subscription>,
    private var creditCards: List<CreditCard>,
    private val onManageClick: (Subscription) -> Unit
) : RecyclerView.Adapter<SubscriptionAdapter.SubscriptionViewHolder>() {

    inner class SubscriptionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val subscriptionNameTextView: TextView = itemView.findViewById(R.id.tv_subscription_name)
        private val subscriptionDescriptionTextView: TextView = itemView.findViewById(R.id.tv_subscription_description)
        private val subscriptionPriceTextView: TextView = itemView.findViewById(R.id.tv_subscription_price)
        private val nextBillingTextView: TextView = itemView.findViewById(R.id.tv_next_billing)
        private val paymentMethodTextView: TextView = itemView.findViewById(R.id.tv_payment_method)
        private val statusTextView: TextView = itemView.findViewById(R.id.tv_status)
        private val manageButton: ImageView = itemView.findViewById(R.id.btn_manage_subscription)

        fun bind(subscription: Subscription) {
            subscriptionNameTextView.text = subscription.planName
            subscriptionDescriptionTextView.text = subscription.description
            subscriptionPriceTextView.text = "${subscription.currency}${subscription.price}/${subscription.billingCycle.lowercase()}"
            nextBillingTextView.text = "Next billing: ${subscription.nextBillingDate}"
            statusTextView.text = subscription.status
            
            // Find the associated credit card
            val associatedCard = creditCards.find { it.id == subscription.paymentMethodId }
            paymentMethodTextView.text = if (associatedCard != null) {
                "Payment: ${associatedCard.maskedCardNumber}"
            } else {
                "Payment: Unknown"
            }
            
            // Set status color based on subscription status
            when (subscription.status.uppercase()) {
                "ACTIVE" -> {
                    statusTextView.setBackgroundResource(R.drawable.bg_button_green)
                }
                "PAUSED" -> {
                    statusTextView.setBackgroundResource(R.drawable.bg_whitegray_circle)
                    statusTextView.setTextColor(itemView.context.getColor(R.color.grey))
                }
                "CANCELLED" -> {
                    statusTextView.setBackgroundResource(R.drawable.bg_whitegray_circle_red)
                }
            }
            
            // Set manage button click listener
            manageButton.setOnClickListener {
                onManageClick(subscription)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubscriptionViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_subscription, parent, false)
        return SubscriptionViewHolder(view)
    }

    override fun onBindViewHolder(holder: SubscriptionViewHolder, position: Int) {
        holder.bind(subscriptions[position])
    }

    override fun getItemCount(): Int = subscriptions.size

    fun updateSubscriptions(newSubscriptions: List<Subscription>, newCreditCards: List<CreditCard>) {
        subscriptions = newSubscriptions
        creditCards = newCreditCards
        notifyDataSetChanged()
    }
}