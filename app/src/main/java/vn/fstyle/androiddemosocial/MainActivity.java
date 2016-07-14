package vn.fstyle.androiddemosocial;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import vn.fstyle.androiddemosocial.facebook.FacebookActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickButton(View v) {
        switch (v.getId()) {
            case R.id.btnFacebook:
                startActivity(new Intent(this, FacebookActivity.class));
                break;
            case R.id.btnTwitter:
                break;
            case R.id.btnGooglePlus:
                break;
            case R.id.btnLine:
                break;
        }
    }
}
