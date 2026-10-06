package com.pemmob.katalogbuku.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.pemmob.katalogbuku.R
import com.pemmob.katalogbuku.data.model.BookDto
import com.pemmob.katalogbuku.ui.theme.KatalogBukuTheme
import com.pemmob.katalogbuku.ui.theme.Spacing
import com.pemmob.katalogbuku.util.toAuthorText
import com.pemmob.katalogbuku.util.toYearText

@Composable
fun BookItem(
    book: BookDto,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        onClick = onClick,
        modifier = modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.medium
    ) {
        Column(modifier = Modifier.padding(Spacing.lg)) {
            Text(
                text = book.title ?: stringResource(R.string.unknown_title),
                style = MaterialTheme.typography.titleMedium,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = book.authorName.toAuthorText(),
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(top = Spacing.xs)
            )
            Text(
                text = stringResource(R.string.first_publish_prefix, book.firstPublishYear.toYearText()),
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(top = Spacing.xs)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun BookItemPreview() {
    KatalogBukuTheme {
        BookItem(
            book = BookDto(
                key = "1",
                title = "Laskar Pelangi",
                authorName = listOf("Andrea Hirata"),
                firstPublishYear = 2005,
                editionCount = 10,
                language = listOf("ind")
            ),
            onClick = {}
        )
    }
}
@Preview(showBackground = true, uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun BookItemDarkPreview() {
    KatalogBukuTheme {
        BookItem(
            book = BookDto(
                key = "1",
                title = "Laskar Pelangi",
                authorName = listOf("Andrea Hirata"),
                firstPublishYear = 2005,
                editionCount = 10,
                language = listOf("ind")
            ),
            onClick = {}
        )
    }
}
