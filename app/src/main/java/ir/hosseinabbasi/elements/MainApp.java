package ir.hosseinabbasi.elements;

import android.app.Application;
import android.content.Context;

import ir.hosseinabbasi.elements.di.component.ApplicationComponent;
import ir.hosseinabbasi.elements.di.component.DaggerApplicationComponent;
import ir.hosseinabbasi.elements.di.module.ApplicationModule;

/**
 * Created by Dr.jacky on 2017/11/18.
 */

public class MainApp extends Application {

    private ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initComponents();
    }

    public ApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
    }

    public static MainApp get(Context context) {
        return (MainApp) context.getApplicationContext();
    }

    private void initComponents() {
        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
        mApplicationComponent.inject(this);
    }
}
