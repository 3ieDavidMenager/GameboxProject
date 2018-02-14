package fr.iiie.android.gameboxproject.business.sample;

import org.greenrobot.eventbus.Subscribe;

import fr.iiie.android.gameboxproject.data.app.App;
import fr.iiie.android.gameboxproject.data.bus.WsDataReadyEvent;
import fr.iiie.android.gameboxproject.data.request.WsRequest;
import fr.iiie.android.gameboxproject.business.Controller;

public class SampleController extends Controller
{

    public SampleController()
    {
        // Required empty constructor
    }

    public void getWsData()
    {
        WsRequest.getWsData();
    }

    @Subscribe
    public void onWsDataReadyEvent(WsDataReadyEvent event)
    {
        App.getAppBus().post(event);
    }
}
