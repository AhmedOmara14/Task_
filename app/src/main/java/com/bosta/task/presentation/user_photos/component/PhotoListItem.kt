package com.bosta.task.presentation.user_profile.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.bosta.task.domain.model.photos.AllPhotosItem

@Composable
fun PhotoListItem(
    photo: AllPhotosItem
) {
    Image(
        painter = rememberAsyncImagePainter(photo.url),
        contentDescription = null,
        modifier = Modifier
            .height(100.dp)
            .width(100.dp)
    )
}