package media.sigma.loginregistration;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import media.sigma.loginregistration.model.CustomerListAdapter;
import media.sigma.loginregistration.model.DataBaseHelper;
import media.sigma.loginregistration.model.Person;
import media.sigma.loginregistration.model.SessionManager;

/**
 * Created by avikal on 3/14/2016.
 */
public class ShowActivity extends AppCompatActivity
{
    ListView user_list;
    SessionManager session;
    DataBaseHelper dataBase;
    Button btn_logout;
    CustomerListAdapter adapter;
    ArrayList<Person> cust_list = new ArrayList<Person>();
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_xml);

        user_list = (ListView) findViewById(R.id.list_view);
        btn_logout = (Button) findViewById(R.id.logout_btn);
        dataBase = new DataBaseHelper(getApplicationContext());



        List<Person> customer_data = dataBase.getUsetDetail();

        for(Person cust : customer_data)
        {
            cust_list.add(cust);
            adapter = new CustomerListAdapter(ShowActivity.this, cust_list);
            user_list.setAdapter(adapter);

        }

        user_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Person picture = cust_list.get(position);
              
            }
        });
/*        session = new SessionManager(getApplicationContext());
        if (!session.isLoggedIn()) {
            logoutUser();
        }

        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logoutUser();
            }
        });*/
    }

    public void logoutUser() {
        session.setLogin(false);

        dataBase.resetTables();

        Intent intent = new Intent(ShowActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();

    }
}
