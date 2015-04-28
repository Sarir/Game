package game.utils.music;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class MenuMusic extends Thread{
	@Override
	public void run() {
		String music = "res/music/menu.mp3";
		
		/*	try {
				URI resource = getClass().getResource(music).toURI();
				File file = new File(resource);
				FileInputStream fis = new FileInputStream(file);
				AdvancedPlayer player = new AdvancedPlayer(fis);
				player.play();
			} catch (URISyntaxException | JavaLayerException | IOException e) {
				e.printStackTrace();
			}*/
			
			try{
			    AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(this.getClass().getResource(music));
			    Clip clip = AudioSystem.getClip();
			    clip.open(audioInputStream);
			    clip.start();
			}
			catch(Exception ex)
			{
			}
			

	}
}
