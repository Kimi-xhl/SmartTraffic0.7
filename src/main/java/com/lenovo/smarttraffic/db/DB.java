package com.lenovo.smarttraffic.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.lenovo.smarttraffic.InitApp;
import com.lenovo.smarttraffic.bean.F1;
import com.lenovo.smarttraffic.bean.F2;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;


public class DB extends OrmLiteSqliteOpenHelper {
    private static String databaseName = "traffic.db";
    private static int databaseVersion = 1;
    private static DB instance;
    private Map<String, Dao> map = new HashMap<>();

    public DB(Context context) {
        super(context, databaseName, null, databaseVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {
        try {
            TableUtils.createTableIfNotExists(connectionSource, F1.class);
            TableUtils.createTableIfNotExists(connectionSource, F2.class);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int i, int i1) {
        try {
            TableUtils.dropTable(connectionSource, F1.class, true);
            TableUtils.dropTable(connectionSource, F2.class, true);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取实例.
     *
     * @return the instance
     */
    public synchronized static DB getInstance() {
        if (instance == null) {
            synchronized (DB.class) {
                if (instance == null) {
                    instance = new DB(InitApp.getInstance());
                }
            }
        }
        return instance;
    }

    /**
     * 获取dao.
     *
     * @return Dao
     */
    public synchronized Dao getDao(Class clazz) {
        Dao dao = null;
        //获取类名
        String name = clazz.getSimpleName();
        //如果已存在则从map中取出
        if (map.containsKey(name)) {
            dao = map.get(name);
        }

        //如果dao为空，则创建dao
        if (dao == null) {
            try {
                //创建dao并存入map中
                dao = super.getDao((Class<Object>) clazz);
                map.put(name, dao);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return dao;
    }
}
