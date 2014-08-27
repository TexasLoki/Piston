package org.pistonmc.data;

public enum Action {

	CROUCH(1),
	UNCROUCH(2),
	LEAVE_BED(3),
	START_SPRINTING(4),
	STOP_SPRINTING(5),
	HORSE_JUMP(6);

	private int id;

	Action(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public static Action getAction(int id) {
		for (Action action : values()) {
			if (action.getId() == id) {
				return action;
			}
		}

		return null;
	}

}
