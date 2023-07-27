package com.example.shardsofinfinityhelper

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxColors
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

const val iconSize: Int = 180
val arrowHeightModifier = Modifier
	.height(45.dp)

class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			PlayerScreen()
		}
	}
}

@Composable
fun PlayerScreen() {
	Column(
		verticalArrangement = Arrangement.SpaceBetween,
		modifier = Modifier
			.fillMaxSize()
			.background(color = colorResource(id = R.color.background))
	) {
		val player1 = Player("player1")
		val player2 = Player("player2")

		PlayerArea(player = player1, modifier = Modifier.rotate(180f))
		PlayerArea(player = player2)
	}
}

@Composable
fun PlayerArea(player: Player, modifier: Modifier = Modifier) {
	Column(
		modifier = modifier,
		verticalArrangement = Arrangement.Center
	) {
		Row(
			horizontalArrangement = Arrangement.SpaceEvenly,
			modifier = Modifier
				.height(300.dp)
				.fillMaxWidth()
		) {
			HealthIconWithInfo(player = player, modifier)
			MasteryIconWithInfo(player = player, modifier)

		}
		Row(
			modifier = modifier
				.fillMaxWidth()
				.padding(top = 20.dp, bottom = 20.dp),
			horizontalArrangement = Arrangement.Center
		) {
			var selected by remember { mutableStateOf(false) }
			Checkbox(
				modifier = modifier
					.scale(1.5f)
					.align(CenterVertically),
				checked = selected,
				onCheckedChange = { selected = it },
				colors = CheckboxDefaults.colors(
					uncheckedColor = Color.White,
					checkedColor = Color.White
				)
			)
			Spacer(modifier = Modifier.width(30.dp))
			Text(
				modifier = modifier.align(CenterVertically),
				text = "Mastery gained?",
				color = Color.White,
				fontSize = 25.sp
			)
		}
	}
}

@Composable
fun PlayerValue(value: Int, modifier: Modifier = Modifier) {
	Text(
		text = "$value",
		fontSize = 50.sp,
		modifier = modifier
	)
}

@Composable
fun HealthIconWithInfo(player: Player, modifier: Modifier = Modifier) {
	var playerHealth by remember {
		mutableStateOf(player.health)
	}
	Column(
		modifier = modifier
			.width(200.dp)
			.fillMaxHeight(),
		horizontalAlignment = Alignment.CenterHorizontally
	) {
		Arrow(R.drawable.baseline_keyboard_arrow_up_24_red) {
			if (player.name == "player1") {
				if (playerHealth != 0) playerHealth -= 1
			} else {
				if (playerHealth != 50) playerHealth += 1
			}
		}
		Box {
			HealthIcon()
			if (player.name == "player1") {
				PlayerValue(
					playerHealth,
					Modifier
						.align(Alignment.Center)
						.rotate(180f)
				)
			} else {
				PlayerValue(playerHealth, modifier.align(Alignment.Center))
			}
		}
		Arrow(R.drawable.baseline_keyboard_arrow_down_24_red) {
			if (player.name == "player1") {
				if (playerHealth != 50) playerHealth += 1
			} else {
				if (playerHealth != 0) playerHealth -= 1
			}
		}
	}
}

@Composable
fun MasteryIconWithInfo(player: Player, modifier: Modifier = Modifier) {
	var playerMastery by remember {
		mutableStateOf(player.mastery)
	}
	Column(
		modifier = modifier
			.width(200.dp)
			.fillMaxHeight(),
		horizontalAlignment = Alignment.CenterHorizontally
	)
	{
		Arrow(R.drawable.baseline_keyboard_arrow_up_24_red) {
			if (player.name == "player1") {
				if (playerMastery != 0) playerMastery -= 1
			} else {
				if (playerMastery != 30) playerMastery += 1
			}
		}
		Box {
			MasteryIcon()
			if (player.name == "player1") {
				PlayerValue(
					playerMastery,
					Modifier
						.align(Alignment.Center)
						.rotate(180f))
			} else {
				PlayerValue(playerMastery, modifier.align(Alignment.Center))
			}
		}
		Arrow(R.drawable.baseline_keyboard_arrow_down_24_red) {
			if (player.name == "player1") {
				if (playerMastery != 30) playerMastery += 1
			} else {
				if (playerMastery != 0) playerMastery -= 1
			}
		}
	}
}

@Composable
fun HealthIcon(modifier: Modifier = Modifier) {
	Image(
		painter = painterResource(id = R.drawable.shards_of_infinity_hp),
		contentDescription = null,
		modifier.size(iconSize.dp)
	)
}

@Composable
fun MasteryIcon(modifier: Modifier = Modifier) {
	Image(
		painter = painterResource(id = R.drawable.shards_of_infinity_mastery),
		contentDescription = null,
		modifier.size(iconSize.dp)
	)
}

@Composable
fun Arrow(paint: Int, modifier: Modifier = Modifier, onClick: () -> Unit) {
	Image(
		painter = painterResource(id = paint),
		contentDescription = null,
		modifier = arrowHeightModifier
			.fillMaxWidth()
			.clickable {
				onClick()
			}
	)
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
	PlayerScreen()
}