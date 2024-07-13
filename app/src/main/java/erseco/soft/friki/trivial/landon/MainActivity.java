package erseco.soft.friki.trivial.landon;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity implements OnClickListener {

	//Aquí almacenamos la versión de la aplicación, que luego se usará en la aplicación
	protected String version;
		
	CountDownTimerPausable timer;
	
	protected static MainActivity instance;
	protected Functions_DataBase db;

	protected ProgressDialog pDialog;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
		
		// Desactivado por que si no la tiran patras en Samsung
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON,WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
	
        //Set which volume should be controlled by the app
        this.setVolumeControlStream(android.media.AudioManager.STREAM_MUSIC);  
        
        //Set the instance to use in other parts
        instance = this;
        
        Functions_Score.reset();
        
        final Button button = (Button) findViewById(R.id.button_play);
        button.setOnClickListener(this);
        
        Functions_Media.startFunctionsMedia(this);

		try {
			version = getPackageManager().getPackageInfo(getPackageName(), 0).versionName;

		} catch (Exception e) {
			// TODO Bloque catch generado automticamente
			e.printStackTrace();
		}
		
        // initialize receiver
        final IntentFilter filter = new IntentFilter(Intent.ACTION_SCREEN_ON);
        filter.addAction(Intent.ACTION_SCREEN_OFF);
        final BroadcastReceiver mReceiver = new ScreenReceiver();
        registerReceiver(mReceiver, filter);


    }
    
    protected void InitDataBase(){
    	
         pDialog = new ProgressDialog(this);
         pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
         pDialog.setMessage(getString(R.string.loading));
         pDialog.setCancelable(true);
         pDialog.setMax(100);

         Functions_Database_AsyncLoader task = new Functions_Database_AsyncLoader();
         task.execute();
         
         //Mostramos el dialogo de progreso mientras se cargan las preguntas
         pDialog.setProgress(0);
         pDialog.show();

    }
    
	public void onClick(View v) {
			
		switch (v.getId()) {
		case R.id.button_play:
			
			Functions_Media.playClick();	
			
			findViewById(R.id.include_trivial).setVisibility(View.GONE);
			findViewById(R.id.include_select_difficulty).setVisibility(View.VISIBLE);
			
			new SelectDifficultyLoader(MainActivity.instance);
			
			break;
			
		}
	}
	
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	return AsignarEventoClick(this, item);
  
    }
    
    protected boolean AsignarEventoClick(Activity act, MenuItem item) {

		switch (item.getItemId()) {
		case android.R.id.home:
			//NavUtils.navigateUpFromSameTask(act);
			return true;
		case R.id.menu_settings:
			act.startActivity(new Intent(act, SettingsActivity.class));
			return true;
		case R.id.menu_exit:
			System.exit(0);
			return true;
		}
		return false;
	}
    
    @Override
    public void onBackPressed() {

		// Comprobamos que la vista est visible, al cambiar los settings, por
		// ejemplo, no lo est
		if (this.findViewById(R.id.include_trivial).getVisibility() == View.VISIBLE) {

			//close the app when back button clicked
			Functions_MessageBox_Close.showMessageBoxClose(this);

		} else {
    	
			// Restart the game (por peticion de samsung)			
			this.findViewById(R.id.include_trivial).setVisibility(View.VISIBLE);
			this.findViewById(R.id.include_select_category).setVisibility(View.GONE);
			this.findViewById(R.id.include_select_difficulty).setVisibility(View.GONE);
			this.findViewById(R.id.include_question).setVisibility(View.GONE);
			this.findViewById(R.id.include_result).setVisibility(View.GONE);
			this.findViewById(R.id.include_gameover).setVisibility(View.GONE);
			
		}
    }

    @Override
    protected void onPause() {
        // when the screen is about to turn off
        if (ScreenReceiver.wasScreenOn) {
            // this is the case when onPause() is called by the system due to a screen state change
        	if (timer != null && !timer.isPaused()) {
        		timer.pause();
        	}
        } else {
            // this is when onPause() is called when the screen state has not changed
        	if (timer != null && !timer.isPaused()) {
        		timer.pause();
        	}
        }
        super.onPause();
    }

    @Override
    protected void onResume() {
        // only when screen turns on
        if (!ScreenReceiver.wasScreenOn) {
            // this is when onResume() is called due to a screen state change
        	if (timer != null && timer.isPaused()) {
        		timer.start();
        	}
        } else {
            // this is when onResume() is called when the screen state has not changed
        	if (timer != null && timer.isPaused()) {
        		timer.start();
        	}
        }
        super.onResume();
    }
}
