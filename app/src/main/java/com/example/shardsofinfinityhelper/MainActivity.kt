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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

val arrowHeightModifier = Modifier
	.height(45.dp)

class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			Players()
		}
	}
}

@Composable
fun HealthIconWithInfo(
	player: Player,
	modifier: Modifier = Modifier,
	icon: @Composable () -> Unit
) {
	var playerHealth by remember {
		mutableStateOf(player.health)
	}

	Column(
		modifier = modifier,
		horizontalAlignment = CenterHorizontally
	) {
		Arrow(R.drawable.baseline_keyboard_arrow_up_24_red) {
			playerHealth = player.addHealth()
		}
		Box(contentAlignment = Alignment.Center) {
			icon()
			PlayerValue(playerHealth)
		}
		Arrow(R.drawable.baseline_keyboard_arrow_down_24_red) {
			playerHealth = player.subtractHealth()
		}
	}
}

@Composable
fun MasteryIconWithInfo(
	player: Player,
	modifier: Modifier = Modifier,
	icon: @Composable () -> Unit
) {
	var playerMastery by remember {
		mutableStateOf(player.mastery)
	}

	Column(
		modifier = modifier,
		horizontalAlignment = CenterHorizontally
	) {
		Arrow(R.drawable.baseline_keyboard_arrow_up_24_red) {
			playerMastery = player.addMastery()
		}
		Box(contentAlignment = Alignment.Center) {
			icon()
			PlayerValue(playerMastery)
		}
		Arrow(R.drawable.baseline_keyboard_arrow_down_24_red) {
			playerMastery = player.subtractMastery()
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
fun HealthIcon(modifier: Modifier = Modifier) {
	Image(
		painter = painterResource(id = R.drawable.shards_of_infinity_hp),
		contentDescription = null,
	)
}

@Composable
fun MasteryIcon(modifier: Modifier = Modifier) {
	Image(
		painter = painterResource(id = R.drawable.shards_of_infinity_mastery),
		contentDescription = null,
	)
}

@Composable
fun Arrow(
	paint: Int,
	modifier: Modifier = Modifier,
	onClick: () -> Unit
) {
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

@Composable
fun CharacterBanner(character: String, characterColor: Int) {
	Row(
		modifier = Modifier
			.padding(start = 16.dp, top = 16.dp, bottom = 0.dp, end = 16.dp)
			.fillMaxWidth()
			.height(40.dp)
			.background(
				color = colorResource(id = characterColor),
				shape = RoundedCornerShape(10.dp)
			)
			.padding(start = 20.dp),
		horizontalArrangement = Arrangement.Start,
		verticalAlignment = CenterVertically
	) {
		Box() {
			Text(
				text = character,
				color = Color.DarkGray,
				fontSize = 25.sp,
				modifier = Modifier
					.offset(
						x = 2.dp,
						y = 2.dp
					)
					.alpha(0.75f)
			)
			Text(
				text = character,
				fontSize = 25.sp,
				color = Color.White
			)
		}
	}
}

@Composable
fun PlayerInfo(player: Player, modifier: Modifier = Modifier) {
	Column(
		modifier = modifier
	) {
		CharacterBanner(
			character = stringResource(id = R.string.rez_name),
			characterColor = R.color.rez_color
		)
		Row(
			modifier = Modifier
				.padding(start = 16.dp, end = 16.dp, top = 8.dp),
			horizontalArrangement = Arrangement.SpaceBetween
		) {
			HealthIconWithInfo(player = player, Modifier.weight(1f)) { HealthIcon() }
			Spacer(modifier = Modifier.width(16.dp))
			MasteryIconWithInfo(player = player, Modifier.weight(1f)) { MasteryIcon() }
		}
		Row(
			horizontalArrangement = Arrangement.Center,
			verticalAlignment = CenterVertically,
			modifier = Modifier
				.align(CenterHorizontally)
				.padding(bottom = 16.dp)
		) {
			var selected by remember { mutableStateOf(false) }

			Checkbox(
				modifier = Modifier
					.scale(1.5f)
					.align(CenterVertically),
				checked = selected,
				onCheckedChange = { selected = it },
				colors = CheckboxDefaults.colors(
					uncheckedColor = Color.White,
					checkedColor = Color.White
				)
			)
			Text(
				modifier = Modifier.align(CenterVertically),
				text = "- Spent Crystal for Mastery?",
				color = Color.White,
				fontSize = 25.sp
			)
		}
	}
}

@Preview
@Composable
fun Players() {
	val player1 = Player("player1")
	val player2 = Player("player2")
	Column(
		modifier = Modifier
			.fillMaxSize()
			.background(color = colorResource(id = R.color.background))
	) {
		PlayerInfo(player = player1, modifier = Modifier.rotate(180f))
		PlayerInfo(player = player2)
	}
}

@Preview(showBackground = true)
@Composable
fun Characters() {
	Column() {
		CharacterBanner(
			character = stringResource(id = R.string.tetra_name),
			characterColor = R.color.tetra_color
		)
		CharacterBanner(
			character = stringResource(id = R.string.decima_name),
			characterColor = R.color.decima_color
		)
		CharacterBanner(
			character = stringResource(id = R.string.ko_syn_wu_name),
			characterColor = R.color.ko_syn_wu_color
		)
		CharacterBanner(
			character = stringResource(id = R.string.volos_name),
			characterColor = R.color.volos_color
		)
		CharacterBanner(
			character = stringResource(id = R.string.rez_name),
			characterColor = R.color.rez_color
		)
	}
}