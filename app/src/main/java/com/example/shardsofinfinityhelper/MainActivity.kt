package com.example.shardsofinfinityhelper

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.shardsofinfinityhelper.ui.theme.ShardsOfInfinityHelperTheme

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
			PlayerScreen()
		}
	}
}

@Composable
fun PlayerScreen() {
	Column(modifier = Modifier.fillMaxSize()) {
		Row(modifier = Modifier
			.height(300.dp)
			.fillMaxWidth(),
			horizontalArrangement = Arrangement.SpaceEvenly
		) {
			Column(
				modifier = Modifier
					.width(200.dp),
				horizontalAlignment = Alignment.CenterHorizontally
			) {
				UpArrow(player1, "Health")
				Image(
					painter = painterResource(id = R.drawable.shards_of_infinity_hp),
					contentDescription = null,
					modifier = iconSizeModifier
				)
				DownArrow(player1, "Health")
			}
			Column(horizontalAlignment = Alignment.CenterHorizontally) {
				UpArrow(player1, "Mastery")
				Image(
					painter = painterResource(id = R.drawable.shards_of_infinity_mastery),
					contentDescription = null,
					modifier = iconSizeModifier
				)
				DownArrow(player1, "Mastery")
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
					"Health" -> player.health++
					"Mastery" -> player.mastery++
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
					"Health" -> player.health--
					"Mastery" -> player.mastery--
				}
			}
	)
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
	PlayerScreen()
}