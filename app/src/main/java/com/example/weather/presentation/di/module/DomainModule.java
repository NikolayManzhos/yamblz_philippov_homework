package com.example.weather.presentation.di.module;


import com.example.weather.domain.interactor.CurrentWeatherInteractor;
import com.example.weather.domain.interactor.CurrentWeatherInteractorImpl;
import com.example.weather.domain.interactor.MainViewInteractor;
import com.example.weather.domain.interactor.MainViewInteractorImpl;
import com.example.weather.domain.interactor.SuggestViewInteractor;
import com.example.weather.domain.interactor.SuggestViewInteractorImpl;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class DomainModule {

    @Binds
    @Singleton
    abstract CurrentWeatherInteractor provideCurrentWeatherInteractor(CurrentWeatherInteractorImpl currentWeatherInteractor);

    @Binds
    @Singleton
    abstract SuggestViewInteractor provideSuggestViewInteractor(SuggestViewInteractorImpl suggestViewInteractor);

    @Binds
    @Singleton
    abstract MainViewInteractor provideMainViewInteractor(MainViewInteractorImpl mainViewInteractor);
}
