package game.utils;

import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.Terminal;

public class screenUtils {
	// Нарисовать строку
	public void putString(String msg, int x, int y, Screen screen){
		screen.putString(x, y, msg, Terminal.Color.WHITE, Terminal.Color.BLACK);
		screen.refresh();
	}
}
