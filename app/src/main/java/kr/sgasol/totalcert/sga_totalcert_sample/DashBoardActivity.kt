package fido.umbridge

import android.widget.Toast
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore

class DashBoardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //		setContentView(R.layout.activity_dashboard);
        //		FirebaseFirestore.getInstance().collection("users").document(FirebaseAuth.getInstance().getUid()).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
        //			@Override
        //			public void onSuccess(DocumentSnapshot documentSnapshot) {
        //				Map<String,Object> m=documentSnapshot.getData();
        //				System.out.println(m+"나니");
        //				Object o=m.get("rental");
        //				ViewPagerAdapter v=new ViewPagerAdapter(DashBoardActivity.this);
        //				((ViewPager)findViewById(R.id.viewpager)).setAdapter(v);
        //			}
        //		}).addOnFailureListener(new OnFailureListener() {
        //			@Override
        //			public void onFailure(@NonNull Exception e) {
        //				Toast.makeText(DashBoardActivity.this,"정보 로딩 실패",Toast.LENGTH_LONG).show();
        //			}
        //		});

    }
}
