package game.menu;

import game.Settings;

import com.googlecode.lanterna.screen.Screen;

public class MenuAuthors extends Menu{

	public MenuAuthors(int menuItems, Screen screen, int x, String defaultId) {
		super(3, screen, 15, "MenuAuthors");
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
	}
	
	@Override
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
			} else if(key.equalsIgnoreCase("Enter")){
				if(logic[2]){
					Settings.CurrentScreen = "MainMenu";
					MainMenu menu = new MainMenu(screen);
				}
			}
		}
	}
}
