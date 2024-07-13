package erseco.soft.friki.trivial.landon;

public class Functions_Score {

	private static long score = 0;
	private static int answered_correct = 0;
	private static int answered_total = 0;
	private static int life = 5;

	protected static long getScore() {
		return score;
	}

	protected static int getPercent() {

		return (int) (((double) answered_correct / (double) answered_total) * 100);
	}

	protected static void incrementScore() {
		
		//Incrementamos el score dependiendo de la dificultad
 		
		long s = 0;
		switch (Functions_Settings.get_difficulty()) {
		
		case 1:
			s = 5;
			break;
		case 2:
			s = 10;
			break;
		case 3:
			s = 15;
			break;			
		
		}
		
		score += s;
	}

	protected static void incrementAnsweredTotal(int a) {
		answered_total += a;
	}

	protected static void incrementAnsweredCorrect(int a) {
		answered_correct += a;
	}

	protected static void incrementLife() {
		if (life < 5) {
			life += 1;
		}
	}

	protected static void decrementLife() {
		if (life > 0) {
			life -= 1;
		}
	}

	protected static int getLife() {
		return life;
	}
	
	protected static void resetLife() {
		life = 5;
	}
	
	protected static void reset(){
		score = 0;
		answered_correct = 0;
		answered_total = 0;
		life = 5;	
	}

}
