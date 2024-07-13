package erseco.soft.friki.trivial.landon;

import java.util.Random;
import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

class QuestionLoader implements OnClickListener {

	private Question q;
	private Button buttonCorrect;

	private Button button1;
	private Button button2;
	private Button button3;
	private Button buttonResult;
	
	private ProgressBar pg;

	QuestionLoader(Activity a) {
		
		button1 = (Button) a.findViewById(R.id.btn_answer1);
		button1.setOnClickListener(this);
		button2 = (Button) a.findViewById(R.id.btn_answer2);
		button2.setOnClickListener(this);
		button3 = (Button) a.findViewById(R.id.btn_answer3);
		button3.setOnClickListener(this);

		pg = (ProgressBar) a.findViewById(R.id.progressBar1);
		
		button1.setBackgroundResource(R.drawable.button_blue);
		button2.setBackgroundResource(R.drawable.button_blue);
		button3.setBackgroundResource(R.drawable.button_blue);
		
		button1.setEnabled(true);
		button2.setEnabled(true);
		button3.setEnabled(true);

		Context context = MainActivity.instance;

		q = MainActivity.instance.db.getRandomQuestion(context);

		TextView txtCategory = (TextView) a.findViewById(R.id.text_category);
		txtCategory.setText(q.getCategoryName());
		txtCategory.setBackgroundResource(Functions_Categories.GetCategoryBackground(q.getCategoryId()));

		TextView txtQuestion = (TextView) a.findViewById(R.id.text_question);
		txtQuestion.setText(Decrypt(q.getQuestion()));

		int num = getRandomNumber();

		setAnswer(button1, button2, button3, q, num);

		// Reads the question by the TTS
		Functions_Media.speak(txtQuestion.getText().toString());

		if (Functions_Settings.enabled_timer()) {
			pg.setVisibility(View.VISIBLE);
			setTimer();
		} else {
			pg.setVisibility(View.GONE);
		}

	}
	
	private String Decrypt(String s) {
		
		s = Functions_Base64.decodeString(s);
		//s = Functions_Encryption.decrypt(s);

		return s;
	}

	private void setTimer() {

		pg.setProgress(0);
		MainActivity.instance.timer = new CountDownTimerPausable(10000, 99) {
			int i = 99;

			@Override
			public void onTick(long millisUntilFinished) {

				i--;
				pg.setProgress(i);

			}

			@Override
			public void onFinish() {
				// Do what you want
				i--;
				pg.setProgress(i);

				onClick(null);

			}
		};
		MainActivity.instance.timer.start();
		
	}

	protected void setAnswer(Button b1, Button b2, Button b3, Question q, int num) {

		switch (num) {
		case 1:
			b1.setText(Decrypt(q.getAnswer1()));
			buttonCorrect = b1;
			b2.setText(Decrypt(q.getAnswer2()));
			b3.setText(Decrypt(q.getAnswer3()));

			break;
		case 2:
			b2.setText(Decrypt(q.getAnswer1()));
			buttonCorrect = b2;
			b1.setText(Decrypt(q.getAnswer2()));
			b3.setText(Decrypt(q.getAnswer3()));

			break;
		case 3:
			b3.setText(Decrypt(q.getAnswer1()));
			buttonCorrect = b3;
			b1.setText(Decrypt(q.getAnswer2()));
			b2.setText(Decrypt(q.getAnswer3()));

			break;

		}

	}

	protected int getRandomNumber() {

		Random rand = new Random();
		int random = rand.nextInt(3) + 1;

		return random;

	}

	public void onClick(View v) {

		// Paramos el timer
		if (MainActivity.instance.timer != null) {
			MainActivity.instance.timer.cancel();
		}
		
		Functions_Score.incrementAnsweredTotal(1);

		button1.setOnClickListener(null);
		button2.setOnClickListener(null);
		button3.setOnClickListener(null);

		button1.setEnabled(false);
		button2.setEnabled(false);
		button3.setEnabled(false);

		changeButtonColor(button1);
		changeButtonColor(button2);
		changeButtonColor(button3);

		Button button = null;

		if (v != null) {
			button = (Button) v.findViewById(v.getId());
			buttonResult = button;

		}

		if (button != null && button.getId() == buttonCorrect.getId()) {

			Functions_Media.playOk();

		} else {

			Functions_Media.playWrong();
		}

		mHandler.removeCallbacks(mGoResult);
		mHandler.postDelayed(mGoResult, 500);
	}

	private Handler mHandler = new Handler();
	private Runnable mGoResult = new Runnable() {
		public void run() {

			mHandler.removeCallbacks(mGoResult);

			if (buttonResult != null && buttonResult.getId() == buttonCorrect.getId()) {
				loadResultLayout(true);

			} else {
				loadResultLayout(false);
			}

		}
	};

	private void changeButtonColor(Button button) {

		if (button.getId() == this.buttonCorrect.getId()) {
			button.setBackgroundResource(R.drawable.button_green);
		} else {
			button.setBackgroundResource(R.drawable.button_red);
		}

	}

	private void loadResultLayout(Boolean correct) {

		Activity ta = MainActivity.instance;

		// Comprobamos que la vista est visible, al cambiar los settings, por
		// ejemplo, no lo est
		if (ta.findViewById(R.id.include_question).getVisibility() == View.VISIBLE) {

			ta.findViewById(R.id.include_trivial).setVisibility(View.GONE);
			ta.findViewById(R.id.include_question).setVisibility(View.GONE);
			ta.findViewById(R.id.include_result).setVisibility(View.VISIBLE);

			new ResultLoader(ta, correct, q);

		}

	}

}
