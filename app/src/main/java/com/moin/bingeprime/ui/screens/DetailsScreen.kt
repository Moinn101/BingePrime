package com.moin.bingeprime.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.SubcomposeAsyncImage
import com.moin.bingeprime.R
import com.moin.bingeprime.data.model.MediaItem
import com.moin.bingeprime.ui.viewmodel.MediaViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun DetailsScreen(navController: NavController, mediaId: Int) {
    val viewModel: MediaViewModel = koinViewModel()
    val movies by viewModel.movies.observeAsState(initial = emptyList())
    val tvShows by viewModel.tvShows.observeAsState(initial = emptyList())
    val isLoading by viewModel.isLoading.observeAsState(initial = true)

    val allMedia by remember(movies, tvShows) {
        derivedStateOf { (movies ?: emptyList()) + (tvShows ?: emptyList()) }
    }
    val mediaItem = allMedia.find { it.id == mediaId }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Details", color = MaterialTheme.colors.onSurface) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {}
                },
                backgroundColor = MaterialTheme.colors.surface
            )
        }
    ) { padding ->
        Box(modifier = Modifier.fillMaxSize().background(Color.Black).padding(padding)) {
            if (isLoading) {
                ShimmerDetail()
            } else if (mediaItem != null) {
                MediaDetailContent(mediaItem)
            }
        }
    }
}

@Composable
fun MediaDetailContent(item: MediaItem, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(Color.Black)
    ) {
        SubcomposeAsyncImage(
            model = item.image_url,
            contentDescription = item.name ?: "No title",
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp),
            loading = {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                        .background(Color.LightGray)
                ) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
            },
            error = { ErrorImagePlaceholder() }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(item.name ?: "Untitled", style = MaterialTheme.typography.h4, color = MaterialTheme.colors.onBackground)
        Text("Release Date: ${item.year?.toString() ?: "Unknown"}", style = MaterialTheme.typography.subtitle1, color = MaterialTheme.colors.onBackground)
        Spacer(modifier = Modifier.height(8.dp))
        Text("Type: ${item.type?.capitalize() ?: "Unknown"}", style = MaterialTheme.typography.subtitle1, color = MaterialTheme.colors.onBackground)
    }
}

@Composable
fun ShimmerDetail() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(Color.Black)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .background(Color.Gray.copy(alpha = 0.3f))
        )
        Spacer(modifier = Modifier.height(16.dp))
        Box(
            modifier = Modifier
                .height(30.dp)
                .width(200.dp)
                .background(Color.Gray.copy(alpha = 0.3f))
        )
        Spacer(modifier = Modifier.height(8.dp))
        Box(
            modifier = Modifier
                .height(20.dp)
                .width(150.dp)
                .background(Color.Gray.copy(alpha = 0.3f))
        )
        Spacer(modifier = Modifier.height(8.dp))
        Box(
            modifier = Modifier
                .height(20.dp)
                .width(150.dp)
                .background(Color.Gray.copy(alpha = 0.3f))
        )
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