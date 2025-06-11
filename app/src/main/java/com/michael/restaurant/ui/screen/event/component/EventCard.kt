package com.michael.restaurant.ui.screen.event.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.michael.restaurant.R
import com.michael.restaurant.data.database.event.model.Event

@Composable
fun EventCard(
    event: Event,
    onMoreClick: () -> Unit
) {
    OutlinedCard(
        modifier = Modifier
            .width(300.dp)
            .height(320.dp)
            .padding(8.dp),
    ) {
        AsyncImage(
            modifier = Modifier
                .height(180.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp)),
            model = "file:///android_asset/image/${event.image}.jpg",
            contentDescription = "",
            placeholder = painterResource(R.drawable.placeholder),
            contentScale = ContentScale.FillWidth
        )

        Spacer(modifier = Modifier.height(8.dp))

        Column(
            modifier = Modifier
                .fillMaxHeight(1f)
                .padding(horizontal = 12.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = event.title,
                style = MaterialTheme.typography.titleMedium,
                overflow = TextOverflow.Ellipsis,
                minLines = 1,
                maxLines = 1
            )

            Spacer(modifier = Modifier.height(8.dp))

            DateEvent(date = event.date)

            TextButton(onClick = onMoreClick) {
                Text(text = stringResource(R.string.more_button))
            }
        }


    }
}