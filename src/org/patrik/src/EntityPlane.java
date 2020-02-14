package org.patrik.src;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;
import static org.lwjgl.opengl.GL11.*;

public class EntityPlane extends AbstractEntityMoving {
	
	int flyingSpeed = 0;
	
	public EntityPlane(Vector3f Pos) {
		super(Pos);
	}
	
	public void setSpeed(int speed) {
		this.flyingSpeed = speed;
	}
	
	public int getSpeed() {
		return flyingSpeed;
	}
	
	@Override
	public void update(int delta) {
		// Some Plane Logic:
		
		glTranslatef(this.Pos.x, this.Pos.y, this.Pos.z);
		glRotatef(this.Rot.x, 1, 0, 0);
		glRotatef(this.Rot.y, 0, 1, 0);
		glRotatef(this.Rot.z, 0, 0, 1);
		
		float angle = Rot.y;
        Vector3f newPos = new Vector3f(Pos);
        float hypotenuse = (flyingSpeed * 0.0002f) * delta;
        float adjacent = hypotenuse * (float) Math.cos(Math.toRadians(angle));
        float opposite = (float) (Math.sin(Math.toRadians(angle)) * hypotenuse);
        newPos.z += adjacent;
        newPos.x -= opposite;
        Pos.z = newPos.z;
        Pos.x = newPos.x;
        
        
        int mouseX = Mouse.getX() - (Display.getWidth() / 2);
        int mouseY = (Display.getDisplayMode().getHeight() - Mouse.getY() - 1) - (Display.getWidth() / 2);
        
        if (mouseX < this.Rot.z && this.Rot.z < 67.5) {
        	this.Rot.z += -mouseX / 1000.0;
        }
        
        if (mouseX > this.Rot.z && this.Rot.z > -67.5) {
        	this.Rot.z += -mouseX / 1000.0;
        }
		
        this.Rot.y += (this.Rot.z / 100);
        
        if (mouseY > this.Rot.x && this.Rot.x < 90) {
        	this.Rot.x += mouseY / 1000.0;
        }
        
        if (mouseY < this.Rot.x && this.Rot.x > -90) {
        	this.Rot.x += mouseY / 1000.0;
        }
	}

	@Override
	public void draw() {
		// Plane Drawing:
		
	}
}
