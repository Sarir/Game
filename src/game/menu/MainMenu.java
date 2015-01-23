package game.menu;

import com.googlecode.lanterna.input.Key;
import com.googlecode.lanterna.screen.Screen;

import game.Settings;
import game.utils.screenUtils;
import game.utils.music.MenuMusic;

public class MainMenu {
	
	private static screenUtils su = new screenUtils();
	
	private static Thread music = new MenuMusic();
	
	private String[] mainMenu = new String[11];
	
	static int[][] coord = new int[4][2];
	static boolean[] logic = new boolean[4];
	
	public MainMenu(final Screen screen){
		mainMenu[0] =  "┌────────────────────────────────────────────────┐";
		mainMenu[1] =  "│                 ГЛАВНОЕ МЕНЮ                   │";
		mainMenu[2] =  "│────────────────────────────────────────────────│";
		mainMenu[3] =  "│             [x]  ИГРАТЬ                        │";// 15 3
		mainMenu[4] =  "│             [ ]  НАСТРОЙКИ                     │";// 15 4
		mainMenu[5] =  "│             [ ]  РАЗРАБОТЧИКИ                  │";// 15 5
		mainMenu[6] =  "│             [ ]  ВЫХОД                         │";// 15 6
		mainMenu[7] =  "│                                                │";
		mainMenu[8] =  "│────────────────────────────────────────────────│";
		mainMenu[9] =  "│   Управление: Вниз Вверх Ввод                  │";
		mainMenu[10] = "└────────────────────────────────────────────────┘";
		
		coord[0][0] = 15; 
		coord[0][1] = 3;
		
		coord[1][0] = 15; 
		coord[1][1] = 4;
		
		coord[2][0] = 15; 
		coord[2][1] = 5;
		
		coord[3][0] = 15; 
		coord[3][1] = 6;
		
		logic[0] = true;
		logic[1] = false;
		logic[2] = false;
		logic[3] = false;
				
		
		final boolean keepRunning = true;
		
		// Перемещение по пунктам
		Runnable runner = new Runnable(){
			@Override
			public void run() {
				while(keepRunning){
					Key key = screen.readInput();
					while(key == null){
						key = screen.readInput();
					}
					//screen.setCursorPosition(screen.getCursorPosition().getColumn(), screen.getCursorPosition().getRow());
					//putString("x", getPos(key.toString())[0], getPos(key.toString())[1], screen);
					setPos(key.toString(), screen);
				}
			}
			
		};
		
		Thread th = new Thread(runner);
		th.start();
		
		drawMenu(screen);
		startMusic();
	}

	private static void setPos(String key, Screen screen) {
		if(Settings.CurrentScreen.equalsIgnoreCase("MainMenu")){
			if(key.equalsIgnoreCase("ArrowUp")){
				for(int i = 0;i<logic.length;i++){
					if(logic[i]){
						if(i != 0){
							logic[i] = false;
							su.putString(" ", coord[i][0], coord[i][1], screen);
							logic[i-1] = true;
							su.putString("x", coord[i-1][0], coord[i-1][1], screen);
							break;
						} else if(i == 0){
							logic[i] = false;
							su.putString(" ", coord[i][0], coord[i][1], screen);
							logic[logic.length - 1] = true;
							su.putString("x", coord[logic.length-1][0], coord[logic.length-1][1], screen);
							break;
						}	
					}
				}
			} else if(key.equalsIgnoreCase("ArrowDown")){
				for(int i = 0;i<logic.length;i++){
					if(logic[i]){
						if(i != logic.length - 1){
							logic[i] = false;
							su.putString(" ", coord[i][0], coord[i][1], screen);
							logic[i+1] = true;
							su.putString("x", coord[i+1][0], coord[i+1][1], screen);
							break;
						} else {
							logic[i] = false;
							su.putString(" ", coord[i][0], coord[i][1], screen);
							logic[0] = true;
							su.putString("x", coord[0][0], coord[0][1], screen);
							break;
						}	
					}
				}
			} else if(key.equalsIgnoreCase("Enter")){
				if(logic[0]){
	
				} else if(logic[1]){
					Settings.CurrentScreen = "SettingMenu";
					SettingsMenu menu = new SettingsMenu(screen);
				} else if(logic[2]){
					
				} else if(logic[3]){ // Выход
					screen.stopScreen();
					System.exit(0);
				}
			}
		}
	}
	
	public void drawMenu(Screen screen){
		for(int i = 0;i<11;i++){
			su.putString(mainMenu[i], 0, i, screen);
		}
	}
	
	public void startMusic(){
		music.start();
	}
	
	public static void stopMuisc(){
		music.stop();
	}
}
