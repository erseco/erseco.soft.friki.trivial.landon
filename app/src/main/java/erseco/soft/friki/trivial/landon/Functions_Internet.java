package erseco.soft.friki.trivial.landon;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

 class Functions_Internet {

	protected static boolean isOnline() {
	    ConnectivityManager cm = (ConnectivityManager) MainActivity.instance.getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo netInfo = cm.getActiveNetworkInfo();
	    if (netInfo != null && netInfo.isConnected()) {
	        return true;
	    }
	        
	    return false;
	}
	
}
