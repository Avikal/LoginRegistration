package media.sigma.loginregistration.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;

public class SessionManager {
	
	private static String TAG = SessionManager.class.getSimpleName();
	
	SharedPreferences pref;
	
	Editor editior;
	Context context;
	
	int PRIVATE_MODE = 0;
	
	private static final String PREF_NAME = "LoginApp";
	
	private static final String KEY_IS_LOGGEDIN = "isLoggedIn";
	
	public SessionManager(Context context1)
	{
		this.context = context1;
		pref = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
		editior = pref.edit();
	}
	
	public void setLogin(boolean isLoggedIn)
	{
		editior.putBoolean(KEY_IS_LOGGEDIN, isLoggedIn);
		editior.commit();
		Log.d(TAG, "User login session modified");
	}
	
	public boolean isLoggedIn()
	{
		return pref.getBoolean(KEY_IS_LOGGEDIN, false);
	}

}
