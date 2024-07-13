package erseco.soft.friki.trivial.landon;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

public class GameoverLoader implements OnClickListener {
/*
	private static final int DIALOG_PROGRESS = 0;
	private static final int DIALOG_SUBMITTED = 1;
	private static final int DIALOG_FAILED = 2;
*/
	GameoverLoader(Activity at) {

		TextView txtScoreTotal = (TextView) at.findViewById(R.id.score_gameover_total);
		String sScore = MainActivity.instance.getResources().getString(R.string.result_score);
		String sPoints = MainActivity.instance.getResources().getString(R.string.result_points);
		txtScoreTotal.setText(sScore + ": " + Functions_Score.getScore() + " " + sPoints);
		
		
		TextView txtScorePercent = (TextView) at.findViewById(R.id.score_gameover_percent);

		String sPercent = MainActivity.instance.getResources().getString(R.string.result_percent);

		txtScorePercent.setText(sPercent + ": " + Functions_Score.getPercent() + " %");

		final Button button = (Button) at.findViewById(R.id.button_playagain);
		button.setOnClickListener(this);
		
		//Comprobamos si se ha superado el máximo total de puntos
		if (Functions_Settings.get_score() < Functions_Score.getScore()) {
			
			//Incrementamos la puntuacion almacenada
			Functions_Settings.set_score(Functions_Score.getScore());
			
			//txtScoreTotal.setText(txtScoreTotal.getText() + " Maxima!");
			
			//Ponemos el botón de game over en verde
			button.setBackgroundResource(R.drawable.button_green);

		} else {
			
			//Ocultamos el layout que muestra lo de máximo y el botón de compartir puntuación
			LinearLayout l = (LinearLayout) at.findViewById(R.id.score_group);
			l.setVisibility(View.GONE);
			
			//Ponemos el botón de game over en rojo
			button.setBackgroundResource(R.drawable.button_red);
			
		}
		
		Functions_Score.reset();
				
		/* esto habra que activarlo en la proxima versión!!!!
		 * 
		long max_score = Functions_Settings.get_score();
		long current_score = Functions_Score.getScore();
		
		
		if (current_score > max_score) {
			Functions_Settings.set_score(current_score);
		}*/

	}

	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.button_playagain:

			// //////////////////////////
			Functions_Media.playClick();
			loadMainLayout();

			break;

		}
	}

	private void loadMainLayout() {

		// Restart the game
		Activity ta = MainActivity.instance;
		
		ta.findViewById(R.id.include_trivial).setVisibility(View.VISIBLE);
		ta.findViewById(R.id.include_select_category).setVisibility(View.GONE);
		ta.findViewById(R.id.include_select_difficulty).setVisibility(View.GONE);
		ta.findViewById(R.id.include_question).setVisibility(View.GONE);
		ta.findViewById(R.id.include_result).setVisibility(View.GONE);
		ta.findViewById(R.id.include_gameover).setVisibility(View.GONE);
		

	}


}
