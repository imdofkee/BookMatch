package com.dofkee.bookme.ui.theme.homepage

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.dofkee.bookme.R
import com.dofkee.bookme.ui.theme.BgColor
import com.dofkee.bookme.ui.theme.BookUiState
import com.dofkee.bookme.ui.theme.BookViewModel
import com.dofkee.bookme.ui.theme.CoralColor
import com.dofkee.bookme.ui.theme.DarkBgColor
import com.dofkee.bookme.ui.theme.DarkBlueColor
import com.dofkee.bookme.ui.theme.OrangeColor
import com.dofkee.bookme.ui.theme.TextColor

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: BookViewModel = hiltViewModel(),
    onSettingsClick: () -> Unit = {},
    onProfileClick: () -> Unit = {}
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    val books = (state as? BookUiState.Success)?.books ?: emptyList()
    val pagerState = rememberPagerState(pageCount = { books.size })
    val currentBook = books.getOrNull(pagerState.currentPage)

    val bgColor = if (isSystemInDarkTheme()) DarkBgColor else BgColor

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(bgColor)
            .padding(horizontal = 24.dp, vertical = 16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onSettingsClick) {
                Icon(painterResource(R.drawable.settingsicon), "Настройки", tint = TextColor)
            }
            IconButton(onClick = onProfileClick) {
                Icon(painterResource(R.drawable.usericon), "Профиль", modifier = Modifier.size(36.dp))
            }
        }

        Spacer(Modifier.height(24.dp))

        Box(modifier = Modifier.weight(1f)) {
            when (state) {
                is BookUiState.Loading -> CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center),
                    color = OrangeColor
                )
                is BookUiState.Error -> Text(
                    text = (state as BookUiState.Error).message,
                    color = Color.Red,
                    modifier = Modifier.align(Alignment.Center)
                )
                is BookUiState.Success -> {
                    if (books.isEmpty()) {
                        Text("Кажется, что книги закончились!", color = TextColor, modifier = Modifier.align(Alignment.Center))
                    } else {
                        HorizontalPager(
                            state = pagerState,
                            modifier = Modifier.fillMaxSize()
                        ) { page ->
                            BookCard(book = books[page])
                        }
                    }
                }
            }
        }

        Spacer(Modifier.height(24.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Box(
                modifier = Modifier
                    .weight(0.3f)
                    .height(50.dp)
                    .background(DarkBlueColor, RoundedCornerShape(16.dp))
                    .clickable(enabled = currentBook != null) {
                        currentBook?.let { viewModel.onDislikeClicked(it) }
                    },
                contentAlignment = Alignment.Center
            ) {
                Icon(painterResource(R.drawable.thumbs_downicon), "Дизлайк", tint = Color.White)
            }

            Box(
                modifier = Modifier
                    .weight(0.35f)
                    .height(50.dp)
                    .background(OrangeColor, RoundedCornerShape(16.dp))
                    .clickable(enabled = currentBook != null) {
                        currentBook?.let { viewModel.onLaterClicked(it) }
                    },
                contentAlignment = Alignment.Center
            ) {
                Icon(painterResource(R.drawable.sleepicon), "Позже", modifier = Modifier.size(24.dp), tint = TextColor)
            }

            Box(
                modifier = Modifier
                    .weight(0.35f)
                    .height(50.dp)
                    .background(CoralColor, RoundedCornerShape(16.dp))
                    .clickable(enabled = currentBook != null) {
                        currentBook?.let { viewModel.onLikeClicked(it) }
                    },
                contentAlignment = Alignment.Center
            ) {
                Icon(painterResource(R.drawable.likeixon), "Лайк", tint = Color.White)
            }
        }

        Spacer(Modifier.height(16.dp))
    }
}