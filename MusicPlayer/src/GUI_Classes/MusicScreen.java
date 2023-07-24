package GUI_Classes;

import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import javax.swing.border.EmptyBorder;
import DatabaseOperations.Database;
import UserClasses.Listener;
import UserClasses.Music;
import UserClasses.User;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.LinkedList;

import java.util.Timer;
import java.util.TimerTask;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Color;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.awt.event.MouseMotionAdapter;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class MusicScreen extends JFrame {

	private JPanel contentPane;
	private JPanel[] panel = new JPanel[Database.getSongPerPage()];
	private JPanel panel1, panel2, panel3, panel4;
	private JLabel[] imagePreviewLbl = new JLabel[Database.getSongPerPage()];
	private JLabel[] songNamePreviewLbl = new JLabel[Database.getSongPerPage()];
	private JLabel[] singerPreviewLbl = new JLabel[Database.getSongPerPage()];
	private JLabel[] durationPreviewLbl = new JLabel[Database.getSongPerPage()];
	private JLabel imagePreviewLbl1, imagePreviewLbl2,imagePreviewLbl3, imagePreviewLbl4;
	private JLabel singerPreviewLbl1, singerPreviewLbl2, singerPreviewLbl3, singerPreviewLbl4;
	private JLabel songNamePreviewLbl1, songNamePreviewLbl2, songNamePreviewLbl3, songNamePreviewLbl4;
	private JLabel durationPreviewLbl1, durationPreviewLbl2, durationPreviewLbl3, durationPreviewLbl4;
	private int pageMusic = 1;
	private JLabel pageInfoLbl;
	private JButton previousBtn;
	private JButton firstBtn;
	private JButton nextBtn;
	private JButton lastBtn;
	private int totalPageNumber;
	private JButton btnNewButton_2;
	private Clip clip;
	private int value = 0;
	private int second = 0;
	private JProgressBar bar;
	private Timer timer;
	private double durationInSeconds = 0;
	private int x = 0;
	private int whichSong = 0;
	private String file_song;
	private File file = new File("");
	private int pageAutoPlay = 1;
	private JButton previousSongBtn;
	private JButton nextSongBtn;
	private JButton playPauseBtn;
	private JSlider slider;
	private JLabel songNameLbl;
	private JLabel songTimeLbl;
	private Color color = new Color(0, 153, 0);
	private int musicPlayingPage = 0;
	private String[] nameAndDuration = new String[2];
	private String duration;
	private boolean previousPage = false;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MusicScreen frame = new MusicScreen(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MusicScreen(User user) {
		timer = new Timer();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 990, 913);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(36, 36, 36));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		for(int i = 0;i<Database.getSongPerPage();i++) {
			panel[i] = new JPanel();
			imagePreviewLbl[i] = new JLabel("ICON");
			singerPreviewLbl[i] = new JLabel((String) null);
			songNamePreviewLbl[i] = new JLabel("Song Name");
			durationPreviewLbl[i] = new JLabel("Duration");
		}

		
		JButton addMusicBtn = new JButton("ADD MUSIC");
		addMusicBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddMusic addM = new AddMusic(user);
				addM.setVisible(true);
				displayMusics();
			}
			
		});
		addMusicBtn.setBounds(584, 10, 111, 23);
		contentPane.add(addMusicBtn);
		
		firstBtn = new JButton("<<");
		firstBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pageMusic = 1;
				displayMusics();
				resetColor();
				setButtons();
			}
		});
		firstBtn.setBounds(319, 671, 49, 23);
		contentPane.add(firstBtn);
		
		previousBtn = new JButton("<");
		previousBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pageMusic--;
				displayMusics();
				resetColor();
				setButtons();
			}
		});
		previousBtn.setBounds(378, 671, 49, 23);
		contentPane.add(previousBtn);
		
		nextBtn = new JButton(">");
		nextBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pageMusic++;
				displayMusics();
				resetColor();
				setButtons();
			}
		});
		nextBtn.setBounds(534, 671, 49, 23);
		contentPane.add(nextBtn);
		
		lastBtn = new JButton(">>");
		lastBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pageMusic = totalPageNumber;
				displayMusics();
				resetColor();
				setButtons();
			}
		});
		lastBtn.setBounds(593, 671, 49, 23);
		contentPane.add(lastBtn);
		
		pageInfoLbl = new JLabel("New label");
		pageInfoLbl.setForeground(new Color(255, 255, 255));
		pageInfoLbl.setBounds(464, 675, 61, 14);
		contentPane.add(pageInfoLbl);
		
		JButton btnNewButton_1 = new JButton("LOG OUT");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clip.close();
				timer.cancel();
				LogInOrSignUp log = new LogInOrSignUp();
				log.setVisible(true);
				dispose();
			}
		});
		btnNewButton_1.setBounds(816, 10, 111, 23);
		contentPane.add(btnNewButton_1);
		
		btnNewButton_2 = new JButton("REFRESH");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				displayMusics();
				setButtons();
			}
		});
		btnNewButton_2.setBounds(705, 10, 101, 23);
		contentPane.add(btnNewButton_2);
		
		
		
		panel1 = new JPanel();
		panel1.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				whichSong = 1;
				pageAutoPlay = pageMusic;
				timer.cancel();
				file_song = Database.getFileSong(songNamePreviewLbl1.getText());
				file = new File("./musics/"+file_song);
				musicPlayingPage = pageMusic;
				playSong(file);
				songTimeLbl.setVisible(true);
			}
		});
		panel1.setLayout(null);
		panel1.setBounds(243, 53+(0*150), 505, 130);
		contentPane.add(panel1);
		imagePreviewLbl1 = new JLabel("ICON");
		imagePreviewLbl1.setBounds(10, 11, 120, 100);
		panel1.add(imagePreviewLbl1);
		singerPreviewLbl1 = new JLabel((String) null);
		singerPreviewLbl1.setBounds(141, 29, 325, 14);
		panel1.add(singerPreviewLbl1);
		songNamePreviewLbl1 = new JLabel("Song Name");
		songNamePreviewLbl1.setBounds(141, 54, 325, 14);
		panel1.add(songNamePreviewLbl1);
		durationPreviewLbl1 = new JLabel("Duration");
		durationPreviewLbl1.setBounds(140, 79, 230, 14);
		panel1.add(durationPreviewLbl1);
		panel1.setBackground(new Color(0, 153, 0));
		
		panel2 = new JPanel();
		panel2.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				whichSong = 2;
				pageAutoPlay = pageMusic;
				timer.cancel();
				file_song = Database.getFileSong(songNamePreviewLbl2.getText());
				file = new File("./musics/"+file_song);
				musicPlayingPage = pageMusic;
				playSong(file);
				songTimeLbl.setVisible(true);
			}
		});
		panel2.setLayout(null);
		panel2.setBounds(243, 53+(1*150), 505, 130);
		contentPane.add(panel2);
		imagePreviewLbl2 = new JLabel("ICON");
		imagePreviewLbl2.setBounds(10, 11, 120, 100);
		panel2.add(imagePreviewLbl2);
		singerPreviewLbl2 = new JLabel((String) null);
		singerPreviewLbl2.setBounds(141, 29, 325, 14);
		panel2.add(singerPreviewLbl2);
		songNamePreviewLbl2 = new JLabel("Song Name");
		songNamePreviewLbl2.setBounds(141, 54, 325, 14);
		panel2.add(songNamePreviewLbl2);
		durationPreviewLbl2 = new JLabel("Duration");
		durationPreviewLbl2.setBounds(140, 79, 230, 14);
		panel2.add(durationPreviewLbl2);
		panel2.setBackground(new Color(0, 153, 0));
		
		panel3 = new JPanel();
		panel3.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				whichSong = 3;
				pageAutoPlay = pageMusic;
				timer.cancel();
				file_song = Database.getFileSong(songNamePreviewLbl3.getText());
				file = new File("./musics/"+file_song);
				musicPlayingPage = pageMusic;
				playSong(file);
				songTimeLbl.setVisible(true);
			}
		});
		panel3.setLayout(null);
		panel3.setBounds(243, 53+(2*150), 505, 130);
		contentPane.add(panel3);
		imagePreviewLbl3 = new JLabel("ICON");
		imagePreviewLbl3.setBounds(10, 11, 120, 100);
		panel3.add(imagePreviewLbl3);
		singerPreviewLbl3 = new JLabel((String) null);
		singerPreviewLbl3.setBounds(141, 29, 325, 14);
		panel3.add(singerPreviewLbl3);
		songNamePreviewLbl3 = new JLabel("Song Name");
		songNamePreviewLbl3.setBounds(141, 54, 325, 14);
		panel3.add(songNamePreviewLbl3);
		durationPreviewLbl3 = new JLabel("Duration");
		durationPreviewLbl3.setBounds(140, 79, 230, 14);
		panel3.add(durationPreviewLbl3);
		panel3.setBackground(new Color(0, 153, 0));
		
		panel4 = new JPanel();
		panel4.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				whichSong = 4;
				pageAutoPlay = pageMusic;
				timer.cancel();
				file_song = Database.getFileSong(songNamePreviewLbl4.getText());
				file = new File("./musics/"+file_song);
				musicPlayingPage = pageMusic;
				playSong(file);
				songTimeLbl.setVisible(true);
			}
		});
		panel4.setLayout(null);
		panel4.setBounds(243, 53+(3*150), 505, 130);
		contentPane.add(panel4);
		imagePreviewLbl4 = new JLabel("ICON");
		imagePreviewLbl4.setBounds(10, 11, 120, 100);
		panel4.add(imagePreviewLbl4);
		singerPreviewLbl4 = new JLabel((String) null);
		singerPreviewLbl4.setBounds(141, 29, 325, 14);
		panel4.add(singerPreviewLbl4);
		songNamePreviewLbl4 = new JLabel("Song Name");
		songNamePreviewLbl4.setBounds(141, 54, 325, 14);
		panel4.add(songNamePreviewLbl4);
		durationPreviewLbl4 = new JLabel("Duration");
		durationPreviewLbl4.setBounds(140, 79, 230, 14);
		panel4.add(durationPreviewLbl4);
		panel4.setBackground(color );
		
		displayMusics();
		setButtons();
		
		String file_song = Database.getFileSong(songNamePreviewLbl1.getText());
		File file = new File("./musics/"+file_song);
		AudioInputStream audioStream = null;
		try {
			audioStream = AudioSystem.getAudioInputStream(file);
		} catch (UnsupportedAudioFileException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		try {
			clip = AudioSystem.getClip();
			try {
				clip.open(audioStream);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		} catch (LineUnavailableException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		bar = new JProgressBar();
		bar.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				timer.cancel();
				x = e.getX();
				if(clip.isRunning()) {
					value = x*100/bar.getWidth();
					bar.setValue(value);
				}
				
				
			}
		});
		bar.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				timer.cancel();
				x = e.getX();
				if(clip.isRunning()) {
					value = x*100/bar.getWidth();
					bar.setValue(value);
				}
				
			}
			
			@Override
			public void mouseReleased(MouseEvent e) {
				
				if(clip.isRunning()) {
					
					timer = new Timer();
					timer.scheduleAtFixedRate(new TimerTask() {
						  @Override
						  public void run() {
							  second++;
							  setProggressBar(durationInSeconds*1000);
						  }
						}, 0,1);
					long position = 0;
					//System.out.println(x);
					value = x*100/bar.getWidth();
					second = (int) (value*durationInSeconds*1000/100);
					position = (long) (10000*value*durationInSeconds*1000);
					bar.setValue(value);
					clip.setMicrosecondPosition(second*1000);
					//System.out.println("new value= "+value+"    "+"new second="+second+"     new postiion="+position);
				}
				
				
			}
		});
		bar.setBounds(305,770,358,10);
		contentPane.add(bar);
		bar.setValue(value);
		bar.setMaximum(100);
		bar.setMinimum(0);
		
		playPauseBtn = new JButton("PLAY");
		playPauseBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(playPauseBtn.getText().equals("STOP")) {
					clip.stop();
					playPauseBtn.setText("PLAY");
					timer.cancel();
				}
				else {
					second--;
					timer = new Timer();
					timer.scheduleAtFixedRate(new TimerTask() {
						  @Override
						  public void run() {
							  second++;
							  setProggressBar(durationInSeconds*1000);
						  }
						}, 0,1);
					clip.setMicrosecondPosition(second*1000);
					clip.start();
				}
				
				
			}
		});
		playPauseBtn.setBounds(404, 791, 89, 23);
		contentPane.add(playPauseBtn);
		
		nextSongBtn = new JButton(">");
		nextSongBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				playNextSong();
			}
		});
		nextSongBtn.setBounds(671, 760, 89, 23);
		contentPane.add(nextSongBtn);
		
		previousSongBtn = new JButton("<");
		previousSongBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				playPreviousSong();
			}
		});
		previousSongBtn.setBounds(206, 760, 89, 23);
		contentPane.add(previousSongBtn);
		
		slider = new JSlider(20,80);
		slider.setValue(70);
		slider.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int xVal = e.getX();
				
				xVal = (slider.getMaximum() - slider.getMinimum()) * xVal/slider.getWidth()+slider.getMinimum()+2;
				
				slider.setValue(xVal);
			}
		});
		slider.addChangeListener(new ChangeListener() {
			

			public void stateChanged(ChangeEvent e) {
				setVolume();
			}});
		slider.setBounds(404, 825, 194, 14);
		contentPane.add(slider);
		
		songNameLbl = new JLabel("", SwingConstants.CENTER);
		songNameLbl.setForeground(new Color(255, 255, 255));
		songNameLbl.setBounds(305, 745, 358, 14);
		contentPane.add(songNameLbl);
		
		songTimeLbl = new JLabel("");
		songTimeLbl.setForeground(new Color(255, 255, 255));
		songTimeLbl.setBounds(514, 795, 101, 14);
		contentPane.add(songTimeLbl);
		songTimeLbl.setVisible(false);
		
		
		
		checkValue thCV = new checkValue();
		Thread thread = new Thread(thCV);
		thread.start();
		if(user instanceof Listener) {
			addMusicBtn.setVisible(false);
		}
		
		
	}
	
	public void displayMusics() {
		LinkedList<Music> musics = Database.displayMusics(pageMusic);
		Iterator<Music> iterator = musics.iterator();
		Music m;
		ImageIcon[] image = new ImageIcon[musics.size()];
		
		for(int i = 0;i<Database.getSongPerPage();i++) {
			switch(i) {
			case 0:
			panel1.setVisible(false);
			imagePreviewLbl1.setVisible(false);
			singerPreviewLbl1.setVisible(false);
			songNamePreviewLbl1.setVisible(false);
			durationPreviewLbl1.setVisible(false);
				break;
			case 1:
				panel2.setVisible(false);
				imagePreviewLbl2.setVisible(false);
				singerPreviewLbl2.setVisible(false);
				songNamePreviewLbl2.setVisible(false);
				durationPreviewLbl2.setVisible(false);
				break;
			case 2:
				panel3.setVisible(false);
				imagePreviewLbl3.setVisible(false);
				singerPreviewLbl3.setVisible(false);
				songNamePreviewLbl3.setVisible(false);
				durationPreviewLbl3.setVisible(false);
				break;
			case 3:
				panel4.setVisible(false);
				imagePreviewLbl4.setVisible(false);
				singerPreviewLbl4.setVisible(false);
				songNamePreviewLbl4.setVisible(false);
				durationPreviewLbl4.setVisible(false);
				break;
			}
			
		}
		
		for(int b = 0;b<musics.size();b++) {
			m = (Music) iterator.next();
			image[b] = new ImageIcon("./pictures/"+m.getFileIcon());
			switch(b) {
			case 0:
			panel1.setVisible(true);
			imagePreviewLbl1.setVisible(true);
			singerPreviewLbl1.setVisible(true);
			songNamePreviewLbl1.setVisible(true);
			durationPreviewLbl1.setVisible(true);
			imagePreviewLbl1.setIcon(image[b]);			
			singerPreviewLbl1.setText(m.getSinger());
			songNamePreviewLbl1.setText(m.getSongName());			
			durationPreviewLbl1.setText(m.getDuration());
				break;
			case 1:
				panel2.setVisible(true);
				imagePreviewLbl2.setVisible(true);
				singerPreviewLbl2.setVisible(true);
				songNamePreviewLbl2.setVisible(true);
				durationPreviewLbl2.setVisible(true);
				imagePreviewLbl2.setIcon(image[b]);			
				singerPreviewLbl2.setText(m.getSinger());
				songNamePreviewLbl2.setText(m.getSongName());			
				durationPreviewLbl2.setText(m.getDuration());
				break;
			case 2:
				panel3.setVisible(true);
				imagePreviewLbl3.setVisible(true);
				singerPreviewLbl3.setVisible(true);
				songNamePreviewLbl3.setVisible(true);
				durationPreviewLbl3.setVisible(true);
				imagePreviewLbl3.setIcon(image[b]);			
				singerPreviewLbl3.setText(m.getSinger());
				songNamePreviewLbl3.setText(m.getSongName());			
				durationPreviewLbl3.setText(m.getDuration());
				break;
			case 3:
				panel4.setVisible(true);
				imagePreviewLbl4.setVisible(true);
				singerPreviewLbl4.setVisible(true);
				songNamePreviewLbl4.setVisible(true);
				durationPreviewLbl4.setVisible(true);
				imagePreviewLbl4.setIcon(image[b]);			
				singerPreviewLbl4.setText(m.getSinger());
				songNamePreviewLbl4.setText(m.getSongName());			
				durationPreviewLbl4.setText(m.getDuration());
				break;
			
			
			}
			
			
			int sizeOfMusics = Database.sizeOfMusics();
			totalPageNumber = (int) Math.ceil((double)sizeOfMusics / Database.getSongPerPage());
			
			
			pageInfoLbl.setText(pageMusic+" / "+totalPageNumber);
			
		}
	}
	
	public void setButtons() {
		if(pageMusic == 1) {
			firstBtn.setEnabled(false);
			previousBtn.setEnabled(false);
		}
		else {
			firstBtn.setEnabled(true);
			previousBtn.setEnabled(true);
		}
		
		if(pageMusic == totalPageNumber) {
			lastBtn.setEnabled(false);
			nextBtn.setEnabled(false);
		}else {
			lastBtn.setEnabled(true);
			nextBtn.setEnabled(true);
		}
		
		
	}
	
	public void setProggressBar(double durationInSecond) {
		value = (int) ((second / durationInSecond) *100);
		bar.setValue(value);
		if(bar.getValue() >= 100) {
			timer.cancel();
		}
		//System.out.println(value+"       "+second+"      "+durationInSecond);
	}
	
	public void resetColor() {
		panel1.setBackground(color);
		panel2.setBackground(color);
		panel3.setBackground(color);
		panel4.setBackground(color);
	}
	
	
	public void changeColor() {
		
		if(songNamePreviewLbl1.getText().equalsIgnoreCase(songNameLbl.getText()))
			panel1.setBackground(Color.CYAN);
		
		else if(songNamePreviewLbl2.getText().equalsIgnoreCase(songNameLbl.getText()))
			panel2.setBackground(Color.CYAN);
		
		else if(songNamePreviewLbl3.getText().equalsIgnoreCase(songNameLbl.getText()))
			panel3.setBackground(Color.CYAN);
		
		else if(songNamePreviewLbl4.getText().equalsIgnoreCase(songNameLbl.getText()))
			panel4.setBackground(Color.CYAN);			
		
		else;
		
	}
	
	public void playSong(File file) {
		value = 0;
		second = 0;
		clip.close();
		
		//System.out.println(file.getAbsolutePath());
		try {
			AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
			
			try {
				clip = AudioSystem.getClip();
				clip.open(audioStream);
				setVolume();
				AudioFormat format = audioStream.getFormat();
				long frames = audioStream.getFrameLength();
				durationInSeconds = (frames+0.0) / format.getFrameRate();
				nameAndDuration = Database.songNameAndDuration(file.getName());
				switch(whichSong) {
				case 1:songNameLbl.setText(nameAndDuration[0]);
					  
					break;
				case 2:songNameLbl.setText(nameAndDuration[0]);
				
					break;
				case 3:songNameLbl.setText(nameAndDuration[0]);
				
				break;
				case 4:songNameLbl.setText(nameAndDuration[0]);
				
				break;
				
				
				}
				songNameLbl.setAlignmentX(CENTER_ALIGNMENT);
				clip.start();
				
				second++;
			    setProggressBar(durationInSeconds);
					
			    	timer = new Timer();
					timer.scheduleAtFixedRate(new TimerTask() {
						  @Override
						  public void run() {
							  second++;
							  setProggressBar(durationInSeconds*1000);
							  
						  }
						}, 0,1);
			} catch (LineUnavailableException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		} catch (UnsupportedAudioFileException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public void setVolume() {
		if (clip != null) {
            // JSlider source = (JSlider) e.getSource();
             float volume = (float) slider.getValue() / 100.0f;

             // Adjust the clip's volume using FloatControl
             if (clip.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
                 FloatControl control = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                 float range = control.getMaximum() - control.getMinimum();
                 float gain = (range * volume) + control.getMinimum();
                 control.setValue(gain);
             } 
			
			
		}
	}
	
	public void setSecondFormat() {
		//System.out.println(second);
		int secondFormat = second / 1000;
		int minuteFormat = secondFormat/60;
		int remainingSeconds = secondFormat % 60;
		if(minuteFormat<10)
			duration = "0"+minuteFormat;
		else
			duration = String.valueOf(minuteFormat);
		duration += ":";
		if(remainingSeconds <10) {
			duration += "0"+ remainingSeconds;
		}
		else
			duration += remainingSeconds;
		
	}
	
	public void playPreviousSong() {
		LinkedList<Music> musics = Database.displayMusics(pageAutoPlay);
		Iterator<Music> iterator = musics.iterator();
		Music m[] = new Music[musics.size()];
		for(int b = 0;b<musics.size();b++) {
			m[b] = (Music) iterator.next();
			
		}
		if(whichSong == 1 && pageAutoPlay != 1) {
			pageAutoPlay--;
			previousPage = true;
		}
		else if(whichSong == 1 && pageAutoPlay == 1){
			pageAutoPlay = totalPageNumber;
			previousPage = true;
		}
		musics = Database.displayMusics(pageAutoPlay);
		iterator = musics.iterator();
		m = new Music[musics.size()];
		for(int b = 0;b<musics.size();b++) {
			m[b] = (Music) iterator.next();
		}
		if(musics.size() != Database.getSongPerPage() && whichSong != musics.size() && previousPage) {
			whichSong = musics.size()+1;
			previousPage = false;
		}
		
		switch(whichSong) {
		case 1:
			whichSong = 4;
			timer.cancel();
			file_song = Database.getFileSong(m[3].getSongName());
			//System.out.println(file_song+"    31");
			file = new File("./musics/"+file_song);
			//System.out.println(file.getAbsolutePath());
			playSong(file);
			break;
		case 2:
			whichSong = 1;
			timer.cancel();
			file_song = Database.getFileSong(m[0].getSongName());
			//System.out.println(file_song+"    31");
			file = new File("./musics/"+file_song);
			//System.out.println(file.getAbsolutePath());
			playSong(file);
			
			break;
		case 3:
			whichSong = 2;
			timer.cancel();
			file_song = Database.getFileSong(m[1].getSongName());
			//System.out.println(file_song+"    31");
			file = new File("./musics/"+file_song);
			//System.out.println(file.getAbsolutePath());
			playSong(file);
			break;
		case 4:
		whichSong = 3;
		timer.cancel();
		file_song = Database.getFileSong(m[2].getSongName());
		//System.out.println(file_song+"    31");
		file = new File("./musics/"+file_song);
		//System.out.println(file.getAbsolutePath());
		playSong(file);
			break;
		
		
		
		}
		
	}
	
	public void playNextSong() {
		
		LinkedList<Music> musics = Database.displayMusics(pageAutoPlay);
		Iterator<Music> iterator = musics.iterator();
		Music m[] = new Music[musics.size()];
		
		for(int b = 0;b<musics.size();b++) {
			m[b] = (Music) iterator.next();
			
		}
		//System.out.println("m : "+m.length);
		if(m.length != Database.getSongPerPage() && whichSong == m.length) {
			if(pageAutoPlay != totalPageNumber) {
				
				pageAutoPlay++;
				timer.cancel();
				musics = Database.displayMusics(pageAutoPlay);
				iterator = musics.iterator();
				m = new Music[musics.size()];
				for(int b = 0;b<musics.size();b++) {
					m[b] = (Music) iterator.next();
				}
				file_song = Database.getFileSong(m[0].getSongName());
				file = new File("./musics/"+file_song);
				playSong(file);
				
				whichSong = 1;
			}
			else {
				pageAutoPlay = 0;
			//	System.out.println("41");
				whichSong = 4;
			}
		}
		else {
			switch(whichSong) {
			case 1:
				if(m.length >=1) {
					whichSong = 2;
					timer.cancel();
					file_song = Database.getFileSong(m[1].getSongName());
					//System.out.println(file_song+"    31");
					file = new File("./musics/"+file_song);
					//System.out.println(file.getAbsolutePath());
					playSong(file);
					if(totalPageNumber == pageAutoPlay && m.length <= 2) {
						//pageAutoPlay = 1;
						whichSong = 4;
					}
				}
				break;
			case 2:
				if(m.length >=2) {
				whichSong = 3;
				timer.cancel();
				file_song = Database.getFileSong(m[2].getSongName());
				file = new File("./musics/"+file_song);
				playSong(file);
				if(totalPageNumber == pageAutoPlay && m.length <= 3) {
					//pageAutoPlay = 1;
					whichSong = 4;
				}
			}
				break;
			case 3:
				if(m.length >=3) {
					whichSong = 4;
					timer.cancel();
					file_song = Database.getFileSong(m[3].getSongName());
					file = new File("./musics/"+file_song);
					playSong(file);
					if(totalPageNumber == pageAutoPlay && m.length <= 4) {
						//pageAutoPlay = 1;
						whichSong = 4;
					}
				}
				break;
			case 4:
				if(pageAutoPlay != totalPageNumber) {
					
					pageAutoPlay++;
					timer.cancel();
					musics = Database.displayMusics(pageAutoPlay);
					iterator = musics.iterator();
					m = new Music[musics.size()];
					for(int b = 0;b<musics.size();b++) {
						m[b] = (Music) iterator.next();
					}
					file_song = Database.getFileSong(m[0].getSongName());
					file = new File("./musics/"+file_song);
					playSong(file);
					
					whichSong = 1;
				}
				else {
					pageAutoPlay = 0;
					//System.out.println("41");
					whichSong = 4;
				}
				break;
			
			
			}
			//System.out.println(whichSong);
		}
		
		
	}
	
	public class checkValue implements Runnable{

		@Override
		public void run() {
			
			while(true) {
				
				//System.out.println("value= "+value);
				if(value >= 100) {
					playNextSong();
					playPauseBtn.setText("PLAY");
					//System.out.println("b");
				}
				if(clip.isRunning()) {
					playPauseBtn.setText("STOP");
				}
				
				if(songNameLbl.getText().equalsIgnoreCase(nameAndDuration[0])) {
					resetColor();
					changeColor();
				}
				
				setSecondFormat();
				songTimeLbl.setText(duration+" / "+nameAndDuration[1]);
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//System.out.println("a");
			}
			
		}
		
	}
}
