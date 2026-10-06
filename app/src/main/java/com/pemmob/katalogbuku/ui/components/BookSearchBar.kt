package com.pemmob.katalogbuku.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pemmob.katalogbuku.R
import com.pemmob.katalogbuku.ui.theme.KatalogBukuTheme

@Composable
fun BookSearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    onSearch: () -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = query,
        onValueChange = onQueryChange,
        modifier = modifier.fillMaxWidth(),
        shape = CircleShape,
        placeholder = { Text(stringResource(R.string.search_placeholder)) },
        leadingIcon = {
            Text(stringResource(R.string.icon_search))
        },
        trailingIcon = {
            if (query.isNotEmpty()) {
                IconButton(onClick = {
                    onQueryChange("")
                }) {
                    Text(stringResource(R.string.icon_clear))
                }
            }
        },
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions(onSearch = { onSearch() }),
        singleLine = true,
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = MaterialTheme.colorScheme.surface,
            unfocusedContainerColor = MaterialTheme.colorScheme.surface
        )
    )
}

@Preview(showBackground = true)
@Composable
private fun BookSearchBarPreview() {
    KatalogBukuTheme {
        BookSearchBar(
            query = "Kancil",
            onQueryChange = {},
            onSearch = {},
            modifier = Modifier.padding(16.dp)
        )
    }
}
@Preview(showBackground = true, uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun BookSearchBarDarkPreview() {
    KatalogBukuTheme {
        BookSearchBar(
            query = "Kancil",
            onQueryChange = {},
            onSearch = {},
            modifier = Modifier.padding(16.dp)
        )
    }
}
