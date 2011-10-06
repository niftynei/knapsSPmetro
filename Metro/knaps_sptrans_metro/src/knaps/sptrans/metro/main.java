package knaps.sptrans.metro;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class main extends Activity{
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        Thread logoTimer = new Thread() {
        	public void run(){
        		try{
        			int logoTimer = 0;
        			while (logoTimer < 5000){
        				sleep(100);
        				logoTimer = logoTimer +100;
        			}
        			startActivity(new Intent("knaps.sptrans.metro.CLEARSCREEN"));
        		} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        		finally{
        			finish();
        		}
        	}
        };
        logoTimer.start();
    }
}