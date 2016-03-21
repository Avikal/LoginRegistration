package media.sigma.loginregistration.model;

public class Person 
{
	String userName,phoneNo,email, /*newEmail ,*/pass,proffesion,address,desscrpation;
	int user_id;
	public byte [] user_image;
	public Person()
	{

	}
	public Person(int user_id,String userName, String phoneNo, String email, String pass, String proffesion, String address, String descrpation,byte[] user_image)
	{
		super();
		this.user_id = user_id;
		this.userName = userName;
		this.phoneNo = phoneNo;
		this.email = email;
//		this.newEmail = newEmail;
		this.pass = pass;
		this.proffesion = proffesion;
		this.address = address;
		this.desscrpation = descrpation;
		this.user_image = user_image;
	}


	public int getId()
	{
		return user_id;
	}
	public void setId(int user_id)
	{
		this.user_id = user_id;
	}
	public String getUsername() {
		return userName;
	}

	public void setUsername(String userName) {
		this.userName = userName;
	}
	
	public String getPhoneNo()
	{
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo)
	{
		this.phoneNo = phoneNo;
	}
	
	public String getEmail()
	{
		return email;
	}
	public void setEmail(String email)
	{
		this.email = email;
	}
	

	public String getPass()
	{
		return pass;
	}
	public void setPass(String pass)
	{
		this.pass = pass;
	}

	public String getProffesion()
	{
		return proffesion;

	}
	public void setProffesion(String proffesion)
	{
		this.proffesion = proffesion;
	}
	public String getAddress()
	{
		return  address;
	}
	public void setAddress(String address)
	{
		this.address = address;
	}
	public String getDesscrpation()
	{
		return desscrpation;
	}
	public void setDesscrpation(String desscrpation)
	{
		this.desscrpation = desscrpation;
	}

	public byte[] getImage()
	{
		return user_image;
	}
	public void setImage(byte[] user_image)
	{
		this.user_image = user_image;
	}
}
