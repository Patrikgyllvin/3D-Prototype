package org.patrik.src;

public interface EntityMoving extends Entity {
	public void update(int delta);
	
	public void setDX(float dx);
	public void setDY(float dy);
	public void setDZ(float dz);
	
	public void setRotDX(float rotdx);
	public void setRotDY(float rotdy);
	public void setRotDZ(float rotdz);
	
	public float getDX();
	public float getDY();
	public float getDZ();
	
	public float getRotDX();
	public float getRotDY();
	public float getRotDZ();
}