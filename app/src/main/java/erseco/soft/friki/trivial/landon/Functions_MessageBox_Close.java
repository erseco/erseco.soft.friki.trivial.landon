package erseco.soft.friki.trivial.landon;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

class Functions_MessageBox_Close {

	protected static void showMessageBoxClose(Context context) {

		DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				switch (which) {
				case DialogInterface.BUTTON_POSITIVE:
					// Yes button clicked
					System.exit(0);
					break;

				case DialogInterface.BUTTON_NEGATIVE:
					// No button clicked
					break;
														
				}
			}
		};

		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setIcon(R.drawable.ic_launcher);
		builder.setTitle(R.string.app_name);
		builder.setMessage(R.string.messagebox_exit_question);
		builder.setPositiveButton(android.R.string.yes, dialogClickListener);
		builder.setNegativeButton(android.R.string.no, dialogClickListener);
		
		//builder.se
		
		builder.show();

	}
	
}
