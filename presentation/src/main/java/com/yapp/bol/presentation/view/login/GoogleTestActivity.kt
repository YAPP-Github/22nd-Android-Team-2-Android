package com.yapp.bol.presentation.view.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.yapp.bol.presentation.BuildConfig
import com.yapp.bol.presentation.R

class GoogleTestActivity : AppCompatActivity() {

    private val googleLoginForResult: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            handleGoogleSignInResult(result)
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_google_test)

        startGoogleLogin()
    }

    private fun startGoogleLogin() {
        val googleLoginClientIntent: Intent = GoogleSignInOptions
            .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(BuildConfig.GOOGLE_LOGIN_API_KEY)
            .requestEmail()
            .build().let {
                GoogleSignIn.getClient(this@GoogleTestActivity, it).signInIntent
            }

        findViewById<SignInButton>(R.id.btn_login).setOnClickListener {
            googleLoginForResult.launch(googleLoginClientIntent)
        }
    }

    // 구글 로그인 작업 수행
    private fun handleGoogleSignInResult(result: ActivityResult) {
        if (result.resultCode == RESULT_OK) {
            try {
                val account = GoogleSignIn
                    .getSignedInAccountFromIntent(result.data)
                    .getResult(ApiException::class.java)
                val token = account.idToken
                requireNotNull(token)
                Log.d("gso check google", token)
            } catch (e: Exception) {
                // TODO : 예외 처리 UI 작업
                // ApiException & IllegalArgumentException 두 예외가 발생할 수 있는데
                // 구분하지 않고 하나의 UI로 처리하면 될 것 같습니다.
            }
        } else {
            // TODO : 그 외 예외 처리 UI 작업
        }
    }
}
