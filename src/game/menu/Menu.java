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
	
	public int menuItems;
	public Screen screen;
	public int x;
	
	public boolean keepRunning = false;
	
	public Key pressedKey;
	
	public Menu(){
		
		// Перемещение по пунктам
		Runnable runner = new Runnable(){
			@Override
			public void run() {
				while(keepRunning && Settings.CurrentScreen.equalsIgnoreCase(defaultId)){
					pressedKey = screen.readInput();
					while(pressedKey == null){
						pressedKey = screen.readInput();
					}
					setPos(pressedKey.toString(), screen);
				}
			}
			
		};
		
		Thread th = new Thread(runner);
		th.start();
	}
	
	public void setPos(String key, Screen screen) {
		if(Settings.CurrentScreen.equalsIgnoreCase(defaultId)){
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
			}
		}
	}
	
	public void drawMenu(Screen screen){
		for(int i = 0;i<11;i++){
			su.putString(menu[i], 0, i, screen);
		}
	}
	
	public String getDefaultId(){
		return defaultId;
	}
	
	public void setParams(int menuItems, final Screen screen, int x, String defaultId){
		this.menuItems = menuItems;
		this.screen = screen;
		this.x = x;
		this.defaultId = defaultId;
		this.keepRunning = true;
		
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
	}
	
	public int getMenuItems(){
		return this.menuItems;
	}
	
	public Screen getScreen(){
		return this.screen;
	}
	
	public int getX(){
		return this.x;
	}
	
	public String getPressedKey(){
		if(this.pressedKey != null){
			return this.pressedKey.toString();
		}
		return "biba";
	}
}
