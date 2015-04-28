package game.menu;

import game.Settings;
import game.utils.music.MenuMusic;

import com.googlecode.lanterna.screen.Screen;

public class MenuMain extends Menu{
	
	private static Thread music = new MenuMusic();
	
	public MenuMain(final Screen screen){
		
		setMenuScreen(
				"┌────────────────────────────────────────────────┐", 
				"│                 ГЛАВНОЕ МЕНЮ                   │", 
				"│────────────────────────────────────────────────│", 
				"│             [x]  ИГРАТЬ                        │", // 15 3
				"│             [ ]  НАСТРОЙКИ                     │", // 15 4
				"│             [ ]  РАЗРАБОТЧИКИ                  │", // 15 5 
				"│             [ ]  ВЫХОД                         │", // 15 6 
				"│                                                │", 
				"│────────────────────────────────────────────────│", 
				"│   Управление: Вниз Вверх Ввод                  │", 
				"└────────────────────────────────────────────────┘");
		
		setParams(4, screen, 15, "MenuMain");
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
		System.out.println(getDefaultId());
		if(Settings.CurrentScreen.equalsIgnoreCase(getDefaultId())){
			if(key.equalsIgnoreCase("Enter")){
				if(logic[0]){
	
				} else if(logic[1]){
					Settings.CurrentScreen = "SettingMenu";
					SettingsMenu menu = new SettingsMenu(screen);
				} else if(logic[2]){
					MenuAuthors menu = new MenuAuthors(screen);
					Settings.CurrentScreen = menu.getDefaultId();
				} else if(logic[3]){ // Выход
					screen.stopScreen();
					System.exit(0);
				}
			}
		}
	}
	
	public void startMusic(){
		music.start();
	}
	
	@SuppressWarnings("deprecation")
	public static void stopMuisc(){
		music.stop();
	}
}
