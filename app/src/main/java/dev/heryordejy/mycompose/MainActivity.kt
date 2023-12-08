package dev.heryordejy.mycompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.heryordejy.mycompose.ui.theme.MyComposeTheme

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      MyComposeTheme {
        // A surface container using the 'background' color from the theme
        Surface(
          modifier = Modifier.fillMaxSize(),
          color = MaterialTheme.colorScheme.primary
        ) {
          
          ListOfGreetingCards()
        }
      }
    }
  }
}


@Composable
fun GreetingCard(int: Int) {
//  is expand state
  val isExpanded = remember { mutableStateOf(false) }
//  padding for if expanded or not
  val expandedPadding by animateDpAsState(
    targetValue = if (isExpanded.value) 20.dp else 0.dp,
    animationSpec = tween(
      durationMillis = 3000, easing = LinearEasing
    ), label = "expandedPadding"
  )
  
  // A surface container using the 'background' color from the theme
  Surface(color = MaterialTheme.colorScheme.primary, modifier = Modifier.padding(all = 20.dp)) {
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp),
      verticalAlignment = Alignment.CenterVertically
    ) {
      Column(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
          .weight(1f)
          .padding(bottom = expandedPadding)
      ) {
        Text(text = "Hello")
        Text(text = "World $int")
        
      }
      FilledTonalButton(onClick = { isExpanded.value = !isExpanded.value }) {
        Text(text = if (isExpanded.value) "Show less" else "Show more")
      }
    }
  }
}

// create a list of GreetingCard with compose
@Composable
fun ListOfGreetingCards() {
  val numbers: List<Int> = List(1000) { it }
  Column {
    LazyColumn {
      items(numbers) { number ->
        
        GreetingCard(int = number)
        
      }
    }
  }
  
}


@Preview(showBackground = true)
@Composable
fun GreetingCardPreview() {
  MyComposeTheme {
    ListOfGreetingCards()
  }
}
