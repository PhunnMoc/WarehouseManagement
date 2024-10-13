package com.example.warehousemanagement.ui.common

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.onClick
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.warehousemanagement.R

@Composable
fun CustomSearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    onSearch: (String) -> Unit,
    active: Boolean,
    onActiveChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    placeHolder: String = "Search",
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
) {
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current
    Box(
        modifier = modifier
    ) {
        BasicTextField(
            value = query,
            onValueChange = onQueryChange,
            textStyle = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                color = Color.Black
            ),
            enabled = enabled,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(onSearch = { onSearch(query) }),
            singleLine = true,
            modifier = Modifier
                .height(56.dp)
                .focusRequester(focusRequester)
                .onFocusChanged { onActiveChange(it.isFocused) }
                .semantics {
                    onClick {
                        focusRequester.requestFocus()
                        true
                    }
                },
            decorationBox = { innerTextField ->
                Box(
                    contentAlignment = Alignment.CenterStart,
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(
                            width = 1.dp,
                            color = colorResource(id = R.color.line_light_gray),
                            shape = RoundedCornerShape(size = 30.dp)
                        )
                        .padding(start = 20.dp, end = 30.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()

                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.weight(1f)
                        ) {
                            leadingIcon?.let { it() }
                            Box {
                                if (query.isEmpty()) {
                                    Text(
                                        color = colorResource(id = R.color.text_color_light_gray),
                                        text = placeHolder,
                                        fontSize = 15.sp
                                    )
                                }
                                innerTextField()
                            }
                        }
                        Row(
                            horizontalArrangement = Arrangement.End
                        ) {
                            trailingIcon?.let { it() }
                        }
                    }
                }
            }
        )
        LaunchedEffect(active) {
            if (!active) {
                focusManager.clearFocus()
            }
        }
    }
}


@Composable
fun ItemSuggestion(query: String, content: String) {
    val queryWork = query.split(" ")
    val contentWord = content.split(" ")
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp)
            .padding(start = 30.dp, top = 10.dp, bottom = 10.dp)
    ) {
        Text(
            buildAnnotatedString {
                contentWord.map {
                    if (queryWork.contains(it)) {
                        withStyle(style = SpanStyle(color = colorResource(id = R.color.background_theme))) {
                            append("$it ")
                        }
                    } else {
                        withStyle(style = SpanStyle(color = colorResource(id = R.color.text_color_dark_gray))) {
                            append("$it ")
                        }
                    }
                }
            }
        )
    }
}

@Composable
fun SuggestionView(
    listSuggestions: List<String>,
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .offset(0.5.dp)
    ) {
        items(listSuggestions) {
            ItemSuggestion("Trinh Iris", it)
        }
    }
}

@Composable
fun SearchBarWithSuggestion(listSuggestions: List<String>) { //API
    var text by remember {  // text will be a flow saveHandle
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = colorResource(id = R.color.line_light_gray),
                shape = RoundedCornerShape(size = 30.dp)
            )
    ) {
        CustomSearchBar(
            query = text,
            onQueryChange = {
                text = it
            },
            onSearch = {},
            active = true,
            onActiveChange = {},
            placeHolder = "Search for me",
            trailingIcon = {
                Icon(
                    modifier = Modifier.size(20.dp)
                        .clickable {  },
                    tint = colorResource(id = R.color.text_color_dark_gray),
                    painter = painterResource(id = R.drawable.icons8_delete),
                    contentDescription = ""
                )
                Icon(
                    modifier = Modifier.size(20.dp)
                        .clickable {  },
                    tint = colorResource(id = R.color.text_color_dark_gray),
                    painter = painterResource(id = R.drawable.icons8_search),
                    contentDescription = ""
                )
            }
        )
        if (text.isNotEmpty()) {
            SuggestionView(listSuggestions)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SearchBarPreview() {
    var text by remember {
        mutableStateOf("")
    }
    val trailingIcon = remember(text) {
        if (text.isEmpty()) {
            R.drawable.icons8_search
        } else {
            R.drawable.icons8_delete
        }
    }
    CustomSearchBar(
        query = text,
        onQueryChange = {
            text = it
        },
        onSearch = {},
        active = true,
        onActiveChange = {},
        placeHolder = "Search for me",
        trailingIcon = {
            Icon(
                modifier = Modifier.size(20.dp),
                tint = colorResource(id = R.color.text_color_dark_gray),
                painter = painterResource(id = trailingIcon),
                contentDescription = ""
            )
        }
    )
}

@Preview(showBackground = true)
@Composable
fun SuggestionViewPreview() {
    val listSuggestions = listOf<String>("Phuong Trinh", "Bun cha Iris Trinh Area", "Product")
    SuggestionView(listSuggestions)
}

@Preview(showBackground = true)
@Composable
fun SearchBarWithSuggestionPreview() {
    val listSuggestions = listOf<String>("Phuong Trinh", "Bun cha Iris Trinh Area", "Product")
    SearchBarWithSuggestion(listSuggestions)
}