package com.example.reposearch

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedContentTransitionScope.*
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.reposearch.ui.details.DetailsScreen
import com.example.reposearch.ui.details.DetailsViewModel
import com.example.reposearch.ui.search.SearchScreen
import com.example.reposearch.ui.search.SearchViewModel
import com.example.reposearch.ui.theme.RepoSearchTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RepoSearchTheme {
                val navHostController = rememberNavController()
                NavHost(
                    navController = navHostController,
                    startDestination = Screen.Search
                ) {
                    composable<Screen.Search> {
                        val viewModel: SearchViewModel = hiltViewModel()
                        SearchScreen(viewModel = viewModel,
                            onRepoClicked = {
                                navHostController.navigate(Screen.Details(it.id))
                            })
                    }
                    composable<Screen.Details>(
                        enterTransition = {
                            slideIntoContainer(SlideDirection.Left)
                        },
                        popExitTransition = {
                            slideOutOfContainer(SlideDirection.Right)
                        }
                    ) {
                        val viewModel: DetailsViewModel = hiltViewModel()
                        DetailsScreen(viewModel,
                            onBack = {
                                navHostController.navigateUp()
                            })
                    }
                }
            }
        }
    }
}