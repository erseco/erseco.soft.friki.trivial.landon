package erseco.soft.friki.trivial.landon;

import android.os.AsyncTask;

import java.util.Locale;

class Functions_Database_AsyncLoader extends AsyncTask<Object, Object, Object>{

	@Override
	protected Boolean doInBackground(Object... params) {
				        
		 String language = Functions_Settings.get_language(); 
	 		if (language.equals("xx")) {
	 			language = Locale.getDefault().getLanguage();
	 		}
	 		
	 		language = language.toLowerCase(Locale.US);
	         
	         if (language.equals("es")) { //Español
	        	 MainActivity.instance.db = new Functions_DataBase_ES();
	         } else if (language.equals("ca")) { //Catalan
	        	 MainActivity.instance.db = new Functions_DataBase_ES();  
	         } else if (language.equals("gl")) { //Gallego
	        	 MainActivity.instance.db = new Functions_DataBase_ES();  
	         } else if (language.equals("eu")) { //Euskera
	        	 MainActivity.instance.db = new Functions_DataBase_ES();  
	         } else { //Inglés
	        	 MainActivity.instance.db = new Functions_DataBase_EN();
	         }
         
         MainActivity.instance.db.initQuestionList();
		
		return true;
	}
	
    @Override
    protected void onPreExecute() {

    }
    
    @Override
    protected void onPostExecute(Object result) {

    	//Ocultamos la ventana de cargando...
    	MainActivity.instance.pDialog.dismiss();
    	
    	//Cargamos la ventana de seleccionar categorias, pues ya se han cargado las preguntas
    	SelectDifficultyLoader.loadCategoryLayout();
  
    }

}
