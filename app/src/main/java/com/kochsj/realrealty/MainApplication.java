package com.kochsj.realrealty;

import android.app.Application;


public class MainApplication extends Application {
    public static String UID;

    public static void setUID(String UID) {
        MainApplication.UID = UID;
    }

    public static String getUID() {
        return UID;
    }

}
//     Called when the application is starting, before any other application objects have been created.
//     Overriding this method is totally optional!
//    @Override
//    public void onCreate() {
//        super.onCreate();
//        // Required initialization logic here!
//
//        Intent intent = new Intent(this, EmailPasswordActivity.class);
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        startActivity(intent);
//    }

    // Called by the system when the device configuration changes while your component is running.
    // Overriding this method is totally optional!
//    @Override
//    public void onConfigurationChanged(Configuration newConfig) {
//        super.onConfigurationChanged(newConfig);
//    }

    // This is called when the overall system is running low on memory,
    // and would like actively running processes to tighten their belts.
    // Overriding this method is totally optional!
//    @Override
//    public void onLowMemory() {
//        super.onLowMemory();
//    }


//public class MainActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener, View.OnClickListener {
//    SignInButton signInButton;
//    Button signOutButton;
//    Button signInWithEmail;
//    TextView statusTextView;
//    GoogleApiClient mGoogleApiClient;
//
//    private static final String TAG = "SignInActivity";
//    private static final int RC_SIGN_IN = 9001;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//                .requestEmail()
//                .build();
//
//        mGoogleApiClient = new GoogleApiClient.Builder(this)
//                .enableAutoManage(this, this)
//                .addApi(Auth.GOOGLE_SIGN_IN_API, googleSignInOptions)
//                .build();
//
//        statusTextView = (TextView) findViewById(R.id.status_textview);
//        signInButton = (SignInButton) findViewById(R.id.sign_in_button);
//        signInButton.setOnClickListener(this);
//
//        signOutButton = (Button) findViewById(R.id.sign_out_button);
//        signOutButton.setOnClickListener(this);
//
//        signInWithEmail = (Button) findViewById(R.id.sign_in_with_email);
//        signInWithEmail.setOnClickListener(this);
//
//    }
//
//    public void onClick(View view) {
//        switch(view.getId()) {
//            case R.id.sign_in_button:
//                signIn();
//                break;
//            case R.id.sign_out_button:
//                signOut();
//                break;
//            case R.id.sign_in_with_email:
//                Intent intent = new Intent(this, EmailPasswordActivity.class);
//                startActivity(intent);
//                break;
//        }
//    }
//
//    private void signIn() {
//        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
//        startActivityForResult(signInIntent, RC_SIGN_IN);
//    }
//
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if(requestCode == RC_SIGN_IN) {
//            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
//            handleSignInResult(result);
//        }
//    }
//
//    private void handleSignInResult(GoogleSignInResult result) {
//        Log.d(TAG, "Handle sign-in result: " + result.isSuccess());
//        if(result.isSuccess()) {
//            //sign in successful. show the authenticated UI
//            GoogleSignInAccount acct = result.getSignInAccount();
//            statusTextView.setText("Hello, " + acct.getDisplayName());
//        } else {
//            // sign in not successful. TODO: show error
//        }
//    }
//
//    @Override
//    public void onConnectionFailed(ConnectionResult connectionResult){
//        Log.d(TAG, "On connection failed: "+ connectionResult);
//    }
//
//    private void signOut(){
//        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(new ResultCallback<Status>() {
//            @Override
//            public void onResult(@NonNull Status status) {
//                statusTextView.setText("Signed out");
//            }
//        });
//    }
////
////    public void showMap(View view) {
////        ViewPager centerView = findViewById(R.id.center_view);
////        MapViewAdapter adapter = new MapViewAdapter(this);
////
////        centerView.setAdapter(adapter);
////
//////        Intent intent = new Intent(this, MapsActivity.class);
//////        startActivity(intent);
////    }
//
//}
