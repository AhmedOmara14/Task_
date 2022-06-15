package com.bosta.task.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bosta.task.presentation.user_photos.PhotosScreen
import com.bosta.task.presentation.user_profile.UserProfileScreen
import com.bosta.task.presentation.users_list.UserListScreen
import com.bosta.task.ui.theme.TaskTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TaskTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    color = MaterialTheme.colors.background
                ) {
                    val navController= rememberNavController()
                    NavHost(navController = navController, startDestination =ScreenNavigation.UserListScreen.route ){
                        composable(route=ScreenNavigation.UserListScreen.route){
                            UserListScreen(navController)
                        }
                        composable(route=ScreenNavigation.UserProfileScreen.route+"/{userId}"){
                            UserProfileScreen(navController)
                        }
                        composable(route=ScreenNavigation.UserPhotosScreen.route+"/{albumId}"){
                            PhotosScreen(navController)
                        }
                    }
                }
            }
        }
    }
}
