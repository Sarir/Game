package game;

import java.io.File;

import game.menu.MainMenu;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import com.googlecode.lanterna.TerminalFacade;
import com.googlecode.lanterna.screen.Screen;

public class Main {

	public static void main(String... args){
		final Screen screen = TerminalFacade.createScreen();
		screen.getTerminal().getTerminalSize().setColumns(50);
		screen.getTerminal().getTerminalSize().setRows(11);
		screen.updateScreenSize();
		screen.startScreen();
		MainMenu mainMenu = new MainMenu(screen);
		
		String bip = "move.mp3";
		Media hit = new Media(bip);
		MediaPlayer mediaPlayer = new MediaPlayer(hit);
		mediaPlayer.play();
	}
}
