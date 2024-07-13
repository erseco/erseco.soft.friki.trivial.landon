package erseco.soft.friki.trivial.landon;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.LinkedList;

class Functions_Categories {

	protected static String GetCategoryName(int id) {

		String r = "";

		switch (id) {

		case 1:
			r = MainActivity.instance.getString(R.string.category_cine);
			break;

		case 2:
			r = MainActivity.instance.getString(R.string.category_series);
			break;

		case 3:
			r = MainActivity.instance.getString(R.string.category_culturilla);
			break;

		case 4:
			r = MainActivity.instance.getString(R.string.category_dibujos);
			break;

		}

		return r;
	}

	protected static int GetCategoryBackground(int id) {

		int res = 0;

		switch (id) {
		case 1:
			res = R.drawable.category_blue;
			break;
		case 2:
			res = R.drawable.category_green;
			break;
		case 3:
			res = R.drawable.category_yellow;
			break;
		case 4:
			res = R.drawable.category_pink;
			break;
		case 5:
			res = R.drawable.category_brown;
			break;
		case 6:
			res = R.drawable.category_yellow;
			break;
		}
		return res;
	}
	
	protected static ArrayAdapter<Category> GetCategoriesAdapter(Context context){
		
		//Creamos la lista
		LinkedList<Category> categories = new LinkedList<Category>();
		//La poblamos con los ejemplos
		categories.add(new Category(1, context.getString(R.string.category_cine )));
		categories.add(new Category(2, context.getString(R.string.category_series )));
		categories.add(new Category(3, context.getString(R.string.category_culturilla )));
		categories.add(new Category(4, context.getString(R.string.category_dibujos )));
		
		//Creamos el adaptador
		ArrayAdapter<Category> adapter = new ArrayAdapter<Category>(context, android.R.layout.simple_list_item_1, categories);
		//Añadimos el layout para el menú y se lo damos al spinner
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		return adapter;
		
	}
	
	protected static ArrayAdapter<Category> GetReasonsAdapter(Context context){
		
		//Creamos la lista
		LinkedList<Category> categories = new LinkedList<Category>();
		//La poblamos con los ejemplos
		categories.add(new Category(0, context.getString(R.string.reason_select_reason )));
		categories.add(new Category(1, context.getString(R.string.reason_wrong_answer )));
		categories.add(new Category(2, context.getString(R.string.reason_wrong_category )));
		categories.add(new Category(3, context.getString(R.string.reason_wrong_question )));
		categories.add(new Category(4, context.getString(R.string.reason_wrong_difficulty )));
				
		
		//Creamos el adaptador
		ArrayAdapter<Category> adapter = new ArrayAdapter<Category>(context, android.R.layout.simple_list_item_1, categories);
		//Añadimos el layout para el menú y se lo damos al spinner
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		return adapter;
		
	}	
	
	protected static ArrayAdapter<Category> GetDifficultyAdapter(Context context){
		
		//Creamos la lista
		LinkedList<Category> categories = new LinkedList<Category>();
		//La poblamos con los ejemplos
		categories.add(new Category(1, context.getString(R.string.difficulty_easy )));
		categories.add(new Category(2, context.getString(R.string.difficulty_medium )));
		categories.add(new Category(3, context.getString(R.string.difficulty_hard )));

		
		//Creamos el adaptador
		ArrayAdapter<Category> adapter = new ArrayAdapter<Category>(context, android.R.layout.simple_list_item_1, categories);
		//Añadimos el layout para el menú y se lo damos al spinner
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		return adapter;
		
	}	
	
	protected  static ArrayList<String> GetCategoriesArray(Context context) {
		
		ArrayList<String> items = new ArrayList<String>();

		items.add(context.getString(R.string.category_cine));
		items.add(context.getString(R.string.category_series));
		items.add(context.getString(R.string.category_culturilla));
		items.add(context.getString(R.string.category_dibujos));
			
		return items;
		
	}
	
	
}
