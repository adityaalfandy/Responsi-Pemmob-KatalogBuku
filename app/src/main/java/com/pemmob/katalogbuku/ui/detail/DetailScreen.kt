package com.pemmob.katalogbuku.ui.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.pemmob.katalogbuku.R
import com.pemmob.katalogbuku.data.model.BookDto
import com.pemmob.katalogbuku.ui.components.InfoRow
import com.pemmob.katalogbuku.ui.theme.KatalogBukuTheme
import com.pemmob.katalogbuku.ui.theme.Spacing
import com.pemmob.katalogbuku.util.toAuthorText
import com.pemmob.katalogbuku.util.toEditionText
import com.pemmob.katalogbuku.util.toLanguageText
import com.pemmob.katalogbuku.util.toYearText

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    book: BookDto?,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { 
                    Text(
                        text = stringResource(R.string.detail_title),
                        style = MaterialTheme.typography.titleLarge
                    ) 
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Text(
                            text = stringResource(R.string.icon_back),
                            style = MaterialTheme.typography.titleLarge,
                            color = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) { innerPadding ->
        if (book == null) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(Spacing.xl),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(R.string.unavailable),
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(bottom = Spacing.md)
                )
                Button(onClick = onBackClick) {
                    Text(stringResource(R.string.back))
                }
            }
        } else {
            val scrollState = rememberScrollState()
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .verticalScroll(scrollState)
                    .padding(Spacing.xl)
            ) {
                Text(
                    text = book.title ?: stringResource(R.string.unknown_title),
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.padding(bottom = Spacing.md)
                )
                
                HorizontalDivider(
                    modifier = Modifier.padding(bottom = Spacing.md),
                    color = MaterialTheme.colorScheme.outlineVariant
                )
                
                InfoRow(
                    label = stringResource(R.string.label_author),
                    value = book.authorName.toAuthorText()
                )
                InfoRow(
                    label = stringResource(R.string.label_year),
                    value = book.firstPublishYear.toYearText()
                )
                InfoRow(
                    label = stringResource(R.string.label_edition),
                    value = book.editionCount.toEditionText()
                )
                InfoRow(
                    label = stringResource(R.string.label_language),
                    value = book.language.toLanguageText()
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun DetailScreenPreview() {
    KatalogBukuTheme {
        DetailScreen(
            book = BookDto(
                key = "1",
                title = "Bumi Manusia",
                authorName = listOf("Pramoedya Ananta Toer"),
                firstPublishYear = 1980,
                editionCount = 5,
                language = listOf("ind")
            ),
            onBackClick = {}
        )
    }
}

@Preview(showBackground = true, uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun DetailScreenDarkPreview() {
    KatalogBukuTheme {
        DetailScreen(
            book = BookDto(
                key = "1",
                title = "Bumi Manusia",
                authorName = listOf("Pramoedya Ananta Toer"),
                firstPublishYear = 1980,
                editionCount = 5,
                language = listOf("ind")
            ),
            onBackClick = {}
        )
    }
}
