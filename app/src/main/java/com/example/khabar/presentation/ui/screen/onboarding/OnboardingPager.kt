package com.example.khabar.presentation.ui.screen.onboarding

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.windowsizeclass.WindowHeightSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.khabar.R
import com.example.khabar.presentation.ui.component.PagerIndicator
import com.example.khabar.presentation.ui.theme.KhabarTheme
import com.example.khabar.presentation.ui.theme.LocalDimens
import com.example.khabar.presentation.viewmodel.onboarding.OnboardingEvent
import kotlinx.coroutines.launch

// TODO: use OnboardingPager as a custom layout
@Composable
fun OnboardingPager(
    pages: List<OnboardingPage>,
    modifier: Modifier = Modifier,
    onEvent: (OnboardingEvent) -> Unit = {}
) {
    val pagerState = rememberPagerState(initialPage = 0) { pages.size }

    HorizontalPager(state = pagerState, modifier = modifier) {
        val page = pages[it]

        if (LocalConfiguration.current.orientation == Configuration.ORIENTATION_LANDSCAPE)  {
            Row(modifier = Modifier.fillMaxSize()) {
                FirstContentPart(page, Modifier.fillMaxSize().weight(1f))
                SecondContentPart(page, pagerState, Modifier.fillMaxSize().weight(1f))
            }
        } else {
            Column(modifier = Modifier.fillMaxSize()) {
                FirstContentPart(page, Modifier.fillMaxSize().weight(1f))
                SecondContentPart(
                    page, pagerState, Modifier.fillMaxSize().weight(1f), onEvent
                )
            }
        }
    }
}

@Composable
private fun FirstContentPart(page: OnboardingPage, modifier: Modifier = Modifier) = Image(
    painter = painterResource(id = page.image),
    contentDescription = null,
    modifier = modifier
)


@Composable
private fun SecondContentPart(
    page: OnboardingPage, pagerState: PagerState,
    modifier: Modifier = Modifier,
    onEvent: (OnboardingEvent) -> Unit = {}
) = Card(
    modifier = modifier,
    shape = RoundedCornerShape(topStart = LocalDimens.current.extraLarge)
) {
    val dimens = LocalDimens.current
    Column(
        modifier = Modifier.fillMaxSize().padding(dimens.medium),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        PagerIndicator(pagerState = pagerState)

        Text(
            text = page.title,
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.ExtraBold,
//            textAlign = TextAlign.Center
        )

        Text(
            text = page.description,
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Center
        )

        if (pagerState.currentPage == pagerState.pageCount - 1) {
            Button(
                modifier = Modifier.size(dimens.extraLarge*3, dimens.extraLarge),
                onClick = { onEvent(OnboardingEvent.ClickGetStarted) }
            ) {
                Text(
                    text = "Get Started",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
            }
        } else {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth().wrapContentHeight()
            ) {
                TextButton(onClick = { onEvent(OnboardingEvent.ClickSkip) }) {
                    Text(
                        text = "Skip Now",
                        style = MaterialTheme.typography.titleLarge,
                    )
                }

                val coroutineScope = rememberCoroutineScope()
                OutlinedButton(
                    onClick = { coroutineScope.launch {
                        pagerState.scrollToPage(pagerState.currentPage + 1)
                    }},
                    modifier = Modifier.size(dimens.extraLarge),
                    border = BorderStroke(dimens.extraLarge/6, MaterialTheme.colorScheme.primary),
                    shape = CircleShape
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_arrow),
                        contentDescription = "Click for next",
                        modifier = Modifier.size(dimens.large)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, device = "id:2.7in QVGA", uiMode = Configuration.UI_MODE_NIGHT_YES, showSystemUi = true)
@Preview(showBackground = true, device = "id:3.2in HVGA slider (ADP1)", showSystemUi = true)
@Preview(showBackground = true, device = "id:3.4in WQVGA", showSystemUi = true)
@Preview(showBackground = true, device = "id:3.7in WVGA (Nexus One)", showSystemUi = true)
@Preview(showBackground = true, device = "id:4in WVGA (Nexus S)", showSystemUi = true)
@Preview(showBackground = true, device = "id:4.65in 720p (Galaxy Nexus)", showSystemUi = true)
@Preview(showBackground = true, device = "id:small_phone", showSystemUi = true)
@Preview(showBackground = true, device = "id:4.7in WXGA", showSystemUi = true)
@Preview(showBackground = true, device = "id:5.1in WVGA", showSystemUi = true)
@Preview(showBackground = true, device = "id:5.4in FWVGA", showSystemUi = true)
@Preview(showBackground = true, device = "id:resizable", showSystemUi = true)
@Preview(showBackground = true, device = "id:medium_phone", showSystemUi = true)
@Preview(showBackground = true, device = "id:6.7in Foldable", showSystemUi = true)
@Preview(showBackground = true, device = "id:7in WSVGA (Tablet)", showSystemUi = true)
@Preview(showBackground = true, device = "id:7.4in Rollable", showSystemUi = true)
@Preview(showBackground = true, device = "id:7.6in Foldable", showSystemUi = true)
@Preview(showBackground = true, device = "id:8in Foldable", showSystemUi = true)
@Preview(showBackground = true, device = "id:medium_tablet", showSystemUi = true)
@Preview(showBackground = true, device = "id:10.1in WXGA (Tablet)", showSystemUi = true)
@Preview(showBackground = true, device = "id:13.5in Freeform", showSystemUi = true)

@Preview(showBackground = true, device = "spec:parent=2.7in QVGA,orientation=landscape", showSystemUi = true)
@Preview(showBackground = true, device = "spec:parent=3.2in HVGA slider (ADP1),orientation=landscape", showSystemUi = true)
@Preview(showBackground = true, device = "spec:parent=3.4in WQVGA,orientation=landscape", showSystemUi = true)
@Preview(showBackground = true, device = "spec:parent=3.7in WVGA (Nexus One),orientation=landscape", showSystemUi = true)
@Preview(showBackground = true, device = "spec:parent=4in WVGA (Nexus S),orientation=landscape", showSystemUi = true)
@Preview(showBackground = true, device = "spec:parent=4.65in 720p (Galaxy Nexus),orientation=landscape", showSystemUi = true)
@Preview(showBackground = true, device = "spec:parent=small_phone,orientation=landscape", showSystemUi = true)
@Preview(showBackground = true, device = "spec:parent=4.7in WXGA", showSystemUi = true)
@Preview(showBackground = true, device = "spec:parent=5.1in WVGA,orientation=landscape", showSystemUi = true)
@Preview(showBackground = true, device = "spec:parent=5.4in FWVGA,orientation=landscape", showSystemUi = true)
@Preview(showBackground = true, device = "spec:parent=resizable,orientation=landscape", showSystemUi = true)
@Preview(showBackground = true, device = "spec:parent=medium_phone,orientation=landscape", showSystemUi = true)
@Preview(showBackground = true, device = "spec:parent=6.7in Foldable,orientation=landscape", showSystemUi = true)
@Preview(showBackground = true, device = "spec:parent=7in WSVGA (Tablet)", showSystemUi = true)
@Preview(showBackground = true, device = "spec:parent=7.4in Rollable,orientation=landscape", showSystemUi = true)
@Preview(showBackground = true, device = "spec:parent=7.6in Foldable,orientation=landscape", showSystemUi = true)
@Preview(showBackground = true, device = "spec:parent=8in Foldable,orientation=landscape", showSystemUi = true)
@Preview(showBackground = true, device = "spec:parent=medium_tablet,orientation=portrait", showSystemUi = true)
@Preview(showBackground = true, device = "spec:parent=10.1in WXGA (Tablet)", showSystemUi = true)
@Preview(showBackground = true, device = "spec:parent=13.5in Freeform,orientation=portrait", showSystemUi = true)
@Preview(showBackground = true)
@Composable
private fun OnboardingPagerPreview() {
    val widthSizeClass = when(LocalConfiguration.current.screenWidthDp) {
        in 0..600 -> WindowWidthSizeClass.Compact
        in 601..840 -> WindowWidthSizeClass.Medium
        else -> WindowWidthSizeClass.Expanded
    }
    val heightSizeClass = when(LocalConfiguration.current.screenHeightDp) {
        in 0..480 -> WindowHeightSizeClass.Compact
        in 481..900 -> WindowHeightSizeClass.Medium
        else -> WindowHeightSizeClass.Expanded
    }
    KhabarTheme(widthSizeClass = widthSizeClass, heightSizeClass = heightSizeClass) {
        OnboardingPager(onboardingPages, Modifier.fillMaxSize())
    }
}
