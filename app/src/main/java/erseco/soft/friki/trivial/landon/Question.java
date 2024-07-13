package erseco.soft.friki.trivial.landon;

class Question {

	private int id;
	private int category;
	private int dificulty;
	private String question;
	private String answer1;
	private String answer2;
	private String answer3;

	Question(int id, int category, int dificulty, String question, String answer1, String answer2, String answer3) {

		this.id = id;
		this.category = category;
		this.dificulty = dificulty;

		this.question = question;
		this.answer1 = answer1;
		this.answer2 = answer2;
		this.answer3 = answer3;

	}
	
	protected int getId() {
		return this.id;
	}

	protected int getCategoryId() {
		return this.category;
	}

	protected String getCategoryName() {
		return Functions_Categories.GetCategoryName(this.category);
	}

	protected int getDifficulty() {
		return this.dificulty;
	}

	protected String getQuestion() {
		return this.question;
	}

	protected String getAnswer1() {
		return this.answer1;
	}

	protected String getAnswer2() {
		return this.answer2;
	}

	protected String getAnswer3() {
		return this.answer3;
	}

}