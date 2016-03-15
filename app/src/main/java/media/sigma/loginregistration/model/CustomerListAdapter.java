package media.sigma.loginregistration.model;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import media.sigma.loginregistration.R;


public class CustomerListAdapter extends BaseAdapter {

	Context context;
	LayoutInflater inflater;
	ArrayList<HashMap<String, String>> data;
	int cust_id;
//	String cust_fullname,cust_fathername,cust_date,cust_address,cust_village,cust_mobile,
//	cust_amount,cust_itemType,cust_weight,cust_desription;

	String user_fullname,user_email,user_pass,user_phone,user_proffession,user_add,user_descripation;
	byte[] image2;
	HashMap<String, String> result = new HashMap<String, String>();
	ArrayList<Person> customer_data = new ArrayList<Person>();

	public CustomerListAdapter(Context context,
			ArrayList<Person> customer_data) {
		this.context = context;
		this.customer_data = customer_data;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return customer_data.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return customer_data.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub

		return 0;
//		return /*customer_data.get(position).getUsername()*/;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		TextView customer_name;
		ImageView customer_image;
		
		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View itemView = inflater.inflate(R.layout.user_data, parent,false);

		customer_name = (TextView) itemView.findViewById(R.id.cust_name);
		customer_image = (ImageView) itemView.findViewById(R.id.cust_image);

		customer_name.setText(customer_data.get(position).getUsername());

		Person person = customer_data.get(position);
//		image2 = picture.cust_image;
		
//		ByteArrayInputStream imageStream = new ByteArrayInputStream(image2);
//		Bitmap theImage = BitmapFactory.decodeStream(imageStream);
//		customer_image.setImageBitmap(theImage);
		
//		cust_id = picture.getId();
		/*cust_fullname = picture.getFullName();
		cust_fathername = picture.getFatherName();
		cust_date = picture.getDate();
		cust_address = picture.getAddress();
		cust_village = picture.getVillage();
		cust_mobile = picture.getMobile();
		cust_amount = picture.getAmount();
		cust_itemType = picture.getitemType();
		cust_weight = picture.getWeight();
		cust_desription = picture.getDesription();*/

		user_fullname = person.getUsername();
		user_email = person.getEmail();
		user_pass = person.getPass();
		user_phone = person.getPhoneNo();
		user_proffession = person.getProffesion();
		user_add = person.getAddress();
		user_descripation = person.getDesscrpation();
		
		/*itemView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(context, FullViewUser.class);
				intent.putExtra("custId", cust_id);
				intent.putExtra("custFullName", cust_fullname);
				intent.putExtra("custFatherName", cust_fathername);
				intent.putExtra("custDate", cust_date);
				intent.putExtra("custAddress", cust_address);
				intent.putExtra("custVillage", cust_amount);
				intent.putExtra("custMobile", cust_mobile);
				intent.putExtra("custAmount", cust_amount);
				intent.putExtra("custItemType", cust_itemType);
				intent.putExtra("custWeight", cust_weight);
				intent.putExtra("custDesription", cust_desription);
				intent.putExtra("custImage", image2);
				
				context.startActivity(intent);
				
			}
		});*/
		return itemView;
	}

}
