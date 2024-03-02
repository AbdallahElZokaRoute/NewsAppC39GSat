package com.route.newsappc39_g_sat.fragments

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.route.newsappc39_g_sat.R
import com.route.newsappc39_g_sat.model.Category
import com.route.newsappc39_g_sat.model.Constants
import com.route.newsappc39_g_sat.model.NewsFragment


@Composable
fun CategoriesFragment(navHostController: NavHostController) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {
        Text(
            text = stringResource(R.string.pick_your_category) +
                    stringResource(R.string.of_interest),
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )
        CategoriesList(navHostController)
    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CategoriesFragmentPreview() {
    CategoriesFragment(rememberNavController())
}

@Composable
fun CategoriesList(navHostController: NavHostController) {
    LazyVerticalGrid(columns = GridCells.Fixed(2)) {
        items(Constants.categories.size) { position ->
            CategoryCard(
                category = Constants.categories.get(position),
                position,
                navHostController = navHostController
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryCard(category: Category, index: Int, navHostController: NavHostController) {
    Card(
        colors = CardDefaults.cardColors(containerColor = colorResource(id = category.backgroundColor)),
        shape = if (index % 2 == 0)
            RoundedCornerShape(topEnd = 24.dp, topStart = 24.dp, bottomStart = 24.dp)
        else
            RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp, bottomEnd = 24.dp),
        modifier = Modifier
            .padding(16.dp),
        onClick = {
            navHostController.navigate("${NewsFragment.route}/${category.apiID}")
        }
    ) {
        Image(
            painter = painterResource(id = category.drawableResId),
            contentDescription = stringResource(
                R.string.category_image
            ), modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .height(100.dp),
            contentScale = ContentScale.Crop
        )
        Text(
            text = stringResource(id = category.titleResID),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 8.dp),
            fontSize = 22.sp,
            fontWeight = FontWeight.Normal,
            color = Color.White
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CategoriesListPreview() {
    CategoriesList(rememberNavController())
}
