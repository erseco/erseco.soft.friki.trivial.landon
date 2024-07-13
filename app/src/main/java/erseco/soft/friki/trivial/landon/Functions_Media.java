package erseco.soft.friki.trivial.landon;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Vibrator;
import android.speech.tts.TextToSpeech;

class Functions_Media {

	private static MediaPlayer mp_click;
	private static MediaPlayer mp_ok;
	private static MediaPlayer mp_wrong;
	private static TextToSpeech mTts;

	protected static void startFunctionsMedia(Context context) {

		try {

			mp_click = MediaPlayer.create(context, R.raw.bottle_open);
			mp_ok = MediaPlayer.create(context, R.raw.audio_ok);
			mp_wrong = MediaPlayer.create(context, R.raw.audio_wrong);

			if (Functions_Settings.enabled_tts()) {
				mTts = new TextToSpeech(MainActivity.instance, null);
			}
		} catch (Exception e) {

		}

	}

	protected static void playClick() {
		if (Functions_Settings.enabled_sound() && mp_click != null) {
			try {
				mp_click.start();
			} catch (Exception e) {

			}

		}
	}

	protected static void playOk() {
		if (Functions_Settings.enabled_sound() && mp_ok != null) {
			try {
				mp_ok.start();
			} catch (Exception e) {

			}

		}
	}

	protected static void playWrong() {

		// Hacemos que vibre
		vibrate();

		if (Functions_Settings.enabled_sound() && mp_wrong != null) {
			try {
				mp_wrong.start();
			} catch (Exception e) {

			}
		}
	}

	protected static void speak(String text) {
		if (Functions_Settings.enabled_sound() && Functions_Settings.enabled_tts() && mTts != null) {
			try {
				mTts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
			} catch (Exception e) {
				
			}
		}

	}

	protected static void vibrate() {
		if (Functions_Settings.enabled_vibrate()) {
			try {
				Vibrator v = (Vibrator) MainActivity.instance.getSystemService(Context.VIBRATOR_SERVICE);
				v.vibrate(200);
			} catch (Exception e) {

			}

		}
	}

}
