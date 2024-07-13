package erseco.soft.friki.trivial.landon;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

class ResultLoader implements OnClickListener {
	
	private Question question;

	ResultLoader(Activity at, Boolean correct, Question q) {

		question = q;
		
		if (Functions_Score.getLife() == 0 && !correct) {
			loadGameoverLayout();
		} else {

			TextView txtResult = (TextView) at.findViewById(R.id.text_result);
			TextView txtScoreTotal = (TextView) at.findViewById(R.id.score_total);
			TextView txtScorePercent = (TextView) at.findViewById(R.id.score_percent);
			
			RatingBar ratingBar = (RatingBar)at.findViewById(R.id.score_lifes);

			if (correct) {

				String sCorrect = MainActivity.instance.getResources().getString(R.string.result_correct);
				
				String increment = "";
				switch (Functions_Settings.get_difficulty()) {
				
				case 1:
					increment = "5";
					break;
				case 2:
					increment = "10";
					break;
				case 3:
					increment = "15";
					break;			
				
				}
				
				sCorrect += " +" + increment;

				txtResult.setText(sCorrect);

				txtResult.setTextColor(Color.parseColor("#31B404")); // GREEN

				//Incrementamos la puntuacion
				Functions_Score.incrementScore();
				
				//Incrementamos el contador de preguntas correctas
				Functions_Score.incrementAnsweredCorrect(1);
				
				//Incrementamos la vida (desactivado porque si no el juego es demasiado sencillo)
				//Functions_Score.incrementLife();

			} else {

				String sWrong = MainActivity.instance.getResources().getString(R.string.result_wrong);

				txtResult.setText(sWrong);
				txtResult.setTextColor(Color.RED);

				Functions_Score.decrementLife();

			}

			String sScore = MainActivity.instance.getResources().getString(R.string.result_score);
			String sPoints = MainActivity.instance.getResources().getString(R.string.result_points);
			String sPercent = MainActivity.instance.getResources().getString(R.string.result_percent);

			txtScoreTotal.setText(sScore + ": " + Functions_Score.getScore() + " " + sPoints);
			txtScorePercent.setText(sPercent + ": " + Functions_Score.getPercent() + " %");
			
			ratingBar.setRating(Functions_Score.getLife());

			final Button buttonContinue = (Button) at.findViewById(R.id.button_continue);
			buttonContinue.setOnClickListener(this);
			
			}

	}

	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.button_continue:

			Functions_Media.playClick();
			
			//Si ya no hay mas categorías disponibles es por que no hay mas preguntas
			if (Functions_Settings.get_categories().size() == 0){
				
				//The question list is empty WIN
				loadGameoverLayout();
				
			}else{
				//Load another question
				loadQuestionLayout();
			}
			
			break;
			
		case R.id.button_correct_question:
			
			if (Functions_Internet.isOnline()) {
				new Functions_MessageBox_Correct(MainActivity.instance,question);
				v.setEnabled(false);
				v.setBackgroundResource(R.drawable.button_gray);
			} else {
				Toast.makeText(MainActivity.instance, MainActivity.instance.getResources().getString(R.string.no_internet_connection), Toast.LENGTH_SHORT).show();	
			}
			break;

		}
				
	}

	private void loadQuestionLayout() {

		Activity ta = MainActivity.instance;

		ta.findViewById(R.id.include_trivial).setVisibility(View.GONE);
		ta.findViewById(R.id.include_question).setVisibility(View.VISIBLE);
		ta.findViewById(R.id.include_result).setVisibility(View.GONE);
		ta.findViewById(R.id.include_gameover).setVisibility(View.GONE);

		new QuestionLoader(ta);

	}

	private void loadGameoverLayout() {

		Activity ta = MainActivity.instance;

		ta.findViewById(R.id.include_trivial).setVisibility(View.GONE);
		ta.findViewById(R.id.include_question).setVisibility(View.GONE);
		ta.findViewById(R.id.include_result).setVisibility(View.GONE);
		ta.findViewById(R.id.include_gameover).setVisibility(View.VISIBLE);

		new GameoverLoader(ta);

	}

}
