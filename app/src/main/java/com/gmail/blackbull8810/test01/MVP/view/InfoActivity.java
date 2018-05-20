package com.gmail.blackbull8810.test01.MVP.view;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import com.gmail.blackbull8810.test01.MVP.data.Info;
import com.gmail.blackbull8810.test01.MVP.presenter.InfoPresenter;
import com.gmail.blackbull8810.test01.R;
import com.gmail.blackbull8810.test01.Retrofit2.APIClient;
import com.gmail.blackbull8810.test01.Retrofit2.APIInterface;

import java.io.UnsupportedEncodingException;
import java.util.List;

public class InfoActivity extends Activity implements InfoView {

    TextView responseText, responseText2;
    APIInterface apiInterface;
    InfoPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        responseText = (TextView) findViewById(R.id.textView);
        responseText2 = (TextView) findViewById(R.id.textView2);
        apiInterface = APIClient.getClient().create(APIInterface.class);

        presenter = new InfoPresenter();
        presenter.attachView(this);
        try {
            presenter.infoListUpdate();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        init();

        /*Call<Info> call4 = apiInterface.getInfoData();
        call4.enqueue(new Callback<Info>() {

            @Override
            public void onResponse(Call<Info> call, Response<Info> response) {
                Info infoData = response.body();

                List<Info.InfoList> infoLists = infoData.data;

                Logger.log("#123 => " + infoLists);

                //int id = infoData.id;
                //int pw = infoData.pw;
                //responseText.setText("id : " + id + "\n" + "pw : " + pw);
                responseText.setText(infoLists.get(1).toString());
                responseText2.setText(infoLists.get(1).getId() + "");
            }
            @Override
            public void onFailure(Call<Info> call, Throwable t) {
                Logger.log("#911 => fail");
            }
        });*/
    }
    public void init() {

    }

    @Override
    public void getInfoListItems(List<Info.InfoList> infos) {

    }

    @Override
    public Context getContext() {
        return null;
    }

    @Override
    public void notConnectNetworking() {

    }
}
