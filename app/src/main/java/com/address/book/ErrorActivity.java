package com.address.book;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class ErrorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.error);
        isOnline();
        if (!isOnline()){try {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Info");
            builder.setIcon(android.R.drawable.ic_dialog_alert);

            builder.setMessage("Internet not available, Cross check your internet connectivity and try again")
                    .setCancelable(false)
                    .setPositiveButton("Exit", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            Intent homeIntent = new Intent(Intent.ACTION_MAIN);
                            homeIntent.addCategory( Intent.CATEGORY_HOME );
                            homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(homeIntent);
                        }
                    })
                    .setNegativeButton("Retry", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            Intent intent = new Intent(ErrorActivity.this,MainActivity.class);
                            startActivity(intent);
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();
//            AlertDialog alertDialog = new AlertDialog.Builder(ErrorActivity.this).create();
//
//            alertDialog.setTitle("Info");
//            alertDialog.setMessage("Internet not available, Cross check your internet connectivity and try again");
//            alertDialog.setIcon(android.R.drawable.ic_dialog_alert);
//            alertDialog.setButton("Exit", new DialogInterface.OnClickListener() {
//                public void onClick(DialogInterface dialog, int which) {
//                    Intent homeIntent = new Intent(Intent.ACTION_MAIN);
//                    homeIntent.addCategory( Intent.CATEGORY_HOME );
//                    homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                    startActivity(homeIntent);
//
//                }
//            });
//
//            alertDialog.show();

        }catch (Exception e){
            e.printStackTrace();
        }
        }


    }
    @Override
    public void onBackPressed ()
    {
         super.onBackPressed();
        moveTaskToBack(true);
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);


}
    protected boolean isOnline(){
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if(netInfo != null && netInfo.isConnectedOrConnecting()){
            return true;
        }
        else{
            return false;
        }

    }

}
