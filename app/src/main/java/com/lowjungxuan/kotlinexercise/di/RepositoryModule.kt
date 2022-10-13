package com.lowjungxuan.kotlinexercise.di

import android.content.Context
import androidx.room.Room
import com.lowjungxuan.kotlinexercise.data.api.ApiClient
import com.lowjungxuan.kotlinexercise.data.api.StudentEndpoint
import com.lowjungxuan.kotlinexercise.student.business.StudentRepository
import com.lowjungxuan.kotlinexercise.data.room.AppDatabase
import com.lowjungxuan.kotlinexercise.student.data.StudentDao
import com.lowjungxuan.kotlinexercise.student.data.StudentDatabaseRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    // retrofit basement
    @Provides
    fun providesApiService(): StudentEndpoint = ApiClient.apiService()
    // inject the StudentRepository interface class
    @Provides
    fun providesStudentRepository(
        databaseRepository: StudentDatabaseRepository
    ): StudentRepository = databaseRepository
    // inject the local database
    @Provides
    fun providesStudentDatabaseRepository(databaseDAO: StudentDao,endpoint: StudentEndpoint): StudentDatabaseRepository {
        return StudentDatabaseRepository(databaseDAO,endpoint)
    }

    @Provides
    fun providesStudentDAO(
        @ApplicationContext context: Context
    ): StudentDao {
        val db = Room.databaseBuilder(
            context, AppDatabase::class.java,
            "ecommerce-database"
        ).build()
        return db.studentDao()
    }
    @Provides
    fun providesIoDispatcher(): CoroutineDispatcher = Dispatchers.IO
}