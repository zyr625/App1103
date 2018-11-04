package lenovo.example.com.app1103;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import lenovo.example.com.app1103.greendao.CacheCarDao;
import lenovo.example.com.app1103.greendao.DaoMaster;
import lenovo.example.com.app1103.greendao.DaoSession;

/**
 * author：shashe
 * 日期：2018/11/3
 */
public class SqliteUtils {
    private CacheCarDao cacheCarDao;

    private SqliteUtils() {
    }

    private static SqliteUtils mSqliteUtils;

    public static SqliteUtils getSqliteUtils() {
        if (mSqliteUtils == null) {
            mSqliteUtils = new SqliteUtils();
        }
        return mSqliteUtils;
    }

    public void init(Context context) {
        DaoMaster.DevOpenHelper car = new DaoMaster.DevOpenHelper(context, "car");
        SQLiteDatabase db = car.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        cacheCarDao = daoMaster.newSession().getCacheCarDao();
    }

    public void insert(CacheCar cacheCar) {
        cacheCarDao.insert(cacheCar);
    }

    public void deleteAll() {
        cacheCarDao.deleteAll();
    }

    public List<CacheCar> queryAll() {
        return cacheCarDao.loadAll();
    }

    public CacheCar query(String key){
        return cacheCarDao.load(Long.parseLong(key));
    }
    public void update(CacheCar cacheCar){
        cacheCarDao.update(cacheCar);
    }
}
