package com.michael.restaurant.ui.screen.about

import android.util.Log
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import coil3.compose.AsyncImage
import com.michael.restaurant.R
import com.michael.restaurant.data.database.about.model.About
import com.michael.restaurant.ui.component.ScreenLoader
import com.michael.restaurant.ui.component.SomethingWentWrong

@Composable
fun AboutSection(
    modifier: Modifier = Modifier,
    isExpanded: Boolean = false,
    viewModel: AboutViewModel = hiltViewModel()
) {
    val aboutState = viewModel.aboutState.collectAsState()

    when (val state = aboutState.value) {
        AboutState.Loading -> ScreenLoader(modifier)

        is AboutState.Success -> SuccessState(
            modifier = modifier,
            about = state.about,
            isExpanded = isExpanded
        )

        is AboutState.Error -> SomethingWentWrong(modifier)
    }
}

@Composable
private fun SuccessState(
    modifier: Modifier,
    about: About,
    isExpanded: Boolean = false
) {
    ConstraintLayout(
        modifier = modifier
    ) {
        val (headerRef, imageRef, bodyRef) = createRefs()

        Text(
            text = about.title,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.constrainAs(headerRef) {
                top.linkTo(parent.top)
                centerHorizontallyTo(parent)
            }
        )
        AsyncImage(
            modifier = Modifier.constrainAs(imageRef) {
                top.linkTo(headerRef.bottom, margin = 12.dp)

                if (!isExpanded) {
                    centerHorizontallyTo(headerRef)
                }
            },
            model = "file:///android_asset/image/${about.imagePath}",
            placeholder = painterResource(R.drawable.placeholder),
            contentDescription = "",
            onError = {
                Log.e("ABOUT_SECTION", "Error: ${it.result}")
            }
        )

        Text(
            modifier = Modifier.constrainAs(bodyRef) {
                if (isExpanded) {
                    top.linkTo(headerRef.bottom, margin = 12.dp)
                    start.linkTo(imageRef.end, margin = 12.dp)
                } else {
                    top.linkTo(imageRef.bottom, margin = 12.dp)
                }
                end.linkTo(parent.end)
                width = Dimension.fillToConstraints
            },
            text = about.body,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}