package com.example.goalsapp

import android.app.Application
import androidx.room.Room
import com.example.goalsapp.data.GoalDatabase
import dagger.Provides
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Singleton

@HiltAndroidApp
class GoalApplication: Application()