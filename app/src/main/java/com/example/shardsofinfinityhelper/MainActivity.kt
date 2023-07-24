package com.example.shardsofinfinityhelper

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

val iconSizeModifier = Modifier
	.size(180.dp)
val arrowHeightModifier = Modifier
	.height(45.dp)

val player1 = Player()
val player2 = Player()

class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			PlayerScreen(player = player1)
			PlayerScreen(player = player2)
		}
	}
}

@Composable
fun PlayerScreen(player: Player) {
	Column(modifier = Modifier.fillMaxSize()) {
		Row(modifier = Modifier
			.height(300.dp)
			.fillMaxWidth(),
			horizontalArrangement = Arrangement.SpaceEvenly
		) {
			Column(
				modifier = Modifier
					.width(200.dp)
					.fillMaxHeight(),
				horizontalAlignment = Alignment.CenterHorizontally
			) {
				UpArrow(player, "Health")
				Box {
					Image(
						painter = painterResource(id = R.drawable.shards_of_infinity_hp),
						contentDescription = null,
						modifier = iconSizeModifier
					)
					Text(
						text = "${player.health}",
						fontSize = 50.sp,
						modifier = Modifier.align(Alignment.Center)
					)
				}
				DownArrow(player, "Health")
			}
			Column(
				modifier = Modifier.fillMaxHeight(),
				horizontalAlignment = Alignment.CenterHorizontally)
			{
				UpArrow(player, "Mastery")
				Box {
					Image(
						painter = painterResource(id = R.drawable.shards_of_infinity_mastery),
						contentDescription = null,
						modifier = iconSizeModifier
					)
					Text(
						text = "${player.mastery}",
						fontSize = 50.sp,
						modifier = Modifier.align(Alignment.Center)
					)
				}
				DownArrow(player, "Mastery")
			}
		}
	}
}

@Composable
fun UpArrow(player: Player, actionType: String) {
	Image(
		painter = painterResource(id = R.drawable.baseline_keyboard_arrow_up_24),
		contentDescription = null,
		modifier = arrowHeightModifier
			.fillMaxWidth()
			.clickable {
				when (actionType) {
					"Health" -> player.upHealth()
					"Mastery" -> player.upMastery()
				}
			}
	)
}

@Composable
fun DownArrow(player: Player, actionType: String) {
	Image(
		painter = painterResource(id = R.drawable.baseline_keyboard_arrow_down_24),
		contentDescription = null,
		modifier = arrowHeightModifier
			.fillMaxWidth()
			.clickable {
				when (actionType) {
					"Health" -> player.downHealth()
					"Mastery" -> player.downMastery()
				}
			}
	)
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
	PlayerScreen(player1)
}