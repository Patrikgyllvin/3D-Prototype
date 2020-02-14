package org.patrik.src;

import org.lwjgl.util.vector.Vector3f;

public interface Entity {
	public void draw();
	
	// setters
	public void setPos(Vector3f pos);
	public void setX(float x);
	public void setY(float y);
	public void setZ(float z);
	
	public void setRot(Vector3f rot);
	public void setRotX(float rotx);
	public void setRotY(float roty);
	public void setRotZ(float rotz);
	
	// getters
	public Vector3f getPos();
	public float getX();
	public float getY();
	public float getZ();
	
	public Vector3f getRot();
	public float getRotX();
	public float getRotY();
	public float getRotZ();
}