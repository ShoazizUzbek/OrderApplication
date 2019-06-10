package shoaziz.example.com.drinkshop.activities;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import shoaziz.example.com.drinkshop.R;
import shoaziz.example.com.drinkshop.model.SignInPresenterIpm;
import shoaziz.example.com.drinkshop.presenter.SignInPresenter;
import shoaziz.example.com.drinkshop.view.SignInView;

public class SignInActivity extends AppCompatActivity implements SignInView {
    Button button;
    ImageButton imageView;
    SignInPresenter signInPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        signInPresenter = new SignInPresenterIpm(SignInActivity.this);
        signInPresenter.signIn("aaa","bbb");

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
        imageView = findViewById(R.id.buttonSignIn);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent  intent = new Intent(SignInActivity.this,HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });
        button = findViewById(R.id.signupbtn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignInActivity.this,SignUpActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void showValidationError() {
        Toast.makeText(this, "fill the fields", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void signInSuccess() {
        Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void signInError() {
        Toast.makeText(this, "error", Toast.LENGTH_SHORT).show();
    }
}
