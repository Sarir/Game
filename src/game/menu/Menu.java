package game.menu;

import com.googlecode.lanterna.input.Key;
import com.googlecode.lanterna.screen.Screen;

import game.Settings;
import game.utils.screenUtils;

public abstract class Menu {
	
	public static screenUtils su = new screenUtils();
	public String[] menu = new String[11];
	
	protected String defaultId;
	
	public static int[][] coord;
	public static boolean[] logic;
	
	public Menu(int menuItems, final Screen screen, int x, String defaultId){
		coord = new int[menuItems][2];
		logic = new boolean[menuItems];
		
		for(int i = 0;i<menuItems;i++){
			coord[i][0] = x; 
			coord[i][1] = 3+i;
			
			if(i == 0){
				logic[0] = true;
			} else {
				logic[i] = false;
			}
			
		}
		
		final boolean keepRunning = true;
		
		// Перемещение по пунктам
		Runnable runner = new Runnable(){
			@Override
			public void run() {
				while(keepRunning && Settings.CurrentScreen.equalsIgnoreCase("MainMenu")){
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
		
		setDefultId(defaultId);
		drawMenu(screen);
	}
	
	public abstract void setPos(String key, Screen screen);
	
	public void drawMenu(Screen screen){
		for(int i = 0;i<11;i++){
			su.putString(menu[i], 0, i, screen);
		}
	}
	
	public void setDefultId(String id){
		this.defaultId = id;
	}
	
	public String getDefaultId(){
		return defaultId;
	}
}
