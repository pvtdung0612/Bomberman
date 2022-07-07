package com.dtanh.bomb.model.ai;

import com.dtanh.bomb.model.Player;
import com.dtanh.bomb.model.Boss;
import static com.dtanh.bomb.model.MapItem.SIZE;

public class AIMedium extends AI {
	Player _player;
	Boss _b;
	int orient = 0;
	int distance_check = 3 * SIZE; // pixel
	int first_check = 0;
	int random_add_time = random.nextInt(10) * 100;
	long start_time_delay = System.currentTimeMillis() + random_add_time;

	public AIMedium(Player player, Boss e) {
		_player = player;
		_b = e;
	}

	// LEFT = 0;
//		 RIGHT = 1;
//		 UP = 2;
//		 DOWN = 3;

	@Override
	public int calculateDirection() {
		if (_player != null && Math.abs(_player.getX() - _b.getX()) < distance_check && Math.abs(_player.getY() - _b.getY()) < distance_check) {
			distance_check = 6 * SIZE;

			if (System.currentTimeMillis() + random_add_time - start_time_delay > 2000) {
				start_time_delay = System.currentTimeMillis() + random_add_time;
				first_check = (++first_check) % 4;
			}

			if (first_check == 0) orient = first_check_left();
			else if (first_check == 1) orient = first_check_right();
			else if (first_check == 2) orient = first_check_up();
			else if (first_check == 3) orient = first_check_down();
		}
		else {
			distance_check = 3 * SIZE;
			int percent = random.nextInt(100);
			if (percent > 95) {
				orient = random.nextInt(4);
			}
		}

		return orient;
	}

	private int first_check_left() {
		if (_player.getX() < _b.getX()) return 0;
		else if (_player.getX() > _b.getX()) return 1;
		else if (_player.getY() < _b.getY()) return 2;
		else if (_player.getY() > _b.getY()) return 3;
		return -1;
	}
	private int first_check_right() {
		if (_player.getX() > _b.getX()) return 1;
		else if (_player.getX() < _b.getX()) return 0;
		else if (_player.getY() < _b.getY()) return 2;
		else if (_player.getY() > _b.getY()) return 3;
		return -1;
	}
	private int first_check_up() {
		if (_player.getY() < _b.getY()) return 2;
		else if (_player.getX() < _b.getX()) return 0;
		else if (_player.getX() > _b.getX()) return 1;
		else if (_player.getY() > _b.getY()) return 3;
		return -1;
	}
	private int first_check_down() {
		if (_player.getY() > _b.getY()) return 3;
		else if (_player.getX() < _b.getX()) return 0;
		else if (_player.getX() > _b.getX()) return 1;
		else if (_player.getY() < _b.getY()) return 2;
		return -1;
	}
}