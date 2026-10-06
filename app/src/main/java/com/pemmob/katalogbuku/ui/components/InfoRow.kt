package com.pemmob.katalogbuku.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.pemmob.katalogbuku.ui.theme.KatalogBukuTheme
import com.pemmob.katalogbuku.ui.theme.Spacing

@Composable
fun InfoRow(
    label: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = Spacing.xs)
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.weight(1f)
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.weight(2f)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun InfoRowPreview() {
    KatalogBukuTheme {
        InfoRow(label = "PENULIS", value = "Andrea Hirata")
    }
}

@Preview(showBackground = true, uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun InfoRowDarkPreview() {
    KatalogBukuTheme {
        InfoRow(label = "PENULIS", value = "Andrea Hirata")
    }
}
