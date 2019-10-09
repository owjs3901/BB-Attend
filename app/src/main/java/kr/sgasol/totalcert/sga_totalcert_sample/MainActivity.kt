package fido.umbridge

import Subject
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import fido.umbridge.ble.startBLE
import java.util.*
import android.content.Intent
import android.graphics.Color
import android.widget.Toast
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.support.v7.app.AppCompatActivity
import kr.sgasol.totalcert.sgatotalcert.UAF_Registration_FingerM_Intent
import kr.sgasol.totalcert.sgatotalcert.UAF_Registration_Finger_Intent
import kr.sgasol.totalcert.sgatotalcert.util.Utility.check_fingerPrint


val subjectList = ArrayList<Subject>()
var fido=false;
val map = HashMap<Int, Subject>()
var nowView:View? = null

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        startBLE(this)

        for (sub in subjectList)
            for (ind in sub.index)
                map.put(ind, sub);
        val v = findViewById<ViewGroup>(R.id.table)
        for (i in 0..4) {
            val vv = (v.getChildAt(i) as ViewGroup)
            for (ic in 0..6) {
                val inc = layoutInflater.inflate(R.layout.cell, null)
                if (map.containsKey(i * 7 + ic)) {


                    val va=map.get(i * 7 + ic)

                    if(i * 7 + ic==28){
                        inc.setBackgroundColor(Color.RED)
                        nowView=inc
                    }
                    inc.findViewById<TextView>(R.id.pro).setText(va?.pro)
                    inc.findViewById<TextView>(R.id.sub).setText(va?.name)
                    inc.findViewById<TextView>(R.id.clas).setText(va?.`class`)
                }
                vv.addView(inc)
            }
        }
    }
    public fun register(v: View){
        var mIntent: Intent? = null


        val deviceType = check_fingerPrint(this)

        if (deviceType == 0) {
            Toast.makeText(this, "지문 인증을 지원하지 않는 스마트폰입니다.", Toast.LENGTH_SHORT).show()
        } else if (deviceType == 1) {

            mIntent = Intent(applicationContext, UAF_Registration_Finger_Intent::class.java)
        } else if (deviceType == 2) {
            mIntent = Intent(applicationContext, UAF_Registration_FingerM_Intent::class.java)
        }

        mIntent!!.putExtra(OPERATION, FIDO_TYPE_REG)
        mIntent.putExtra(APPID, "http://fido.sgablc.kr:9051/appid")
        mIntent.putExtra(AGENTURL, "http://fido.sgablc.kr:9051")
        mIntent.putExtra(USERID, "Umbridge_User")

//				Log.d(TAG, "appid : " + appid);
//				Log.d(TAG, "targetUrl : " + targetUrl);

        startActivityForResult(mIntent, REG_ACTIVITY_RES)
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        val intent = Intent(intent)

        if (resultCode == FIDO_RESULT_FAIL) {
            if (intent != null) {
                val failCD = data!!.getStringExtra(FIDO_RESULT_FAIL_CD)

                if (failCD == CANCLE_CD) {                        // 사용자의 취소인 경우
                    Toast.makeText(applicationContext, "FIDO 등록을 취소하였습니다.\n오류코드 : $failCD", Toast.LENGTH_SHORT).show()
                } else if (failCD == NETWORK_ERREOR_CD) {         // 네트워크 오류인 경우
                    Toast.makeText(applicationContext, "네트워크 오류가 발생하였습니다.\n오류코드 : $failCD", Toast.LENGTH_SHORT).show()
                } else if (failCD == AUTHFAIL_ERROR_CD) {         // 인증 오류인 경우
                    Toast.makeText(applicationContext, "FIDO 인증에 실패하였습니다.\n오류코드 : $failCD", Toast.LENGTH_SHORT).show()
                } else if (failCD == LOCAL_AUTHFAIL_ERROR_CD) {   // local 인증 실패인 경우(pin)
                    Toast.makeText(applicationContext, "Local 인증에 실패하였습니다.\n오류코드 : $failCD", Toast.LENGTH_SHORT).show()
                }
            }
        } else if (resultCode == FIDO_RESULT_SUCCESS) {
            if (requestCode == REG_ACTIVITY_RES) {
                Toast.makeText(applicationContext, "FIDO 등록에 성공하였습니다.", Toast.LENGTH_SHORT).show()
            } else if (requestCode == AUTH_ACTIVITY_RES) {
                fido=true;
                nowView?.setBackgroundColor(Color.GREEN)
                Toast.makeText(applicationContext, "FIDO 인증에 성공하였습니다. 출석 완료", Toast.LENGTH_SHORT).show()
            }
        } else if (resultCode == FIDO_RESULT_REGEDINFO) {
            val result_reged = data!!.getStringExtra(FIDO_RESULT_SUCCESS_CD)

            if (requestCode == REGEDINFO_ACTIVITY_RES) {
                Toast.makeText(applicationContext, result_reged, Toast.LENGTH_SHORT).show()
            }
        } else if (resultCode == FIDO_RESULT_DELETEINFO) {
            val result = data!!.getStringExtra(FIDO_RESULT_SUCCESS_CD)

            if (requestCode == DELETEINFO_ACTIVITY_RES) {
                if (result == SUCCESS_CD) {
                    Toast.makeText(applicationContext, "FIDO 등록 정보 삭제 성공", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(applicationContext, "FIDO 등록 정보 삭제 실패", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
