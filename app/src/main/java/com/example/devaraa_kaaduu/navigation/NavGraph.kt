package com.example.devaraa_kaaduu.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.devaraa_kaaduu.data.groveList
import com.example.devaraa_kaaduu.ui.screens.*
object Routes {

    const val CAMERA = "camera_screen"
    const val MYTH = "myth_screen"
    const val LORE = "lore_screen"

    const val REPORT_ISSUE = "report_issue"
    const val SPECIES_INTRO = "species_intro"
    const val SPECIES_SCAN = "species_scan"
    const val PROFILE = "profile"
}


@Composable
fun NavGraph() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "splash"
    ) {

        // 🌿 Splash Screen
        composable("splash") {
            SplashScreen(navController)
        }

        // 🔐 Login Screen (ADD THIS)
        composable("login") {
            LoginScreen(navController)
        }

        composable("createaccount") {
            CreateAccount(navController)
        }

        // 🏠 Home Screen
        composable("home") {
            HomeScreen(navController)   // ✅ FIXED
        }

        // 📋 List Screen
        composable("list") {
            GroveListScreen(navController)
        }

        composable("grove_directory") {
            GroveDirectoryScreen(navController)
        }

        composable("map_screen") {
            MapScreen()
        }

        composable(Routes.MYTH) {
            MythLegendScreen(navController)
        }

        composable(Routes.LORE) {
            LoreDetailScreen(navController)
        }

        composable(Routes.REPORT_ISSUE) {
            ReportIssueScreen(navController)
        }

        composable(Routes.SPECIES_INTRO) {
            SpeciesIntroScreen(navController)
        }

        composable(Routes.SPECIES_SCAN) {
            SpeciesScanScreen(navController)
        }

        composable(Routes.PROFILE) {
            ProfilePopupScreen(navController)
        }

        // 🌳 Detail Screen (FIXED)
        composable("detail/{name}") { backStackEntry ->
            val name = backStackEntry.arguments?.getString("name")
            val grove = groveList.find { it.name == name }

            grove?.let {
                GroveDetailScreen(it, navController) // 👈 FIX
            }
        }

        // 🚨 Report Screen (FIXED)
        composable("report") {
            ReportScreen(navController) // 👈 FIX
        }
    }
}