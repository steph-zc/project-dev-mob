package br.com.uri.projetodevmob

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class DashboardActivity : AppCompatActivity() {

    private lateinit var txtHello: TextView
    private lateinit var txtFact: TextView
    private lateinit var btnNewFact: Button
    private lateinit var btnLogout: Button

    private lateinit var facts: Array<String>

    private val auth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        txtHello = findViewById(R.id.txtHello)
        txtFact = findViewById(R.id.txtFact)
        btnNewFact = findViewById(R.id.btnNewFact)
        btnLogout = findViewById(R.id.btnLogout)

        facts = resources.getStringArray(R.array.fun_facts)

        val name = auth.currentUser?.displayName
        txtHello.text = getString(R.string.hello_user, name)

        showRandomFact()
        btnNewFact.setOnClickListener { showRandomFact() }
        btnLogout.setOnClickListener { logout() }
    }

    private fun showRandomFact() {
        txtFact.text = facts.random()
    }

    private fun logout() {
        auth.signOut()
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }
}
