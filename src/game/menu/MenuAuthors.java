package game.menu;

import game.Settings;
import com.googlecode.lanterna.screen.Screen;

public class MenuAuthors extends Menu{

	public MenuAuthors(final Screen screen) {
		
		setMenuScreen(
				"┌────────────────────────────────────────────────┐", 
				"│                 РАЗРАБОТЧИКИ                   │", 
				"│────────────────────────────────────────────────│",
				"│                  Sarir                         │", // 15 3 
				"│                  Serious07                     │", // 15 4
				"│             [x]  НАЗАД                         │", // 15 5
				"│                                                │", // 15 6 
				"│                                                │", 
				"│────────────────────────────────────────────────│", 
				"│   Игра разработана в 2015 году. Вер. 0.01      │", 
				"└────────────────────────────────────────────────┘");
		
		setParams(3, screen, 15, "MenuAuthors");
		drawMenu(screen);
		

		Runnable runner = new Runnable() {
			@Override
			public void run() {
				while(keepRunning && Settings.CurrentScreen.equalsIgnoreCase(getDefaultId())){
					pressedKey = screen.readInput();
					while(pressedKey == null){
						pressedKey = screen.readInput();
					}
					enter(pressedKey.toString(), screen);
					setPos(pressedKey.toString(), screen);
				}
			}
		};
		
		Thread th = new Thread(runner);
		th.start();
	}
	
	private void enter(String key, Screen screen) {
		if(Settings.CurrentScreen.equalsIgnoreCase(getDefaultId())){
			if(key.equalsIgnoreCase("Enter")){
				if(logic[2]){
					MenuMain menu = new MenuMain(screen);
					Settings.CurrentScreen = menu.getDefaultId();
				}
			}
		}
	}
}
