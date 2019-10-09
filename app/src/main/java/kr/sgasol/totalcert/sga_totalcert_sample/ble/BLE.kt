package fido.umbridge.ble

import android.app.Activity
import android.content.Intent
import android.support.v4.app.ActivityCompat.startActivityForResult
import com.google.common.base.Charsets
import fido.umbridge.*
import kr.sgasol.totalcert.sgatotalcert.Select_Authenticator_Intent
import no.nordicsemi.android.support.v18.scanner.*
import java.io.UnsupportedEncodingException
import java.nio.charset.Charset
import java.util.*


var b = false
var load=false;

var url = ""

var nowURL: String? = null


public fun startBLE(a: Activity) {
    if (b) return
    b = true
    val scanner = BluetoothLeScannerCompat.getScanner()
    val settings = ScanSettings.Builder()
            .setLegacy(false)
            .setScanMode(ScanSettings.SCAN_MODE_LOW_LATENCY)
            .setReportDelay(1000)
            .setUseHardwareBatchingIfSupported(true)
            .build()
    val filters = ArrayList<ScanFilter>()
    filters.add(ScanFilter.Builder().setDeviceAddress("C8:F9:D8:D2:8D:F4").build())
    scanner.startScan(filters, settings, object : ScanCallback() {
        override fun onScanResult(callbackType: Int, result: ScanResult) {
            super.onScanResult(callbackType, result)
            try {
                val url1 = String(result.scanRecord?.bytes!!, Charset.forName("UTF-8")).split("a@".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[1]
                println("스캔~ $url1")

                if (url1 != url) {
                    url = url1
//                        val `in` = Intent(a, ArtInfoActivity::class.java)
//                        `in`.putExtra("r", url)
//                        a.startActivity(`in`)
                }
            } catch (e: UnsupportedEncodingException) {
                e.printStackTrace()
            }

        }

        override fun onBatchScanResults(results: List<ScanResult>) {
            super.onBatchScanResults(results)
            println("스캔 결과2" + results.size)
            for (r in results) {
                try {
                    val st = r.scanRecord?.bytes?.toString(Charsets.UTF_8)
                    if (nowURL == null) {
                        nowURL = st?.split("38")?.get(1)
                    } else {
                        val mIntent = Intent(a, Select_Authenticator_Intent::class.java)
                        mIntent.putExtra(OPERATION, FIDO_TYPE_AUTH)
                        mIntent.putExtra(APPID, "http://fido.sgablc.kr:9051/appid")
                        mIntent.putExtra(AGENTURL, "http://fido.sgablc.kr:9051")
                        mIntent.putExtra(USERID, "Umbridge_User")
                        if(!load){
                            load=true
                            a.startActivityForResult(mIntent, AUTH_ACTIVITY_RES)
                        }
                    }
                } catch (e: UnsupportedEncodingException) {
                    e.printStackTrace()
                }

            }
        }

        override fun onScanFailed(errorCode: Int) {
            super.onScanFailed(errorCode)
            println("스캔 실패$errorCode")
            b = false
        }
    })
}