package me.soxey6.engine.managers.input;

import java.util.ArrayList;

import me.soxey6.engine.managers.event.EventManager;

import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector2f;

/**
 * The input Manager class is used for managing the input of the users peripherals. They simply check to see if there are changes, if so, trigger events that can be hooked.
 * Use is fairly basic but this really shouldn't be touched as it will do what you want it to do.
 * @author pchilds
 *
 */
public class InputManager {
	private static InputManager inputManager;
	/**
	 *  Where we store newly pressed keys for ONE input tick
	 */
	private ArrayList<Integer> keysNewDown;
	
	/**
	 *  Where we store currently pressed keys
	 */
	private ArrayList<Integer> keysCurDown;
	
	/**
	 * Where we store keys recently released for ONE input tick
	 */
	private ArrayList<Integer> keysOldDown;
	
	/**
	 * Where the current mouse position is stored.
	 */
	private Vector2f mouseCurPos;
	
	/**
	 * Where the previous mouse position is stored.
	 */
	private Vector2f mouseOldPos;
	
	private Click mouseCurClick;
	
	private Click mouseOldClick;

	
	public InputManager()
	{
		inputManager=this;
	}
	
	/**
	 * This function is triggered to update all the input information.
	 */
	public void input()
	{
		updateKeys();
		updateMouse();
	}

	private void updateKeys() {
		// TODO Auto-generated method stub
		
	}
	
	private void updateMouse() {
		// Update the old mousePosition if it isn't already
		if(mouseOldPos!=mouseCurPos)
			mouseOldPos=mouseCurPos;
		
		// Check to see if the mouse has moved and if so, update mouseCurPos and trigger mouse move event.
		if(Mouse.getX()!=mouseCurPos.getX()||Mouse.getY()!=mouseCurPos.getY())
		{
			mouseCurPos=new Vector2f(mouseCurPos.getX(),mouseCurPos.getY());
			EventManager.getEventManager().trigger("MOUSE_MOVE");
		}
		
		// Check to see if the mouse status has changed, and if so update mouseClicked and trigger the appropriate event
		if(Mouse.isButtonDown(0)&&mouseCurClick==null)
		{
			// Register new click.
			mouseCurClick = new Click(Mouse.getX(), Mouse.getX());
			EventManager.getEventManager().trigger("MOUSE_CLICK");
		}else if(!Mouse.isButtonDown(0)&&mouseCurClick!=null)
		{
			// Register the release
			mouseOldClick=mouseCurClick;
			mouseCurClick=null;
			EventManager.getEventManager().trigger("MOUSE_RELEASE");
		}
	}

	public static InputManager getInputManager() {
		return inputManager;
	}

	public static void setInputManager(InputManager inputManager) {
		InputManager.inputManager = inputManager;
	}

	public ArrayList<Integer> getKeysNewDown() {
		return keysNewDown;
	}

	public void setKeysNewDown(ArrayList<Integer> keysNewDown) {
		this.keysNewDown = keysNewDown;
	}

	public ArrayList<Integer> getKeysCurDown() {
		return keysCurDown;
	}

	public void setKeysCurDown(ArrayList<Integer> keysCurDown) {
		this.keysCurDown = keysCurDown;
	}

	public ArrayList<Integer> getKeysOldDown() {
		return keysOldDown;
	}

	public void setKeysOldDown(ArrayList<Integer> keysOldDown) {
		this.keysOldDown = keysOldDown;
	}

	public Vector2f getMouseCurPos() {
		return mouseCurPos;
	}

	public void setMouseCurPos(Vector2f mouseCurPos) {
		this.mouseCurPos = mouseCurPos;
	}

	public Vector2f getMouseOldPos() {
		return mouseOldPos;
	}

	public void setMouseOldPos(Vector2f mouseOldPos) {
		this.mouseOldPos = mouseOldPos;
	}

	public Click getMouseCurClick() {
		return mouseCurClick;
	}

	public void setMouseCurClick(Click mouseCurClick) {
		this.mouseCurClick = mouseCurClick;
	}

	public Click getMouseOldClick() {
		return mouseOldClick;
	}

	public void setMouseOldClick(Click mouseOldClick) {
		this.mouseOldClick = mouseOldClick;
	}
}
