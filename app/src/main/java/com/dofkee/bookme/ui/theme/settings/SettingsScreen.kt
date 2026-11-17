package com.dofkee.bookme.ui.theme.settings

import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.os.LocaleListCompat
import com.dofkee.bookme.R
import com.dofkee.bookme.ui.theme.TextColor

enum class AppLanguage(val code: String, val labelRes: Int) {
    SYSTEM("",   R.string.language_system),
    ENGLISH("en", R.string.language_english),
    RUSSIAN("ru", R.string.language_russian),
    SPANISH("es", R.string.language_spanish),
    DEICHLAND("de", R.string.language_deuchland),
    FRENCH("fr", R.string.language_franch),
    JAPANISE("ja", R.string.language_japanise),
    PORTUGISE("pt", R.string.language_portugal),
    UKRAIN("uk", R.string.language_ukrain),
    CHEC("ch", R.string.language_cheh)

}

@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier,
    onBack: () -> Unit
) {
    var showLanguageDialog by remember { mutableStateOf(false) }

    val currentLanguage = rememberCurrentLanguage()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 16.dp)
    ) {
        // Шапка
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            IconButton(onClick = onBack) {
                Icon(
                    painter = painterResource(R.drawable.strelka_back),
                    contentDescription = stringResource(R.string.back),
                    tint = TextColor
                )
            }
            Text(text = stringResource(R.string.settings))
        }

        Spacer(Modifier.height(24.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { showLanguageDialog = true }
                .padding(vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = stringResource(R.string.change_language))
            Text(text = stringResource(currentLanguage.labelRes))
        }
    }

    if (showLanguageDialog) {
        LanguagePickerDialog(
            selected = currentLanguage,
            onDismiss = { showLanguageDialog = false },
            onSelect = { lang ->
                applyLanguage(lang)
                showLanguageDialog = false
            }
        )
    }
}

@Composable
private fun rememberCurrentLanguage(): AppLanguage {
    val locales = AppCompatDelegate.getApplicationLocales()
    return remember(locales) {
        if (locales.isEmpty) {
            AppLanguage.SYSTEM
        } else {
            val langCode = locales.get(0)?.language ?: ""
            AppLanguage.entries.firstOrNull { it.code == langCode } ?: AppLanguage.SYSTEM
    }
}
}


private fun applyLanguage(lang: AppLanguage) {
    val locales = if (lang == AppLanguage.SYSTEM) {
        LocaleListCompat.getEmptyLocaleList()
    } else {
        LocaleListCompat.forLanguageTags(lang.code)
    }
    AppCompatDelegate.setApplicationLocales(locales)
}

@Composable
private fun LanguagePickerDialog(
    selected: AppLanguage,
    onDismiss: () -> Unit,
    onSelect: (AppLanguage) -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(stringResource(R.string.choose_language)) },
        text = {
            Column {
                AppLanguage.entries.forEach { lang ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onSelect(lang) }
                            .padding(vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = lang == selected,
                            onClick = { onSelect(lang) }
                        )
                        Spacer(Modifier.width(8.dp))
                        Text(text = stringResource(lang.labelRes))
                    }
                }
            }
        },
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text(stringResource(R.string.cancel))
            }
        }
    )
}