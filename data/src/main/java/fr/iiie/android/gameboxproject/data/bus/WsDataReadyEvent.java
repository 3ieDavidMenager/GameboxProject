package fr.iiie.android.gameboxproject.data.bus;

import java.util.List;

import fr.iiie.android.gameboxproject.data.model.MyCustomModel;

public class WsDataReadyEvent
{
    private List<MyCustomModel> myCustomModelList;

    public WsDataReadyEvent(List<MyCustomModel> myCustomModelList)
    {
        this.myCustomModelList = myCustomModelList;
    }

    public List<MyCustomModel> getMyCustomModelList()
    {
        return myCustomModelList;
    }
}
