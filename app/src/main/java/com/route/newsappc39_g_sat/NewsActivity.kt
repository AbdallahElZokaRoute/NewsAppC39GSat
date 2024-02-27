package com.route.newsappc39_g_sat

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.route.newsappc39_g_sat.api.APIManager
import com.route.newsappc39_g_sat.model.ArticlesItem
import com.route.newsappc39_g_sat.model.ArticlesResponse
import com.route.newsappc39_g_sat.model.Constants
import com.route.newsappc39_g_sat.ui.theme.NewsAppC39GSatTheme
import com.route.newsappc39_g_sat.ui.theme.green
import com.route.newsappc39_g_sat.utils.NewsDrawerSheet
import com.route.newsappc39_g_sat.utils.NewsList
import com.route.newsappc39_g_sat.utils.NewsSourcesTabRow
import com.route.newsappc39_g_sat.utils.NewsTopAppBar
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewsAppC39GSatTheme {
                NewsScreen()
            }
        }
    }
}

@Composable
fun NewsScreen() {
    // Scaffold == Coordinator Layout
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    ModalNavigationDrawer(drawerContent = {
        // Drawer Sheet
        NewsDrawerSheet()
    }, drawerState = drawerState) {

        Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
            NewsTopAppBar {
                // Open Navigation Drawer
                // Coroutines
                scope.launch {
                    drawerState.open()
                }
            }
        }
        ) { paddingValues ->
            NewsFragment(
                Modifier
                    .paint(
                        painter = painterResource(id = R.drawable.pattern),
                        contentScale = ContentScale.Crop
                    )
                    .padding(
                        top = paddingValues.calculateTopPadding()
                    )
            )
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun NewsScreenPreview() {
    NewsScreen()
}

@Composable
fun NewsFragment(modifier: Modifier = Modifier) {
    val articlesList = remember {
        mutableStateListOf<ArticlesItem>()
    }
    Column(modifier = modifier.fillMaxSize()) {
        NewsSourcesTabRow { tabSelectedId ->
            articlesList.clear()
            APIManager
                .getNewsServices()
                .getNewsArticles(Constants.API_KEY, tabSelectedId)
                .enqueue(object : Callback<ArticlesResponse> {
                    override fun onResponse(
                        call: Call<ArticlesResponse>,
                        response: Response<ArticlesResponse>
                    ) {
                        if (response.body()?.articles?.isNotEmpty() == true)
                            articlesList.addAll(response.body()?.articles!!)
                    }

                    override fun onFailure(call: Call<ArticlesResponse>, t: Throwable) {
                        TODO("Not yet implemented")
                    }
                })
        }
        NewsList(articlesList.toList())
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun NewsFragmentPreview() {
    NewsFragment()
}
// Client - Server Communication

// Client <->  Server
// Http s -> Secure Layer
// Application Programming interface <->
// Application communicates with another application
// Web Service <-> Network APIs

// XML <-> HTML <-> JSON



