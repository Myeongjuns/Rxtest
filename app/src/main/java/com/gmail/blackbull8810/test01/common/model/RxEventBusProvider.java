package com.gmail.blackbull8810.test01.common.model;

import com.gmail.blackbull8810.test01.util.Logger;

public class RxEventBusProvider {
    private static final RxEventBus rxEventBus = new RxEventBus();

    public static RxEventBus provide(){
        Logger.log("event bus hashcode -> " + rxEventBus.hashCode());
        return rxEventBus;
    }
}
