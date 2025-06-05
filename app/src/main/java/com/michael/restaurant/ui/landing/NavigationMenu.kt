package com.michael.restaurant.ui.landing

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.sp

@Composable
fun NavigationMenu(
    modifier: Modifier = Modifier,
    sectionItems: List<SectionItem>,
    drawerState: DrawerState,
    onItemClick: (String) -> Unit,
    content: @Composable () -> Unit,
) {
    ModalNavigationDrawer(
        drawerContent = {
            NavigationSheet(modifier, sectionItems, onItemClick)
        },
        drawerState = drawerState,
        content = content
    )
}

@Composable
private fun NavigationSheet(
    modifier: Modifier,
    sectionItems: List<SectionItem>,
    onItemClick: (String) -> Unit
) {
    ModalDrawerSheet(
        modifier = modifier
    ) {
        sectionItems.forEach { section ->
            NavigationDrawerItem(
                modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding),
                icon = {
                    Icon(
                        imageVector = ImageVector.vectorResource(section.iconResId),
                        contentDescription = stringResource(section.titleResId),
                        tint = MaterialTheme.colorScheme.primary
                    )
                },
                label = {
                    Text(
                        text = stringResource(section.titleResId),
                        fontSize = 16.sp,
                        color = MaterialTheme.colorScheme.primary
                    )
                },
                selected = false,
                onClick = { onItemClick(section.id) }
            )
        }
    }
}