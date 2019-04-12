package ships;

import java.awt.Image;

import acm.graphics.GImage;
import others.BattleField;

public abstract class Ship extends GImage {
	int len_h, len_v;
	POSE pose;
	boolean isDestroyed;
	String imageName;
	boolean isFinal;

	enum POSE {
		HORIZONTAL, VERTICAL
	}

	public Ship(String name) {
		super(name);
		imageName = name;
		pose = POSE.VERTICAL;
		
	}

	protected void normalize() {
		System.out.println(len_h + "" + len_v);
		setSize(BattleField.SIDE*len_h, BattleField.SIDE*len_v);
	}
	
	public void rotate() {
		int temp = len_h;
		len_h = len_v;
		len_v = temp;
		
		if (pose == POSE.VERTICAL) {
			imageName = imageName.replace("_v", "_h");

			pose = POSE.HORIZONTAL;
		} else {
			imageName = imageName.replace("_h", "_v");

			pose = POSE.VERTICAL;
		}
		
		setImage(imageName);
		normalize();
	}

	public int getLen_h() {
		return len_h;
	}

	public int getLen_v() {
		return len_v;
	}

	public boolean isFinal() {
		return isFinal;
	}

	public void setFinal(boolean isFinal) {
		this.isFinal = isFinal;
	}

}
