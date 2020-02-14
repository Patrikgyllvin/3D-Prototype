package org.patrik.src;

import org.lwjgl.*;
import org.lwjgl.input.*;
import org.lwjgl.opengl.*;
import org.lwjgl.util.vector.Vector3f;
import static org.lwjgl.opengl.GL11.*;

public class EntityDoor extends AbstractEntityMoving {

	public EntityDoor(Vector3f Pos) {
		super(Pos);
	}
	
	public void update() {
		glTranslatef(Pos.x, Pos.y, Pos.z);
		
		glRotatef(Rot.y, 0, 1, 0);
		
		if (Mouse.isButtonDown(0)) {
			Rot.setY(Rot.getY() + -Mouse.getDY());
		}
		
		if (Mouse.isButtonDown(0) && Rot.getY() > 0) {
			Rot.setY(5);
		}
		
		if (!Mouse.isButtonDown(0) && Rot.getY() > 0) {
			Rot.setY(Rot.getY() - 1);
		}
		
	}
	
	@Override
	public void draw() {
		
	}
}