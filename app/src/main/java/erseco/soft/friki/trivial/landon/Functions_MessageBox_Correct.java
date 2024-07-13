package erseco.soft.friki.trivial.landon;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

class Functions_MessageBox_Correct {

	private final AlertDialog alertDialog;
	
	Functions_MessageBox_Correct(Context context, final Question question) {
		// get prompts.xml view
		LayoutInflater li = LayoutInflater.from(context);
		View promptsView = li.inflate(R.layout.inputbox_questioncorrect, null);

		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

		alertDialogBuilder.setIcon(android.R.drawable.ic_menu_upload);
		alertDialogBuilder.setTitle(R.string.menu_correct);
		
		// set prompts.xml to alertdialog builder
		alertDialogBuilder.setView(promptsView);
		
		final TextView lblQuestion = (TextView) promptsView.findViewById(R.id.question_label);
		
		String quest =  Functions_Base64.decodeString(question.getQuestion()) + "<br />";
		String answer1 = "<b><font color=\"green\">" + Functions_Base64.decodeString(question.getAnswer1()) + "</font></b>" + "<br />";
		String answer2 = "<b><font color=\"red\">" + Functions_Base64.decodeString(question.getAnswer2()) + "</font></b>" + "<br />";
		String answer3 = "<b><font color=\"red\">" + Functions_Base64.decodeString(question.getAnswer3()) + "</font></b>";
		
		Spanned reqString = Html.fromHtml(quest + answer1 + answer2 + answer3);
		
		//lblQuestion.append(reqString);
		
		lblQuestion.setText(reqString);	

		final Spinner spinnerReason = (Spinner) promptsView.findViewById(R.id.spinner_reason);
		spinnerReason.setAdapter(Functions_Categories.GetReasonsAdapter(context));

		spinnerReason.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
				// TODO Apéndice de método generado automáticamente
				
				if (id == 0) {
					alertDialog.getButton(DialogInterface.BUTTON_POSITIVE).setEnabled(false);
					
				} else {
					alertDialog.getButton(DialogInterface.BUTTON_POSITIVE).setEnabled(true);
					
				}
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Apéndice de método generado automáticamente
				
			}


		});
		

		final EditText tComments = (EditText) promptsView.findViewById(R.id.suggest_comments);
		

		// set dialog message
		alertDialogBuilder.setCancelable(true);
		alertDialogBuilder.setPositiveButton(context.getString(android.R.string.ok),
				new DialogInterface.OnClickListener() {
			
					public void onClick(DialogInterface dialog, int id) {

						// Hay que sumarle uno porque pilla el índice
						int reason = (int) spinnerReason.getSelectedItemId() + 1;
							
	
						HighScore.QuestionCorrect(reason, Functions_Base64.decodeString(question.getQuestion()), Functions_Base64.decodeString(question.getAnswer1()), Functions_Base64.decodeString(question.getAnswer2()), Functions_Base64.decodeString(question.getAnswer3()), tComments.getText().toString());

					}
				});
		alertDialogBuilder.setNegativeButton(context.getString(android.R.string.cancel),
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						dialog.cancel();
					}
				});

		// create alert dialog
		alertDialog = alertDialogBuilder.create();
		alertDialog.show();
		
		alertDialog.getButton(DialogInterface.BUTTON_POSITIVE).setEnabled(false);
		

	}
	


}
