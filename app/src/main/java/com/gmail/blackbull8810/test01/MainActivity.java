package com.gmail.blackbull8810.test01;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.gmail.blackbull8810.test01.Retrofit2.APIClient;
import com.gmail.blackbull8810.test01.Retrofit2.APIInterface;

public class MainActivity extends AppCompatActivity {

    TextView responseText, responseText2;
    APIInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        responseText = (TextView) findViewById(R.id.textView);
        responseText2 = (TextView) findViewById(R.id.textView2);
        apiInterface = APIClient.getClient().create(APIInterface.class);


        /*Call<Info> call4 = apiInterface.getInfoData();
        call4.enqueue(new Callback<Info>() {

            @Override
            public void onResponse(Call<Info> call, Response<Info> response) {
                Info Info = response.body();

                List<Info.InfoList> infoLists = Info.data;

                Logger.log("#111 => " + infoLists);

                //int id = infoData.id;
                //int pw = infoData.pw;
                //responseText.setText("id : " + id + "\n" + "pw : " + pw);
                responseText.setText(infoLists.get(1).toString());
                responseText2.setText(infoLists.get(1).getId() + "");

            }

            @Override
            public void onFailure(Call<Info> call, Throwable t) {
                Logger.log("#111 => fail");
            }
        });*/



    }
}