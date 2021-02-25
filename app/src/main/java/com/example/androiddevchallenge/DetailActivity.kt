/*
 * Copyright 2021 The Android Open Source Project
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
package com.example.androiddevchallenge

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.ui.Dog

class DetailActivity : AppCompatActivity() {

    companion object {
        val EXTRA_DOG: String = "EXTRA_DOG"

        fun newInstance(context: Context, dog: Dog): Intent {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(EXTRA_DOG, dog)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val dog = intent.getParcelableExtra<Dog>(EXTRA_DOG)
        if (dog != null) {
            setContent {
                DetailScreen(dog = dog)
            }
        } else {
            finish()
        }
    }
}

@Composable
fun DetailScreen(dog: Dog) {
    Column {
        val typography = MaterialTheme.typography
        TopAppBar() {
            val context = LocalContext.current
            IconButton(onClick = { (context as Activity).finish() }) {
                Icon(Icons.Filled.ArrowBack, "Back")
            }
        }
        Image(
            painter = painterResource(id = dog.imageId),
            contentDescription = "The pup",
            modifier = Modifier
                .fillMaxWidth()
                .height(350.dp),
            contentScale = ContentScale.Crop
        )
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = dog.name, style = typography.h4)
            Text(text = dog.breed, style = typography.body1)
            Text(modifier = Modifier.padding(0.dp, 8.dp), text = dog.bio, style = typography.body2)
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            val context = LocalContext.current
            Button(
                onClick = {
                    Toast.makeText(context, "They're good dogs!", Toast.LENGTH_SHORT).show()
                }
            ) {
                Text(text = "Adopt!", style = typography.h3)
            }
        }
    }
}
