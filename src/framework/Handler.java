package framework;

import java.awt.Graphics2D;
import java.util.ArrayList;

public class Handler {
	
	public ArrayList<GameObject> object = new ArrayList<GameObject>();
	
	public boolean left,right,down,up,sprith = false; 
	
	
	public void tick(){
		for(int i=0;i<object.size();i++){
			GameObject temp = object.get(i);
			temp.tick();
		}
	}
	
	public void render(Graphics2D g){
		for(int i=0;i<object.size();i++){
			GameObject temp = object.get(i);
			temp.render(g);
		}
	}
	
	public  void clearEveryThing(){
		object.clear();
	}
	
	public void addObject(GameObject ob){
		object.add(ob);
	}
	
	public void removeObject(GameObject ob){
		object.remove(ob);
	}
	
	public void clearAll(){
		object.clear();
	}
	

	public boolean isSprith() {
		return sprith;
	}

	public void setSprith(boolean sprith) {
		this.sprith = sprith;
	}

	public boolean isLeft() {
		return left;
	}

	public void setLeft(boolean left) {
		this.left = left;
	}

	public boolean isRight() {
		return right;
	}

	public void setRight(boolean right) {
		this.right = right;
	}

	public boolean isDown() {
		return down;
	}

	public void setDown(boolean down) {
		this.down = down;
	}

	public boolean isUp() {
		return up;
	}

	public void setUp(boolean up) {
		this.up = up;
	}
	
	
	
	
}
