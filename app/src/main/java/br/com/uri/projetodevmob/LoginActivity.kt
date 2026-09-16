package br.com.uri.projetodevmob

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private lateinit var edtEmail: EditText
    private lateinit var edtPassword: EditText
    private lateinit var btnLogin: Button

    private val auth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        edtEmail = findViewById(R.id.edtEmail)
        edtPassword = findViewById(R.id.edtPassword)
        btnLogin = findViewById(R.id.btnLogin)

        btnLogin.setOnClickListener { login() }
    }

    private fun login() {
        val email = edtEmail.text.toString()
        val password = edtPassword.text.toString()

        // o Firebase fecha o app se receber email ou senha vazios
        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, R.string.fill_fields, Toast.LENGTH_SHORT).show()
            return
        }

        auth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener { result ->
                val name = result.user?.displayName
                Toast.makeText(this, getString(R.string.hello_user, name), Toast.LENGTH_SHORT).show()
                finish()
            }
            .addOnFailureListener { error ->
                Toast.makeText(this, error.message, Toast.LENGTH_LONG).show()
            }
    }
}
