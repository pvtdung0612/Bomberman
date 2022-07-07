package com.dtanh.bomb.model.ai;

public class AILow extends AI {
	int orient = 0;

	@Override
	public int calculateDirection() {
		int percent = random.nextInt(100);
		if (percent > 95) {
			orient = random.nextInt(4);
		}
		return orient;
	}

}
