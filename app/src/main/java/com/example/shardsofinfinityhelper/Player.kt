package com.example.shardsofinfinityhelper

data class Player(
	var health: Int = 50,
	var mastery: Int = 0,
	var levelledThisTurn: Boolean = false
) {
	fun upHealth() {
		if (health == 50) {
			return
		}
		health++
	}

	fun downHealth() {
		if (health == 0) {
			return
		}
		health--
	}

	fun upMastery() {
		if (mastery == 50) {
			return
		}
		mastery++
	}

	fun downMastery() {
		if (mastery == 0) {
			return
		}
		mastery--
	}
}
