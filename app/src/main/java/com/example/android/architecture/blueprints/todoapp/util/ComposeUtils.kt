/*
 * Copyright 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.architecture.blueprints.todoapp.util

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.PullToRefreshDefaults.Indicator
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

val primaryDarkColor: Color = Color(0xFF263238)

/**
 * Display an initial empty state or swipe to refresh content.
 *
 * @param loading (state) when true, display a loading spinner over [content]
 * @param empty (state) when true, display [emptyContent]
 * @param emptyContent (slot) the content to display for the empty state
 * @param onRefresh (event) event to request refresh
 * @param modifier the modifier to apply to this layout.
 * @param content (slot) the main content to show
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoadingContent(
    loading: Boolean,
    empty: Boolean,
    emptyContent: @Composable () -> Unit,
    onRefresh: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    if (empty) {
        emptyContent()
    }  else {
        // Initialize the PullToRefresh state using remember
        val pullToRefreshState = rememberPullToRefreshState()

        PullToRefreshBox(
            isRefreshing = loading,  // use the loading directly for refreshing state
            onRefresh = onRefresh,  // define the onRefresh event
            state = pullToRefreshState,  // pass the remembered state
            modifier = modifier,
            contentAlignment = Alignment.TopStart,
            indicator = {
                // Define the indicator for pull-to-refresh
                Indicator(
                    modifier = Modifier.align(Alignment.TopCenter),
                    isRefreshing = loading,
                    state = pullToRefreshState
                )
            }
        ) {
            content()  // Main content
        }
    }

}
