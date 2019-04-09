package ships;
import java.awt.Image;

import acm.graphics.GImage;

public abstract class Ship extends GImage{

	
	public Ship(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}
	
	enum POSE {
		HORIZONTAL, 
		VERTICAL
	}
	
	int length;
	POSE pose;
	boolean isDestroyed;
}
