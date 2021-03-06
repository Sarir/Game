package game;

import game.menu.MenuMain;
import com.googlecode.lanterna.TerminalFacade;
import com.googlecode.lanterna.screen.Screen;

public class Main {

	public static void main(String... args){
		final Screen screen = TerminalFacade.createScreen();
		screen.getTerminal().getTerminalSize().setColumns(50);
		screen.getTerminal().getTerminalSize().setRows(11);
		screen.updateScreenSize();
		screen.startScreen();
		MenuMain mainMenu = new MenuMain(screen);
		mainMenu.startMusic();
		Settings.CurrentScreen = mainMenu.getDefaultId();
	}
}
