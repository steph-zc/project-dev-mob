package br.com.uri.projetodevmob

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class DashboardActivity : AppCompatActivity() {

    private lateinit var txtHello: TextView
    private lateinit var txtFact: TextView
    private lateinit var btnNewFact: Button

    private lateinit var facts: Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        txtHello = findViewById(R.id.txtHello)
        txtFact = findViewById(R.id.txtFact)
        btnNewFact = findViewById(R.id.btnNewFact)

        facts = resources.getStringArray(R.array.fun_facts)

        val name = FirebaseAuth.getInstance().currentUser?.displayName
        txtHello.text = getString(R.string.hello_user, name)

        showRandomFact()
        btnNewFact.setOnClickListener { showRandomFact() }
    }

    private fun showRandomFact() {
        txtFact.text = facts.random()
    }
}
