package ir.hosseinabbasi.elements.di.component;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;

import javax.inject.Singleton;

import dagger.Component;
import ir.hosseinabbasi.elements.MainApp;
import ir.hosseinabbasi.elements.data.DataManager;
import ir.hosseinabbasi.elements.di.ApplicationContext;
import ir.hosseinabbasi.elements.di.module.ApplicationModule;
import ir.hosseinabbasi.elements.di.module.DataModule;
import ir.hosseinabbasi.elements.utils.UtilsComponent;
import ir.hosseinabbasi.elements.utils.UtilsModule;

/**
 * Created by Dr.jacky on 2017/11/18.
 */

@Singleton
@Component(modules = {ApplicationModule.class, UtilsModule.class,  DataModule.class})
public interface ApplicationComponent extends UtilsComponent, DataComponent{

    void inject(MainApp app);

    @ApplicationContext
    Context context();

    Resources exposeResources();

    Application application();

    DataManager getDataManager();
}
