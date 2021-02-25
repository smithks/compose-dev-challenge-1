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

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.ui.Dog
import com.example.androiddevchallenge.ui.theme.MyTheme

// Sample data
val dogs = listOf(
    Dog(
        "Dave",
        "Golden Retriever",
        "Dave is a fun, friendly pup who is having a blast on the farm. He is enjoying making tons of new doggy friends and has quickly become best friends with several new playmates who he loves to wrestle with.",
        R.drawable.golden
    ),
    Dog(
        "Max",
        "Corgi",
        "Max is for sure sugar and spice and everything nice…along with a killer set of ears. At between a year and two old, max is looking for his forever home!",
        R.drawable.corgi
    ),
    Dog(
        "Sally",
        "German Shepard",
        "Are you looking for an athletic pup to join you for outdoor time?  Look no further than our fun loving, Sally. She’s currently a happy and healthy Shepard mix around 2 years old. Sally came to the farm recently and she’s making the round and meeting all sorts of new friends since she’s been here.",
        R.drawable.german
    ),
    Dog(
        "Lilly",
        "Husky",
        "Who else is riding on the Freeway of Love and looking to find their doggo soul mate in the process? Fall in love? Live happily ever after? Well, we have your Aretha right here and you and she will likely be singing and humming from here on out, together forever.",
        R.drawable.husky
    ),
    Dog(
        "Bort",
        "Labrador",
        "Bort is everything you could ever dream up in a dog. She is so cute and squishy you'll barely be able to stop petting her. Bort is a young lab mix. If you like larger dogs, she is for you!",
        R.drawable.lab
    ),
    Dog(
        "Sam",
        "Poodle",
        "Sam is a super sweetie who is looking for an equally sweet family! Who could resist those curls!",
        R.drawable.poodle
    ),
    Dog(
        "Janet",
        "Mix",
        "Janet is a dainty sweetie with puppy-dog eyes that will warm your heart! She over three years old and weighs around 40 lbs. Janet would do great in a home with children or other pups.",
        R.drawable.mix
    )
)

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTheme {
                MyApp(dogs)
            }
        }
    }
}

// Start building your app here!
@Composable
fun MyApp(dogs: List<Dog>) {
    LazyColumn(modifier = Modifier.padding(horizontal = 8.dp, vertical = 8.dp)) {
        items(dogs) { dog ->
            val context = LocalContext.current
            DogCard(dog) {
                val intent = DetailActivity.newInstance(context, dog)
                context.startActivity(intent)
            }
        }
    }
}

@Composable
fun DogCard(dog: Dog, onClick: () -> Unit) {
    val typography = MaterialTheme.typography
    Card(
        backgroundColor = MaterialTheme.colors.primary,
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
            .padding(8.dp)
            .clickable(onClick = onClick),
        elevation = 4.dp
    ) {
        Column {
            Image(
                painter = painterResource(id = dog.imageId),
                contentDescription = "Picture of the pup",
                modifier = Modifier
                    .height(160.dp)
                    .fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(16.dp, 0.dp),
                verticalArrangement = Arrangement.Top
            ) {
                Text(
                    text = dog.name,
                    style = typography.h5
                )
                Text(
                    text = dog.breed,
                    style = typography.body1
                )
            }
        }
    }
}

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LightPreview() {
    MyTheme {
        MyApp(dogs)
    }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun DarkPreview() {
    MyTheme(darkTheme = true) {
        MyApp(dogs)
    }
}
