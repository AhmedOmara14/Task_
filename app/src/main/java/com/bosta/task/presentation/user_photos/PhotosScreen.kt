package com.bosta.task.presentation.user_photos

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.composable
import com.bosta.task.R
import com.bosta.task.domain.model.photos.AllPhotosItem
import com.bosta.task.presentation.ScreenNavigation
import com.bosta.task.presentation.user_profile.component.PhotoListItem
import java.util.*
import kotlin.collections.ArrayList

@Composable
fun PhotosScreen(
    navController: NavController, viewModel: UserPhotosViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.surface)
    ) {
        state.userPhotos.let {
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
                            text = stringResource(R.string.all_photos),
                            style = MaterialTheme.typography.h5,
                            overflow = TextOverflow.Ellipsis,
                        )
                        Spacer(modifier = Modifier.height(5.dp))
                    }
                }

                val textVal = remember { mutableStateOf(TextFieldValue("")) }
                GetAllPhotos(textVal)
                PhotoList(textVal, state.userPhotos, navController)

            }
        }
    }
}

@Composable
fun GetAllPhotos(textVal: MutableState<TextFieldValue>) {
    TextField(
        value = textVal.value,
        onValueChange = { textVal.value = it },
        placeholder = { Text(text = "Search") },
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp),
        textStyle = TextStyle(Color.Black, fontSize = 18.sp),
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.Search,
                contentDescription = "Search",
                modifier = Modifier
                    .padding(15.dp)
                    .size(24.dp)
            )
        },
        trailingIcon = {
            if (textVal.value != TextFieldValue("")) {
                IconButton(
                    onClick = {
                        textVal.value = TextFieldValue("")
                    }
                ) {
                    Icon(
                        imageVector = Icons.Filled.Close,
                        contentDescription = "Close",
                        modifier = Modifier
                            .padding(15.dp)
                            .size(24.dp)
                    )
                }
            }
        },
        singleLine = true
    )
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PhotoList(
    textVal: MutableState<TextFieldValue>,
    userPhotos: List<AllPhotosItem>, navController: NavController
) {
    val photos = userPhotos
    var filteredCountries: List<AllPhotosItem>
    LazyVerticalGrid(
        cells = GridCells.Fixed(4),
        modifier = Modifier.padding(10.dp)
    ) {
        val searchText = textVal.value.text
        filteredCountries = if (searchText.isEmpty()) {
            photos
        } else {
            val resultList = ArrayList<AllPhotosItem>()
            for (photo in photos) {
                if (photo.title.lowercase(Locale.getDefault())
                        .contains(searchText.lowercase(Locale.getDefault()))
                ) resultList.add(photo)
            }
            resultList
        }
        items(filteredCountries) { filteredCountries ->
            PhotoListItem(
                photo = filteredCountries
            )

        }
    }
}

