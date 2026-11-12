package com.dofkee.bookme.ui.theme.booklists

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dofkee.bookme.R
import com.dofkee.bookme.ui.theme.BgColor
import com.dofkee.bookme.ui.theme.CoralColor
import com.dofkee.bookme.ui.theme.DarkBgColor
import com.dofkee.bookme.ui.theme.DarkBooklist
import com.dofkee.bookme.ui.theme.LightBooklist
import com.dofkee.bookme.ui.theme.TextColor

@Composable
fun BooklistsScreen(
    modifier: Modifier = Modifier,
    onCollectionClick: (String) -> Unit
) {
    BooklistsContent(
        modifier = modifier,
        onBooklistClick = onCollectionClick
    )
}

@Composable
fun BooklistsContent(
    modifier: Modifier = Modifier,
    onBooklistClick: (String) -> Unit
) {
    val isDarkTheme = isSystemInDarkTheme()

    val bgColor = if (isDarkTheme) DarkBgColor else BgColor
    // было: if (isDarkTheme) TextColor else TextColor — ветви идентичны
    val textColor = TextColor
    val recColor = if (isDarkTheme) DarkBooklist else LightBooklist

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(bgColor)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp)
                .padding(bottom = 100.dp)
        ) {
            Spacer(modifier = Modifier.height(24.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(R.drawable.settingsicon),
                    contentDescription = "Settings",
                    tint = Color(0xFF7C8A97),
                    modifier = Modifier.size(32.dp).clickable { /* Настройки */ }
                )
                Text(
                    text = "BOOKLISTS",
                    color = Color(0xFFFFD54F),
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Black,
                    style = TextStyle(
                        shadow = Shadow(
                            color = Color.Black,
                            offset = Offset(2f, 2f),
                            blurRadius = 2f
                        )
                    )
                )
                Image(
                    painter = painterResource(id = R.drawable.baseuser),
                    contentDescription = "Profile",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                )
            }

            Spacer(modifier = Modifier.height(40.dp))

            Text(
                text = "Мои Буклисты:",
                color = textColor,
                fontSize = 18.sp,
                modifier = Modifier.padding(bottom = 12.dp)
            )

            MenuButton(text = "Сохраненное #1", bgColor = CoralColor, textColor = TextColor) { onBooklistClick("Сохраненное #1") }
            Spacer(modifier = Modifier.height(12.dp))
            MenuButton(text = "Сохраненное #2", bgColor = CoralColor, textColor = TextColor) { onBooklistClick("Сохраненное #2") }
            Spacer(modifier = Modifier.height(12.dp))
            MenuButton(text = "Сохраненное #3", bgColor = CoralColor, textColor = TextColor) { onBooklistClick("Сохраненное #3") }

            Spacer(modifier = Modifier.height(40.dp))

            Text(
                text = "Рекомендуем:",
                color = textColor,
                fontSize = 18.sp,
                modifier = Modifier.padding(bottom = 12.dp)
            )

            MenuButton(text = "Экранизированные книги", bgColor = recColor, textColor = TextColor) { }
            Spacer(modifier = Modifier.height(12.dp))
            MenuButton(text = "Книги на английском языке", bgColor = recColor, textColor = TextColor) { }
            Spacer(modifier = Modifier.height(12.dp))
            MenuButton(text = "Антиутопии", bgColor = recColor, textColor = TextColor) { }
            Spacer(modifier = Modifier.height(12.dp))
            MenuButton(text = "Интеллегенетная классика", bgColor = recColor, textColor = TextColor) { }
        }
    }
}

@Composable
fun MenuButton(
    text: String,
    bgColor: Color,
    textColor: Color,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(52.dp)
            .clip(RoundedCornerShape(26.dp))
            .background(bgColor)
            .clickable { onClick() },
        contentAlignment = Alignment.CenterStart
    ) {
        Text(
            text = text,
            color = textColor,
            fontSize = 15.sp,
            modifier = Modifier.padding(start = 20.dp)
        )
    }
}

@Composable
fun BooklistScreen(title: String, onBack: () -> Unit) {
    val isDarkTheme = isSystemInDarkTheme()
    val bgColor = if (isDarkTheme) DarkBgColor else BgColor
    val textColor = TextColor

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(bgColor)
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Ты не добавил ни одной книги",
            color = textColor,
            fontSize = 24.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(24.dp))
        Button(onClick = onBack) {
            Text("Назад")
        }
    }
}