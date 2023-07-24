package DatabaseOperations;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import UserClasses.Listener;
import UserClasses.Music;
import UserClasses.Singer;
import UserClasses.User;




public class Database {
	private static final String username = "root";
	private static final String password = "Ayhan1989";
	private static final String url = "jdbc:mysql://localhost:3306/music_player";
	private static final int songPerPage = 4;
	
	public static int getSongPerPage() {
		return songPerPage;
	}
	
	public static Connection connectDatabase() {
		Connection con = null;
		try {
			con = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return con;
	}
	
	public static boolean checkUsername(String username) {
		Connection con = connectDatabase();
		int i = 0;
		String query = "select username from user_info where username = '"+username+"';";
		try {
			Statement statement = con.prepareStatement(query);
			ResultSet result = statement.executeQuery(query);
			while(result.next()) {
				i++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(i != 0)
			return true;
		
		return false;
	}
	
	public static void insertIntoUser_Info(String username, String password, String type, String name) {
		Connection con = connectDatabase();
		String query = "";
		PreparedStatement statement;
		
		if(type.equalsIgnoreCase("listener")) {
			query = "insert into user_info (username, password, type) values('"+username+"','"+password+"','listener');";
		}
		else {
			query = "insert into user_info values ('"+username+"','"+password+"','singer','"+name+"');";
		}
		try {
			statement = con.prepareStatement(query);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static boolean checkLogInInformation(String username, String password) {
		Connection con = connectDatabase();
		String query = "select username, password from user_info where username='"+username+"' and password='"+password+"';";
		
		int i = 0;
		try {
			Statement statement = con.prepareStatement(query);
			ResultSet result = statement.executeQuery(query);
			while(result.next()) {
				i++;
			}
			
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(i != 0)
			return true;
		return false;
	}
	
	public static User getUser(String username) {
		User user = null;
		Connection con = connectDatabase();
		String query = "select * from user_info where username = '"+username+"';";
		String type ="", password = "", name="";
		try {
			Statement statement = con.prepareStatement(query);
			ResultSet result = statement.executeQuery(query);
			while(result.next()) {
				type = result.getString(3);
				password = result.getString(2);
				name = result.getString(4);
				if(type.equalsIgnoreCase("listener"))
					user = new Listener(username, password);
				else
					user = new Singer(username, password, name);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return user;
	}
	
	public static void insertIntoMusics(String file_song, String duration, String singer_username, String song_name, String file_icon) {
		Connection con = connectDatabase();
		PreparedStatement statement;
		String query = "insert into musics values ('"+file_song+"','"+duration+"','"+singer_username+"','"+song_name+"', '"+file_icon+"');";
		
		try {
			statement = con.prepareStatement(query);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public static LinkedList<Music> displayMusics(int a){
		LinkedList<Music> musics = new LinkedList<Music>();
		Music m = null;
		Connection con = connectDatabase();
		String query = "select * from musics;";
		String querySinger = "";
		
		String singer   = "";
		String fileSong = "";
		String duration = "";
		String songName = "";
		String fileIcon = "";
		
		
		int i = 0;
		try {
			Statement statement = con.prepareStatement(query);
			ResultSet result = statement.executeQuery(query);
			ResultSet resultSinger;
			while(result.next()) {
				i++;
				if(i<=a*songPerPage && i > (a-1)*songPerPage) {
					fileSong = result.getString(1);
					duration = result.getString(2);
					songName = result.getString(4);
					fileIcon = result.getString(5);
					
					querySinger = "select user_info.name from musics join user_info on user_info.username ='"+result.getString(3)+"';";
					statement = con.prepareStatement(querySinger);
					resultSinger = statement.executeQuery(querySinger);
					while(resultSinger.next()) {
						singer = resultSinger.getString(1);						
					}
					m = new Music(singer, fileSong, duration, songName, fileIcon);
					musics.add(m);
					
				}
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return musics;
	}
	
	public static int sizeOfMusics() {
		int size = 0;
		Connection con = connectDatabase();
		String query = "select * from musics;";
		String querySinger = "";
		try {
			Statement statement = con.prepareStatement(query);
			ResultSet result = statement.executeQuery(query);
			while(result.next()) {
				size++;				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	 
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return size;
	}
	
	public static String getFileSong(String songName) {
		Connection con = connectDatabase();
		String query = "select file_song from musics where song_name = '"+songName+"';";
		String file_song = "";
		try {
			Statement statement = con.prepareStatement(query);
			ResultSet result = statement.executeQuery(query);
			while(result.next()) {
				file_song = result.getString(1);				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return file_song;
		
	}
	
	public static String[] songNameAndDuration(String file_song) {
		Connection con = connectDatabase();
		String query = "select song_name, duration from musics where file_song = '"+file_song+"';";
		String[] nameAndDuration = new String[2];
		try {
			Statement statement = con.prepareStatement(query);
			ResultSet result = statement.executeQuery(query);
			while(result.next()) {
				nameAndDuration[0] = result.getString(1);
				nameAndDuration[1] = result.getString(2);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return nameAndDuration;
	}
	
	
}
