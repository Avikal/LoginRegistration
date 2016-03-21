package media.sigma.loginregistration.model;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import media.sigma.loginregistration.R;
import media.sigma.loginregistration.ShowActivity;

/**
 * Created by avikal on 3/18/2016.
 */
public class UpdateClass extends AppCompatActivity
{
    Bundle bundle;
    EditText full_name,email_edit,pass_edit,phone_edit,profesion_edit,address_edit,des_edit;

    String name,email,pass,phone,profesion,address,des;
    int REQUEST_CAMERA = 0, SELECT_FILE = 1;
    private static final int CAMERA_REQUEST = 1;
    private static final int PICK_FROM_GALLERY = 2;
    int user_id;
    Button updare;
    DataBaseHelper dataBase;
    ImageView user_picture, user_picture1;
    byte[] imageInByte;
    ImageView cust_image;
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_layout);
        dataBase = new DataBaseHelper(getApplicationContext());
        bundle = getIntent().getExtras();
        updare = (Button) findViewById(R.id.update_cust);
        full_name = (EditText) findViewById(R.id.editTextfullname);
        email_edit = (EditText) findViewById(R.id.editTextemail);
        pass_edit = (EditText) findViewById(R.id.editTextPass);
        phone_edit = (EditText) findViewById(R.id.editTextPhone);
        profesion_edit = (EditText) findViewById(R.id.editTextProfesion);
        address_edit = (EditText) findViewById(R.id.editTextAddress);
        des_edit = (EditText) findViewById(R.id.editTextdescription);
        user_picture1 = (ImageView) findViewById(R.id.cust_picture);

        user_id = bundle.getInt("user_id");
        name = bundle.getString("full_name");
        email = bundle.getString("email");
        pass = bundle.getString("password");
        phone = bundle.getString("phone");
        profesion = bundle.getString("profession");
        address = bundle.getString("address");
        des = bundle.getString("describe");
        imageInByte = bundle.getByteArray("custImage");

//        user_id.setText(""+user_id);
        full_name.setText(name);
        email_edit.setText(email);
        pass_edit.setText(pass);
        phone_edit.setText(phone);
        profesion_edit.setText(profesion);
        address_edit.setText(address);
        des_edit.setText(des);

        ByteArrayInputStream imageStream = new ByteArrayInputStream(imageInByte);
        Bitmap theImage = BitmapFactory.decodeStream(imageStream);

        user_picture1.setImageBitmap(theImage);


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

        user_picture1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dialog.show();
            }
        });

        updare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = full_name.getText().toString();
                email = email_edit.getText().toString();
                pass = pass_edit.getText().toString();
                phone = phone_edit.getText().toString();
                profesion = profesion_edit.getText().toString();
                address = address_edit.getText().toString();
                des = des_edit.getText().toString();

                dataBase.updateuser(user_id,name, email, pass, phone, profesion, address,des,imageInByte);

                Intent intent = new Intent(UpdateClass.this, ShowActivity.class);
                startActivity(intent);
            }
        });


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
                    Log.e("output before conversion", imageInByte.toString());
                    // Inserting Contacts
                    Log.d("Insert: ", "Inserting ..");
                    user_picture1.setImageBitmap(yourImage);
//				user_picture.setVisibility(View.GONE);
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

                    Log.e("output before conversion", imageInByte.toString());
                    // Inserting Contacts
                    Log.d("Insert: ", "Inserting ..");


                    user_picture1.setImageBitmap(yourImage);
//				user_picture.setVisibility(View.GONE);
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
}
