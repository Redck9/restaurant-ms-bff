package com.redck.restaurantmsbff.service;

import com.redck.restaurantmsbff.client.TableClient;
import com.redck.restaurantmsbff.domain.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TableService
{
    private final TableClient tableClient;

    @Autowired
    public TableService(final TableClient tableClient)
    {
        this.tableClient = tableClient;
    }

    /**
     * Call get table client.
     * @param tableUid table uid.
     * @return table.
     */
    public Table getTable(final String tableUid)
    {
        return tableClient.getTable(tableUid);
    }

    /**
     * Call create table client.
     * @param table table to create.
     * @return table.
     */
    public Table createTable(final Table table)
    {
        return tableClient.createTable(table);
    }

    /**
     * Call edit table client.
     * @param tableUid table uid to edit.
     * @param table table to be edited
     * @return table edited.
     */
    public Table editTable(final String tableUid, final Table table)
    {
        return tableClient.editTable(tableUid, table);
    }

    /**
     * Call remove table.
     * @param tableUid table uid.
     * @return Table Deleted.
     */
    public Table deleteTable(final String tableUid)
    {
        return tableClient.deleteTable(tableUid);
    }

    /**
     * Call Get all tables.
     * @return Table List.
     */
    public List<Table> getAllTables()
    {
        return tableClient.getAllTables();
    }

    /**
     * Call Get all tables from a restaurant.
     * @param restaurantUid restaurant uid.
     * @return Table List.
     */
    public List<Table> getAllTablesFromRestaurant(final String restaurantUid)
    {
        return tableClient.getAllTablesFromRestaurant(restaurantUid);
    }
}
