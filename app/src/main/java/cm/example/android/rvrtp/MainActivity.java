package cm.example.android.rvrtp;
        import android.app.NotificationChannel;
        import android.app.NotificationManager;
        import android.content.Context;
        import android.content.Intent;
        import android.graphics.Color;
        import android.support.annotation.NonNull;
        import android.support.design.widget.BottomNavigationView;
        import android.support.v4.app.Fragment;
        import android.support.v4.app.FragmentTransaction;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.MenuItem;
        import android.widget.ProgressBar;

        import com.google.firebase.messaging.FirebaseMessaging;


public class MainActivity extends AppCompatActivity {

    Fragment fragment;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
                = new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                displaySelectedScreen(item.getItemId());
                return true;
            }
        };


         BottomNavigationView navView = findViewById(R.id.nav_view);
         navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

         displaySelectedScreen(R.id.navigation_home);

        ///////////////////////////////////////////////////////////////////////////////////////////
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationManager mNotificationManager =
                    (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel mChannel = new NotificationChannel(Constants.CHANNEL_ID, Constants.CHANNEL_NAME, importance);
            mChannel.setDescription(Constants.CHANNEL_DESCRIPTION);
            mChannel.enableLights(true);
            mChannel.setLightColor(Color.GREEN);
            mChannel.enableVibration(true);
            mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
            mNotificationManager.createNotificationChannel(mChannel);
        }
        String topic = "newPlacement";
        FirebaseMessaging.getInstance().subscribeToTopic(topic);

//////////////////////////////////////////////////////////////////////////////////////////



    }
    public void mail(){
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("*/*");
        intent.putExtra(Intent.EXTRA_SUBJECT, "urgent");
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"tpo@rvrjc.ac.in"});
        intent.putExtra(Intent.EXTRA_TEXT, "Respected Sir \n");
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }

    }

   public void displaySelectedScreen(int itemId){

        //creating fragment object
        fragment = null;

        //initializing the fragment object which is selected
        switch (itemId) {
            case R.id.navigation_home:
                fragment=new company();
                break;
            case R.id.navigation_mail:
                mail();
                break;
            case R.id.navigation_notifications:
                fragment=new notifications();
                break;
        }

       if (fragment != null) {
           FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
           ft.replace(R.id.content_frame, fragment);
           ft.addToBackStack("tag");
           ft.commit();
       }


    }

}

