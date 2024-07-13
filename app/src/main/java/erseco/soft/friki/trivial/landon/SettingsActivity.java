package erseco.soft.friki.trivial.landon;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.view.View;

public class SettingsActivity extends PreferenceActivity {

	@SuppressWarnings("deprecation")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		addPreferencesFromResource(R.xml.activity_settings);

		Preference pref = findPreference("version");
		pref.setSummary(MainActivity.instance.version);

		// Add the event handler for detecting preferences changed
		SharedPreferences mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
		mSharedPreferences.registerOnSharedPreferenceChangeListener(mListener);

		//Activar para la sigiente version
		//Preference prefScore = findPreference("score");
		//getPreferenceScreen().removePreference(prefScore);

	}

	// Listener defined by anonymous inner class.
	public OnSharedPreferenceChangeListener mListener = new OnSharedPreferenceChangeListener() {

		public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {

			//Init the media objects again
			Functions_Media.startFunctionsMedia(MainActivity.instance);
			
			//If the language changes, load the Questions of the proper language
			if (key.equals("language")){
				

				//Cuando el idioma se cambie volvemos a la pantalla inicial
				Activity ta = MainActivity.instance;
				
				ta.findViewById(R.id.include_trivial).setVisibility(View.VISIBLE);
				ta.findViewById(R.id.include_select_category).setVisibility(View.GONE);
				ta.findViewById(R.id.include_select_difficulty).setVisibility(View.GONE);
				ta.findViewById(R.id.include_question).setVisibility(View.GONE);
				ta.findViewById(R.id.include_result).setVisibility(View.GONE);
				ta.findViewById(R.id.include_gameover).setVisibility(View.GONE);
				
			}
			
		}

	};
	
}