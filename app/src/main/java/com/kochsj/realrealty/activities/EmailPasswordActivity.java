package com.kochsj.realrealty.activities;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.kochsj.realrealty.MainApplication;
import com.kochsj.realrealty.R;
import com.kochsj.realrealty.databinding.ActivityEmailpasswordBinding;
import com.kochsj.realrealty.models.Agent;
import com.kochsj.realrealty.models.House;
import com.kochsj.realrealty.models.User;


public class EmailPasswordActivity extends BaseActivity implements View.OnClickListener {

    private static final String TAG = "EmailPassword";
    final FirebaseFirestore db = FirebaseFirestore.getInstance();

    private ActivityEmailpasswordBinding mBinding;

    // [START declare_auth]
    private FirebaseAuth mAuth;
    // [END declare_auth]

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityEmailpasswordBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
        setProgressBar(mBinding.progressBar);

        // Buttons
        mBinding.emailSignInButton.setOnClickListener(this);
        mBinding.emailCreateAccountButton.setOnClickListener(this);
        mBinding.signOutButton.setOnClickListener(this);
        mBinding.verifyEmailButton.setOnClickListener(this);
        mBinding.reloadButton.setOnClickListener(this);

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
                createAccount(mBinding.fieldEmail.getText().toString(), mBinding.fieldPassword.getText().toString());
                break;
            case R.id.signOutButton:
                signOut();
                break;
            case R.id.verifyEmailButton:
                verifyEmail();
                break;
            case R.id.reloadButton:
                reload();
                break;

        }
    }

    private void signIn(String email, String password) {
        Log.d(TAG, "signIn:" + email);
        if (!validateForm()) {
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
                            Toast.makeText(EmailPasswordActivity.this, "Authentication failed.",
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

    private boolean validateForm() {
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

    private void signOut() {
        mAuth.signOut();
        updateUI(null);
    }
    private void createAccount(String email, String password) {
        Log.d(TAG, "createAccount:" + email);
        if (!validateForm()) {
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
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(EmailPasswordActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                        hideProgressBar();
                    }
                });
    }

    private void reload() {
    }

    private void verifyEmail() {
    }


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    private void updateUI(FirebaseUser user) {
        hideProgressBar();
        if (user != null) {
            String userID = user.getUid();
            mBinding.status.setText(getString(R.string.emailpassword_status_fmt,
                    user.getEmail(), user.isEmailVerified()));
            mBinding.detail.setText(getString(R.string.firebase_status_fmt, userID));
            MainApplication.setUID(userID);

            mBinding.emailPasswordButtons.setVisibility(View.GONE);
            mBinding.emailPasswordFields.setVisibility(View.GONE);
            mBinding.signedInButtons.setVisibility(View.VISIBLE);

            if (user.isEmailVerified()) {
                mBinding.verifyEmailButton.setVisibility(View.GONE);
            } else {
                mBinding.verifyEmailButton.setVisibility(View.VISIBLE);
            }
            Log.d(TAG, MainApplication.getUID());
            Agent a = new Agent(
                    "Ben",
                    "Testcase",
                    "42512345678",
                    "test@test.test",
                    "Real Realty"
            );
            Log.d(TAG, "new agent: ");
            House h = new House(
                    "12345677",
                    "123 Main St",
                    "Redmond",
                    "WA",
                    "98052",
                    "",
                    "2",
                    "2",
                    123
            );
            Log.d(TAG, "new house: ");
            User u = new User(
                    userID,
                    "Stephen",
                    "Koch",
                    "4258675309",
                    "lou@lou.lou",
                    h,
                    a,
                    "myprofilepictureurl.com"
            );
            Log.d(TAG, "new user: ");


            db.collection("users")
                    .add(u.constructUserHashMap())
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.w(TAG, "Error adding document", e);
                        }
                    });

            Log.d(TAG, "woooooo yeah!");
        } else {
            mBinding.status.setText(R.string.signed_out);
            mBinding.detail.setText(null);

            mBinding.emailPasswordButtons.setVisibility(View.VISIBLE);
            mBinding.emailPasswordFields.setVisibility(View.VISIBLE);
            mBinding.signedInButtons.setVisibility(View.GONE);
        }
    }

}
