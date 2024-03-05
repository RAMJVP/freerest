

package com.bit.service;

import java.io.IOException;

import com.google.api.client.util.store.DataStore;
import com.google.api.client.util.store.DataStoreFactory;
import com.google.api.client.util.store.MemoryDataStoreFactory;

public class CustomDataStoreFactory implements DataStoreFactory {

    private final MemoryDataStoreFactory memoryDataStoreFactory;

    public CustomDataStoreFactory() {
        this.memoryDataStoreFactory = new MemoryDataStoreFactory();
    }

    @Override
    public <V extends java.io.Serializable> DataStore<V> getDataStore(String id) throws IOException {
        return memoryDataStoreFactory.getDataStore(id);
    }
    
    



    // Other methods like setDataStoreRequestInitializer, getPermissions, etc., are omitted for brevity.
}
