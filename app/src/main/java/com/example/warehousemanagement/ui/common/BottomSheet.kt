
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.ListItem
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class) // Opt-in required for ModalBottomSheetLayout
@Composable
fun BottomSheet(
    bottomSheetTitle: String,
    items: List<String>,
    onItemClick: (String) -> Unit
) {
    val sheetState = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)
    val scope = rememberCoroutineScope()
    var searchText by remember { mutableStateOf("") }

    ModalBottomSheetLayout(
        sheetState = sheetState,
        sheetContent = {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center // Center the text horizontally
                ) {
                    Text(
                        text = bottomSheetTitle,
                        style = MaterialTheme.typography.h6
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Search Bar
                TextField(
                    value = searchText,
                    onValueChange = { searchText = it },
                    placeholder = { Text("Search") },
                    leadingIcon = {
                        Icon(Icons.Filled.Search, contentDescription = "Search Icon")
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = MaterialTheme.colors.surface
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))

                // LazyColumn with filtered items
                LazyColumn {
                    val filteredItems =
                        items.filter { it.contains(searchText, ignoreCase = true) }  // Filter list

                    items(filteredItems.size) { index ->  // Use size-based items with index
                        val item = filteredItems[index]  // Get item by index
                        ListItem(
                            text = { Text(item) },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp)
                                .clickable { onItemClick(item) }
                        )
                        Divider()
                    }
                }

            }
        }
    ) {
        // Button to trigger bottom sheet
        Button(onClick = {
            scope.launch {
                sheetState.show() // Show the bottom sheet when button is clicked
            }
        }) {
            Text("Open Bottom Sheet")
        }
    }
}

@Preview
@Composable
fun PreviewBottomSheet() {
    val items = listOf("Action", "Drama", "Comedy", "Thriller", "Sci-Fi", "Fantasy")
    BottomSheet(bottomSheetTitle = "GENRE", items = items, onItemClick = { genre ->
        // Handle item click
        println("Selected Genre: $genre")
    })
}
