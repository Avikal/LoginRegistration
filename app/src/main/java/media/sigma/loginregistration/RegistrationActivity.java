package media.sigma.loginregistration;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

import media.sigma.loginregistration.model.DataBaseHelper;
import media.sigma.loginregistration.model.SessionManager;

/**
 * Created by avikal on 3/14/2016.
 */
public class RegistrationActivity extends AppCompatActivity
{
    EditText name, email, pass, phone, profession, address, description;
    int REQUEST_CAMERA = 0, SELECT_FILE = 1;
    private static final int CAMERA_REQUEST = 1;
    private static final int PICK_FROM_GALLERY = 2;
    String userName, userEmail, userPass, userPhone, userProfession, userAdd, userDescription;
    Button register;
    SessionManager session;
    ImageView user_picture, user_picture1;
    byte[] imageInByte;
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
        user_picture = (ImageView) findViewById(R.id.user_pic);
        user_picture1 = (ImageView) findViewById(R.id.user_picture);
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
        final String[] option = new String[] { "Take from Camera",
                "Select from Gallery" };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.select_dialog_item, option);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Select Option");
        builder.setAdapter(adapter, new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
                Log.e("Selected Item", String.valueOf(which));
                if (which == 0) {
                    callCamera();
                }
                if (which == 1) {
                    callGallery();
                }

            }
        });
        final AlertDialog dialog = builder.create();

        user_picture.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dialog.show();
            }
        });
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

                long d = db.addUser(userName,userEmail,userPass,userPhone,userProfession,userAdd,userDescription,imageInByte);
                if(d==-1)
                {
                    Toast.makeText(getApplicationContext(), "Please Fill all information Correct", Toast.LENGTH_SHORT).show();
                }
                else
                {
//                    session.setLogin(true);
                    Intent intent = new Intent(RegistrationActivity.this,ShowActivity.class);
                    intent.putExtra("login_user", userName);
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(), "Data Inserted", Toast.LENGTH_SHORT).show();
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

    public void callCamera() {
        Intent cameraIntent = new Intent(
                android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        cameraIntent.putExtra("crop", "true");
        cameraIntent.putExtra("aspectX", 0);
        cameraIntent.putExtra("aspectY", 0);
        cameraIntent.putExtra("outputX", 200);
        cameraIntent.putExtra("outputY", 150);
        startActivityForResult(cameraIntent, CAMERA_REQUEST);

    }

    /**
     * open gallery method
     */

    public void callGallery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 0);
        intent.putExtra("aspectY", 0);
        intent.putExtra("outputX", 200);
        intent.putExtra("outputY", 150);
        intent.putExtra("return-data", true);
        startActivityForResult(
                Intent.createChooser(intent, "Complete action using"),
                PICK_FROM_GALLERY);

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_OK)
            return;

        switch (requestCode) {
            case CAMERA_REQUEST:

                Bundle extras = data.getExtras();

                if (extras != null) {
                    Bitmap yourImage = extras.getParcelable("data");
                    // convert bitmap to byte
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    yourImage.compress(Bitmap.CompressFormat.PNG, 100, stream);
                    imageInByte = stream.toByteArray();

//				image_str = Base64.encodeBytes(imageInByte);
//
//				imageInByte1 = imageInByte;
//                    Log.e("output before conversion", imageInByte.toString());
                    // Inserting Contacts
                    Log.d("Insert: ", "Inserting ..");
                    user_picture1.setImageBitmap(yourImage);
                    user_picture.setVisibility(View.GONE);
                    user_picture1.setVisibility(View.VISIBLE);
                    // db.addContact(new Contact(resultCode, "Android",
                    // imageInByte));
                    // Intent i = new Intent(CmplainActivity.this,
                    // CmplainActivity.class);
                    // startActivity(i);
                    // finish();

                }
                break;
            case PICK_FROM_GALLERY:
                Bundle extras2 = data.getExtras();

                if (extras2 != null) {
                    Bitmap yourImage = extras2.getParcelable("data");
                    // convert bitmap to byte
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    yourImage.compress(Bitmap.CompressFormat.PNG, 100, stream);
                    imageInByte = stream.toByteArray();
//				image_str = Base64.encodeBytes(imageInByte);

//                    Log.e("output before conversion", imageInByte.toString());
                    // Inserting Contacts
                    Log.d("Insert: ", "Inserting ..");


                    user_picture1.setImageBitmap(yourImage);
                    user_picture.setVisibility(View.GONE);
                    user_picture1.setVisibility(View.VISIBLE);
//				imageInByte1 = imageInByte;
                    // db.addContact(new Contact(resultCode, "Android",
                    // imageInByte));
                    // Intent i = new Intent(CmplainActivity.this,
                    // CmplainActivity.class);
                    // startActivity(i);
                    // finish();
                }

                break;
        }
    }

}
