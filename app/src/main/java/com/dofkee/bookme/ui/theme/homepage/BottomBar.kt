package com.dofkee.bookme.ui.theme.homepage

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.dofkee.bookme.R
import com.dofkee.bookme.Routes
import com.dofkee.bookme.navigateTab
import com.dofkee.bookme.ui.theme.OrangeColor
import com.dofkee.bookme.ui.theme.TextColor

@Composable
fun BottomBar(navController: NavHostController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 8.dp)
            .height(64.dp)
            .background(OrangeColor, RoundedCornerShape(32.dp))
            .padding(horizontal = 32.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(R.drawable.favoriteicon),
            contentDescription = "Буклисты",
            tint = TextColor,
            modifier = Modifier
                .size(28.dp)
                .clickable { navController.navigateTab(Routes.BOOKLISTS) }
        )
        Icon(
            painter = painterResource(R.drawable.bookicon),
            contentDescription = "Главная",
            tint = TextColor,
            modifier = Modifier
                .size(28.dp)
                .clickable { navController.navigateTab(Routes.MAIN_PAGE) }
        )
        Icon(
            painter = painterResource(R.drawable.usericon),
            contentDescription = "Профиль",
            tint = TextColor,
            modifier = Modifier
                .size(28.dp)
                .clickable { navController.navigateTab(Routes.ACCOUNT) }
        )
    }
}