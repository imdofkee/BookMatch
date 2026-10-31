//package com.dofkee.bookme
//
//import androidx.compose.foundation.layout.padding
//import androidx.compose.material3.Scaffold
//import androidx.compose.material3.ModalBottomSheet
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Modifier
//import androidx.lifecycle.viewmodel.compose.viewModel
//import androidx.navigation.compose.NavHost
//import androidx.navigation.compose.composable
//import androidx.navigation.compose.rememberNavController
//import com.dofkee.bookme.ui.theme.homepage.HomeScreen
//
//
//object Routes {
//    const val MAIN_PAGE = "main_page"
//    const val ACCOUNT = "accpunt"
//    const val SETTINGS = "settings"
//    const val BOOKLISTS = "booklists"
//    const val COLLECTION = "collection/{idCollection}"
//
//    fun idCollection(id: String) = "collection/$id"
//}
//
//@Composable
//fun AppRoutes(modifier: Modifier) {
//    val router = rememberNavController()
//
//    Scaffold(
//        modifier = modifier,
//        bottomBar = {BottomBar(router)}
//    ) { innerPadding ->
//        NavHost(
//            navController = router,
//            startDestination = Routes.MAIN_PAGE,
//            modifier = Modifier.padding(innerPadding)
//        )
//        {
//            composable(Routes.MAIN_PAGE) {
//                val viewModel: MainPageViewModel = viewModel()
//                val state by viewModel.state.collectAsState()
//                UiStateContent(state = state, onRetry = viewModel::load) { home ->
//                    HomeScreen()                    )
//                }
//            }
//        }
//    }
//}