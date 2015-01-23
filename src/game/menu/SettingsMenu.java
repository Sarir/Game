package game.menu;

import game.Settings;
import game.utils.screenUtils;

import com.googlecode.lanterna.input.Key;
import com.googlecode.lanterna.screen.Screen;

public class SettingsMenu {
	
	private static screenUtils su = new screenUtils();
	
	private String[] menu = new String[11];
	
	static int[][] menuItems = new int[3][2];
	static boolean[] selectedItem = new boolean[3];
	
	public SettingsMenu(final Screen screen){
		menu[0] =  "┌────────────────────────────────────────────────┐";
		menu[1] =  "│                 НАСТРОЙКИ                      │";
		menu[2] =  "│────────────────────────────────────────────────│";
		menu[3] =  "│             [x]  ГРОМКОСТЬ МУЗЫКИ              │";// 15 3
		menu[4] =  "│             [ ]  ГРОМКОСТЬ ЗВУКОВ              │";// 15 4
		menu[5] =  "│             [ ]  НАЗАД                         │";// 15 5
		menu[6] =  "│                                                │";
		menu[7] =  "│                                                │";
		menu[8] =  "│────────────────────────────────────────────────│";
		menu[9] =  "│   Управление: Вниз Вверх Ввод                  │";
		menu[10] = "└────────────────────────────────────────────────┘";

		menuItems[0][0] = 15; 
		menuItems[0][1] = 3;
		
		menuItems[1][0] = 15; 
		menuItems[1][1] = 4;
		
		menuItems[2][0] = 15; 
		menuItems[2][1] = 5;
		
		selectedItem[0] = true;
		selectedItem[1] = false;
		selectedItem[2] = false;
				
		
		final boolean keepRunning = true;
		
		// Перемещение по пунктам
		Runnable runner = new Runnable(){
			@Override
			public void run() {
				while(keepRunning && Settings.CurrentScreen.equalsIgnoreCase("SettingMenu")){
					Key key = screen.readInput();
					while(key == null){
						key = screen.readInput();
					}
					setPos(key.toString(), screen);
				}
			}
			
		};
		
		Thread th = new Thread(runner);
		th.start();
		
		drawMenu(screen);
	}

	private static void setPos(String key, Screen screen) {
		if(Settings.CurrentScreen.equalsIgnoreCase("SettingMenu")){
			if(key.equalsIgnoreCase("ArrowUp")){
				for(int i = 0;i<selectedItem.length;i++){
					if(selectedItem[i]){
						if(i != 0){
							selectedItem[i] = false;
							su.putString(" ", menuItems[i][0], menuItems[i][1], screen);
							selectedItem[i-1] = true;
							su.putString("x", menuItems[i-1][0], menuItems[i-1][1], screen);
							break;
						} else if(i == 0){
							selectedItem[i] = false;
							su.putString(" ", menuItems[i][0], menuItems[i][1], screen);
							selectedItem[selectedItem.length - 1] = true;
							su.putString("x", menuItems[selectedItem.length-1][0], menuItems[selectedItem.length-1][1], screen);
							break;
						}	
					}
				}
			} else if(key.equalsIgnoreCase("ArrowDown")){
				for(int i = 0;i<selectedItem.length;i++){
					if(selectedItem[i]){
						if(i != selectedItem.length - 1){
							selectedItem[i] = false;
							su.putString(" ", menuItems[i][0], menuItems[i][1], screen);
							selectedItem[i+1] = true;
							su.putString("x", menuItems[i+1][0], menuItems[i+1][1], screen);
							break;
						} else {
							selectedItem[i] = false;
							su.putString(" ", menuItems[i][0], menuItems[i][1], screen);
							selectedItem[0] = true;
							su.putString("x", menuItems[0][0], menuItems[0][1], screen);
							break;
						}	
					}
				}
			} else if(key.equalsIgnoreCase("Enter")){
				if(selectedItem[0]){
					
				} else if(selectedItem[1]){
					
				} else if(selectedItem[2]){ // Назад
					Settings.CurrentScreen = "MainMenu";
					MainMenu menu = new MainMenu(screen);
				}
			}
		}
	}
	
	public void drawMenu(Screen screen){
		for(int i = 0;i<11;i++){
			su.putString(menu[i], 0, i, screen);
		}
	}
}
