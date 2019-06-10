package shoaziz.example.com.drinkshop.activities;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import shoaziz.example.com.drinkshop.R;

public class SignUpActivity extends AppCompatActivity {
    Button button;
    ImageButton  imageButton;
    EditText id,phone,name,pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
        id = findViewById(R.id.edtStudentId);
        phone = findViewById(R.id.edtPhoneSignUp);
        name = findViewById(R.id.edtnameSignUp);

        imageButton = findViewById(R.id.buttonSignUp);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent  intent = new Intent(SignUpActivity.this,HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });
        button = findViewById(R.id.signinbtn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpActivity.this,SignInActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
