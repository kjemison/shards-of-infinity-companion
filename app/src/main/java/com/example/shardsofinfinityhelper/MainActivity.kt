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
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat

val arrowHeightModifier = Modifier
	.height(45.dp)

val charactersList = listOf<Character>(
	Character(
		name = R.string.tetra_name.toString(),
		nameResource = R.string.tetra_name,
		colorResource = R.color.tetra_color
	),
	Character(
		name = R.string.decima_name.toString(),
		nameResource = R.string.decima_name,
		colorResource = R.color.decima_color
	),
	Character(
		name = R.string.ko_syn_wu_name.toString(),
		nameResource = R.string.ko_syn_wu_name,
		colorResource = R.color.ko_syn_wu_color
	),
	Character(
		name = R.string.volos_name.toString(),
		nameResource = R.string.volos_name,
		colorResource = R.color.volos_color
	),
	Character(
		name = R.string.rez_name.toString(),
		nameResource = R.string.rez_name,
		colorResource = R.color.rez_color
	)
)

class MainActivity : ComponentActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			val player1 = Player()
			val player2 = Player()

			Column(modifier = Modifier
				.fillMaxSize()
				.background(color = colorResource(id = R.color.background)),
				verticalArrangement = Arrangement.SpaceEvenly
			) {
				val charactersListState = remember {
					mutableStateListOf(
						Character(
							name = R.string.tetra_name.toString(),
							nameResource = R.string.tetra_name,
							colorResource = R.color.tetra_color
						),
						Character(
							name = R.string.decima_name.toString(),
							nameResource = R.string.decima_name,
							colorResource = R.color.decima_color
						),
						Character(
							name = R.string.ko_syn_wu_name.toString(),
							nameResource = R.string.ko_syn_wu_name,
							colorResource = R.color.ko_syn_wu_color
						),
						Character(
							name = R.string.volos_name.toString(),
							nameResource = R.string.volos_name,
							colorResource = R.color.volos_color
						),
						Character(
							name = R.string.rez_name.toString(),
							nameResource = R.string.rez_name,
							colorResource = R.color.rez_color
						)
					)
				}

				PlayerArea(player1, modifier = Modifier.rotate(180f), charactersListState)
				PlayerArea(player2, modifier = Modifier, charactersListState)
			}
		}

		val windowInsetsController = WindowCompat.getInsetsController(window, window.decorView)
		// Configure the behavior of the hidden system bars.
		windowInsetsController.systemBarsBehavior =
			WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE

		window.decorView.setOnApplyWindowInsetsListener { view, windowInsets ->
			if (windowInsets.isVisible(WindowInsetsCompat.Type.navigationBars())
				|| windowInsets.isVisible(WindowInsetsCompat.Type.statusBars()))
			{
				windowInsetsController.hide(WindowInsetsCompat.Type.systemBars())
			}

			view.onApplyWindowInsets(windowInsets)
		}

//		// Hide top action bar
//		actionBar?.hide()
//		WindowCompat.setDecorFitsSystemWindows(window, false)
//		window.insetsController?.apply {
//			hide(WindowInsets.Type.statusBars())
//			systemBarsBehavior = WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
//		}
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
fun CharacterBanner(
	characterName: String,
	characterColor: Int,
	modifier: Modifier = Modifier,
	onClick: () -> Unit
) {
	Row(
		modifier = modifier
			.clickable {
				onClick()
			}
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
		Box {
			Text(
				text = characterName,
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
				text = characterName,
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
		player.characterState?.let {
			CharacterBanner(
				characterName = stringResource(id = it.nameResource),
				characterColor = it.colorResource,
				modifier = Modifier.padding(16.dp),
				onClick = {}
			)
		}
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
				.padding(bottom = 24.dp)
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

@Composable
fun PlayerArea(
	player: Player,
	modifier: Modifier = Modifier,
	characterListState: SnapshotStateList<Character>
) {
	Column(
		modifier = modifier
			.background(color = colorResource(id = R.color.background)),
		verticalArrangement = Arrangement.SpaceBetween,
	) {
		if (player.characterState == null) {
			ChooseCharacters(modifier = Modifier, player, characterListState)
		} else {
			PlayerInfo(player = player, modifier = Modifier)
		}
	}
}

@Composable
fun ChooseCharacters(
	modifier: Modifier = Modifier,
	player: Player = Player(),
	characterListState: SnapshotStateList<Character>
){
	Column(
		horizontalAlignment = CenterHorizontally,
		modifier = modifier
			.background(color = colorResource(id = R.color.background))
			.padding(16.dp),
		verticalArrangement = Arrangement.spacedBy(16.dp)
	) {
		Text(
			color = Color.White,
			text = "Pick a character: ",
			fontSize = 40.sp,
			modifier = Modifier.padding(16.dp)
		)

		for (i in 0 until characterListState.size) {
			CharacterBanner(
				characterName = stringResource(id = characterListState[i].nameResource),
				characterColor = characterListState[i].colorResource,
				onClick = {
					player.characterState = characterListState[i]
					characterListState.removeAt(i)
				}
			)
		}
	}
}