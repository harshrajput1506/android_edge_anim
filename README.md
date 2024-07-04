# Edge Animation Android App

Welcome to the Edge Animation Android App! This application demonstrates a dynamic edge light animation using Kotlin and Jetpack Compose.

<p align="center">
    <img src="https://img.shields.io/badge/Kotlin-1DA1F2?style=for-the-badge&logo=kotlin&logoColor=white" alt="Kotlin Badge"/>
    <img src="https://img.shields.io/badge/Android-3DDC84?style=for-the-badge&logo=android&logoColor=white" alt="Android Badge"/>
</p>

## Features

- **Edge Light Animation**: A captivating edge light animation with customizable gradient colors.
- **Toggle Animation**: Switch the animation on or off using a simple toggle button.

## Screenshots

<p align="center">
    <img src="/Screenshots/Screenshot_1.png" alt="Edge Animation Screenshot" width="300" style="margin-right: 8;"/>
    <img src="/Screenshots/screen_recording.gif" alt="Edge Animation Screen Recording" width="300"/>
</p>

## Approach

The animation is implemented using Jetpack Compose's `rememberInfiniteTransition` and `animateFloat` to create a continuous rotation effect. Here's a breakdown of the approach:

1. **State Management**: A boolean state `checked` is used to toggle the animation.
2. **Infinite Transition**: `rememberInfiniteTransition` is used to create an infinite animation that rotates from 0 to 360 degrees.
3. **Gradient Brush**: A vertical gradient brush is defined with colors transitioning from blue to cyan to magenta.
4. **Surface Drawing**: The `Surface` composable uses `drawWithContent` to conditionally apply the rotation animation to a drawn circle with the gradient brush.
5. **Scaffold and Layout**: The `Scaffold` and `Column` composables are used for layout, with a `Switch` to toggle the animation and a `Text` for the title.

## Code

Here's the implementation of the edge animation:

```kotlin
@Composable
fun AnimatedSurface() {
    var checked by remember { mutableStateOf(false) }
    val infiniteTransition = rememberInfiniteTransition("edge animation")
    val degrees by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 2000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ), label = "edge"
    )
    val brush = Brush.verticalGradient(colors = listOf(Color.Blue, Color.Cyan, Color.Magenta), tileMode = TileMode.Repeated)
    
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(4.dp)
            .drawWithContent {
                if (checked) {
                    rotate(degrees = degrees) {
                        drawCircle(
                            brush = brush,
                            radius = size.height,
                            blendMode = BlendMode.SrcIn
                        )
                    }
                }
                drawContent()
            }
    ) {
        Scaffold(modifier = Modifier.fillMaxSize()) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Edge Light Animation",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Medium
                )
                Switch(
                    checked = checked,
                    onCheckedChange = { checked = it }
                )
            }
        }
    }
}
```

## Installation

1. Clone the repository:
  ```sh
    git clone https://github.com/harshrajput1506/android_edge_anim.git
  ```
3. Open the project in Android Studio.
4. Build and run the app on an Android device or emulator.

## Usage

- Launch the app on your device.
- Toggle the switch to start or stop the edge light animation.

Feel free to explore and modify the code to suit your needs. Enjoy the mesmerizing edge light animation!

## Release APK

You can download the release APK from the following path in the repository:

[![Download APK](https://img.shields.io/badge/Download-APK-blue.svg?style=for-the-badge)](/app/release/app-release.apk)

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---

If you encounter any issues or have suggestions, please open an issue on GitHub.

