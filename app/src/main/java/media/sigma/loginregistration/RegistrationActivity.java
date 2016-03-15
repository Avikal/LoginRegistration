package media.sigma.loginregistration;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import media.sigma.loginregistration.model.DataBaseHelper;
import media.sigma.loginregistration.model.SessionManager;

/**
 * Created by avikal on 3/14/2016.
 */
public class RegistrationActivity extends AppCompatActivity
{
    EditText name, email, pass, phone, profession, address, description;

    String userName, userEmail, userPass, userPhone, userProfession, userAdd, userDescription;
    Button register;
    SessionManager session;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_xml);
//        session = new SessionManager(getApplicationContext());
        name = (EditText) findViewById(R.id.editTextRegistername);
        phone = (EditText) findViewById(R.id.editTextRegisterPhone);
        email = (EditText) findViewById(R.id.editTextRegisterEmail);
        pass = (EditText) findViewById(R.id.editTextRegisterPass);
        profession = (EditText) findViewById(R.id.editTextRegisterProfession);
        address = (EditText) findViewById(R.id.editTextRegisterAddress);
        description = (EditText) findViewById(R.id.editTextRegisterDescribe);

        register = (Button) findViewById(R.id.user_Submit);
//        nav_back = (ImageView) findViewById(R.id.nav_back_register);

        userName = name.getText().toString();
        userEmail = email.getText().toString();
        userPass = pass.getText().toString();
        userPhone = phone.getText().toString();
        userProfession = profession.getText().toString();
        userAdd = address.getText().toString();
        userDescription = description.getText().toString();
//		userPhone = phone.getText().toString();
//		userEmail = email.getText().toString();
//		userPass = pass.getText().toString();

        register.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                userName = name.getText().toString();
                userEmail = email.getText().toString();
                userPass = pass.getText().toString();
                userPhone = phone.getText().toString();
                userProfession = profession.getText().toString();
                userAdd = address.getText().toString();
                userDescription = description.getText().toString();
                DataBaseHelper db = new DataBaseHelper(getApplicationContext());

                long d = db.addUser(userName,userEmail,userPass,userPhone,userProfession,userAdd,userDescription);
                if(d==-1)
                {
                    Toast.makeText(getApplicationContext(), "Please Fill all information Correct", 5).show();
                }
                else
                {
//                    session.setLogin(true);
                    Intent intent = new Intent(RegistrationActivity.this,ShowActivity.class);
                    intent.putExtra("login_user", userName);
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(), "Data Inserted", 5).show();
                    finish();
                }

            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent back = new Intent(RegistrationActivity.this,LoginActivity.class);
        startActivity(back);
        finish();
    }




}
