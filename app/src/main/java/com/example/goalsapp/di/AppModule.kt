package com.example.goalsapp.di

import android.app.Application
import androidx.room.Room
import com.example.goalsapp.data.GoalDatabase
import com.example.goalsapp.data.GoalRepository
import com.example.goalsapp.data.GoalRepositoryImplementation
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    // Added a function that implements the database
    @Provides
    @Singleton
    fun providesGoalDatabase(app: Application): GoalDatabase {
        return Room.databaseBuilder(
            app,
            GoalDatabase::class.java,
            "goal_db"
        ).build()
    }

    @Provides
    @Singleton
    fun providesGoalRepository(goalDatabase: GoalDatabase): GoalRepository {
        return GoalRepositoryImplementation(goalDatabase.dao)
    }
}