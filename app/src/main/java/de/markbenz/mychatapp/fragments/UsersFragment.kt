package de.markbenz.mychatapp.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

import de.markbenz.mychatapp.R
import de.markbenz.mychatapp.adapters.UsersAdapter
import kotlinx.android.synthetic.main.fragment_users.*
import kotlin.coroutines.experimental.coroutineContext

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class UsersFragment : Fragment() {
    var mUserDatabase: DatabaseReference? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_users, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var linearLayoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL, false)
        mUserDatabase = FirebaseDatabase.getInstance().reference.child("Users")

        userRecyclerViewId.setHasFixedSize(true)

        userRecyclerViewId.layoutManager = linearLayoutManager
        userRecyclerViewId.adapter = UsersAdapter(mUserDatabase!!, context!!)


    }


}
