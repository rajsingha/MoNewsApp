package com.moengage.test.newsapp.homescreen.presentation.ui

import android.Manifest
import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.core.app.ActivityCompat.shouldShowRequestPermissionRationale
import androidx.core.content.ContextCompat
import androidx.core.view.WindowCompat
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.SubcomposeAsyncImage
import com.moengage.test.core.ui.base.UiEvents
import com.moengage.test.core.ui.components.animations.shimmerLoadingAnimation
import com.moengage.test.core.ui.components.buttons.PrimaryButton
import com.moengage.test.core.ui.components.dialogs.ProgressDialog
import com.moengage.test.core.ui.theme.Grey400
import com.moengage.test.core.ui.theme.MoNewsAppTheme
import com.moengage.test.core.ui.theme.Spacing
import com.moengage.test.core.utils.Constants.NO_APPLICATION_TO_HANDLE
import com.moengage.test.core.utils.Utils.openUrlInBrowser
import com.moengage.test.newsapp.R
import com.moengage.test.newsapp.homescreen.data.dto.NewsArticleResponse
import com.moengage.test.newsapp.homescreen.presentation.utils.HomeScreenUiEvents
import com.moengage.test.newsapp.homescreen.presentation.viewmodels.HomeScreenViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlin.random.Random.Default.nextInt


private var onSoftBackPressed: () -> Unit = {}
private var onReadMorePressed: (url: String) -> Unit = {}
private var onSortByNewArticlesPressed: () -> Unit = {}
private var onSortByOldArticlesPressed: () -> Unit = {}

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: HomeScreenViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()
            val snackBarHostState = remember {
                SnackbarHostState()
            }
            val permissionLauncher = rememberLauncherForActivityResult(
                ActivityResultContracts.RequestPermission()
            ) { isGranted ->
                if (isGranted.not()) {
                    viewModel.onUiEvent(
                        HomeScreenUiEvents.OnTriggerUiEvents(
                            UiEvents.ShowSnackBar(
                                getString(R.string.permission_denied_not_receive_notification_from_us)
                            )
                        )
                    )
                }
            }

            SetStatusBarColorDark()
            MoNewsAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.Black
                ) {
                    Scaffold(
                        containerColor = Color.Black,
                        snackbarHost = {
                            SnackbarHost(snackBarHostState)
                        }

                    ) { paddingValues ->
                        LazyList(
                            modifier = Modifier.padding(paddingValues),
                            articles = uiState.newsArticles
                        )
                    }
                    if (uiState.isLoading) {
                        ProgressDialog()
                    }
                }
            }

            LaunchedEffect(Unit) {
                viewModel.uiEvents.collectLatest {
                    when (it) {
                        is UiEvents.ShowSnackBar -> {
                            snackBarHostState.showSnackbar(it.message)
                        }

                        else -> {}
                    }
                }
            }

            clickListeners(onSoftBackBtnPressed = {
                finish()
            }, onReadMoreBtnPressed = { url ->
                try {
                    openUrlInBrowser(url)
                } catch (e: ActivityNotFoundException) {
                    viewModel.onUiEvent(
                        HomeScreenUiEvents.OnTriggerUiEvents(
                            UiEvents.ShowSnackBar(
                                NO_APPLICATION_TO_HANDLE
                            )
                        )
                    )
                }
            }, onSortByNewArticlesBtnPressed = {
                viewModel.onUiEvent(HomeScreenUiEvents.OnRecentArticlesSelected)
            }, onSortByOldArticlesBtnPressed = {
                viewModel.onUiEvent(HomeScreenUiEvents.OnOldArticlesSelected)
            })

            LaunchedEffect(Unit) {
                delay(10000)
                askNotificationPermission {
                    permissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
                }
            }
        }
    }

    private fun askNotificationPermission(requestPermission: () -> Unit) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) ==
                PackageManager.PERMISSION_GRANTED
            ) {
                // FCM SDK (and your app) can post notifications.
            } else if (shouldShowRequestPermissionRationale(
                    this,
                    Manifest.permission.POST_NOTIFICATIONS
                )
            ) {
                //TODO show permission dialog if necessary
            } else {
                requestPermission.invoke()
            }
        }
    }
}

@Composable
fun SetStatusBarColorDark() {
    val view = LocalView.current
    val isDarkMod = isSystemInDarkTheme()
    DisposableEffect(Unit) {
        val activity = view.context as Activity
        activity.window.statusBarColor = activity.resources.getColor(R.color.black)
        WindowCompat.getInsetsController(activity.window, activity.window.decorView).apply {
            isAppearanceLightStatusBars = !isDarkMod
            isAppearanceLightNavigationBars = !isDarkMod
        }
        onDispose { }
    }
}

private fun clickListeners(
    onSoftBackBtnPressed: () -> Unit,
    onReadMoreBtnPressed: (url: String) -> Unit,
    onSortByNewArticlesBtnPressed: () -> Unit,
    onSortByOldArticlesBtnPressed: () -> Unit,
) {
    onSoftBackPressed = onSoftBackBtnPressed
    onReadMorePressed = onReadMoreBtnPressed
    onSortByNewArticlesPressed = onSortByNewArticlesBtnPressed
    onSortByOldArticlesPressed = onSortByOldArticlesBtnPressed
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun LazyList(
    modifier: Modifier,
    articles: List<NewsArticleResponse.Article?> = emptyList()
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(vertical = Spacing.spacing12)
            .background(Color.Black),
        verticalArrangement = Arrangement.spacedBy(Spacing.spacing10)
    ) {
        stickyHeader {
            StickyHeader()
        }
        items(articles, key = { it?.id ?: nextInt() }) {
            ArticleCard(it)
        }
    }
}

@Composable
private fun ArticleCard(article: NewsArticleResponse.Article?) {
    if (article?.urlToImage?.isNotEmpty() == true
        &&
        article.title?.isNotEmpty() == true
        &&
        article.url?.isNotEmpty() == true
    ) {
        ConstraintLayout(
            modifier = Modifier
                .padding(vertical = Spacing.spacing12, horizontal = Spacing.spacing20)
                .fillMaxWidth()
        ) {
            val (textCard, thumbnail) = createRefs()

            SubcomposeAsyncImage(
                article.urlToImage,
                contentDescription = null,
                modifier = Modifier
                    .aspectRatio(1f / 1f)
                    .constrainAs(thumbnail) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    }
                    .clip(RoundedCornerShape(Spacing.spacing12)),
                contentScale = ContentScale.Crop,
                loading = {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(RoundedCornerShape(Spacing.spacing8))
                            .background(Color.LightGray)
                            .shimmerLoadingAnimation(false),
                    )
                },
                error = {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(RoundedCornerShape(Spacing.spacing12))
                            .background(Color.Gray) // Set your default placeholder color here
                    )
                }
            )

            DescriptionCard(
                modifier = Modifier
                    .clip(RoundedCornerShape(Spacing.spacing12))
                    .constrainAs(textCard) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom)
                    }
                    .padding(Spacing.spacing8)
                    .fillMaxWidth()
                    .alpha(0.97f)
                    .background(
                        Grey400,
                        shape = RoundedCornerShape(Spacing.spacing12)
                    )
                    .clickable {},
                article = article
            )
        }
    }
}

@Composable
private fun StickyHeader() {
    Column(
        modifier = Modifier
            .padding(
                horizontal = Spacing.spacing20
            )
            .background(Color.Black)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_arrow_back),
                contentDescription = null,
                modifier = Modifier.clickable {
                    onSoftBackPressed.invoke()
                }
            )
            Spacer(modifier = Modifier.width(Spacing.spacing10))
            Text(
                text = stringResource(R.string.hi_user),
                color = Color.White,
                style = MaterialTheme.typography.displayMedium
            )
            SortingComponent(modifier = Modifier.weight(1f))
        }
        Spacer(modifier = Modifier.height(Spacing.spacing20))
        Text(
            text = stringResource(R.string.discover_the_latest_news),
            color = Color.White,
        )
        Spacer(modifier = Modifier.height(Spacing.spacing10))
    }
}

@Composable
private fun SortingComponent(modifier: Modifier = Modifier) {
    var expandedState by remember { mutableStateOf(false) }
    val rotationState by animateFloatAsState(
        targetValue = if (expandedState) 180f else 0f, label = ""
    )

    ConstraintLayout(modifier.background(Color.Black)) {
        val (dateBtn) = createRefs()
        Row(
            modifier = Modifier
                .background(
                    Color.White,
                    shape = RoundedCornerShape(Spacing.spacing12)
                )
                .constrainAs(dateBtn) {
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                }
                .clickable {
                    expandedState = expandedState.not()
                    if (expandedState) {
                        onSortByOldArticlesPressed.invoke()
                    } else {
                        onSortByNewArticlesPressed.invoke()
                    }
                }
                .clip(RoundedCornerShape(Spacing.spacing12))
                .border(
                    width = Spacing.spacing1,
                    color = Color.Black,
                    shape = RoundedCornerShape(
                        Spacing.spacing12
                    )
                )
                .padding(horizontal = Spacing.spacing12, vertical = Spacing.spacing8),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End,
        ) {
            Text(
                text = stringResource(R.string.published_on),
                color = Color.Black,
                style = MaterialTheme.typography.titleSmall
            )
            IconButton(modifier = Modifier
                .size(Spacing.spacing24)
                .rotate(rotationState), onClick = {
                expandedState = expandedState.not()
                if (expandedState) {
                    onSortByOldArticlesPressed.invoke()
                } else {
                    onSortByNewArticlesPressed.invoke()
                }
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_arrow_downward),
                    contentDescription = null,
                    tint = Color.Black
                )
            }
        }
    }

}

@Composable
private fun DescriptionCard(
    modifier: Modifier,
    article: NewsArticleResponse.Article?
) {
    Box(
        modifier = modifier
    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .padding(Spacing.spacing8)
        ) {
            val (titleText, descriptionText, authorText, button) = createRefs()

            Text(
                modifier = Modifier
                    .constrainAs(titleText) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(parent.top)
                    },
                textAlign = TextAlign.Start,
                maxLines = 3,
                text = article?.title.orEmpty(),
                style = MaterialTheme.typography.displaySmall,
                color = Color.Black
            )

            Text(
                modifier = Modifier
                    .constrainAs(descriptionText) {
                        start.linkTo(titleText.start)
                        end.linkTo(titleText.end)
                        top.linkTo(titleText.bottom, Spacing.spacing8)
                    },
                text = article?.description.orEmpty(),
                textAlign = TextAlign.Start,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.headlineSmall,
                color = Color.Black
            )

            Text(
                modifier = Modifier
                    .constrainAs(authorText) {
                        start.linkTo(titleText.start)
                        bottom.linkTo(button.bottom)
                    },
                text = article?.author.orEmpty() + " | " +
                        article?.publishedAt?.take(
                            10
                        ).orEmpty(),
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.headlineSmall,
                color = Color.Black
            )

            PrimaryButton(
                modifier = Modifier
                    .constrainAs(button) {
                        top.linkTo(descriptionText.bottom, Spacing.spacing8)
                        end.linkTo(titleText.end)
                        bottom.linkTo(parent.bottom)
                    }, text = stringResource(R.string.read_more),
                textColor = Color.White, onClick = {
                    onReadMorePressed.invoke(article?.url.orEmpty())
                }
            )
        }
    }
}



