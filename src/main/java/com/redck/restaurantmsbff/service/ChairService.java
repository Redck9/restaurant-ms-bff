package com.redck.restaurantmsbff.service;

import com.redck.restaurantmsbff.client.ChairClient;
import com.redck.restaurantmsbff.domain.Chair;
import com.redck.restaurantmsbff.domain.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChairService
{
    private final ChairClient chairClient;

    @Autowired
    public ChairService(final ChairClient chairClient)
    {
        this.chairClient = chairClient;
    }

    /**
     * Call get chair client.
     * @param chairUid chair uid.
     * @return chair.
     */
    public Chair getChair(final String chairUid)
    {
        return chairClient.getChair(chairUid);
    }

    /**
     * Call create chair client.
     * @param chair chair to create.
     * @return chair.
     */
    public Chair createChair(final Chair chair)
    {
        return chairClient.createChair(chair);
    }

    /**
     * Call edit chair client.
     * @param chairUid chair uid to edit.
     * @param chair chair to be edited
     * @return chair edited.
     */
    public Chair editChair(final String chairUid, final Chair chair)
    {
        return chairClient.editChair(chairUid, chair);
    }

    /**
     * Call remove chair.
     * @param chairUid chair uid.
     * @return Chair Deleted.
     */
    public Chair deleteChair(final String chairUid)
    {
        return chairClient.deleteChair(chairUid);
    }

    /**
     * Call Get all tables.
     * @return Table List.
     */
    public List<Chair> getAllChair()
    {
        return chairClient.getAllChair();
    }

    /**
     * Call Get all chairs from a table.
     * @param tableUid table uid.
     * @return Chair List.
     */
    public List<Chair> getAllChairFromTable(final String tableUid)
    {
        return chairClient.getAllChairFromTable(tableUid);
    }
}
