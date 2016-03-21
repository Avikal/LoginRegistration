package media.sigma.loginregistration;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayInputStream;

import media.sigma.loginregistration.model.DataBaseHelper;
import media.sigma.loginregistration.model.UpdateClass;

/**
 * Created by avikal on 3/17/2016.
 */
public class FullView extends AppCompatActivity
{
    String full_name,email,password,phone,proffesion,address,describe;
    Bundle bundle;
    Button update,delete;
    TextView id_txt,name_txt,email_txt,password_txt,phone_txt,proffesion_txt,address_txt,describe_txt;
    int user_id;
    DataBaseHelper dataBase;
    byte[] image;
    ImageView cust_image;
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.full_view);

        bundle = getIntent().getExtras();
        dataBase = new DataBaseHelper(getApplicationContext());
        user_id = bundle.getInt("user_id");
        full_name = bundle.getString("full_name");
        email = bundle.getString("email");
        password = bundle.getString("password");
        phone = bundle.getString("phone");
        proffesion = bundle.getString("profession");
        address = bundle.getString("address");
        describe = bundle.getString("describe");
        image = bundle.getByteArray("custImage");

        name_txt = (TextView) findViewById(R.id.name_1);
        email_txt = (TextView) findViewById(R.id.email_1);
        password_txt = (TextView) findViewById(R.id.pass1);
        phone_txt = (TextView) findViewById(R.id.phone1);
        proffesion_txt = (TextView) findViewById(R.id.profession1);
        address_txt = (TextView) findViewById(R.id.adress1);
        describe_txt = (TextView) findViewById(R.id.describe1);
        id_txt = (TextView) findViewById(R.id.id_1);
        update = (Button) findViewById(R.id.update_btn);
        delete = (Button) findViewById(R.id.delete_btn);
        cust_image = (ImageView) findViewById(R.id.customer_image);

        ByteArrayInputStream imageStream = new ByteArrayInputStream(image);
        Bitmap theImage = BitmapFactory.decodeStream(imageStream);

        cust_image.setImageBitmap(theImage);

        id_txt.setText(""+user_id);
        name_txt.setText(full_name);
        email_txt.setText(email);
        password_txt.setText(password);
        phone_txt.setText(phone);
        proffesion_txt.setText(proffesion);
        address_txt.setText(address);
        describe_txt.setText(describe);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent full = new Intent(FullView.this, UpdateClass.class);
                full.putExtra("user_id", user_id);
                full.putExtra("full_name", full_name);
                full.putExtra("email", email);
                full.putExtra("password", password);
                full.putExtra("phone", phone);
                full.putExtra("profession", proffesion);
                full.putExtra("address", address);
                full.putExtra("describe", describe);
                full.putExtra("custImage", image);
                startActivity(full);
                finish();

            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataBase.deleteCustRow(user_id);
                Intent delete = new Intent(FullView.this,ShowActivity.class);
                startActivity(delete);
                finish();
            }
        });

    }
}
