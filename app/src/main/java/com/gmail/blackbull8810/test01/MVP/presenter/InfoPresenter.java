package com.gmail.blackbull8810.test01.MVP.presenter;

import com.gmail.blackbull8810.test01.MVP.data.Info;
import com.gmail.blackbull8810.test01.MVP.model.InfoModel;
import com.gmail.blackbull8810.test01.MVP.view.InfoView;
import com.gmail.blackbull8810.test01.common.presenter.Presenter;
import com.gmail.blackbull8810.test01.util.Logger;

import java.io.UnsupportedEncodingException;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class InfoPresenter implements Presenter<InfoView> {

    InfoView infoView;
    InfoModel infoModel;
    CompositeDisposable compositeDisposable;

    @Override
    public void attachView(InfoView view) {
        this.infoView = view;
        infoModel = new InfoModel(view.getContext());
        compositeDisposable = new CompositeDisposable();
    }

    @Override
    public void detachView() {
        this.infoView = null;
        compositeDisposable.clear();
    }

    @Override
    public void notConnectNetworking() {
        infoView.notConnectNetworking();
    }

    public void infoListUpdate() throws UnsupportedEncodingException {

        Logger.log("#8810 (InfoPresenter) infoListUpdate 작동.");

        /*Disposable disposable = infoModel.getInfoList()
                .toFlowable()
                .flatMap(new Function<Info, Flowable<List<Info.InfoList>>>() {
                    @Override
                    public Flowable<List<Info.InfoList>> apply(Info info) throws Exception {
                        return null;
                    }
                })
                .subscribeOn(io.reactivex.schedulers.Schedulers.io())
                .observeOn(io.reactivex.android.schedulers.AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Info.InfoList>>() {
                    @Override
                    public void accept(List<Info.InfoList> infoLists) throws Exception {

                        infoView.getInfoListItems(infoLists);
                        Logger.log("#########################8810 rx accept 동작");

                    }
                });*/
        Disposable disposable = infoModel.getInfoList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Info>() {
                    @Override
                    public void accept(Info info) throws Exception {
                        Logger.log("#########################8810 rx accept 동작 111");
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Logger.log("#########################8810 rx accept 동작 222");
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        Logger.log("#########################8810 rx run 동작 333");
                    }
                });

        compositeDisposable.add(disposable);
    }
}
