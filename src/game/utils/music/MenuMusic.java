package game.utils.music;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URI;
import java.net.URISyntaxException;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class MenuMusic extends Thread{
	@Override
	public void run() {
		String music = "/res/music/menu.mp3";
		URI resource;
		try {
			resource = getClass().getResource(music).toURI();
			File file = new File(resource);
			FileInputStream fis = new FileInputStream(file);
			BufferedInputStream bis = new BufferedInputStream(fis);
			Player player = new Player(bis);
			player.play();
		} catch (URISyntaxException | JavaLayerException | FileNotFoundException e1) {
			e1.printStackTrace();
		}
	}
}
