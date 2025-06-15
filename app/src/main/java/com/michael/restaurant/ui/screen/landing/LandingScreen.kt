package com.michael.restaurant.ui.screen.landing

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.michael.restaurant.R
import com.michael.restaurant.ui.adaptive.DeviceType
import com.michael.restaurant.ui.screen.about.AboutSection
import com.michael.restaurant.ui.screen.event.EventSection
import com.michael.restaurant.ui.screen.menu.MenuSection
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LandingScreen(
    modifier: Modifier = Modifier,
    deviceType: DeviceType
) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val lazyColumnState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    val scrollBehavior =
        TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())
    val snackBarHostState = remember { SnackbarHostState() }

    NavigationMenu(
        sectionItems = SectionItem.listOfSections,
        drawerState = drawerState,
        onItemClick = { it ->
            coroutineScope.launch {
                drawerState.close()
                lazyColumnState.animateScrollToItem(index = it, scrollOffset = -18)
            }
        }
    ) {
        Scaffold(
            modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
            snackbarHost = { SnackbarHost(hostState = snackBarHostState) },
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

            LazyColumn(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
                    .padding(horizontal = 18.dp),
                state = lazyColumnState,
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                items(SectionItem.listOfSections) { section ->

                    val sectionModifier = modifier
                        .fillMaxWidth(
                            fraction = if (deviceType == DeviceType.MOBILE) 1f else 0.8f
                        )
                    val isExpanded = deviceType == DeviceType.TABLET

                    when (section) {
                        is SectionItem.About -> AboutSection(
                            modifier = sectionModifier,
                            isExpanded = isExpanded
                        )

                        is SectionItem.Events -> EventSection(
                            modifier = sectionModifier,
                            isExpanded = isExpanded
                        )

                        is SectionItem.Menu -> MenuSection(
                            modifier = sectionModifier,
                            snackBarHostState = snackBarHostState,
                            isExpanded = isExpanded
                        )

                        else -> Text(
                            text = stringResource(section.titleResId),
                            modifier = sectionModifier.height(600.dp)
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