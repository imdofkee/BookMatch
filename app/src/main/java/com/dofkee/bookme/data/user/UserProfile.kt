package com.dofkee.bookme.data.user

import androidx.compose.foundation.Image
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dofkee.bookme.R
import com.dofkee.bookme.ui.theme.BgColor
import com.dofkee.bookme.ui.theme.CoralColor
import com.dofkee.bookme.ui.theme.DarkBgColor
import com.dofkee.bookme.ui.theme.DarkBlueColor
import com.dofkee.bookme.ui.theme.DarkFilledTextInput
import com.dofkee.bookme.ui.theme.FilledTextInput
import com.dofkee.bookme.ui.theme.GrayTextColor
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.TimeZone

@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(modifier: Modifier) {
    val isDarkTheme = isSystemInDarkTheme()

    val backgroundColor = if (isDarkTheme) DarkBgColor else BgColor
    val fieldBackgroundColor = if (isDarkTheme) DarkFilledTextInput else FilledTextInput
    val textColor = if (isDarkTheme) Color.White else Color.Black
    val secondaryTextColor = if (isDarkTheme) Color.White.copy(alpha = 0.8f) else Color.Black.copy(alpha = 0.7f)
    val placeholderColor = if (isDarkTheme) GrayTextColor else GrayTextColor

    // все состояния
    var firstName by remember { mutableStateOf("Как тебя зовут?") }
    var lastName by remember { mutableStateOf("") }
    var userTag by remember { mutableStateOf("") }
    var birthDateMillis by remember { mutableStateOf<Long?>(null) }
    var showDatePicker by remember { mutableStateOf(false) }

    // жанры
    val allGenres = listOf("Комедия", "Современная проза", "Приключения", "Фэнтези", "Фантастика", "Триллер", "Ужасы", "Мистика", "Нон-фикшн", "Сказки", "Детектив")
    val selectedGenres = remember { mutableStateListOf("", "", "", "", "") }

    val displayName = listOf(firstName.trim(), lastName.trim()).filter { it.isNotBlank() }.joinToString(" ")
    val displayTag = userTag.trim()

    // форматирование др
    val birthDateText = remember(birthDateMillis) {
        birthDateMillis?.let { millis ->
            val sdf = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
            sdf.timeZone = TimeZone.getTimeZone("UTC")
            sdf.format(Date(millis))
        } ?: "ДД.ММ.ГГГГ"
    }

    Scaffold(
        containerColor = backgroundColor
    ) { paddingValues ->
        Column(
            modifier = Modifier.fillMaxSize().padding(paddingValues).verticalScroll(rememberScrollState()).padding(horizontal = 20.dp)
        ) {
            // НАЗАД
            IconButton(
                onClick = { },
                modifier = Modifier
                    .padding(top = 16.dp)
                    .offset(x = (-12).dp)
            ) {
                Icon(
                    painter = painterResource(R.drawable.strelka_back),
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
                Image(
                    painter = painterResource(R.drawable.baseuser),
                    contentDescription = "Фото профиля",
                    modifier = Modifier
                        .size(110.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.width(16.dp))

                Column {
                    Text(
                        text = displayName.ifBlank { "Имя Фамилия" },
                        color = if (displayName.isBlank()) placeholderColor else textColor,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium
                    )
                    Text(
                        text = if (displayTag.isBlank()) "@тег" else "@$displayTag",
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
                    .clip(RoundedCornerShape(23.dp))
                    .background(fieldBackgroundColor)
            ) {
                CustomTextField(
                    value = firstName,
                    onValueChange = { firstName = it },
                    textColor = textColor,
                    placeholder = "Имя",
                    placeholderColor = placeholderColor
                )
                HorizontalDivider(color = backgroundColor, thickness = 1.dp)
                CustomTextField(
                    value = lastName,
                    onValueChange = { lastName = it },
                    textColor = textColor,
                    placeholder = "Фамилия",
                    placeholderColor = placeholderColor
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // тег
            Text("Тэг пользователя", color = textColor, fontSize = 16.sp, modifier = Modifier.padding(bottom = 8.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(23.dp))
                    .background(fieldBackgroundColor)
            ) {
                CustomTextField(
                    value = userTag,
                    onValueChange = { raw ->
                        userTag = raw.filter { ch ->
                            (ch in 'a'..'z') || (ch in 'A'..'Z') ||
                                    (ch in '0'..'9') || ch == '_'
                        }
                    },
                    textColor = textColor,
                    placeholder = "тег",
                    placeholderColor = placeholderColor,
                    prefix = "@"
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // др datePicker
            Text("Дата рождения", color = textColor, fontSize = 16.sp, modifier = Modifier.padding(bottom = 8.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(23.dp))
                    .background(fieldBackgroundColor)
                    .clickable { showDatePicker = true }
                    .padding(16.dp)
            ) {
                Text(
                    text = birthDateText,
                    color = if (birthDateMillis == null) placeholderColor else textColor,
                    fontSize = 16.sp
                )
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

    // выбор даты
    if (showDatePicker) {
        val currentYear = Calendar.getInstance().get(Calendar.YEAR)
        val datePickerState = rememberDatePickerState(
            initialSelectedDateMillis = birthDateMillis,
            yearRange = 1900..currentYear
        )
        DatePickerDialog(
            onDismissRequest = { showDatePicker = false },
            confirmButton = {
                TextButton(onClick = {
                    birthDateMillis = datePickerState.selectedDateMillis
                    showDatePicker = false
                }) { Text("OK") }
            },
            dismissButton = {
                TextButton(onClick = { showDatePicker = false }) { Text("Отмена") }
            }
        ) {
            DatePicker(state = datePickerState)
        }
    }
}

@Composable
fun CustomTextField(
    value: String,
    onValueChange: (String) -> Unit,
    textColor: Color,
    placeholder: String,
    placeholderColor: Color,
    prefix: String? = null
) {
    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        textStyle = TextStyle(color = textColor, fontSize = 16.sp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 16.dp),
        cursorBrush = SolidColor(textColor),
        decorationBox = { innerTextField ->
            Row(verticalAlignment = Alignment.CenterVertically) {
                if (prefix != null) {
                    Text(
                        text = prefix,
                        color = textColor,
                        fontSize = 16.sp
                    )
                }
                Box(modifier = Modifier.weight(1f)) {
                    if (value.isEmpty()) {
                        Text(
                            text = placeholder,
                            color = placeholderColor,
                            fontSize = 16.sp
                        )
                    }
                    innerTextField()
                }
            }
        }
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
        DarkBlueColor
    } else {
        if (isDarkTheme) DarkBgColor else BgColor
    }
    val textColor = if (isSelected) {
        Color.White
    } else {
        GrayTextColor
    }
    val borderColor = if (isSelected) DarkBlueColor else textColor.copy(alpha = 0.3f)

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
                painter = painterResource(R.drawable.strelka_back),
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