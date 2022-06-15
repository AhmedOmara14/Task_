package com.bosta.task.presentation.user_profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.bosta.task.R
import com.bosta.task.domain.model.album.Album
import com.bosta.task.presentation.ScreenNavigation
import com.bosta.task.presentation.user_profile.component.AlbumItem

@Composable
fun UserProfileScreen(
    navController: NavController,
    viewModel: ProfileUserViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    val stateAlbum = viewModel.stateAlbum.value
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.surface)
    ) {
        state.userProfile.let { profile ->
            if (state.error.isNotBlank()) {
                Text(
                    text = state.error,
                    color = MaterialTheme.colors.error,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(20.dp)
                )
            }
            if (state.isLoading) {
                CircularProgressIndicator()
            } else {
                LazyColumn(
                    contentPadding = PaddingValues(10.dp),
                ) {
                    item {
                        Text(
                            text = stringResource(R.string.profile),
                            style = MaterialTheme.typography.h5,
                            overflow = TextOverflow.Ellipsis,
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = "${profile[0].name}",
                            fontFamily = FontFamily.Monospace,
                            style = MaterialTheme.typography.h6,
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text =
                            "street :" + "${profile[0].address.street}\n" +
                                    "suite :" + "${profile[0].address.suite}\n" +
                                    "city :" + "${profile[0].address.city}\n",
                            fontFamily = FontFamily.Monospace,
                            style = MaterialTheme.typography.h6,
                        )
                        Spacer(modifier = Modifier.height(10.dp))

                        Text(
                            text = stringResource(R.string.my_albums),
                            fontFamily = FontFamily.Monospace,
                            style = MaterialTheme.typography.h6,
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                    }
                }
            }
        }

        stateAlbum.album.let { profile ->
            if (!state.isLoading) {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(), contentPadding = PaddingValues(10.dp),
                ) {
                    items(stateAlbum.album) { item: Album ->
                        AlbumItem(album = item,onItemClick = {

                            navController.navigate(ScreenNavigation.UserPhotosScreen.route + "/${item.id}")
                        })
                    }
                }
            }
        }


    }
}