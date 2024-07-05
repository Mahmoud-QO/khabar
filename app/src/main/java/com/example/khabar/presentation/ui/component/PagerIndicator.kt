package com.example.khabar.presentation.ui.component

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.khabar.presentation.ui.theme.LocalDimens

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PagerIndicator(
    pagerState: PagerState,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.tertiary
) = Row(modifier) {
    val dimens = LocalDimens.current
    repeat(pagerState.pageCount) {
        val width = animateDpAsState(
            targetValue = if (it == pagerState.currentPage) dimens.small*5 else dimens.small,
            label = "OnboardingPagerIndicator Width"
        )
        Box(Modifier
            .padding(dimens.extraSmall)
            .width(width.value)
            .height(dimens.small)
            .clip(CircleShape)
            .background(color)
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Preview(showBackground = true)
@Composable
private fun Preview() {
    PagerIndicator(rememberPagerState(1) { 4 })
}
