package com.dofkee.bookme.ui.theme.homepage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import com.dofkee.bookme.R
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.tooling.parseSourceInformation
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dofkee.bookme.ui.theme.BgColor
import com.dofkee.bookme.ui.theme.CoralColor
import com.dofkee.bookme.ui.theme.DarkBlueColor
import com.dofkee.bookme.ui.theme.OrangeColor
import com.dofkee.bookme.ui.theme.TextColor


@Composable
fun HomeScreen(modifier: Modifier) {
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
            Icon(
                painter = painterResource(R.drawable.settingsicon),
                contentDescription = "Настройки",
            )

            Box(
                modifier = Modifier
                    .size(10.dp)
                    .clip(CircleShape)
                    .background(Color.LightGray),
                contentAlignment = Alignment.Center
            ) {
//                painter = painterResource(R.drawable.usericon)
                Icon(
                    painter = painterResource(id = R.drawable.usericon),
                    contentDescription = "Профиль",
                    tint = Color.White,
                    modifier = Modifier.size(100.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            shape = RoundedCornerShape(32.dp),
            colors = CardDefaults.cardColors(containerColor = OrangeColor)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                verticalArrangement = Arrangement.Bottom // Прижимаем текст к низу
            ) {
                Text(
                    text = "Рей Бредбери",
                    color = TextColor,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "451 ГРАДУС ПО ФАРЕНГЕЙТУ",
                    color = TextColor,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Black,
                    letterSpacing = 1.sp
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = "451 градус по Фаренгейту — температура, при которой воспламеняется и горит бумага. Главный герой — Монтэг — пожарник, но смысл этой профессии давно изменился. Дома теперь строятся из термостойких сплавов, а пожарники занимаются тем, что сжигают книги. Не произведения определенных авторов — запрещена литература вообще и люди, хранящие и читающие книги, совершают преступление против государства. Бессмысленные развлечения, успокоительные таблетки, выматывающая работа — вот и все занятия человека. Уставший от такой жизни Монтэг прочитывает первую свою книгу...",
                    color = TextColor,
                    fontSize = 10.sp,
                    lineHeight = 18.sp,
//                    textAlign = TextAlign.Justify
                )
            }
        }

        Spacer(modifier = modifier)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Кнопка дизлайка
            Box(
                modifier = Modifier
                    .weight(0.3f)
                    .height(50.dp)
                    .background(DarkBlueColor, RoundedCornerShape(16.dp)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(R.drawable.thumbs_downicon),
                    contentDescription = "Дизлайк",
                    tint = TextColor
                )
            }

            Box(
                modifier = Modifier
                    .weight(0.35f)
                    .height(50.dp)
                    .background(OrangeColor, RoundedCornerShape(16.dp)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(R.drawable.sleepicon),
                    contentDescription = "Later",
                    modifier = modifier.size(10.dp),
                    tint = TextColor
                )
            }

            Box(
                modifier = Modifier
                    .weight(0.35f)
                    .height(50.dp)
                    .background(CoralColor, RoundedCornerShape(16.dp))
            ) {

                Icon(
                    painter = painterResource(R.drawable.likeixon),
                    contentDescription = "Like"
                )

            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
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
                modifier = Modifier.size(28.dp)
            )
            Icon(
                painter = painterResource(R.drawable.bookicon),
                contentDescription = "Читать",
                tint = TextColor,
                modifier = Modifier.size(28.dp)
            )
            Icon(
                painter = painterResource(R.drawable.usericon),
                contentDescription = "Профиль",
                tint = TextColor,
                modifier = Modifier.size(28.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp)) // Отступ снизу системной панели
    }
}
