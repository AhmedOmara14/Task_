package com.bosta.task.presentation

sealed class ScreenNavigation(val route :String){
    object UserListScreen:ScreenNavigation("user_list_screen")
    object UserProfileScreen:ScreenNavigation("user_profile_screen")
    object UserPhotosScreen:ScreenNavigation("user_photo_screen")
    object ComposeDialogDemo:ScreenNavigation("ComposeDialogDemo")
}
