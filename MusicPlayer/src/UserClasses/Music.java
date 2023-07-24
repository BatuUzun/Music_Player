package UserClasses;
public class Music {
	private String singer;
	private String fileSong;
	private String duration;
	private String songName;
	private String fileIcon;
	
	public Music(String singer, String fileSong, String duration,String songName, String fileIcon) {
		this.singer= singer;
		this.fileSong= fileSong;
		this.duration= duration;
		this.songName= songName;
		this.fileIcon= fileIcon;
	}

	public String getSinger() {
		return singer;
	}

	public String getFileSong() {
		return fileSong;
	}

	public String getDuration() {
		return duration;
	}

	public String getSongName() {
		return songName;
	}

	public String getFileIcon() {
		return fileIcon;
	}
	
	
	

	
	
}
