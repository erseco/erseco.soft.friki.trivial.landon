package erseco.soft.friki.trivial.landon;

import android.os.AsyncTask;
import android.text.Html;
import android.text.Spanned;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Locale;

public class HighScore {

	private static final String WRITE_URL = "http://hosting.ernesto.es/trivial_landon/score_write.php";
	private static final String READ_URL = "http://hosting.ernesto.es/trivial_landon/score_read.php";
	private static final String SUGGESTION_URL = "http://hosting.ernesto.es/trivial_landon/question_suggest.php";
	private static final String CORRECTION_URL = "http://hosting.ernesto.es/trivial_landon/question_correct.php";

	private static final String PASS = "C1qR*P24s";
	private static final Object SUCCESS = "success";

	protected static void ScoreSubmit(String name, long score) {

		//Obtenemos el lenguaje seleccionado
		String language = Functions_Settings.get_language();
		if (language.equals("xx")){
			//Si es el por defecto obtenemos el del sistema
			language = Locale.getDefault().getLanguage().toLowerCase(Locale.US);
		}
		
		String version = MainActivity.instance.version;
		
		name = Encode(name);
		version = Encode(version);
		
		String address = WRITE_URL + "?name=" + name + "&score=" + score + "&language="
				+ language + "&version=" + version + "&pass=" + PASS;
		
		HTTPSendAsync async = new HTTPSendAsync();
		async.address = address;
		async.execute();
		
	}	

	protected static void QuestionSuggest(int category, int difficulty, String question, String answer1,
			String answer2, String answer3, String comments) {

		String language = Locale.getDefault().getLanguage().toLowerCase(Locale.US);
		
		question = Encode(question);
		answer1 = Encode(answer1);
		answer2 = Encode(answer2);
		answer3 = Encode(answer3);
		comments = Encode(comments);
		
		String version = MainActivity.instance.version;
		version = Encode(version);		
		
		String address = SUGGESTION_URL + "?category=" + category + "&difficulty=" + difficulty + "&language="
				+ language + "&question=" + question + "&answer1=" + answer1 + "&answer2=" + answer2 + "&answer3="
				+ answer3 + "&comments=" + comments +  "&version=" + version + "&pass=" + PASS;
		
		HTTPSendAsync async = new HTTPSendAsync();
		async.address = address;
		async.execute();
		
	}
	
	protected static void QuestionCorrect(int reason, String question, String answer1,
			String answer2, String answer3, String comments) {

		String language = Locale.getDefault().getLanguage().toLowerCase(Locale.US);
		
		question = Encode(question);
		answer1 = Encode(answer1);
		answer2 = Encode(answer2);
		answer3 = Encode(answer3);
		comments = Encode(comments);
		
		String version = MainActivity.instance.version;
		version = Encode(version);		
		
		String address = CORRECTION_URL + "?reason=" + reason + "&language="
				+ language + "&question=" + question + "&answer1=" + answer1 + "&answer2=" + answer2 + "&answer3="
				+ answer3 + "&comments=" + comments +  "&version=" + version + "&pass=" + PASS;
		
		HTTPSendAsync async = new HTTPSendAsync();
		async.address = address;
		async.execute();
		
	}
	
	private static String Encode(String s) {
		
		try {
			return URLEncoder.encode(s, "utf-8");
		} catch (UnsupportedEncodingException e) {
			return s;
		}
		
	}
	/*
	private void postData() {
	    // Create a new HttpClient and Post Header
	    HttpClient httpclient = new DefaultHttpClient();
	    HttpPost httppost = new HttpPost("http://www.yoursite.com/script.php");

	    try {
	        // Add your data
	        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
	        nameValuePairs.add(new BasicNameValuePair("id", "12345"));
	        nameValuePairs.add(new BasicNameValuePair("stringdata", "AndDev is Cool!"));
	        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

	        // Execute HTTP Post Request
	        HttpResponse response = httpclient.execute(httppost);
	        
	    } catch (ClientProtocolException e) {
	        // TODO Auto-generated catch block
	    } catch (IOException e) {
	        // TODO Auto-generated catch block
	    }
	} */

	static class HTTPSendAsync extends AsyncTask<Object, Object, Object> {

		protected String address;
		private boolean result = false;
		
		public boolean isResult() {
			return result;
		}

		public boolean setResult(boolean result) {
			this.result = result;
			return result;
		}

		@Override
		protected Boolean doInBackground(Object... params) {

			try {
				URL url = new URL(address);
				URLConnection conn = url.openConnection();
				BufferedReader r = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				String line = r.readLine();
				if (line.equals(SUCCESS)) {
					setResult(true);
				} else {
					setResult(false);
				}
				return false;
			} catch (Exception e) {
				setResult(false);
				e.printStackTrace();
				
			}
			

			return null;
		}
		
		@Override
		protected void onPostExecute(Object result) {
			
			String message;
			if (this.setResult(true)) {
				message = MainActivity.instance.getString(R.string.text_received);
			} else {
				message = MainActivity.instance.getString(R.string.text_send_error);
				
			}
		
			Toast.makeText(MainActivity.instance, message, Toast.LENGTH_SHORT).show();	
			
		}

	}

	protected static void PrintScores(TextView text) {
		
		String version = MainActivity.instance.version;
		version = Encode(version);		
		
		String address = READ_URL + "?version=" + version + "&pass=" + PASS;
		
		
		PrintScores_HTTPSendAsync async = new PrintScores_HTTPSendAsync();
		async.address = address;
		async.textView = text;
		async.execute();
		
	}
	
	static class PrintScores_HTTPSendAsync extends AsyncTask<Object, Object, Object> {

		protected TextView textView;
		
		protected String address;
		private boolean result = false;
		
		private String scores2;
		
		public boolean isResult() {
			return result;
		}

		public boolean setResult(boolean result) {
			this.result = result;
			return result;
		}

		@Override
		protected String doInBackground(Object... params) {

			try {
				URL url = new URL(address);
				URLConnection conn = url.openConnection();
				BufferedReader r = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				String score;
				String scores = "";
				while ((score = r.readLine()) != null) {
					
					if (score.startsWith("scoreLine:")){
					
						scores += score.replace("scoreLine:", "") + "\n";
					
					}
					
				}
				scores2 = scores;
				return scores;
			} catch (Exception e) {
				setResult(false);
				e.printStackTrace();
				
			}
			
			return null;
		}
		
		@Override
		protected void onPostExecute(Object result) {
			
			try{
				
				Spanned scoresHTML = Html.fromHtml(scores2);
				textView.setText(scoresHTML);
			
			} catch (Exception e) {
				textView.setText("server error...");
			}
			
		}

	}
	
}
