package pl.depta.rafal.shoppinglist.data.db;


import javax.inject.Inject;


public class AppDbHelper implements DbHelper {

    private final AppDatabase mAppDatabase;

    @Inject
    public AppDbHelper(AppDatabase appDatabase) {
        this.mAppDatabase = appDatabase;
    }


}
