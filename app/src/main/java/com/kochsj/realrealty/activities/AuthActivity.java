package com.kochsj.realrealty.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.kochsj.realrealty.MainApplication;
import com.kochsj.realrealty.R;
import com.kochsj.realrealty.databinding.ActivityAuthBinding;
import com.kochsj.realrealty.models.User;
import com.kochsj.realrealty.services.UserDatabaseService;
import com.kochsj.realrealty.wrappers.AuthWrapper;


public class AuthActivity extends BaseActivity implements View.OnClickListener {

    private static final String TAG = "AuthActivity";

    private ActivityAuthBinding mBinding;

    // [START declare_auth]
    private FirebaseAuth mAuth;
    // [END declare_auth]

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityAuthBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
        setProgressBar(mBinding.progressBar);
        setProgressBar(mBinding.progressBar2);

        // Buttons
        mBinding.emailSignInButton.setOnClickListener(this);
        mBinding.emailCreateAccountButton.setOnClickListener(this);
        mBinding.createAccountCreateAccountButton.setOnClickListener(this);

        mBinding.createAccountLayout.setVisibility(View.GONE);


        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.emailSignInButton:
                signIn(mBinding.fieldEmail.getText().toString(), mBinding.fieldPassword.getText().toString());
                break;
            case R.id.emailCreateAccountButton:
                mBinding.emailpasswordLayout.setVisibility(View.GONE);
                mBinding.createAccountLayout.setVisibility(View.VISIBLE);
                break;

            case R.id.createAccountCreateAccountButton:
                createAccount(
                        mBinding.createAccountFieldEmail.getText().toString(),
                        mBinding.createAccountFieldPassword.getText().toString(),
                        mBinding.createAccountFieldFirstName.getText().toString(),
                        mBinding.createAccountFieldLastName.getText().toString(),
                        mBinding.createAccountFieldPhoneNumber.getText().toString()
                );


        }
    }

    private void signIn(String email, String password) {
        Log.d(TAG, "signIn:" + email);
        if (!validateSignInForm()) {
            return;
        }

        showProgressBar();

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(AuthActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }

                        if (!task.isSuccessful()) {
                            mBinding.status.setText(R.string.auth_failed);
                        }
                        hideProgressBar();
                    }
                });
    }

    private boolean validateSignInForm() {
        boolean isValid = true;

        String email = mBinding.fieldEmail.getText().toString();
        if (TextUtils.isEmpty(email)) {
            mBinding.fieldEmail.setError("Required.");
            isValid = false;
        } else {
            mBinding.fieldEmail.setError(null);
        }

        String password = mBinding.fieldPassword.getText().toString();
        if (TextUtils.isEmpty(password)) {
            mBinding.fieldPassword.setError("Required.");
            isValid = false;
        } else {
            mBinding.fieldPassword.setError(null);
        }

        return isValid;
    }

    private boolean validateRegisterForm() {
        boolean isValid = true;

        String email = mBinding.createAccountFieldEmail.getText().toString();
        if (TextUtils.isEmpty(email)) {
            mBinding.createAccountFieldEmail.setError("Required.");
            isValid = false;
        } else {
            mBinding.createAccountFieldEmail.setError(null);
        }

        String password = mBinding.createAccountFieldPassword.getText().toString();
        if (TextUtils.isEmpty(password)) {
            mBinding.createAccountFieldPassword.setError("Required.");
            isValid = false;
        } else {
            mBinding.createAccountFieldPassword.setError(null);
        }

        String firstName = mBinding.createAccountFieldFirstName.getText().toString();
        if (TextUtils.isEmpty(firstName)) {
            mBinding.createAccountFieldFirstName.setError("Required.");
        } else {
            mBinding.createAccountFieldFirstName.setError(null);
        }

        String lastName = mBinding.createAccountFieldLastName.getText().toString();
        if (TextUtils.isEmpty(lastName)) {
            mBinding.createAccountFieldLastName.setError("Required.");
        } else {
            mBinding.createAccountFieldLastName.setError(null);
        }

        String phoneNumber = mBinding.createAccountFieldPhoneNumber.getText().toString();
        if (TextUtils.isEmpty(phoneNumber)) {
            mBinding.createAccountFieldPhoneNumber.setError("Required.");
        } else {
            mBinding.createAccountFieldPhoneNumber.setError(null);
        }

        return isValid;
    }

    private void createAccount(String email, String password, String firstName, String lastName, String phoneNumber) {
        final String userEmail = email;
        final String userFirstName = firstName;
        final String userLastName = lastName;
        final String userPhoneNumber = phoneNumber;

        if (!validateRegisterForm()) {
            return;
        }

        showProgressBar();

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser firebaseUser = mAuth.getCurrentUser();

                            // make a user to store in db
                            String uid = firebaseUser.getUid();

                            final UserDatabaseService userDatabaseService = new UserDatabaseService(uid);

                            User user = new User(
                                    uid,
                                    userFirstName,
                                    userLastName,
                                    userPhoneNumber,
                                    userEmail,
                                    User.emptyHouse(),
                                    User.emptyAgent(),
                                    ""
                            );

                            userDatabaseService.addUserToDatabase(user);

                            updateUI(firebaseUser);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(AuthActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                        hideProgressBar();
                    }
                });
    }


//    @Override
//    public void onPointerCaptureChanged(boolean hasCapture) {
//
//    }

    private void updateUI(FirebaseUser user) {
        final Intent intent = new Intent(this, AuthWrapper.class);

        hideProgressBar();
        if (user != null) {
            String userID = user.getUid();
            MainApplication.setUID(userID);

//            startActivity(intent);

            final UserDatabaseService userDatabaseService = new UserDatabaseService(userID);

            userDatabaseService.getUserData().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if(task.isSuccessful()){
                        DocumentSnapshot snapshot = task.getResult();

                        User user = userDatabaseService.userFromSnapshot(snapshot);

                        MainApplication.setUserData(user);


                        // load the search/home page

                        startActivity(intent);


                    } else {
                        Log.d("UDS", "get failed with ", task.getException());
                    }
                }
            });

            mBinding.authLayout.setVisibility(View.GONE);


        } else {
            mBinding.status.setText(R.string.signed_out);
            mBinding.detail.setText(null);

            mBinding.emailPasswordButtons.setVisibility(View.VISIBLE);
            mBinding.emailPasswordFields.setVisibility(View.VISIBLE);

        }
    }

}
