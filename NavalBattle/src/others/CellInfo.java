package others;

import java.io.Serializable;

import ships.Ship.ShipType;

public class CellInfo implements Serializable {
	private static final long serialVersionUID = -7153573821809305336L;

	private ShipType shipType;

	public CellInfo() {
		shipType = ShipType.NONE;
	}

	public ShipType getShipType() {
		return shipType;
	}

	public void setShipType(ShipType shipType) {
		this.shipType = shipType;
	}
}
