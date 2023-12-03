package com.example.uf1_proyecto_compose

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.uf1_proyecto_compose.UserInterface.addNedit_task.AddEditTaskScreen
import com.example.uf1_proyecto_compose.UserInterface.task_list.TaskListScreen
import com.example.uf1_proyecto_compose.util.Routes
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController,
                startDestination = Routes.TASK_LIST ){

                composable(Routes.TASK_LIST){
                    TaskListScreen(
                        onNavigate ={
                            navController.navigate(it.route)
                        }
                    )
                }
                composable(
                    route = Routes.ADD_EDIT_TASK + "?taskId={taskId}",
                    arguments = listOf(
                        navArgument(name ="taskId"){
                            type = NavType.IntType
                            defaultValue = -1
                        }
                    )
                ){
                    AddEditTaskScreen(onPopBackStack = {
                        navController.popBackStack()
                    })
                }
            }

        }
    }
}
