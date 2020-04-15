package input;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseHandler extends MouseAdapter{
	
	
	public static int x;
	public static int y;
	public static boolean pressed;
	public static boolean buttonLeft,buttonRight,middleMouseButton = false;
	

	public void mousePressed(MouseEvent e) {
		
		pressed = true;
		if(e.getButton() == MouseEvent.BUTTON1){
			buttonLeft = true;
			middleMouseButton = false;
			buttonRight = false;
			
		}
		if(e.getButton() == MouseEvent.BUTTON2){
			middleMouseButton = true;
			buttonRight = false;
			buttonLeft = false;
		}
		if(e.getButton() == MouseEvent.BUTTON3){
			buttonRight = true;
			buttonLeft = false;
			middleMouseButton = false;
		}
		
	}

	public void mouseReleased(MouseEvent e) {
		pressed = true;
		
	}
	public void mouseDragged(MouseEvent e){
		x = e.getX();
		y = e.getY();
		
	}
	public void mouseMoved(MouseEvent e){
		x = e.getX();
		y = e.getY();
		
	}
	
	public boolean isPressed(){
		return pressed;
	}
	public int getX(){ return x;}
	public int getY(){ return y;}
	public boolean isButtonLeft() { return buttonLeft;};
	public boolean isButtonRight() { return buttonRight;}
	public boolean isButtonMiddle(){ return middleMouseButton;}
	public void setButtonLeft(boolean left){
		buttonLeft = left;
	}
	public void setButtonRight(boolean right){
		buttonRight = right;
	}
	
	public void setButtonMiddle(boolean middle){
		middleMouseButton = middle;
	}
	

}
