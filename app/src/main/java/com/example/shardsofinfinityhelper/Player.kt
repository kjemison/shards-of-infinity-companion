package com.example.shardsofinfinityhelper

data class Player(
	val name: String,
	var health: Int = 50,
	var mastery: Int = 0,
	var levelledThisTurn: Boolean = false
 )