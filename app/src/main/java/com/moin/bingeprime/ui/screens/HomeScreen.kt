package com.moin.bingeprime.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.compose.SubcomposeAsyncImage
import com.moin.bingeprime.R
import com.moin.bingeprime.data.model.MediaItem
import com.moin.bingeprime.ui.viewmodel.MediaViewModel
import org.koin.androidx.compose.koinViewModel
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState

@Composable
fun HomeScreen(navController: NavController) {
    val viewModel: MediaViewModel = koinViewModel()
    val movies by viewModel.movies.observeAsState(initial = emptyList())
    val tvShows by viewModel.tvShows.observeAsState(initial = emptyList())
    val isLoading by viewModel.isLoading.observeAsState(initial = true)
    val error by viewModel.error.observeAsState(initial = null)
    var selectedTab by remember { mutableStateOf(0) }
    val pagerState = rememberPagerState(initialPage = 0)
    val scaffoldState = rememberScaffoldState()

    LaunchedEffect(error) {
        error?.let {
            scaffoldState.snackbarHostState.showSnackbar(
                message = it,
                actionLabel = "Dismiss",
                duration = SnackbarDuration.Short
            )
            viewModel.clearError()
        }
    }

    Scaffold(
        scaffoldState = scaffoldState,
        modifier = Modifier.fillMaxSize().background(Color.Black)
    ) { padding ->
        Column(modifier = Modifier.padding(padding)) {
            TopAppBar(
                title = {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("BingePrime", style = MaterialTheme.typography.h6, color = MaterialTheme.colors.onSurface)
                    }
                },
                backgroundColor = MaterialTheme.colors.surface
            )
            Text(
                text = "Coming Soon",
                style = MaterialTheme.typography.subtitle1,
                color = MaterialTheme.colors.onSurface,
                modifier = Modifier
                    .padding(start = 16.dp, top = 8.dp, bottom = 4.dp)
                    .align(Alignment.Start)
            )
            HorizontalPager(
                count = 1,
                state = pagerState,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .background(Color.Black),
                contentPadding = PaddingValues(horizontal = 16.dp)
            ) {
                Row(
                    modifier = Modifier
                        .horizontalScroll(rememberScrollState())
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = {}) {
                        AsyncImage(
                            model = "https://cdn.watchmode.com/provider_logos/netflix_100px.png",
                            contentDescription = "Netflix",
                            modifier = Modifier
                                .size(70.dp)
                                .padding(4.dp)
                                .background(Color.DarkGray, shape = MaterialTheme.shapes.medium)
                                .padding(4.dp)
                        )
                    }
                    IconButton(onClick = {}) {
                        AsyncImage(
                            model = "https://cdn.watchmode.com/provider_logos/prime_video_100px.png",
                            contentDescription = "Prime",
                            modifier = Modifier
                                .size(70.dp)
                                .padding(4.dp)
                                .background(Color.DarkGray, shape = MaterialTheme.shapes.medium)
                                .padding(4.dp)
                        )
                    }
                    IconButton(onClick = {}) {
                        AsyncImage(
                            model = "https://cdn.watchmode.com/provider_logos/disneyPlus_100px.png",
                            contentDescription = "Disney+",
                            modifier = Modifier
                                .size(70.dp)
                                .padding(4.dp)
                                .background(Color.DarkGray, shape = MaterialTheme.shapes.medium)
                                .padding(4.dp)
                        )
                    }
                    IconButton(onClick = {}) {
                        AsyncImage(
                            model = "https://cdn.watchmode.com/provider_logos/appleTvPlus_100px.png",
                            contentDescription = "Apple Tv+",
                            modifier = Modifier
                                .size(70.dp)
                                .padding(4.dp)
                                .background(Color.DarkGray, shape = MaterialTheme.shapes.medium)
                                .padding(4.dp)
                        )
                    }
                    IconButton(onClick = {}) {
                        AsyncImage(
                            model = "https://cdn.watchmode.com/provider_logos/youtube_100px.png",
                            contentDescription = "Youtube",
                            modifier = Modifier
                                .size(70.dp)
                                .padding(4.dp)
                                .background(Color.DarkGray, shape = MaterialTheme.shapes.medium)
                                .padding(4.dp)
                        )
                    }
                    IconButton(onClick = {}) {
                        AsyncImage(
                            model = "https://cdn.watchmode.com/provider_logos/mtv_100px.png",
                            contentDescription = "MTV",
                            modifier = Modifier
                                .size(70.dp)
                                .padding(4.dp)
                                .background(Color.DarkGray, shape = MaterialTheme.shapes.medium)
                                .padding(4.dp)
                        )
                    }
                    IconButton(onClick = {}) {
                        AsyncImage(
                            model = "https://cdn.watchmode.com/provider_logos/450_autogenerated.png",
                            contentDescription = "Zee5",
                            modifier = Modifier
                                .size(70.dp)
                                .padding(4.dp)
                                .background(Color.DarkGray, shape = MaterialTheme.shapes.medium)
                                .padding(4.dp)
                        )
                    }
                }
            }
            TabRow(
                selectedTabIndex = selectedTab,
                modifier = Modifier.fillMaxWidth().background(Color.Black),
                backgroundColor = MaterialTheme.colors.surface
            ) {
                Tab(
                    selected = selectedTab == 0,
                    onClick = { selectedTab = 0 },
                    text = { Text("Movies", color = if (selectedTab == 0) MaterialTheme.colors.onSurface else MaterialTheme.colors.onBackground) }
                )
                Tab(
                    selected = selectedTab == 1,
                    onClick = { selectedTab = 1 },
                    text = { Text("TV Shows", color = if (selectedTab == 1) MaterialTheme.colors.onSurface else MaterialTheme.colors.onBackground) }
                )
            }
            Box(modifier = Modifier.weight(1f).background(Color.Black)) {
                if (isLoading) {
                    ShimmerList()
                } else {
                    val items = if (selectedTab == 0) movies else tvShows
                    MediaList(items = items.filter { it.name != null }, navController = navController)
                }
            }
        }
    }
}

@Composable
fun MediaList(items: List<MediaItem>, navController: NavController, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier.padding(8.dp).background(Color.Black)) {
        items(items) { item ->
            MediaItemCard(item) { navController.navigate("details/${item.id}") }
        }
    }
}

@Composable
fun MediaItemCard(item: MediaItem, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .clickable { onClick() },
        elevation = 4.dp,
        backgroundColor = Color.DarkGray
    ) {
        Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            SubcomposeAsyncImage(
                model = item.image_url.takeIf { !it.isNullOrEmpty() } ?: "",
                contentDescription = item.name ?: "No title",
                modifier = Modifier
                    .size(100.dp)
                    .padding(end = 16.dp),
                loading = {
                    Box(
                        modifier = Modifier
                            .size(100.dp)
                            .background(Color.LightGray)
                    ) {
                        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                    }
                },
                error = { ErrorImagePlaceholder() }
            )
            Column {
                Text(
                    text = item.name ?: "Untitled",
                    style = MaterialTheme.typography.h6,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    color = MaterialTheme.colors.onSurface
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Release Date: ${item.year?.toString() ?: "Unknown"}",
                    style = MaterialTheme.typography.caption,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    color = MaterialTheme.colors.onSurface
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Type: ${item.type?.capitalize() ?: "Unknown"}",
                    style = MaterialTheme.typography.caption,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    color = MaterialTheme.colors.onSurface
                )
            }
        }
    }
}

@Composable
private fun ErrorImagePlaceholder() {
    Box(
        modifier = Modifier
            .size(100.dp)
            .background(Color.LightGray),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.error_image),
            contentDescription = "Error Image",
            modifier = Modifier.size(48.dp)
        )
    }
}

@Composable
fun ShimmerList() {
    LazyColumn(modifier = Modifier.background(Color.Black)) {
        items(10) { ShimmerItem() }
    }
}

@Composable
fun ShimmerItem() {
    Card(
        modifier = Modifier.fillMaxWidth().padding(8.dp),
        elevation = 4.dp,
        backgroundColor = Color.DarkGray
    ) {
        Row(modifier = Modifier.padding(16.dp)) {
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .padding(end = 16.dp)
                    .background(Color.Gray.copy(alpha = 0.3f))
            )
            Column {
                Box(modifier = Modifier.height(20.dp).width(150.dp).background(Color.Gray.copy(alpha = 0.3f)))
                Spacer(modifier = Modifier.height(4.dp))
                Box(modifier = Modifier.height(16.dp).width(100.dp).background(Color.Gray.copy(alpha = 0.3f)))
                Spacer(modifier = Modifier.height(4.dp))
                Box(modifier = Modifier.height(16.dp).width(100.dp).background(Color.Gray.copy(alpha = 0.3f)))
            }
        }
    }
}