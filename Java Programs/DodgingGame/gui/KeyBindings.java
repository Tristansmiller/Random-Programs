package gui;

import java.awt.event.KeyEvent;

public enum KeyBindings {
	moveLeft(KeyEvent.VK_LEFT),
	moveRight(KeyEvent.VK_RIGHT),
	moveUp(KeyEvent.VK_UP),
	moveDown(KeyEvent.VK_DOWN);
	KeyBindings(int keycode){
		code = keycode;
	}
	public final int code;
}
