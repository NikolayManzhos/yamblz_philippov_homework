package com.example.weather.domain.interactor;

import com.example.weather.data.repository.weather.WeatherRepository;
import com.example.weather.domain.models.ForecastModel;
import com.example.weather.utils.rx.SchedulerProvider;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.ReplaySubject;

@Singleton
public class CurrentWeatherInteractorImpl implements CurrentWeatherInteractor {

    private WeatherRepository weatherRepository;
    private SchedulerProvider schedulerProvider;

    private Disposable weatherDisposable;
    private ReplaySubject<ForecastModel> weatherReplaySubject;

    @Inject
    public CurrentWeatherInteractorImpl(WeatherRepository weatherRepository,
                                        SchedulerProvider schedulerProvider) {
        this.weatherRepository = weatherRepository;
        this.schedulerProvider = schedulerProvider;
    }

    @Override
    public Observable<ForecastModel> requestWeather(boolean force) {
        if (force && weatherDisposable != null) {
            weatherDisposable.dispose();
        }
        if (weatherDisposable == null || weatherDisposable.isDisposed()) {
            weatherReplaySubject = ReplaySubject.create(1);

            weatherDisposable = weatherRepository.getWeather(force)
                    .compose(schedulerProvider.applyIoSchedulers())
                    .subscribe(weatherReplaySubject::onNext, weatherReplaySubject::onError);
        }
        return weatherReplaySubject;
    }
}
