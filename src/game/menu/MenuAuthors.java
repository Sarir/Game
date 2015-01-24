package game.menu;

import game.Settings;
import com.googlecode.lanterna.screen.Screen;

public class MenuAuthors extends Menu{

	public MenuAuthors(final Screen screen) {
		menu[0] =  "┌────────────────────────────────────────────────┐";
		menu[1] =  "│                 РАЗРАБОТЧИКИ                   │";
		menu[2] =  "│────────────────────────────────────────────────│";
		menu[3] =  "│                  Sarir                         │";// 15 3
		menu[4] =  "│                  Serious07                     │";// 15 4
		menu[5] =  "│             [x]  НАЗАД                         │";// 15 5
		menu[6] =  "│                                                │";// 15 6
		menu[7] =  "│                                                │";
		menu[8] =  "│────────────────────────────────────────────────│";
		menu[9] =  "│   Игра разработана в 2015 году. Вер. 0.01      │";
		menu[10] = "└────────────────────────────────────────────────┘";
		setParams(3, screen, 15, "MenuAuthors");
		drawMenu(screen);
		

		Runnable runner = new Runnable() {
			@Override
			public void run() {
				while(keepRunning && Settings.CurrentScreen.equalsIgnoreCase(defaultId)){
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
		if(Settings.CurrentScreen.equalsIgnoreCase(defaultId)){
			System.out.println("Key:" + key);
			if(key.equalsIgnoreCase("Enter")){
				if(logic[2]){
					Settings.CurrentScreen = "MainMenu";
					MainMenu menu = new MainMenu(screen);
				}
			}
		}
	}
}
