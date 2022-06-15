package com.bosta.task.presentation.user_photos

import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun ComposeDialogDemo() {
    // State to manage if the alert dialog is showing or not.
    // Default is false (not showing)
        DialogDemo()
}

@Composable
fun DialogDemo() {
        AlertDialog(
            onDismissRequest = {
            },
            title = {
                Text("Title")
            },
            confirmButton = {
                Button(
                    onClick = {
                        // Change the state to close the dialog
               //         setShowDialog(false)
                    },
                ) {
                    Text("Confirm")
                }
            },
            dismissButton = {
                Button(
                    onClick = {
                        // Change the state to close the dialog
                  //      setShowDialog(false)
                    },
                ) {
                    Text("Dismiss")
                }
            },
            text = {
                Text("This is a text on the dialog")
            },
        )
    }