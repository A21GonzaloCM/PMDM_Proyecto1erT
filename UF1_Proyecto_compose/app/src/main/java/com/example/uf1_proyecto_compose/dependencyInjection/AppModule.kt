package com.example.uf1_proyecto_compose.dependencyInjection

import android.app.Application
import androidx.room.Room
import com.example.uf1_proyecto_compose.data.TaskRepository
import com.example.uf1_proyecto_compose.data.TaskRepositoryImplementation
import com.example.uf1_proyecto_compose.data.TasksAppDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideToDoAppDB(app: Application): TasksAppDB{
        return Room.databaseBuilder(
            app,
            TasksAppDB::class.java,
            "tasks_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideTaskRepository(db: TasksAppDB): TaskRepository{
       return TaskRepositoryImplementation(db.dao)
    }

}