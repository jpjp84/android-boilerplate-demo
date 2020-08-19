package com.jp.boilerplate.di

import android.content.Context
import androidx.room.Room
import com.jp.boilerplate.data.datasource.CalendarDataSource
import com.jp.boilerplate.data.datasource.UserDataSource
import com.jp.boilerplate.data.datasource.local.CalendarLocalDataSource
import com.jp.boilerplate.data.datasource.local.UserLocalDataSource
import com.jp.boilerplate.data.datasource.remote.CalendarRemoteDataSource
import com.jp.boilerplate.data.datasource.remote.UserRemoteDataSource
import com.jp.boilerplate.data.meta.db.AppDB
import com.jp.boilerplate.data.repository.CalendarRepository
import com.jp.boilerplate.data.repository.CalendarRepositoryImpl
import com.jp.boilerplate.data.repository.UserRepository
import com.jp.boilerplate.data.repository.UserRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object DataModule {

    @Singleton
    @Provides
    fun provideDataBase(@ApplicationContext context: Context): AppDB {
        return Room.databaseBuilder(
            context.applicationContext,
            AppDB::class.java,
            "boilerplate.db"
        ).build()
    }

    @Singleton
    @Provides
    @AppModule.LocalDataSource
    fun provideLocalUserDataSource(appDb: AppDB): UserDataSource =
        UserLocalDataSource(appDb.userDao())

    @Singleton
    @Provides
    @AppModule.RemoteDataSource
    fun provideRemoteUserDataSource(): UserDataSource = UserRemoteDataSource()

    @Singleton
    @Provides
    @AppModule.LocalDataSource
    fun provideLocalCalendarDataSource(appDb: AppDB): CalendarDataSource =
        CalendarLocalDataSource(appDb.userDao())

    @Singleton
    @Provides
    @AppModule.RemoteDataSource
    fun provideRemoteCalendarDataSource(): CalendarDataSource = CalendarRemoteDataSource()
}

@Module
@InstallIn(ApplicationComponent::class)
object RepositoryBindModule {

    @Singleton
    @Provides
    fun provideRepository(
        @AppModule.LocalDataSource userLocalDataSource: UserDataSource,
        @AppModule.RemoteDataSource userRemoteDataSource: UserDataSource
    ): UserRepository = UserRepositoryImpl(userLocalDataSource, userRemoteDataSource)

    @Singleton
    @Provides
    fun provideCalendarRepository(
        @AppModule.LocalDataSource calendarLocalDataSource: CalendarDataSource,
        @AppModule.RemoteDataSource calendarRemoteDataSource: CalendarDataSource
    ): CalendarRepository = CalendarRepositoryImpl(calendarLocalDataSource, calendarRemoteDataSource)
}
