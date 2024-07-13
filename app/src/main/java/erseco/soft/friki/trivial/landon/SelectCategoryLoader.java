package erseco.soft.friki.trivial.landon;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import erseco.soft.friki.trivial.landon.MultiSpinner.MultiSpinnerListener;

class SelectCategoryLoader implements OnClickListener {

	SelectCategoryLoader(Activity at) {

		final Button buttonContinue = (Button) at.findViewById(R.id.category_button_continue);
		buttonContinue.setOnClickListener(this);
		
		//Forzamo a poner todas las categorías, por si luego no se selecciona ninguna
		boolean[] values = {true,true,true,true};
		Functions_Settings.set_categories(values);

		MultiSpinner multiSpinner = (MultiSpinner) at.findViewById(R.id.multi_spinner);

		multiSpinner.setItems(Functions_Categories.GetCategoriesArray(at), at.getString(R.string.category_all), new MultiSpinnerListener() {

			@Override
			public void onItemsSelected(boolean[] selected) {
				// TODO Apéndice de método generado automáticamente
				
				Functions_Settings.set_categories(selected);
				
				if (Functions_Settings.get_categories().size() == 0 ){
				
					//Forzamos a poner todas las categorías, porque algún graciosillo ha desmarcado todas las categorías
					boolean[] values = {true,true,true,true};
					Functions_Settings.set_categories(values);
					
				}
				
				
			}

		});

	}

	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.category_button_continue:
			Functions_Media.playClick();
			loadQuestionLayout();
			break;

		}		

	}

	private void loadQuestionLayout() {

		Activity ta = MainActivity.instance;

		ta.findViewById(R.id.include_trivial).setVisibility(View.GONE);
		ta.findViewById(R.id.include_select_category).setVisibility(View.GONE);
		ta.findViewById(R.id.include_select_difficulty).setVisibility(View.GONE);
		ta.findViewById(R.id.include_question).setVisibility(View.VISIBLE);
		ta.findViewById(R.id.include_result).setVisibility(View.GONE);
		ta.findViewById(R.id.include_gameover).setVisibility(View.GONE);

		new QuestionLoader(ta);
		
	}

}