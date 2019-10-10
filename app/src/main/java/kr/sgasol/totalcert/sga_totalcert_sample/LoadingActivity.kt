package fido.umbridge

import Subject
import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.firestore.FirebaseFirestore

class LoadingActivity : AppCompatActivity() {

    lateinit var id: String
    lateinit var pw: String;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(R.anim.fadein, R.anim.fadeout)

        id = intent.getStringExtra("id")
        pw = intent.getStringExtra("pw")

        setContentView(R.layout.activity_loading)
        if (!hasPermission())
            getPermission()
        else {
            logInCheck()
        }
    }

    private fun logInCheck() {
        FirebaseFirestore.getInstance().collection("users").document(id).get().addOnCompleteListener(OnCompleteListener {
            if (it.isSuccessful) {
                if (it.getResult()?.getString("pw") == pw) {
                    overridePendingTransition(0, R.anim.fadeout)
                    FirebaseFirestore.getInstance().collection("subject").get().addOnCompleteListener(OnCompleteListener {
                        if (it.isSuccessful) {
                            for (document in it.getResult()?.documents!!) {
                                val a=document.toObject(Subject::class.java)!!
                                subjectList.add(a)
                                uuidToName.put(document.id,a)
                                subToUuid.put(a,document.id)
                            }
                            setResult(Activity.RESULT_OK)
                        } else
                            Toast.makeText(this@LoadingActivity, "로그인 실패!", Toast.LENGTH_LONG).show()
                        finish()
                    })
                    return@OnCompleteListener
                } else
                    Toast.makeText(this@LoadingActivity, "로그인 실패!", Toast.LENGTH_LONG).show()
            } else
                Toast.makeText(this@LoadingActivity, "로그인 실패!", Toast.LENGTH_LONG).show()
            finish()
        })
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.fadein, R.anim.fadeout)
    }

    private fun hasPermission(): Boolean {
        return ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED
    }

    private fun getPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION), 100)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (hasPermission())
            logInCheck()

    }
}
