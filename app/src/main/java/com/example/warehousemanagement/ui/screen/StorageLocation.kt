
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.warehousemanagement.R
import com.example.warehousemanagement.domain.model.StorageLocation
import com.example.warehousemanagement.test.listLocation
import com.example.warehousemanagement.ui.common.FilterAndSortButtons
import com.example.warehousemanagement.ui.common.HeaderOfScreen
import com.example.warehousemanagement.ui.common.MiniButton
import com.example.warehousemanagement.ui.common.SearchBarPreview

@Composable
fun WarehouseManagementScreen(warehouseAreas: List<StorageLocation>) {
    var newAreaName by remember { mutableStateOf(TextFieldValue("")) }
    var newAreaDescription by remember { mutableStateOf(TextFieldValue("")) }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.padding(16.dp).fillMaxSize()) {
            HeaderOfScreen(
                mainTitleText = "STORAGE LOCATION",
                startContent = {
                    Image(
                        painter = painterResource(id = R.drawable.icons8_back),
                        contentDescription = "Back",
                        modifier = Modifier
                            .size(25.dp)
                            .clickable {
                                /*TODO: Implement back navigation*/
                            }
                    )
                },
                endContent = {}
            )

            SearchBarPreview()
            FilterAndSortButtons(onFilterClick = { /*TODO*/ }) { /*TODO*/ }

            Spacer(modifier = Modifier.height(16.dp))

            // Hiển thị danh sách khu vực trong các thẻ
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(warehouseAreas) { area ->
                    WarehouseAreaCard(area, onClick = {})
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }

        // MiniButton luôn hiển thị trên danh sách ở góc dưới bên phải
        MiniButton(
            onClick = { /* Handle button click */ },
            icon = painterResource(id = R.drawable.ic_add_mini_button),
            contentDescription = "Add Icon",
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)  // Thêm khoảng cách từ viền màn hình
        )
    }
}


@Composable
fun WarehouseAreaCard(
    area: StorageLocation,
    onClick: () -> Unit
) {
    Card(
        elevation = 4.dp,
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "ID: ${area.id}", style = MaterialTheme.typography.subtitle2)
            Text(text = "Tên: ${area.storageLocationName}", style = MaterialTheme.typography.h6)

            // Hiển thị hình ảnh từ URL
            Image(
                painter = rememberImagePainter(data = "area.storageLocationImageUrl"),  // Tải ảnh từ URL
                contentDescription = "",
                modifier = Modifier
                    .width(50.dp)
                    .height(50.dp)
                    .padding(top = 8.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewWarehouseManagementScreen() {
    WarehouseManagementScreen(listLocation)
}
