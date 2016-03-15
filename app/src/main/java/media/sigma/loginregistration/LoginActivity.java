package media.sigma.loginregistration;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import media.sigma.loginregistration.model.DataBaseHelper;
import media.sigma.loginregistration.model.SessionManager;

/**
 * Created by avikal on 3/14/2016.
 */
public class LoginActivity extends AppCompatActivity
{
    Toolbar toolbar;
    Button login;
    EditText user_login, user_pass;
    String login_user, pass_user;
    DataBaseHelper dataBase;
    private SessionManager session;
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_xml);
       /* session = new SessionManager(getApplicationContext());
        if (session.isLoggedIn()) {
            Intent intent = new Intent(LoginActivity.this, ShowActivity.class);
            startActivity(intent);
            finish();
        }*/
        dataBase = new DataBaseHelper(this);
        dataBase = dataBase.open();

        user_login = (EditText) findViewById(R.id.editTextLoginname);
        user_pass = (EditText) findViewById(R.id.editTextLoginPass);
        login = (Button) findViewById(R.id.user_login);


        login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                login_user = user_login.getText().toString();
                pass_user = user_pass.getText().toString();
                String storePassword = dataBase.singleEntry(login_user);
                if (pass_user.equals(storePassword)) {
//                    session.setLogin(true);
                    Intent intent = new Intent(LoginActivity.this,
                            ShowActivity.class);
                    intent.putExtra("login_user", login_user);
                    startActivity(intent);
                    Toast.makeText(LoginActivity.this,
                            "Congrats: Login Successfull", Toast.LENGTH_LONG)
                            .show();
                    finish();
                } else {

                    Toast.makeText(LoginActivity.this,
                            "User Name or Password does not match",
                            Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(LoginActivity.this,
                            LoginActivity.class);

                    startActivity(intent);
                    finish();
                }
            }
        });
    }

}
