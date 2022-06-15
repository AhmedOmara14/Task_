package com.bosta.task.presentation.user_profile.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.bosta.task.domain.model.album.Album
import com.bosta.task.domain.model.all_users.AllUsers

@Composable
fun AlbumItem(
    album: Album, onItemClick: (Album) -> Unit
) {
    Text(
        text = "${album.title})",
        color = Color.LightGray,
        style = TextStyle(textDecoration = TextDecoration.Underline),
        overflow = TextOverflow.Ellipsis,
        modifier = Modifier
            .fillMaxSize()
            .clickable { onItemClick(album) }
            .padding(0.dp, 10.dp, 0.dp, 0.dp),
    )

}