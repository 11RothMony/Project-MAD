package kh.edu.rupp.ite.projectmad.ui.element.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kh.edu.rupp.ite.projectmad.R
import kh.edu.rupp.ite.projectmad.data.model.CreditCard

class CreditCardAdapter(
    private var creditCards: List<CreditCard>,
    private val onDeleteClick: (CreditCard) -> Unit
) : RecyclerView.Adapter<CreditCardAdapter.CreditCardViewHolder>() {

    inner class CreditCardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val cardNumberTextView: TextView = itemView.findViewById(R.id.tv_card_number)
        private val cardHolderTextView: TextView = itemView.findViewById(R.id.tv_card_holder)
        private val cardExpiryTextView: TextView = itemView.findViewById(R.id.tv_card_expiry)
        private val cardTypeImageView: ImageView = itemView.findViewById(R.id.img_card_type)
        private val defaultLabelTextView: TextView = itemView.findViewById(R.id.tv_default_label)
        private val deleteButton: ImageView = itemView.findViewById(R.id.btn_delete_card)

        fun bind(creditCard: CreditCard) {
            cardNumberTextView.text = creditCard.maskedCardNumber
            cardHolderTextView.text = creditCard.cardHolderName
            cardExpiryTextView.text = "Expires ${creditCard.expiryMonth}/${creditCard.expiryYear}"
            
            // Show default label if this is the default card
            defaultLabelTextView.visibility = if (creditCard.isDefault) View.VISIBLE else View.GONE
            
            // Set card type icon (this would be enhanced to show actual card type icons)
            when (creditCard.cardType.uppercase()) {
                "VISA" -> cardTypeImageView.setImageResource(R.drawable.baseline_add_card_24)
                "MASTERCARD" -> cardTypeImageView.setImageResource(R.drawable.baseline_add_card_24)
                else -> cardTypeImageView.setImageResource(R.drawable.baseline_add_card_24)
            }
            
            // Set delete button click listener
            deleteButton.setOnClickListener {
                onDeleteClick(creditCard)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CreditCardViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_credit_card, parent, false)
        return CreditCardViewHolder(view)
    }

    override fun onBindViewHolder(holder: CreditCardViewHolder, position: Int) {
        holder.bind(creditCards[position])
    }

    override fun getItemCount(): Int = creditCards.size

    fun updateCreditCards(newCreditCards: List<CreditCard>) {
        creditCards = newCreditCards
        notifyDataSetChanged()
    }
}