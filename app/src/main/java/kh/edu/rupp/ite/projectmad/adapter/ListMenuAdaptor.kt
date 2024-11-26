//package kh.edu.rupp.ite.projectmad.adapter
//
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.TextView
//import androidx.recyclerview.widget.RecyclerView
//import kh.edu.rupp.ite.projectmad.R
//import kh.edu.rupp.ite.projectmad.model.ListMenu
//
//class ListMenuAdaptor(private val users: List<ListMenu>) : RecyclerView.Adapter<ListMenuAdaptor.UserViewHolder>() {
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
//        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_menu, parent, false)
//        return UserViewHolder(view)
//    }
//
//    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
//        val user = users[position]
//        holder.bind(user)
//    }
//
//    override fun getItemCount(): Int = users.size
//
//    inner class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        private val nameTextView: TextView = itemView.findViewById(R.id.title_paragraph)
////        private val emailTextView: TextView = itemView.findViewById(R.id.emailTextView)
//
//        fun bind(user: ListMenu) {
//            nameTextView.text = user.name
////            emailTextView.text = user.email
//        }
//    }
//}
