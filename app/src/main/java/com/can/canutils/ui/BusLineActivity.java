package com.can.canutils.ui;

import android.os.Bundle;

import com.can.canutils.R;
import com.can.custom_view.bean.BusBaseBean;
import com.can.custom_view.bean.BusStationBean;
import com.can.custom_view.views.BusLineView;
import com.can.mvp.base.BaseActivity;
import com.can.mvp.views.BindView;

import java.util.ArrayList;

/**
 * Created by CAN on 19-3-21.
 */

public class BusLineActivity extends BaseActivity {

    @BindView(id = R.id.view_bus_line)
    BusLineView mBusLineView;

    @Override
    public int getLayoutId() {
        return R.layout.activity_bus_line;
    }

    @Override
    public void initData(Bundle bundle) {
        super.initData(bundle);

        ArrayList list = new ArrayList<BusBaseBean>();

        BusBaseBean busBaseBean = new BusBaseBean();
        busBaseBean.setBusStationBean(new BusStationBean(0,"广州大道中路口"));

        BusBaseBean busBaseBean2 = new BusBaseBean();
        busBaseBean2.setBusStationBean(new BusStationBean(1,"珠江新城2号线"));

        BusBaseBean busBaseBean3 = new BusBaseBean();
        busBaseBean3.setBusStationBean(new BusStationBean(2,"我的老家就要到了"));

        list.add(busBaseBean);
        list.add(busBaseBean);
        list.add(busBaseBean);
        list.add(busBaseBean3);
        list.add(busBaseBean);
        list.add(busBaseBean);
        list.add(busBaseBean);
        list.add(busBaseBean);
        list.add(busBaseBean2);
        list.add(busBaseBean);
        list.add(busBaseBean);


        mBusLineView.updateView(list);
    }
}
