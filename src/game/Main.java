package game;

import game.menu.MainMenu;

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
		mainMenu.startMusic();
	}
}
