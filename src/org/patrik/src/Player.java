package org.patrik.src;

import static org.lwjgl.opengl.GL11.glRotatef;
import static org.lwjgl.opengl.GL11.glTranslatef;

import org.patrik.THREEDEERPG.*;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector3f;

public class Player extends AbstractEntityMoving {
	
	/**
	 * the max amount the player can look up the x axis
	 */
	public static final int maxLookUp = 85;
	
	/**
	 * the max amount the player can look down the x axis
	 */
	public static final int maxLookDown = -85;
	
	public Player(Vector3f Pos) {
		super(Pos);
	}

	@Override
	public void update(int delta) {
		
		glRotatef(Rot.x, 1, 0, 0);
		glRotatef(Rot.y, 0, 1, 0);
		glRotatef(Rot.z, 0, 0, 1);
		
		glTranslatef(Pos.x, Pos.y, Pos.z);
		
		if (Mouse.isGrabbed()) {
			float mouseDX = Mouse.getDX() * ThreeDeeRPG.mouseSpeed * 0.16f;
			float mouseDY = Mouse.getDY() * ThreeDeeRPG.mouseSpeed * 0.16f;
			
			if (Rot.y + mouseDX >= 360) {
				Rot.y = Rot.y + mouseDX - 360;
            } else if (Rot.y + mouseDX < 0) {
            	Rot.y = 360 - Rot.y + mouseDX;
            } else {
            	Rot.y += mouseDX;
            }
            if (this.Rot.x - mouseDY >= this.maxLookDown && this.Rot.x - mouseDY <= this.maxLookUp) {
            	this.Rot.x += -mouseDY;
            } else if (this.Rot.x - mouseDY < this.maxLookDown) {
            	Rot.x = maxLookDown;
            } else if (Rot.x - mouseDY > this.maxLookUp) {
            	this.Rot.x = this.maxLookUp;
            }
		}
		
		boolean keyUp = Keyboard.isKeyDown(Keyboard.KEY_W);
		boolean keyDown = Keyboard.isKeyDown(Keyboard.KEY_S);
		boolean keyLeft = Keyboard.isKeyDown(Keyboard.KEY_A);
		boolean keyRight = Keyboard.isKeyDown(Keyboard.KEY_D);
		boolean keyJump = Keyboard.isKeyDown(Keyboard.KEY_SPACE);
		boolean keyRun = Keyboard.isKeyDown(Keyboard.KEY_LSHIFT);
		
		// sprinting
        if (keyRun) {
        	ThreeDeeRPG.walkingSpeed = 30;
        } else {
        	ThreeDeeRPG.walkingSpeed = 5;
        }
		
		// walking
		if (keyUp && keyRight && !keyLeft && !keyDown) {
            float angle = this.Rot.y + 45;
            Vector3f newPos = new Vector3f(this.Pos);
            float hypotenuse = (ThreeDeeRPG.walkingSpeed * 0.0002f) * delta;
            float adjacent = hypotenuse * (float) Math.cos(Math.toRadians(angle));
            float opposite = (float) (Math.sin(Math.toRadians(angle)) * hypotenuse);
            newPos.z += adjacent;
            newPos.x -= opposite;
            this.Pos.z = newPos.z;
            this.Pos.x = newPos.x;
        }
	 
        if (keyUp && keyLeft && !keyRight && !keyDown) {
            float angle = this.Rot.y - 45;
            Vector3f newPos = new Vector3f(this.Pos);
            float hypotenuse = (ThreeDeeRPG.walkingSpeed * 0.0002f) * delta;
            float adjacent = hypotenuse * (float) Math.cos(Math.toRadians(angle));
            float opposite = (float) (Math.sin(Math.toRadians(angle)) * hypotenuse);
            newPos.z += adjacent;
            newPos.x -= opposite;
            this.Pos.z = newPos.z;
            this.Pos.x = newPos.x;
        }
        
        if (keyUp && !keyLeft && !keyRight && !keyDown) {
            float angle = this.Rot.y;
            Vector3f newPos = new Vector3f(this.Pos);
            float hypotenuse = (ThreeDeeRPG.walkingSpeed * 0.0002f) * delta;
            float adjacent = hypotenuse * (float) Math.cos(Math.toRadians(angle));
            float opposite = (float) (Math.sin(Math.toRadians(angle)) * hypotenuse);
            newPos.z += adjacent;
            newPos.x -= opposite;
            this.Pos.z = newPos.z;
            this.Pos.x = newPos.x;
        }
        
        if (keyDown && keyLeft && !keyRight && !keyUp) {
            float angle = this.Rot.y - 135;
            Vector3f newPos = new Vector3f(this.Pos);
            float hypotenuse = (ThreeDeeRPG.walkingSpeed * 0.0002f) * delta;
            float adjacent = hypotenuse * (float) Math.cos(Math.toRadians(angle));
            float opposite = (float) (Math.sin(Math.toRadians(angle)) * hypotenuse);
            newPos.z += adjacent;
            newPos.x -= opposite;
            this.Pos.z = newPos.z;
            this.Pos.x = newPos.x;
        }
        
        if (keyDown && keyRight && !keyLeft && !keyUp) {
            float angle = this.Rot.y + 135;
            Vector3f newPos = new Vector3f(this.Pos);
            float hypotenuse = (ThreeDeeRPG.walkingSpeed * 0.0002f) * delta;
            float adjacent = hypotenuse * (float) Math.cos(Math.toRadians(angle));
            float opposite = (float) (Math.sin(Math.toRadians(angle)) * hypotenuse);
            newPos.z += adjacent;
            newPos.x -= opposite;
            this.Pos.z = newPos.z;
            this.Pos.x = newPos.x;
        }
        
        if (keyDown && !keyUp && !keyLeft && !keyRight) {
            float angle = this.Rot.y;
            Vector3f newPos = new Vector3f(this.Pos);
            float hypotenuse = -(ThreeDeeRPG.walkingSpeed * 0.0002f) * delta;
            float adjacent = hypotenuse * (float) Math.cos(Math.toRadians(angle));
            float opposite = (float) (Math.sin(Math.toRadians(angle)) * hypotenuse);
            newPos.z += adjacent;
            newPos.x -= opposite;
            this.Pos.z = newPos.z;
            this.Pos.x = newPos.x;
        }
        
        if (keyLeft && !keyRight && !keyUp && !keyDown) {
            float angle = this.Rot.y - 90;
            Vector3f newPos = new Vector3f(this.Pos);
            float hypotenuse = (ThreeDeeRPG.walkingSpeed * 0.0002f) * delta;
            float adjacent = hypotenuse * (float) Math.cos(Math.toRadians(angle));
            float opposite = (float) (Math.sin(Math.toRadians(angle)) * hypotenuse);
            newPos.z += adjacent;
            newPos.x -= opposite;
            this.Pos.z = newPos.z;
            this.Pos.x = newPos.x;
        }
        
        if (keyRight && !keyLeft && !keyUp && !keyDown) {
            float angle = this.Rot.y + 90;
            Vector3f newPos = new Vector3f(this.Pos);
            float hypotenuse = (ThreeDeeRPG.walkingSpeed * 0.0002f) * delta;
            float adjacent = hypotenuse * (float) Math.cos(Math.toRadians(angle));
            float opposite = (float) (Math.sin(Math.toRadians(angle)) * hypotenuse);
            newPos.z += adjacent;
            newPos.x -= opposite;
            this.Pos.z = newPos.z;
            this.Pos.x = newPos.x;
        }
	}

	@Override
	public void draw() {
		// draw hand and lantern if holding:
		
	}
}