package app.vincent_valdinata.mobiledeveloperintern2024.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import app.vincent_valdinata.mobiledeveloperintern2024.R
import app.vincent_valdinata.mobiledeveloperintern2024.models.User
import com.bumptech.glide.Glide


class UserAdapter(private var users: List<User>) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = users[position]
        holder.bind(user)
    }

    override fun getItemCount(): Int = users.size

    fun updateUsers(newUsers: List<User>) {
        users = newUsers
        notifyDataSetChanged()
    }

    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val userNameTextView: TextView = itemView.findViewById(R.id.userNameTextView)
        private val emailTextView: TextView = itemView.findViewById(R.id.emailTextView)
        private val avatarImageView: ImageView = itemView.findViewById(R.id.avatarImageView)

        fun bind(user: User) {
            userNameTextView.text = "${user.first_name} ${user.last_name}"
            emailTextView.text = user.email

            // Load the avatar image using Glide
            Glide.with(itemView.context)
                .load(user.avatar)  // The URL for the avatar image
                .placeholder(R.drawable.ic_avatar_placeholder)  // Optional placeholder image
                .error(R.drawable.ic_avatar_placeholder)  // Error image if the URL is not valid
                .into(avatarImageView)
        }
    }
}
