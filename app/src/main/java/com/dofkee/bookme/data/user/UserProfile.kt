package com.dofkee.bookme.data.user

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dofkee.bookme.R

// Определение цветов для тем
val DarkBackground = Color(0xFF1E2022)
val DarkFieldBg = Color(0xFF3B3F44)
val LightBackground = Color(0xFFF4F5F7)
val LightFieldBg = Color(0xFFE0E6ED)
val AccentBlue = Color(0xFF0A64B5)
val TextGrey = Color(0xFF8A8D91)
val LightTextGrey = Color(0xFF9EA3A8)

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ProfileScreen(modifier: Modifier) {
    val isDarkTheme = isSystemInDarkTheme()

    val backgroundColor = if (isDarkTheme) DarkBackground else LightBackground
    val fieldBackgroundColor = if (isDarkTheme) DarkFieldBg else LightFieldBg
    val textColor = if (isDarkTheme) Color.White else Color.Black
    val secondaryTextColor = if (isDarkTheme) Color.White.copy(alpha = 0.8f) else Color.Black.copy(alpha = 0.7f)

    // состояния для фио
    var firstName by remember { mutableStateOf("Имя") }
    var lastName by remember { mutableStateOf("Фамилия") }
    var userTag by remember { mutableStateOf("@тег") }

    // жанры
    val allGenres = listOf("Комедия", "Современная проза", "Приключения", "Фэнтези", "Фантастика", "Триллер", "Ужасы", "Мистика", "Нон-фикшн", "Сказки", "Детектив")
    val selectedGenres = remember { mutableStateListOf("", "", "", "", "") }

    Scaffold(
        containerColor = backgroundColor
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp)
        ) {
            // НАЗАД
            IconButton(
                onClick = { },
                modifier = Modifier
                    .padding(top = 16.dp)
                    .offset(x = (-12).dp)
            ) {
                Icon(
                    painter = painterResource(R.drawable.settingsicon),
                    contentDescription = "Back",
                    tint = if (isDarkTheme) Color.White else Color(0xFF333333),
                    modifier = Modifier.size(32.dp)
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // фио и фото
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                // будущая картинка профиля
                Box(
                    modifier = Modifier
                        .size(110.dp)
                        .clip(CircleShape)
                        .background(Color.Gray)
                )

                Spacer(modifier = Modifier.width(16.dp))

                Column {
                    Text(
                        text = "$firstName $lastName",
                        color = textColor,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium
                    )
                    Text(
                        text = userTag,
                        color = secondaryTextColor,
                        fontSize = 14.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // поле ввода
            Text("Имя", color = textColor, fontSize = 16.sp, modifier = Modifier.padding(bottom = 8.dp))

            // блок с фио
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .background(fieldBackgroundColor)
            ) {
                CustomTextField(
                    value = firstName,
                    onValueChange = { firstName = it },
                    textColor = textColor
                )
                HorizontalDivider(color = backgroundColor, thickness = 1.dp)
                CustomTextField(
                    value = lastName,
                    onValueChange = { lastName = it },
                    textColor = textColor
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // тег
            Text("Тэг пользователя", color = textColor, fontSize = 16.sp, modifier = Modifier.padding(bottom = 8.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .background(fieldBackgroundColor)
            ) {
                CustomTextField(
                    value = userTag,
                    onValueChange = { userTag = it },
                    textColor = textColor
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // др
            Text("Дата рождения", color = textColor, fontSize = 16.sp, modifier = Modifier.padding(bottom = 8.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .background(fieldBackgroundColor.copy(alpha = 0.5f)) // прозрачность?
                    .padding(16.dp)
            ) {
                Text("01.01.2001", color = TextGrey, fontSize = 16.sp)
            }

            Spacer(modifier = Modifier.height(32.dp))

            // мои жанры
            Text("Мои жанры", color = textColor, fontSize = 18.sp, fontWeight = FontWeight.Medium, modifier = Modifier.padding(bottom = 12.dp))

            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                allGenres.forEach { genre ->
                    val isSelected = selectedGenres.contains(genre)
                    GenreChip(
                        text = genre,
                        isSelected = isSelected,
                        isDarkTheme = isDarkTheme,
                        onClick = {
                            if (isSelected) {
                                selectedGenres.remove(genre)
                            } else {
                                selectedGenres.add(genre)
                            }
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}

@Composable
fun CustomTextField(
    value: String,
    onValueChange: (String) -> Unit,
    textColor: Color
) {
    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        textStyle = TextStyle(color = textColor, fontSize = 16.sp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 16.dp),
        cursorBrush = SolidColor(textColor)
    )
}

@Composable
fun GenreChip(
    text: String,
    isSelected: Boolean,
    isDarkTheme: Boolean,
    onClick: () -> Unit
) {
    // темы
    val backgroundColor = if (isSelected) {
        AccentBlue
    } else {
        if (isDarkTheme) DarkBackground else LightBackground
    }
    val textColor = if (isSelected) {
        Color.White
    } else {
        if (isDarkTheme) LightTextGrey else TextGrey
    }
    val borderColor = if (isSelected) AccentBlue else textColor.copy(alpha = 0.3f)

    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(50))
            .background(backgroundColor)
            .border(1.dp, borderColor, RoundedCornerShape(50))
            .clickable { onClick() }
            .padding(horizontal = 12.dp, vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (!isSelected) {
            Icon(
                // временные иконки
                painter = painterResource(R.drawable.thumbs_downicon),
                contentDescription = null,
                tint = textColor,
                modifier = Modifier
                    .size(14.dp)
                    .padding(end = 4.dp)
            )
        }
        Text(
            text = text,
            color = textColor,
            fontSize = 13.sp
        )
    }
}