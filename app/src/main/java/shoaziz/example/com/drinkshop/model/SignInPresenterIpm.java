package shoaziz.example.com.drinkshop.model;

import android.text.TextUtils;

import shoaziz.example.com.drinkshop.presenter.SignInPresenter;
import shoaziz.example.com.drinkshop.view.SignInView;

public class SignInPresenterIpm implements SignInPresenter {
    SignInView signInView;

    public SignInPresenterIpm(SignInView signInView) {
        this.signInView = signInView;
    }


    @Override
    public void signIn(String studentId, String password) {
        if (TextUtils.isEmpty(studentId)||TextUtils.isEmpty(password)){
            signInView.showValidationError();
        }else {
            if (studentId.equals("aaa")&& password.equals("bbb")){
                signInView.signInSuccess();
            }else {
                signInView.signInError();
            }
        }
    }
}
