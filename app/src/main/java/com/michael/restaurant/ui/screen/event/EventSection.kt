package com.michael.restaurant.ui.screen.event

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.michael.restaurant.R
import com.michael.restaurant.data.database.event.model.Event
import com.michael.restaurant.ui.component.ScreenLoader
import com.michael.restaurant.ui.component.SomethingWentWrong
import com.michael.restaurant.ui.screen.event.component.EventList
import com.michael.restaurant.ui.screen.event.component.EventsEmpty
import com.michael.restaurant.ui.screen.event.component.ExpandedEventCard

@Composable
fun EventSection(
    modifier: Modifier = Modifier,
    isExpanded: Boolean = false,
    viewModel: EventViewModel = hiltViewModel()
) {
    val eventState = viewModel.eventState.collectAsState()

    Column(
        modifier = modifier.padding(vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(R.string.events_title),
            textAlign = if (isExpanded) TextAlign.Center else TextAlign.Start,
            style = MaterialTheme.typography.titleLarge
        )
        when (val state = eventState.value) {
            is EventState.Loading -> ScreenLoader()
            is EventState.Success -> SuccessState(
                isExpanded = isExpanded,
                events = state.events,
                extendEvent = state.extendEvent,
                onMoreClick = viewModel::changeEventId,
                onCollapsedClick = viewModel::clearEventId
            )

            is EventState.Empty -> EventsEmpty(modifier = Modifier.fillMaxWidth())
            is EventState.Error -> SomethingWentWrong(modifier = Modifier.fillMaxWidth())
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
private fun SuccessState(
    isExpanded: Boolean,
    events: List<Event>,
    extendEvent: Event?,
    onMoreClick: (Int) -> Unit,
    onCollapsedClick: () -> Unit
) {
    val duration = 500

    AnimatedContent(
        targetState = extendEvent,
        transitionSpec = {
            ((scaleIn(animationSpec = tween(duration)) + fadeIn(animationSpec = tween(duration))).togetherWith(
                scaleOut(animationSpec = tween(duration)) + fadeOut(animationSpec = tween(duration))
            ))
                .using(SizeTransform(clip = false))
        }
    ) { extendEvent ->
        if (extendEvent != null) {
            ExpandedEventCard(
                event = extendEvent,
                onCollapsedClick = onCollapsedClick
            )
        } else {
            EventList(
                isExpanded = isExpanded,
                events = events,
                onMoreClick = onMoreClick
            )
        }
    }

}

@Preview(showSystemUi = true)
@Composable
fun EventSectionPreview() {
    Scaffold { innerPadding ->
        EventSection(modifier = Modifier.padding(innerPadding))
    }
}