package ir.hosseinabbasi.elements.di.component;

import dagger.Component;
import ir.hosseinabbasi.elements.di.PerActivity;
import ir.hosseinabbasi.elements.di.module.ActivityModule;
import ir.hosseinabbasi.elements.ui.data.DataListView;
import ir.hosseinabbasi.elements.ui.detail.DetailView;
import ir.hosseinabbasi.elements.ui.main.MainActivity;

/**
 * Created by Dr.jacky on 2017/11/18.
 */

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
    void inject(MainActivity activity);
    void inject(DataListView fragment);
    void inject(DetailView fragment);
}
