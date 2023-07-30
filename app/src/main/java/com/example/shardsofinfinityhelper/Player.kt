package com.example.shardsofinfinityhelper

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class Player(
	var character: Character? = null,
	var health: Int = 50,
	var mastery: Int = 0
 ) {
	var characterState by mutableStateOf(character)
	private var playerHealth by mutableStateOf(health)
	private var playerMastery by mutableStateOf(mastery)

	fun addHealth(): Int {
		if (playerHealth < 50) {
			playerHealth += 1
		}
		return playerHealth
	}

	fun subtractHealth(): Int {
		if (playerHealth > 0) {
			playerHealth -= 1
		}
		return playerHealth
	}

	fun addMastery(): Int {
		if (playerMastery < 30) {
			playerMastery += 1
		}
		return playerMastery
	}

	fun subtractMastery(): Int {
		if (playerMastery > 0) {
			playerMastery -= 1
		}
		return playerMastery
	}
}