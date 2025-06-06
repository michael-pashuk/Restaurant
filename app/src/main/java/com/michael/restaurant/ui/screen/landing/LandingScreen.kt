package com.michael.restaurant.ui.screen.landing

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.onPlaced
import androidx.compose.ui.layout.positionInParent
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.michael.restaurant.R
import com.michael.restaurant.ui.adaptive.DeviceType
import com.michael.restaurant.ui.screen.about.AboutSection
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LandingScreen(
    modifier: Modifier = Modifier,
    deviceType: DeviceType
) {
    val sectionsPosition = remember { mutableMapOf<String, Int>() }
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scrollState = rememberScrollState()
    val coroutineScope = rememberCoroutineScope()
    val scrollBehavior =
        TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())

    NavigationMenu(
        sectionItems = SectionItem.listOfSections,
        drawerState = drawerState,
        onItemClick = {
            coroutineScope.launch {
                drawerState.close()
                scrollState.animateScrollTo(sectionsPosition[it] ?: 0)
            }
        }
    ) {
        Scaffold(
            modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
            topBar = {
                LandingAppBar(
                    scrollBehavior = scrollBehavior,
                    onNavigationIconClick = {
                        coroutineScope.launch {
                            drawerState.open()
                        }
                    },
                    onTranslateIconClick = { /* TODO: ADD TRANSLATE */ }
                )
            }
        ) { innerPadding ->

            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
                    .padding(horizontal = 12.dp)
                    .verticalScroll(state = scrollState),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                val sectionModifier = modifier.fillMaxWidth(
                    fraction = if (deviceType == DeviceType.MOBILE) 1f else 0.8f
                )
                val isExpanded = deviceType == DeviceType.TABLET

                SectionItem.listOfSections.forEach { section ->
                    when (section) {
                        is SectionItem.About -> AboutSection(
                            modifier = sectionModifier.addSectionPosition(
                                section = section,
                                sectionsPosition = sectionsPosition
                            ),
                            isExpanded = isExpanded
                        )

                        else -> Text(
                            text = stringResource(section.titleResId),
                            modifier = sectionModifier
                                .height(600.dp)
                                .addSectionPosition(
                                    section = section,
                                    sectionsPosition = sectionsPosition
                                )
                        )
                    }
                }
            }
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun LandingAppBar(
    scrollBehavior: TopAppBarScrollBehavior,
    onNavigationIconClick: () -> Unit,
    onTranslateIconClick: () -> Unit
) {
    val collapseProgress = scrollBehavior.state.collapsedFraction

    val logoScale by animateFloatAsState(
        targetValue = 1f - 0.6f * collapseProgress
    )

    LargeTopAppBar(
        scrollBehavior = scrollBehavior,
        title = {
            Image(
                modifier = Modifier
                    .fillMaxSize()
                    .scale(logoScale),
                imageVector = ImageVector.vectorResource(R.drawable.logo),
                contentDescription = "Logo"
            )
        },
        navigationIcon = {
            IconButton(
                onClick = onNavigationIconClick
            ) {
                Icon(Icons.Default.Menu, "")
            }
        },
        actions = {
            IconButton(onClick = onTranslateIconClick) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.translate),
                    contentDescription = "Translate"
                )
            }
        }
    )
}

private fun Modifier.addSectionPosition(
    section: SectionItem,
    sectionsPosition: MutableMap<String, Int>
) = this.onPlaced { coordinates ->
    sectionsPosition[section.id] =
        coordinates.positionInParent().y.toInt()
}