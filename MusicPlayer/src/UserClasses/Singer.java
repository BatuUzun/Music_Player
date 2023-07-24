package UserClasses;
public class Singer extends User {
	
	private String singerName;
	
	
	
	public Singer(String username, String password, String singerName) {
		super(username, password);
		this.singerName = singerName;
	}

	public String getSingerName() {
		return singerName;
	}
	
	
	

	
}
