package ie.dorset.lucasbandeira23884.onlineshopping.ui.activities

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import ie.dorset.lucasbandeira23884.onlineshopping.R
import ie.dorset.lucasbandeira23884.onlineshopping.firestore.FireStoreClass
import ie.dorset.lucasbandeira23884.onlineshopping.models.User
import kotlinx.android.synthetic.main.activity_register.*


class RegisterActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)


        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }

        tv_login.setOnClickListener {
            val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
            startActivity(intent)
        }

//        btn_register.setOnClickListener {
//            validateRegisterDetails()
//        }

            btn_register.setOnClickListener {
                when {
                    TextUtils.isEmpty(et_first_name.text.toString().trim { it <= ' ' }) -> {
                        Toast.makeText(
                            this@RegisterActivity,
                            "Please enter first name.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                    TextUtils.isEmpty(et_last_name.text.toString().trim { it <= ' ' }) -> {
                        Toast.makeText(
                            this@RegisterActivity,
                            "Please enter last name.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    TextUtils.isEmpty(et_reg_email.text.toString().trim { it <= ' ' }) -> {
                        Toast.makeText(
                            this@RegisterActivity,
                            "Please enter email.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    TextUtils.isEmpty(et_reg_password.text.toString().trim { it <= ' ' }) -> {
                        Toast.makeText(
                            this@RegisterActivity,
                            "Please enter password.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    TextUtils.isEmpty(et_confirm_password.text.toString().trim { it <= ' ' }) -> {
                        Toast.makeText(
                            this@RegisterActivity,
                            "Please confirm password.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    else -> {

                        val email: String = et_reg_email.text.toString().trim { it <= ' ' }
                        val password: String = et_reg_password.text.toString().trim { it <= ' ' }

                        // Create an instance and create a register a user with email and password.
                        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(
                                OnCompleteListener<AuthResult> { task ->

                                    // If the registration is successfully done
                                    if (task.isSuccessful) {

                                        // Firebase registered user
                                        val firebaseUser: FirebaseUser = task.result!!.user!!

                                        val user = User(
                                            firebaseUser.uid,
                                            et_first_name.text.toString().trim{it <= ' '},
                                            et_last_name.text.toString().trim{it <= ' '},
                                            et_reg_email.text.toString().trim{it <= ' '}
                                        )

                                        FireStoreClass().registerUser(this@RegisterActivity, user)

//                                        FirebaseAuth.getInstance().signOut()
//                                        // Finish the Register Screen
//                                        finish()

                                        Toast.makeText(
                                            this@RegisterActivity,
                                            resources.getString(R.string.register_success),
                                            Toast.LENGTH_SHORT
                                        ).show()

                                        /**
                                         * Here the new user registered is automatically signed-in so we just sign-out the user from firebase
                                         * and send him to Main Screen with user id and email that user have used for registration.
                                         */


//                                        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
//                                        intent.putExtra("user_id", firebaseUser.uid)
//                                        intent.putExtra("email_id", email)

//                                        finish()
                                    } else {
                                        // If the registering is not successful then show error message.
                                        Toast.makeText(
                                            this@RegisterActivity,
                                            task.exception!!.message.toString(),
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                })
                    }
                }
            }
        setSupportActionBar(toolbar_register_activity)

        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_ios_new_24)
        }

        toolbar_register_activity.setNavigationOnClickListener { onBackPressed() }


    }

    fun userRegistrationSuccess() {

        val intent =
            Intent(this@RegisterActivity, DashboardActivity::class.java)
        startActivity(intent)

        Toast.makeText(
            this@RegisterActivity,
            resources.getString(R.string.register_success),
            Toast.LENGTH_SHORT
        ).show()

        FirebaseAuth.getInstance().signOut()
        // Finish the Register Screen
        finish()
    }
}





