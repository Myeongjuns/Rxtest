package com.gmail.blackbull8810.test01.MVP.view;

import com.gmail.blackbull8810.test01.MVP.data.Info;
import com.gmail.blackbull8810.test01.common.view.MvpView;

import java.util.List;

public interface InfoView extends MvpView {
    void getInfoListItems(List<Info.InfoList> infos);
}
