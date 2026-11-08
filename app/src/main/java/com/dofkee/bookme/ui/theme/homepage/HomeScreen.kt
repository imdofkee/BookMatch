package com.dofkee.bookme.ui.theme.homepage

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.dofkee.bookme.R
import com.dofkee.bookme.domain.model.Book
import com.dofkee.bookme.ui.component.BookCard
import com.dofkee.bookme.ui.theme.BgColor
import com.dofkee.bookme.ui.theme.CoralColor
import com.dofkee.bookme.ui.theme.DarkBlueColor
import com.dofkee.bookme.ui.theme.OrangeColor
import com.dofkee.bookme.ui.theme.TextColor

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onSettingsClick: () -> Unit = {},
    onProfileClick: () -> Unit = {},
    onBooklistsClick: () -> Unit = {},
    onReadClick: () -> Unit = {},
    onLike: () -> Unit = {},
    onDislike: () -> Unit = {},
    onReadLater: () -> Unit = {}
) {
    // мок книга
    val testBook = Book(
        id = 1,
        title = "451 градус по Фаренгейту",
        authors = listOf("Рэй Брэдбери"),
        bookAnnotation = "451 градус по Фаренгейту — температура, при которой воспламеняется и горит бумага. Главный герой — Монтэг, пожарник, но смысл этой профессии давно изменился...",
        photo = "https://imo10.labirint.ru/books/712914/cover.jpg/484-0",
        genres = listOf("Фантастика")
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(BgColor)
            .padding(horizontal = 24.dp, vertical = 16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onSettingsClick) {
                Icon(
                    painter = painterResource(R.drawable.settingsicon),
                    contentDescription = "Настройки",
                    tint = TextColor
                )
            }

            IconButton(onClick = onProfileClick) {
                Icon(
                    painter = painterResource(id = R.drawable.usericon),
                    contentDescription = "Профиль",
                    modifier = Modifier.size(36.dp)
                )
            }
        }

        Spacer(Modifier.height(24.dp))

        Box(modifier = Modifier.weight(1f)) {
            BookCard(book = testBook)
        }

        Spacer(Modifier.height(24.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Дизлайк
            Box(
                modifier = Modifier
                    .weight(0.3f)
                    .height(50.dp)
                    .background(DarkBlueColor, RoundedCornerShape(16.dp))
                    .clickable { onDislike() },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(R.drawable.thumbs_downicon),
                    contentDescription = "Дизлайк",
                    tint = Color.White
                )
            }

            Box(
                modifier = Modifier
                    .weight(0.35f)
                    .height(50.dp)
                    .background(OrangeColor, RoundedCornerShape(16.dp))
                    .clickable { onReadLater() },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(R.drawable.sleepicon),
                    contentDescription = "Позже",
                    modifier = Modifier.size(24.dp),
                    tint = TextColor
                )
            }
            Box(
                modifier = Modifier
                    .weight(0.35f)
                    .height(50.dp)
                    .background(CoralColor, RoundedCornerShape(16.dp))
                    .clickable { onLike() },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(R.drawable.likeixon), // Твоя иконка лайка
                    contentDescription = "Лайк",
                    tint = Color.White
                )
            }
        }
    }
}