package erseco.soft.friki.trivial.landon;

import android.content.Context;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

class Functions_DataBase {

	protected List<Question> questions_category_1;
	protected List<Question> questions_category_2;
	protected List<Question> questions_category_3;
	protected List<Question> questions_category_4;
//	protected List<Question> questions_category_5;
//	protected List<Question> questions_category_6;

	protected IntHolder position_category_1;
	protected IntHolder position_category_2;
	protected IntHolder position_category_3;
	protected IntHolder position_category_4;
//	protected IntHolder position_category_5;
//	protected IntHolder position_category_6;
	
	protected void initQuestionList() {
		
		int difficulty = Functions_Settings.get_difficulty();
		
		position_category_1 = new IntHolder(0) ;
		position_category_2 = new IntHolder(0) ;
		position_category_3 = new IntHolder(0) ;
		position_category_4 = new IntHolder(0) ;
//		position_category_5 = new IntHolder(0) ;
//		position_category_6 = new IntHolder(0) ;
		
		questions_category_1 = new ArrayList<Question>();
		questions_category_2 = new ArrayList<Question>();
		questions_category_3 = new ArrayList<Question>();
		questions_category_4 = new ArrayList<Question>();
//		questions_category_5 = new ArrayList<Question>();
//		questions_category_6 = new ArrayList<Question>();		
		
		switch (difficulty) {
		
		case 1: //Easy
			
			easy_list_1();
			easy_list_2();
			easy_list_3();
			easy_list_4();
			easy_list_5();
			
			break;
			
		case 2: //Medium
			
			medium_list_1();
			medium_list_2();
			medium_list_3();
			medium_list_4();
			medium_list_5();
			
			break;			
			
		case 3: //Hard
			
			hard_list_1();
			hard_list_2();
			hard_list_3();
			hard_list_4();
			hard_list_5();
			
			break;
		
		}
		
		//Desordenamos los arrays de preguntas
		Collections.shuffle(questions_category_1);
		Collections.shuffle(questions_category_2);
		Collections.shuffle(questions_category_3);
		Collections.shuffle(questions_category_4);
//		Collections.shuffle(questions_category_5);
//		Collections.shuffle(questions_category_6);
		
		
	}

	protected void easy_list_1() {
	}

	protected void easy_list_2() {
	}

	protected void easy_list_3() {
	}

	protected void easy_list_4() {
	}

	protected void easy_list_5() {
	}
	
	
	
	protected void medium_list_1() {
	}

	protected void medium_list_2() {
	}

	protected void medium_list_3() {
	}

	protected void medium_list_4() {
	}

	protected void medium_list_5() {
	}

	
	
	protected void hard_list_1() {
	}

	protected void hard_list_2() {
	}

	protected void hard_list_3() {
	}

	protected void hard_list_4() {
	}

	protected void hard_list_5() {
	}
	
	protected Question getRandomQuestion(Context context) {
		
		Question q = null;
		
		List<Question> questions;

		questions = get_randon_category_database();
		
		if  (!questions.isEmpty()) {
			
			IntHolder p = temp_position;
			
	         q= questions.get(p.position++);
			
	        if (p.position == questions.size()) {
	        	
				// Ya hemos llegado al final de las preguntas, a borrar esta
				// categorías
				p.position = 0;
				Functions_Settings.delete_category(q.getCategoryId());
				
	         }
		
		}
			
		// Devolvemos la pregunta
		return q;

	}
	
	private IntHolder temp_position = null;
	
	private List<Question> get_randon_category_database() {
		
		List<Question> questions;
				
		final List<Integer> categories =  Functions_Settings.get_categories();
		
		Random rand = new Random();
		int random = rand.nextInt(categories.size());
		
		int category = categories.get(random);
		
		//android.util.Log.d("Random Category", String.valueOf(random) + " " + String.valueOf(category));
		
		switch (category) {
		
		case 1:
			questions = questions_category_1;
			temp_position = position_category_1;
			break;
		case 2:
			questions = questions_category_2;
			temp_position = position_category_2;
			break;
		case 3:
			questions = questions_category_3;
			temp_position = position_category_3;
			break;
		case 4:
			questions = questions_category_4;
			temp_position = position_category_4;
			break;
//		case 5:
//			questions = questions_category_5;
//			temp_position = position_category_5;
//			break;
//		case 6:
//			questions = questions_category_6;
//			temp_position = position_category_6;
//			break;		

		default: 
			//Nunca debería pasar por aquí
			questions = null;
			temp_position = null;
		}	
		
		//Esto es para eliminar la categoría si no le quedan preguntas
		if (questions.isEmpty()) {
			
			//Eliminamos la categoría
			Functions_Settings.delete_category(category);
			
			//Llamamos recursivamente a la funcion
			return get_randon_category_database();
			
		}
				
		return questions;
	}

}
