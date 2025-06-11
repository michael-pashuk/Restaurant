package com.michael.restaurant.ui.screen.event.component

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.michael.restaurant.data.database.event.model.Event

@Composable
fun EventList(
    modifier: Modifier = Modifier,
    isExpanded: Boolean,
    events: List<Event>,
    onMoreClick: (Int) -> Unit
) {
    Crossfade(targetState = isExpanded) { expanded ->
        when (expanded) {
            true -> FlowRow(
                modifier = modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                maxItemsInEachRow = Int.MAX_VALUE,
            ) {
                events.forEach {
                    EventCard(
                        event = it,
                        onMoreClick = { onMoreClick(it.id) }
                    )
                }
            }

            false ->
                LazyRow(
                    modifier = modifier,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    items(items = events, key = { it -> it.id }) {
                        EventCard(
                            event = it,
                            onMoreClick = { onMoreClick(it.id) }
                        )
                    }
                }
        }
    }
}