package com.example.warehousemanagement.domain.feature.ui.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.warehousemanagement.R

@Composable
fun FunctionContainer(isAdmin: Boolean) {
    Column {
        if (isAdmin){
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                repeat(3) {
                    ItemFunction("Function", R.drawable.icons8_square_function,100.dp)
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {

                repeat(3) {
                    ItemFunction("Function", R.drawable.icons8_square_function,100.dp)
                }
            }

        }
        else{
            Row (
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,

            ){
                Text("Shortcut",
                    modifier = Modifier.padding(start = 16.dp),
                    fontSize = 11.sp)

            }
            Spacer(modifier = Modifier.padding(5.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {

                repeat(3) {
                    ItemFunction("Function", R.drawable.icons8_square_function,80.dp)
                }
                ItemFunction(functionName = "More", iconResource = R.drawable.icons8_squared_menu_80,80.dp)
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun PreviewFunctionContainer() {
    FunctionContainer(isAdmin = false)
}