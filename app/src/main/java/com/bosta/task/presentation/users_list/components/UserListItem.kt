package com.bosta.task.presentation.users_list.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import  com.bosta.task.domain.model.all_users.AllUsers


@Composable
fun UserListItem(
    user: AllUsers,
    onItemClick: (AllUsers) -> Unit
) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .clickable { onItemClick(user) }
        .padding(20.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "${user.id}.${user.name}",
            style = MaterialTheme.typography.body1,
            overflow = TextOverflow.Ellipsis
        )

    }
}