package ships;

import acm.graphics.GImage;
import others.BattleField;

public abstract class Ship extends GImage {
	int len_h, len_v;
	POSE pose;

	String imageName;
	boolean isFinal;
	boolean isDestroyed;

	enum POSE {
		HORIZONTAL, VERTICAL
	}

	public Ship(String name) {
		super(name);
		imageName = name;
		pose = POSE.VERTICAL;
		
	}

	protected void normalize() {
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

	public POSE getPose() {
		return pose;
	}

	public boolean isFinal() {
		return isFinal;
	}

	public void setFinal(boolean isFinal) {
		this.isFinal = isFinal;
	}

}
