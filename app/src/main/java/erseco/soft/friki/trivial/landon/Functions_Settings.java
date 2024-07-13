package erseco.soft.friki.trivial.landon;


import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

import java.util.ArrayList;
import java.util.List;

class Functions_Settings {

	protected static boolean enabled_sound() {

		Context context = MainActivity.instance;
		SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context);

		return settings.getBoolean("sound_enabled", true);

	}

	protected static boolean enabled_tts() {

		Context context = MainActivity.instance;
		SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context);

		return settings.getBoolean("tts_enabled", false);

	}

	protected static boolean enabled_vibrate() {

		Context context = MainActivity.instance;
		SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context);

		return settings.getBoolean("vibrate_enabled", true);

	}
	
	protected static boolean enabled_timer() {

		Context context = MainActivity.instance;
		SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context);

		return settings.getBoolean("timer_enabled", true);

	}

	protected static String get_language() {

		Context context = MainActivity.instance;
		SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context);

		return settings.getString("language", "xx");

	}

	protected static long get_score() {

		Context context = MainActivity.instance;
		SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context);

		return settings.getLong("score", 0);

	}

	protected static void set_score(long s) {

		Context context = MainActivity.instance;
		SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context);

		Editor editor = settings.edit();
		editor.putLong("score", Long.valueOf(s));
		editor.commit();

	}

	protected static String get_score_user() {

		Context context = MainActivity.instance;
		SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context);

		String defValue = "default";

		return settings.getString("score_user", defValue);

	}

	protected static void set_score_user(String s) {

		Context context = MainActivity.instance;
		SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context);

		Editor editor = settings.edit();
		editor.putString("score_user", String.valueOf(s));
		editor.commit();

	}

	private static int difficulty = 0;

	protected static int get_difficulty() {
		return difficulty;
	}

	protected static void set_difficulty(int value) {
		difficulty = value;
	}
	
	private static List<Integer> categories;

	protected static List<Integer> get_categories() {
		return categories;
	}
	
	protected static void delete_category(int idCategory) {
		
		for (int i = 0; i < categories.size(); i++) {
			int id = categories.get(i);

			if (id == idCategory) {
				categories.remove(i);
				break;
			}

		}
		
	}
	
	protected static void set_categories(boolean[] values) {
		
		ArrayList<Integer> arrayList = new ArrayList<Integer>();

		for (int i = 0; i < values.length; i++){
			
			if (values[i]){
				//Hay que sumarle uno, porque el indice es 0
				arrayList.add(i +1);	
			}
						
		}
		
		categories = arrayList;
	}

}
