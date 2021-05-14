package model;

public class Researcher {
	private int id;
	private String name;
	private String email;
	private String password;
	private String createdAt;
	private String updatedAt;
	private int researchCategory;
	private String token;
	
	public Researcher() {
		super();
	}

	public Researcher(String name, String email, String password, int researchCategory) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.researchCategory = researchCategory;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public String getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	public int getResearchCategory() {
		return researchCategory;
	}

	public void setResearchCategory(int researchCategory) {
		this.researchCategory = researchCategory;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public String toString() {
		return "Researcher [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password
				+ ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", researchCategory=" + researchCategory
				+ ", token=" + token + "]";
	}

	
}
