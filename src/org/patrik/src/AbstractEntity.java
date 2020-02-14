package org.patrik.src;

import org.lwjgl.util.vector.Vector3f;

public abstract class AbstractEntity implements Entity {
	
	Vector3f Pos = new Vector3f();
	Vector3f Rot = new Vector3f();
	
	public AbstractEntity(Vector3f pos) {
		this.Pos.set(pos);
	}

	@Override
	public void setPos(Vector3f pos) {
		this.Pos.set(pos);
	}

	@Override
	public void setX(float x) {
		this.Pos.setX(x);
	}

	@Override
	public void setY(float y) {
		this.Pos.setY(y);
	}
	
	@Override
	public void setZ(float z) {
		this.Pos.setZ(z);
	}

	@Override
	public Vector3f getPos() {
		return Pos;
	}

	@Override
	public float getX() {
		return Pos.getX();
	}

	@Override
	public float getY() {
		return Pos.getY();
	}

	@Override
	public float getZ() {
		return Pos.getZ();
	}
	
	public void setRot(Vector3f rot) {
		this.Rot.set(rot);
	}
	
	public void setRotX(float rotx) {
		this.Rot.setX(rotx);
	}
	
	public void setRotY(float roty) {
		this.Rot.setY(roty);
	}
	
	public void setRotZ(float rotz) {
		this.Rot.setZ(rotz);
	}
	
	public Vector3f getRot() {
		return Rot;
	}
	
	public float getRotX() {
		return Rot.getX();
	}
	
	public float getRotY() {
		return Rot.getY();
	}
	
	public float getRotZ() {
		return Rot.getZ();
	}
}