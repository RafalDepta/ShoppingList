package pl.depta.rafal.shoppinglist.data;

import javax.inject.Inject;

import pl.depta.rafal.shoppinglist.data.db.DbHelper;


public class AppDataManager implements DataManager {

    private final DbHelper mDbHelper;
    private final PreferencesHelper mPreferencesHelper;

    @Inject
    public AppDataManager(DbHelper dbHelper,
                          PreferencesHelper preferencesHelper) {
        mDbHelper = dbHelper;
        mPreferencesHelper = preferencesHelper;
    }

}
