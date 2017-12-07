package ir.hosseinabbasi.elements.di.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ir.hosseinabbasi.elements.data.network.ApiEndPoint;
import ir.hosseinabbasi.elements.data.network.ApiHelper;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * Created by Dr.jacky on 2017/11/18.
 */

@Module
public class DataModule {

    @Provides
    @Singleton
    public ApiHelper provideApi(Retrofit retrofit) {
        return retrofit.create(ApiHelper.class);
    }

    @Provides
    @Singleton
    public Retrofit provideRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(ApiEndPoint.ENDPOINT_GOOGLEDOCS)
                .client(new OkHttpClient.Builder().build())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }
}
