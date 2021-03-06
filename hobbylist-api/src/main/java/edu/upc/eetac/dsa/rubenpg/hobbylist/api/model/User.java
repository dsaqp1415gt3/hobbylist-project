package edu.upc.eetac.dsa.rubenpg.hobbylist.api.model;

public class User {
	private String username;
	private String password;
	private String name;
	private String email;
	private String rolename;
	private boolean loginSuccessful;
 
	public String getUsername() {
		return username;
	}
 
	public void setUsername(String username) {
		this.username = username;
	}
 
	public String getPassword() {
		return password;
	}
 
	public void setPassword(String password) {
		this.password = password;
	}
 
	public String getName() {
		return name;
	}
 
	public void setName(String name) {
		this.name = name;
	}
 
	public String getEmail() {
		return email;
	}
 
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getRolename() {
		return rolename;
	}
 
	public void setRolename(String rolename) {
		this.rolename = rolename;
	}
 
	public boolean isLoginSuccessful() {
		return loginSuccessful;
	}
 
	public void setLoginSuccessful(boolean loginSuccessful) {
		this.loginSuccessful = loginSuccessful;
	}
}