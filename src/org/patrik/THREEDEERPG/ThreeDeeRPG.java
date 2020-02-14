package org.patrik.THREEDEERPG;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.FloatBuffer;

import javax.swing.plaf.synth.Region;

import org.lwjgl.*;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.*;
import org.lwjgl.util.vector.Vector3f;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.util.glu.GLU.*;

class ThreeDeeRPG {
	
	/**
	 * Time since the last frame
	 */
	private long lastFrame;
	
	/**
	 * set the field of view
	 */
	private static final float FOV = 68;
	
	/**
	 * determine if an object is too close to our eye to be seen
	 */
	private static final float zNear = 0.003f;
	
	/**
	 * determine if an object is too far away for our eye to be rendered
	 */
	private static final float zFar = 100;
	
	public Vector3f Pos = new Vector3f(0, 0, 0);
	public Vector3f Rot = new Vector3f(0, 0, 0);
	
	/**
	 * sets the players current walking speed
	 */
	public int walkingSpeed = 5;
	
	/**
	 * the current speed of the mouse look
	 */
	public int mouseSpeed = 1;
	
	/**
	 * the max amount the player can look up the x axis
	 */
	public static final int maxLookUp = 85;
	
	/**
	 * the max amount the player can look down the x axis
	 */
	public static final int maxLookDown = -85;
	
	/* DisplayLists ETC. */
	
	int objectDisplayList;
	int objectsDisplayList;
	
	/* DisplayLists ETC. */
	
	private long getTime() {
		return (Sys.getTime() * 1000) / Sys.getTimerResolution();
	}
	
	private int getDelta() {
		long currentTime = getTime();
		int delta = (int) (currentTime - lastFrame);
		lastFrame = getTime();
		return delta;
	}
	
	private void initOpenGL() {
		glMatrixMode(GL_PROJECTION);
	    glLoadIdentity();
	    
	    gluPerspective(FOV, (float) Display.getWidth() / (float) Display.getHeight(), zNear, zFar);
	    
	    glMatrixMode(GL_MODELVIEW);
	    glLoadIdentity();
	    
	    glShadeModel(GL_SMOOTH);
	    glEnable(GL_DEPTH_TEST);
	    glEnable(GL_LIGHTING);
	    glEnable(GL_LIGHT0);
	    glEnable(GL_LIGHT1);
	    glEnable(GL_LIGHT2);
	    glLightModel(GL_LIGHT_MODEL_AMBIENT, asFloatBuffer(new float[]{0.05f, 0.05f, 0.05f, 1f}));
	    glLight(GL_LIGHT0, GL_DIFFUSE, asFloatBuffer(new float[]{1.5f, 1.5f, 1.5f, 1f}));
	    glLight(GL_LIGHT1, GL_DIFFUSE, asFloatBuffer(new float[]{1.5f, 1.5f, 1.5f, 1f}));
	    glLight(GL_LIGHT2, GL_DIFFUSE, asFloatBuffer(new float[]{1.5f, 1.5f, 1.5f, 1f}));
	    glEnable(GL_CULL_FACE);
	    glCullFace(GL_BACK);
	    glEnable(GL_COLOR_MATERIAL);
	    glColorMaterial(GL_FRONT, GL_DIFFUSE);
	}
	
	private void initDisplayLists() {
		objectDisplayList = glGenLists(1);
		
	    glNewList(objectDisplayList, GL_COMPILE);
	    {
	    	ModelLoader m = null;
	    	try {
	    		m = OBJLoader.loadModel(new File("res/baron58.obj"));
	    	} catch (FileNotFoundException e) {
	    		e.printStackTrace();
	    		Display.destroy();
	    		System.exit(1);
	    	} catch (IOException e) {
	    		e.printStackTrace();
	    		Display.destroy();
	    		System.exit(1);
	    	}
	    	glColor3f(1, 0.3f, 0.3f);
	    	glBegin(GL_TRIANGLES);
	    	for (Face face : m.faces) {
                Vector3f n1 = m.normals.get((int) face.normal.x - 1);
                glNormal3f(n1.x, n1.y, n1.z);
                Vector3f v1 = m.vertices.get((int) face.vertex.x - 1);
                glVertex3f(v1.x, v1.y, v1.z);
                Vector3f n2 = m.normals.get((int) face.normal.y - 1);
                glNormal3f(n2.x, n2.y, n2.z);
                Vector3f v2 = m.vertices.get((int) face.vertex.y - 1);
                glVertex3f(v2.x, v2.y, v2.z);
                Vector3f n3 = m.normals.get((int) face.normal.z - 1);
                glNormal3f(n3.x, n3.y, n3.z);
                Vector3f v3 = m.vertices.get((int) face.vertex.z - 1);
                glVertex3f(v3.x, v3.y, v3.z);
            }
	    	glEnd();
	    }
	    glEndList();
	    
	    objectsDisplayList = glGenLists(1);
	    
	    glNewList(objectsDisplayList, GL_COMPILE);
	    {
	    	ModelLoader m = null;
	    	try {
	    		m = OBJLoader.loadModel(new File("res/untitled.obj"));
	    	} catch (FileNotFoundException e) {
	    		e.printStackTrace();
	    		Display.destroy();
	    		System.exit(1);
	    	} catch (IOException e) {
	    		e.printStackTrace();
	    		Display.destroy();
	    		System.exit(1);
	    	}
	    	glColor3f(1, 1, 1);
	    	glBegin(GL_TRIANGLES);
	    	for (Face face : m.faces ) {
                Vector3f n1 = m.normals.get((int) face.normal.x - 1);
                glNormal3f(n1.x, n1.y, n1.z);
                Vector3f v1 = m.vertices.get((int) face.vertex.x - 1);
                glVertex3f(v1.x, v1.y, v1.z);
                Vector3f n2 = m.normals.get((int) face.normal.y - 1);
                glNormal3f(n2.x, n2.y, n2.z);
                Vector3f v2 = m.vertices.get((int) face.vertex.y - 1);
                glVertex3f(v2.x, v2.y, v2.z);
                Vector3f n3 = m.normals.get((int) face.normal.z - 1);
                glNormal3f(n3.x, n3.y, n3.z);
                Vector3f v3 = m.vertices.get((int) face.vertex.z - 1);
                glVertex3f(v3.x, v3.y, v3.z);
            }
	    	glEnd();
	    }
	    glEndList();
	}
	
	public ThreeDeeRPG() throws LWJGLException {
		Display.setDisplayMode(Display.getDesktopDisplayMode());
		Display.setTitle("3D RPG");
		Display.create();
		Mouse.setGrabbed(true);
		
		initOpenGL();
		
	    lastFrame = getTime();
	    
	    initDisplayLists();
	    
	    
		while(!Display.isCloseRequested()) {
			
			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
			
			int delta = getDelta();
			
			glLoadIdentity();
			glRotatef(Rot.x, 1, 0, 0);
			glRotatef(Rot.y, 0, 1, 0);
			glRotatef(Rot.z, 0, 0, 1);
			
			glTranslatef(Pos.x, Pos.y, Pos.z);
			
			glPushMatrix();
			glTranslatef(Pos.x, Pos.y - 10, Pos.z);
			glCallList(objectsDisplayList);
			glCallList(objectDisplayList);
			glPopMatrix();
			
			glLight(GL_LIGHT0, GL_POSITION, asFloatBuffer(new float[]{-10, 0, 0, 1f}));
			glLight(GL_LIGHT1, GL_POSITION, asFloatBuffer(new float[]{-10, 15, 0, 1f}));
			glLight(GL_LIGHT2, GL_POSITION, asFloatBuffer(new float[]{-10, 30, 0, 1f}));
			
			while (Keyboard.next()) {
				if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
					Mouse.setGrabbed(!Mouse.isGrabbed());
				}
			}
			
			if (Mouse.isGrabbed()) {
				float mouseDX = Mouse.getDX() * mouseSpeed * 0.16f;
				float mouseDY = Mouse.getDY() * mouseSpeed * 0.16f;
				
				if (Rot.y + mouseDX >= 360) {
					Rot.y = Rot.y + mouseDX - 360;
                } else if (Rot.y + mouseDX < 0) {
                	Rot.y = 360 - Rot.y + mouseDX;
                } else {
                	Rot.y += mouseDX;
                }
                if (Rot.x - mouseDY >= maxLookDown && Rot.x - mouseDY <= maxLookUp) {
                	Rot.x += -mouseDY;
                } else if (Rot.x - mouseDY < maxLookDown) {
                	Rot.x = maxLookDown;
                } else if (Rot.x - mouseDY > maxLookUp) {
                	Rot.x = maxLookUp;
                }
			}
			
			boolean keyUp = Keyboard.isKeyDown(Keyboard.KEY_W);
			boolean keyDown = Keyboard.isKeyDown(Keyboard.KEY_S);
			boolean keyLeft = Keyboard.isKeyDown(Keyboard.KEY_A);
			boolean keyRight = Keyboard.isKeyDown(Keyboard.KEY_D);
			boolean keyFlyUp = Keyboard.isKeyDown(Keyboard.KEY_SPACE);
			boolean keyFlyDown = Keyboard.isKeyDown(Keyboard.KEY_LSHIFT);
			boolean keyRun = Keyboard.isKeyDown(Keyboard.KEY_LCONTROL);
			
			// walking
			if (keyUp && keyRight && !keyLeft && !keyDown) {
                float angle = Rot.y + 45;
                Vector3f newPos = new Vector3f(Pos);
                float hypotenuse = (walkingSpeed * 0.0002f) * delta;
                float adjacent = hypotenuse * (float) Math.cos(Math.toRadians(angle));
                float opposite = (float) (Math.sin(Math.toRadians(angle)) * hypotenuse);
                newPos.z += adjacent;
                newPos.x -= opposite;
                Pos.z = newPos.z;
                Pos.x = newPos.x;
            }
		 
            if (keyUp && keyLeft && !keyRight && !keyDown) {
                float angle = Rot.y - 45;
                Vector3f newPos = new Vector3f(Pos);
                float hypotenuse = (walkingSpeed * 0.0002f) * delta;
                float adjacent = hypotenuse * (float) Math.cos(Math.toRadians(angle));
                float opposite = (float) (Math.sin(Math.toRadians(angle)) * hypotenuse);
                newPos.z += adjacent;
                newPos.x -= opposite;
                Pos.z = newPos.z;
                Pos.x = newPos.x;
            }
            
            if (keyUp && !keyLeft && !keyRight && !keyDown) {
                float angle = Rot.y;
                Vector3f newPos = new Vector3f(Pos);
                float hypotenuse = (walkingSpeed * 0.0002f) * delta;
                float adjacent = hypotenuse * (float) Math.cos(Math.toRadians(angle));
                float opposite = (float) (Math.sin(Math.toRadians(angle)) * hypotenuse);
                newPos.z += adjacent;
                newPos.x -= opposite;
                Pos.z = newPos.z;
                Pos.x = newPos.x;
            }
            
            if (keyDown && keyLeft && !keyRight && !keyUp) {
                float angle = Rot.y - 135;
                Vector3f newPos = new Vector3f(Pos);
                float hypotenuse = (walkingSpeed * 0.0002f) * delta;
                float adjacent = hypotenuse * (float) Math.cos(Math.toRadians(angle));
                float opposite = (float) (Math.sin(Math.toRadians(angle)) * hypotenuse);
                newPos.z += adjacent;
                newPos.x -= opposite;
                Pos.z = newPos.z;
                Pos.x = newPos.x;
            }
            
            if (keyDown && keyRight && !keyLeft && !keyUp) {
                float angle = Rot.y + 135;
                Vector3f newPos = new Vector3f(Pos);
                float hypotenuse = (walkingSpeed * 0.0002f) * delta;
                float adjacent = hypotenuse * (float) Math.cos(Math.toRadians(angle));
                float opposite = (float) (Math.sin(Math.toRadians(angle)) * hypotenuse);
                newPos.z += adjacent;
                newPos.x -= opposite;
                Pos.z = newPos.z;
                Pos.x = newPos.x;
            }
            
            if (keyDown && !keyUp && !keyLeft && !keyRight) {
                float angle = Rot.y;
                Vector3f newPos = new Vector3f(Pos);
                float hypotenuse = -(walkingSpeed * 0.0002f) * delta;
                float adjacent = hypotenuse * (float) Math.cos(Math.toRadians(angle));
                float opposite = (float) (Math.sin(Math.toRadians(angle)) * hypotenuse);
                newPos.z += adjacent;
                newPos.x -= opposite;
                Pos.z = newPos.z;
                Pos.x = newPos.x;
            }
            
            if (keyLeft && !keyRight && !keyUp && !keyDown) {
                float angle = Rot.y - 90;
                Vector3f newPos = new Vector3f(Pos);
                float hypotenuse = (walkingSpeed * 0.0002f) * delta;
                float adjacent = hypotenuse * (float) Math.cos(Math.toRadians(angle));
                float opposite = (float) (Math.sin(Math.toRadians(angle)) * hypotenuse);
                newPos.z += adjacent;
                newPos.x -= opposite;
                Pos.z = newPos.z;
                Pos.x = newPos.x;
            }
            
            if (keyRight && !keyLeft && !keyUp && !keyDown) {
                float angle = Rot.y + 90;
                Vector3f newPos = new Vector3f(Pos);
                float hypotenuse = (walkingSpeed * 0.0002f) * delta;
                float adjacent = hypotenuse * (float) Math.cos(Math.toRadians(angle));
                float opposite = (float) (Math.sin(Math.toRadians(angle)) * hypotenuse);
                newPos.z += adjacent;
                newPos.x -= opposite;
                Pos.z = newPos.z;
                Pos.x = newPos.x;
            }
            
	        // flying
	        if (keyFlyUp && !keyFlyDown) {
	            double newPosY = (walkingSpeed * 0.0002f) * delta;
	            Pos.y -= newPosY;
	        }
	        
	        if (keyFlyDown && !keyFlyUp) {
	            double newPosY = (walkingSpeed * 0.0002f) * delta;
	            Pos.y += newPosY;
	        }
	        
	        // sprinting
	        if (keyRun) {
	        	walkingSpeed = 30;
	        } else {
	        	walkingSpeed = 5;
	        }
	        
			Display.update();
			Display.sync(60);
		}
		
		glDeleteLists(objectDisplayList, 1);
		
		Display.destroy();
		System.exit(0);
	}
	
	private static FloatBuffer asFloatBuffer(float[] values) {
		FloatBuffer buffer = BufferUtils.createFloatBuffer(values.length);
		buffer.put(values);
		buffer.flip();
		return buffer;
	}
	
	public static void main(String[] args) throws LWJGLException {
		new ThreeDeeRPG();
	}
}