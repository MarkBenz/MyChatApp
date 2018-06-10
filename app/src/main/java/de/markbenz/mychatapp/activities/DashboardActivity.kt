package de.markbenz.mychatapp.activities

import android.content.Intent
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import de.markbenz.mychatapp.R
import de.markbenz.mychatapp.R.id.dashboardViewPagerId
import de.markbenz.mychatapp.R.id.mainTabs
import de.markbenz.mychatapp.adapters.SectionPagerAdapter
import kotlinx.android.synthetic.main.activity_dashboard.*

class DashboardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        var sectionAdapter: SectionPagerAdapter? = null

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        supportActionBar!!.title = "Dashboard"

        sectionAdapter = SectionPagerAdapter(supportFragmentManager)
        dashboardViewPagerId.adapter = sectionAdapter
        mainTabs.setupWithViewPager(dashboardViewPagerId)
        mainTabs.setTabTextColors(Color.WHITE, Color.GREEN)


        if(intent.extras != null){
            var username = intent.extras.get("name")
            Toast.makeText(this, username.toString(), Toast.LENGTH_LONG)
                    .show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.main_menu, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        super.onOptionsItemSelected(item)

        if (item != null){
            if(item.itemId == R.id.logoutId){
                FirebaseAuth.getInstance().signOut()
                startActivity(Intent(this, MainActivity::class.java))
                finish()

            }
            if(item.itemId == R.id.settingsId){
                startActivity(Intent(this, SettingsActivity::class.java))
                finish()
            }

        }

        return true
    }
}
