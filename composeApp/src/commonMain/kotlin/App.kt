import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.animation.core.tween
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.with
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.core.lifecycle.LifecycleEffect
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.core.screen.ScreenKey
import cafe.adriel.voyager.core.screen.uniqueScreenKey
import cafe.adriel.voyager.core.stack.StackEvent
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import cafe.adriel.voyager.transitions.ScreenTransition
import cafe.adriel.voyager.transitions.ScreenTransitionContent
import cafe.adriel.voyager.transitions.SlideTransition

@OptIn(ExperimentalResourceApi::class)
@Composable
fun App() {
    MaterialTheme {
//1.        MessageCard("iOS Compose Multiplatform")
//        RowExample()
//        ColumnExample()
//        BoxExample()
//        ComplexExample()
//       Navigator(HomeScreen)
//        Navigator(HomeScreen) { navigator ->
//             SlideTransition(navigator)
//        }
        Navigator(LazyColumnHomeScreen) { navigator ->
             CrossfadeTransition(navigator)
        }
    }
}

// Samples:
@Composable
fun MessageCard(name: String) {
    Text(text = "Hello $name!")
}

@Composable
fun ColumnExample() {
    Column {
        MessageCard("Text1")
        MessageCard("Text2")
        MessageCard("Text3")
    }
}

@Composable
fun RowExample() {
    Row {
        MessageCard("Text1")
        MessageCard("Text2")
        MessageCard("Text3")
    }
}

@Composable
fun BoxExample() {
    Box(
        modifier = Modifier
            .background(Color.Red)
            .size(200.dp)
            .padding(32.dp)
            .background(Color.Green)
            .padding(32.dp)
            .background(Color.Blue)
    )
}

@Composable
fun ComplexExample() {
    Column {
        Row {
            ColumnExample()
            RowExample()
        }
        BoxExample()
    }

}



// Navigation:
object HomeScreen : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        Column {
            Button(
                onClick = { navigator.push(SimpleTextScreen()) },
            ) {
                Text(text = "Text example")
            }
            Button(
                onClick = { navigator.push(ColumnScreen()) },
            ) {
                Text(text = "Column example")
            }
            Button(
                onClick = { navigator.push(RowScreen()) },
            ) {
                Text(text = "Row example")
            }
            Button(
                onClick = { navigator.push(BoxScreen()) },
            ) {
                Text(text = "Box example")
            }
            Button(
                onClick = { navigator.push(ComplexScreen()) },
            ) {
                Text(text = "Complex example")
            }
        }

    }
}

object LazyColumnHomeScreen: Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val list = listOf(
            "Simple Text", "Row", "Column", "Box", "Complex"
        )
        LazyColumn(modifier = Modifier.fillMaxHeight()) {
            items(items = list, itemContent = { item ->
                when (item) {
                    "Simple Text" -> {
                        Button(onClick = {
                            navigator.push(SimpleTextScreen())
                        }) {
                            Text(text = item, style = TextStyle(fontSize = 16.sp))
                        }
                    }
                    "Row" -> {
                        Button(onClick = {
                            navigator.push(RowScreen())
                        }) {
                            Text(text = item, style = TextStyle(fontSize = 16.sp))
                        }
                    }
                    "Column" -> {
                        Button(onClick = {
                            navigator.push(ColumnScreen())
                        }) {
                            Text(text = item, style = TextStyle(fontSize = 16.sp))
                        }
                    }
                    "Box" -> {
                        Button(onClick = {
                            navigator.push(BoxScreen())
                        }) {
                            Text(text = item, style = TextStyle(fontSize = 16.sp))
                        }
                    }
                    "Complex" -> {
                        Button(onClick = {
                            navigator.push(ComplexScreen())
                        }) {
                            Text(text = item, style = TextStyle(fontSize = 16.sp))
                        }
                    }
                }
            })
        }
    }
}

class SimpleTextScreen : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        Column{
            Button(
                enabled = navigator.canPop,
                onClick = navigator::pop
            ) {
                Text(text = "Go back")
            }
            MessageCard("Simple Text example")
        }

    }
}

class RowScreen : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        Column{
            Button(
                enabled = navigator.canPop,
                onClick = navigator::pop
            ) {
                Text(text = "Go back")
            }
            RowExample()
        }

    }
}

class ColumnScreen : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        Column{
            Button(
                enabled = navigator.canPop,
                onClick = navigator::pop
            ) {
                Text(text = "Go back")
            }
            ColumnExample()
        }

    }
}

class BoxScreen : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        Column{
            Button(
                enabled = navigator.canPop,
                onClick = navigator::pop
            ) {
                Text(text = "Go back")
            }
            BoxExample()
        }

    }
}

class ComplexScreen : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        Column{
            Button(
                enabled = navigator.canPop,
                onClick = navigator::pop
            ) {
                Text(text = "Go back")
            }
            ComplexExample()
        }

    }
}

@Composable
public fun CrossfadeTransition(
    navigator: Navigator,
    animationSpec: FiniteAnimationSpec<Float> = tween(),
    label: String = "Crossfade",
    modifier: Modifier = Modifier,
    content: @Composable (Screen) -> Unit = { it.Content() }
) {
    Crossfade(
        targetState = navigator.lastItem,
        animationSpec = animationSpec,
        modifier = modifier,
        label = label
    ) { screen ->
        navigator.saveableState("transition", screen) {
            content(screen)
        }
    }
}