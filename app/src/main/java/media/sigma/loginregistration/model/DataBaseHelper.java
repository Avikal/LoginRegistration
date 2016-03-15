package media.sigma.loginregistration.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class DataBaseHelper extends SQLiteOpenHelper {

	private static final int DATABASE_VERSION = 1;

	private static final String DATABASE_NAME = "login.db";

	private static final String TABLE_LOGIN = "login_table";

	private static final String KEY_ID = "id";
	private static final String KEY_NAME = "name";
	private static final String KEY_EMAIL = "email";
	private static final String KEY_PASS = "password";
	private static final String KEY_PHONE = "phone";
	private static final String KEY_PROFFESION = "profession";
	private static final String KEY_ADDRESS = "address";
	private static final String KEY_DESCRIPTION = "descrption";

	// private static final String KEY_NAME = "name";
	// private static final String KEY_PHONE = "phone";
	// private static final String KEY_EMAIL = "email";
	// private static final String KEY_PASS = "pass";
//	private static final String KEY_UID = "uid";
	// private static final String KEY_CREATED_AT = "created_at";
	// private static final String KEY_NEW_EMAIL = "newemail";
	String newEmail;
	Context context;

	public DataBaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
		this.context = context;
	}

	public DataBaseHelper open() throws SQLException {
		SQLiteDatabase db = this.getReadableDatabase();
		return this;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		String CREATE_LOGIN_TABLE = "CREATE TABLE " + TABLE_LOGIN + "("
				+ KEY_ID + " INTEGER PRIMARY KEY," 
				+ KEY_NAME + " TEXT,"
				+ KEY_EMAIL + " TEXT," 
				+ KEY_PASS + " TEXT," 
				+ KEY_PHONE	+ " TEXT,"
				+ KEY_PROFFESION + " TEXT," 
				+ KEY_ADDRESS + " TEXT,"
				+ KEY_DESCRIPTION + " TEXT" + ")";
		// String CREATE_LOGIN_TABLE = "CREATE TABLE " + TABLE_LOGIN + "("
		// + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
		// + KEY_EMAIL + " TEXT UNIQUE," + KEY_UID + " TEXT,"
		// + KEY_CREATED_AT + " TEXT" + ")";
//		 String CREATE_ACCOUNTS_TABLE = "CREATE TABLE "+TABLE_LOGIN+"(KEY_ID INTEGER PRIMARY KEY,KEY_NAME TEXT,KEY_EMAIL TEXT,KEY_PASS TEXT,KEY_PHONE TEXT,KEY_PROFFESION TEXT,KEY_ADDRESS TEXT,KEY_DESCRIPTION TEXT);";
		db.execSQL(CREATE_LOGIN_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE IF EXIST " + TABLE_LOGIN);
		onCreate(db);
	}

	/**
	 * Storing user details in database
	 * */
	public long addUser(String name, String email, String password,
			String phone, String profession, String address, String description) {
		// SQLiteDatabase db = this.getWritableDatabase();
		long d = 0;
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.query("login_table", null, " name=?",
				new String[] { email }, null, null, null);

		ContentValues values = new ContentValues();
		if (cursor.getCount() < 1) {
			values.put(KEY_NAME, name); // Name
			values.put(KEY_EMAIL, email); // Email
			values.put(KEY_PASS, password); // Password
			values.put(KEY_PHONE, phone);
			values.put(KEY_PROFFESION, profession);
			values.put(KEY_ADDRESS, address);
			values.put(KEY_DESCRIPTION, description);

			// Inserting Row
			d = db.insert(TABLE_LOGIN, null, values);
			db.close(); // Closing database connection
		}
		return d;
	}

	// public long addUser(Person person)
	// {
	// long d = 0;
	// SQLiteDatabase db = this.getWritableDatabase();
	// Cursor cursor=db.query("login_table", null, " name=?", new
	// String[]{person.getEmail()}, null, null, null);
	// ContentValues values = new ContentValues();
	// if(cursor.getCount()<1)
	// {
	// values.put(KEY_NAME, person.getUsername());
	// values.put(KEY_PHONE, person.getPhoneNo());
	// values.put(KEY_EMAIL, person.getEmail());
	// values.put(KEY_PASS, person.getPass());
	// String name = person.getUsername();
	// Toast.makeText(context, name, 10).show();
	//
	// d = db.insert(TABLE_LOGIN, null, values);
	// db.close();
	// }
	//
	// return d;
	// }
	public long updateUser(Person person, String newEmail) {
		long d = 0;
		SQLiteDatabase db = this.getWritableDatabase();
		this.newEmail = newEmail;
		// Cursor cursor=db.query("login_table", null, " name=?", new
		// String[]{person.getEmail()}, null, null, null);
		ContentValues values = new ContentValues();

		values.put(KEY_NAME, person.getUsername());
		// values.put(KEY_PHONE, person.getPhoneNo());
		values.put(KEY_EMAIL, this.newEmail);
		// values.put(KEY_PASS, person.getPass());
		String name = person.getUsername();
		Toast.makeText(context, name, 10).show();

		d = db.update(TABLE_LOGIN, values, KEY_EMAIL + " = ?",
				new String[] { String.valueOf(person.getEmail()) });
		db.close();

		return d;
	}

	public String singleEntry(String userName) {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.query("login_table", null, " name=?",
				new String[] { userName }, null, null, null);
		if (cursor.getCount() < 1) {
			cursor.close();
			return "NOT EXIST";
		}
		cursor.moveToFirst();
		String password = cursor.getString(cursor.getColumnIndex("password"));
		cursor.close();
		return password;
	}

	public String singleName(String userName) {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.query("login_table", null, " name=?",
				new String[] { userName }, null, null, null);
		if (cursor.getCount() < 1) {
			cursor.close();
			return "Not Exist";
		}
		cursor.moveToFirst();
		String name = cursor.getString(cursor.getColumnIndex("name"));
		cursor.close();
		return name;
	}

	public String singleMobile(String userName) {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.query("login_table", null, " name=?",
				new String[] { userName }, null, null, null);
		if (cursor.getCount() < 1) {
			cursor.close();
			return "Not Exist";
		}

		cursor.moveToFirst();
		String mobile = cursor.getString(cursor.getColumnIndex("phone"));
		cursor.close();
		return mobile;
	}

	public String singleEmail(String userName) {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.query("login_table", null, " name=?",
				new String[] { userName }, null, null, null);
		if (cursor.getCount() < 1) {
			cursor.close();
			return "Not Exist";
		}
		cursor.moveToFirst();
		String email = cursor.getString(cursor.getColumnIndex("email"));
		cursor.close();
		return email;
	}

	public int getRowCount() {
		String countQuery = "SELECT  * FROM " + TABLE_LOGIN;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(countQuery, null);
		int rowCount = cursor.getCount();
		db.close();
		cursor.close();

		// return row count
		return rowCount;
	}

	/**
	 * Re crate database Delete all tables and create them again
	 * */
	public void resetTables() {
		SQLiteDatabase db = this.getWritableDatabase();
		// Delete All Rows
		db.delete(TABLE_LOGIN, null, null);
		db.close();
	}

	/**
	 * Getting user data from database
	 * */
	public HashMap<String, String> getUserDetails() {
		HashMap<String, String> user = new HashMap<String, String>();
		String selectQuery = "SELECT  * FROM " + TABLE_LOGIN;

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);
		// Move to first row
		cursor.moveToFirst();
		if (cursor.getCount() > 0) {
			user.put("name", cursor.getString(1));
			user.put("email", cursor.getString(2));
			user.put("uid", cursor.getString(3));
			user.put("created_at", cursor.getString(4));
		}
		cursor.close();
		db.close();
		// return user
		return user;
	}

	public List<Person> getUsetDetail()
	{
		List<Person> user_data = new ArrayList<Person>();

		String selectQuery = "SELECT * FROM " + TABLE_LOGIN;

		SQLiteDatabase db = this.getWritableDatabase();

		Cursor cursor = db.rawQuery(selectQuery,null);

		if(cursor.moveToFirst())
		{
			do {
				Person user = new Person();

				user.setUsername(cursor.getString(1));
				user.setEmail(cursor.getString(2));
				user.setPass(cursor.getString(3));
				user.setPhoneNo(cursor.getString(4));
				user.setProffesion(cursor.getString(5));
				user.setAddress(cursor.getString(6));
				user.setDesscrpation(cursor.getString(7));

				user_data.add(user);
			}while (cursor.moveToNext());

		}
		return user_data;
	}

}
