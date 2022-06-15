package com.bosta.task.presentation.users_list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.bosta.task.domain.model.all_users.AllUsers
import com.bosta.task.presentation.ScreenNavigation
import com.bosta.task.presentation.users_list.components.UserListItem

@Composable
fun UserListScreen(
    navController: NavController,
    viewModel: ListUsersViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(state.users) { item: AllUsers ->
                UserListItem(user = item, onItemClick = {
                    navController.navigate(ScreenNavigation.UserProfileScreen.route + "/${item.id}")
                })
            }
        }
        if (state.error.isNotBlank()) {
            Text(
                text = state.error,
                color = MaterialTheme.colors.error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp)
                    .align(Alignment.Center)
            )
        }
        if (state.isLoading) CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))

    }
}