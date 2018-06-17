package de.markbenz.mychatapp.adapters

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.google.firebase.database.DatabaseReference
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView
import de.markbenz.mychatapp.R
import de.markbenz.mychatapp.activities.ChatActivity
import de.markbenz.mychatapp.activities.ProfileActivity
import de.markbenz.mychatapp.models.Users


class UsersAdapter(databaseQuery: DatabaseReference, var context: Context)
    : FirebaseRecyclerAdapter<Users, UsersAdapter.ViewHolder>(
        Users::class.java,
        R.layout.users_row,
        ViewHolder::class.java,
        databaseQuery

) {
    override fun populateViewHolder(viewHolder: ViewHolder?, user: Users?, position: Int) {
        var userId = getRef(position).key //the firebase key of current user
        viewHolder!!.bindView(user!!, context)

        viewHolder.itemView.setOnClickListener {
            // Alert dialog

            var options = arrayOf("Open Profile", "Send Message")
            var builder = AlertDialog.Builder(context)
            builder.setTitle("Select Options")
            builder.setItems(options, DialogInterface.OnClickListener { dialogInterface, i ->
                var userName = viewHolder.userNameTxt
                var userStat = viewHolder.userStatusTxt
                var profilePic = viewHolder.userProfilePicLink

                if(i == 0){
                    //open user Profile
                    var profileIntent = Intent(context, ProfileActivity::class.java)
                    profileIntent.putExtra("userId", userId)
                    context.startActivity(profileIntent)
                }else{

                    var chatIntent = Intent(context, ChatActivity::class.java)
                    chatIntent.putExtra("userId", userId)
                    chatIntent.putExtra("name", userName)
                    chatIntent.putExtra("status", userStat)
                    chatIntent.putExtra("profile", profilePic)

                    context.startActivity(chatIntent)
                    //Send Message
                }
            })

            builder.show()

        }
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var userNameTxt: String? = null
        var userStatusTxt: String? = null
        var userProfilePicLink: String? = null

        fun bindView(user: Users, context: Context) {
            var userName = itemView.findViewById<TextView>(R.id.userName)
            var userStatus = itemView.findViewById<TextView>(R.id.userStatus)
            var userProfilePic = itemView.findViewById<CircleImageView>(R.id.usersProfile)



            userNameTxt = user.display_name
            userStatusTxt = user.user_status
            userProfilePicLink = user.thumb_image

            userName.text = user.display_name
            userStatus.text = user.user_status

            Picasso.get()
                    .load(userProfilePicLink)
                    .placeholder(R.drawable.profile_img)
                    .into(userProfilePic)
        }
    }
}

