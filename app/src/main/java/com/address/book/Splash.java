package com.address.book;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

public class Splash extends Activity {
	ImageView iv_logo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splashscreen);
         Thread t1 = new Thread(){
			public void run()
			{
				try
				{
					Thread.sleep(0001);
				}
				catch (InterruptedException e)
				{

				}
				finally {
					Intent obj = new Intent(Splash.this,MainActivity.class);
					startActivity(obj);
					finish();
				}
			}
		 };
		 t1.start();
	}
}
