package ir.hosseinabbasi.elements.utils.rx.qualifiers;

import java.lang.annotation.Retention;

import javax.inject.Qualifier;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by Dr.jacky on 2017/11/18.
 */

@Qualifier @Retention(RUNTIME)
public @interface UIThreadPref {}
