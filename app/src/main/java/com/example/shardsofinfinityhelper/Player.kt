package com.example.shardsofinfinityhelper

data class Player(
	val name: String,
	var health: Int = 50,
	var mastery: Int = 0,
	var levelledThisTurn: Boolean = false
 )

fun Player.addHealth(): Int {
	if (health < 50) {
		health += 1
	}
	return health
}

fun Player.subtractHealth(): Int {
	if (health > 0) {
		health -= 1
	}
	return health
}

fun Player.addMastery(): Int {
	if (mastery < 30) {
		mastery += 1
	}
	return mastery
}

fun Player.subtractMastery(): Int {
	if (mastery > 0) {
		mastery -= 1
	}
	return mastery
}