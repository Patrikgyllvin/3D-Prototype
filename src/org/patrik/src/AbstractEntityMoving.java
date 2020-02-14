package org.patrik.src;

import org.lwjgl.util.vector.Vector3f;

public abstract class AbstractEntityMoving extends AbstractEntity implements EntityMoving {
	
	private float dx, dy, dz, rotdx, rotdy, rotdz;
	
	public AbstractEntityMoving(Vector3f Pos) {
		super(Pos);
	}
	
	public void update(int delta) {
		Pos.setX((Pos.getX() + dx) * delta);
		Pos.setY((Pos.getY() + dy) * delta);
		Pos.setZ((Pos.getZ() + dz) * delta);
		
		Rot.setX((Rot.getX() + rotdx) * delta);
		Rot.setY((Rot.getY() + rotdy) * delta);
		Rot.setZ((Rot.getZ() + rotdz) * delta);
	}
	
	public void setDX(float dx) {
		this.dx = dx;
	}
	
	public void setDY(float dy) {
		this.dy = dy;
	}
	
	public void setDZ(float dz) {
		this.dz = dz;
	}
	
	public float getDX() {
		return this.dx;
	}
	
	public float getDY() {
		return this.dy;
	}
	
	public float getDZ() {
		return this.dz;
	}
	
	public void setRotDX(float rotdx) {
		this.rotdx = rotdx;
	}
	
	public void setRotDY(float rotdy) {
		this.rotdy = rotdy;
	}
	
	public void setRotDZ(float rotdz) {
		this.rotdz = rotdz;
	}
	
	public float getRotDX() {
		return this.rotdx;
	}
	
	public float getRotDY() {
		return this.rotdy;
	}
	
	public float getRotDZ() {
		return this.rotdz;
	}
}