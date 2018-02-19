package id.tech.firas.mytraffic.models;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class DataItem{

	@SerializedName("password")
	private String password;

	@SerializedName("full_name")
	private String fullName;

	@SerializedName("flag")
	private int flag;

	@SerializedName("insert_date")
	private String insertDate;

	@SerializedName("id_user")
	private int idUser;

	@SerializedName("update_date")
	private String updateDate;

	@SerializedName("username")
	private String username;

	public void setPassword(String password){
		this.password = password;
	}

	public String getPassword(){
		return password;
	}

	public void setFullName(String fullName){
		this.fullName = fullName;
	}

	public String getFullName(){
		return fullName;
	}

	public void setFlag(int flag){
		this.flag = flag;
	}

	public int getFlag(){
		return flag;
	}

	public void setInsertDate(String insertDate){
		this.insertDate = insertDate;
	}

	public String getInsertDate(){
		return insertDate;
	}

	public void setIdUser(int idUser){
		this.idUser = idUser;
	}

	public int getIdUser(){
		return idUser;
	}

	public void setUpdateDate(String updateDate){
		this.updateDate = updateDate;
	}

	public String getUpdateDate(){
		return updateDate;
	}

	public void setUsername(String username){
		this.username = username;
	}

	public String getUsername(){
		return username;
	}

	@Override
 	public String toString(){
		return 
			"DataItem{" + 
			"password = '" + password + '\'' + 
			",full_name = '" + fullName + '\'' + 
			",flag = '" + flag + '\'' + 
			",insert_date = '" + insertDate + '\'' + 
			",id_user = '" + idUser + '\'' + 
			",update_date = '" + updateDate + '\'' + 
			",username = '" + username + '\'' + 
			"}";
		}
}