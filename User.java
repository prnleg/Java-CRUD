package net.java.usermanage.model;


import java.io.FileWriter;			// Para poder escrever um arquivo, no caso .json
import java.io.IOException;			// Para o Try/Catch, no caso usado no witeJSON()

import org.json.simple.JSONArray;	// json-simple settar os objeto e os agrupar
import org.json.simple.JSONObject;

public class User {
	
	private int id;
	private String name;
	private String birth;
	private String email;
	private String work;
	
	
	public User(int id, String name, String birth, String email, String work) {
		this.id = id;
		this.name = name;
		this.birth = birth;
		this.email = email;
		this.work = work;
	}
	
	
	public User(String name, String birth, String email, String work) {
		this.name = name;
		this.birth = birth;
		this.email = email;
		this.work = work;
	}

	
	public void invertDate() {
		this.setBirth(this.getBirth().replace("-", "/"));
		String birth = this.getBirth();
		birth = birth.substring(8, 10) + birth.substring(7, 8) + birth.substring(5, 7)
			+ birth.substring(4, 5) + birth.substring(0, 4);
		this.setBirth(birth);
	}
	
	
	public void twoNames() {
		String[] name = this.getName().split(" ");
		String lastName = name[name.length-1];
		if (name.length > 1){
			this.setName(name[0] + " " + lastName);			
		}
	}
	
	
	@SuppressWarnings("unchecked")
	public void writeJSON() {
		JSONObject userDetail = new JSONObject();

		String[] person = this.getEmail().split("@");
		String noBar = this.getBirth();
		noBar.replaceAll("\\\\", "");
		
		userDetail.put("Nome:", this.getName());
		userDetail.put("Nascimento:", noBar);
		userDetail.put("Email:", this.getEmail());
		userDetail.put("Cargo:", this.getWork());
		
		JSONObject userReal = new JSONObject(); 
        userReal.put("Usuario:", userDetail);
        
        JSONArray userList = new JSONArray();
        userList.add(userReal);
        
        // Esse diretório terá que ser alterado, é possivel fazer sem
        // porém o tomcat não permite criação de arquivos sem diretório.
        try (FileWriter file = new FileWriter(person[0]  + ".json")) {
            file.write(userList.toJSONString());
            file.flush();
        } catch (IOException e) { e.printStackTrace(); }
	}
	
	
	public int getId() { return id; }
	
	public void setId(int id) { this.id = id; }
	
	public String getName() { return name; }
	
	public void setName(String name) { this.name = name; }
	
	public String getBirth() { return birth; }
	
	public void setBirth(String birth) { this.birth = birth; }
	
	public String getEmail() { return email; }
	
	public void setEmail(String email) { this.email = email; }
	
	public String getWork() { return work; }
	
	public void setWork(String work) { this.work = work; }
	
}
