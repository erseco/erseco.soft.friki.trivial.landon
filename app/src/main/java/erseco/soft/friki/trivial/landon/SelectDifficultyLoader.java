package erseco.soft.friki.trivial.landon;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

class SelectDifficultyLoader implements OnClickListener {

	SelectDifficultyLoader(Activity at) {

		final Button buttonEasy = (Button) at.findViewById(R.id.text_difficulty_easy);
		buttonEasy.setOnClickListener(this);

		final Button buttonMedium = (Button) at.findViewById(R.id.text_difficulty_medium);
		buttonMedium.setOnClickListener(this);

		final Button buttonHard = (Button) at.findViewById(R.id.text_difficulty_hard);
		buttonHard.setOnClickListener(this);

	}

	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.text_difficulty_easy:
			Functions_Settings.set_difficulty(1);
			break;
		case R.id.text_difficulty_medium:
			Functions_Settings.set_difficulty(2);
			break;
		case R.id.text_difficulty_hard:
			Functions_Settings.set_difficulty(3);
			break;

		}		
		
        //Init the questions
		MainActivity.instance.InitDataBase();

		Functions_Media.playClick();

	}

	//Esta funcion se llamará cuando se carguen las preguntas desde el hilo, por eso es estática
	protected static void loadCategoryLayout() {

		Activity ta = MainActivity.instance;

		ta.findViewById(R.id.include_trivial).setVisibility(View.GONE);
		ta.findViewById(R.id.include_select_difficulty).setVisibility(View.GONE);
		ta.findViewById(R.id.include_select_category).setVisibility(View.VISIBLE);
		ta.findViewById(R.id.include_question).setVisibility(View.GONE);
		ta.findViewById(R.id.include_result).setVisibility(View.GONE);
		ta.findViewById(R.id.include_gameover).setVisibility(View.GONE);

		new SelectCategoryLoader(ta);

	}

}
