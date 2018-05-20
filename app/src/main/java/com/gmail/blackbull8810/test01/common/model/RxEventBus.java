package com.gmail.blackbull8810.test01.common.model;


import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;
import rx.subscriptions.CompositeSubscription;

// this is the middleman object
public class RxEventBus {

    CompositeSubscription compositeSubscription = new CompositeSubscription();

    private final Subject<Object, Object> subject
            = new SerializedSubject<>(PublishSubject.create());


    public <T> Subscription onEvent(Class<T> clazz, Action1<T> handler) {
        Subscription subscription = subject.ofType(clazz).subscribe(handler);
        compositeSubscription.add(subscription);
        return subscription;
    }

    public <T> Subscription onEventOnMain(Class<T> clazz, Action1<T> handler) {
        Subscription subscription = subject.ofType(clazz).observeOn(AndroidSchedulers.mainThread()).subscribe(handler);
        compositeSubscription.add(subscription);
        return subscription;
    }

    public void post(Object event) {
        subject.onNext(event);
    }

    public void unregister(){
        compositeSubscription.clear();
    }
}