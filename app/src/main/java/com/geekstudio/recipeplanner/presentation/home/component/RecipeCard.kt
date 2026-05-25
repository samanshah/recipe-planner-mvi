package com.geekstudio.recipeplanner.presentation.home.component

import android.R.attr.onClick
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.geekstudio.recipeplanner.domain.model.Recipe

@Composable
fun RecipeCard(
    recipe: Recipe,
    onFavoriteClick: () -> Unit,
    onClick: () -> Unit
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClick()
            },
        shape = RoundedCornerShape(20.dp)
    ) {

        Column {

            AsyncImage(
                model = recipe.imageUrl,
                contentDescription = recipe.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp),
                contentScale = ContentScale.Crop
            )

            Column(
                modifier = Modifier.padding(16.dp)
            ) {

                Text(
                    text = recipe.title,
                    style = MaterialTheme.typography.titleLarge
                )

            }

            IconButton(
                onClick = onFavoriteClick
            ) {

                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = null
                )

            }

        }

    }

}