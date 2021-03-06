package com.smartboox.travel.core.database;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.Model;
import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;

import java.util.List;
import java.util.Objects;

public class ActiveAndroidDatabaseHelper {

    public static <T extends Model> T getItemById(Class<T> table, long id) {
        return new Select()
                .from(table)
                .where("Id = ?", id)
                .executeSingle();
    }

    public static <T extends Model> T getItemById(Class<T> table, String column, int id) {
        String whereClause = String.format("%s = ?",column);
        return new Select()
                .from(table)
                .where(whereClause, id)
                .executeSingle();
    }

    public static <T extends Model> List<T> getList(Class<T> table) {
        return new Select()
                .from(table)
                .execute();
    }

    public static <T extends Model> List<T> getListById(Class<T> table, String column, int id) {
        String whereClause = String.format("%s = ?",column);
        return new Select()
                .from(table)
                .where(whereClause,id)
                .execute();
    }

    public static <T extends Model> long saveItem(T item) {
        return item.save();
    }

    public static <T extends Model> int saveAll(List<T> itemList) {
        int count = 0;
        if (itemList != null && itemList.size() > 0) {
            for (T item : itemList) {
                item.save();
                count++;
            }
        }

        return count;
    }

    public static <T extends Model> T removeItem(Class<T> table, long id) {
        T item = T.load(table, id);
        if (item != null) {
            item.delete();
            return item;
        }
        return null;
    }

    public static <T extends Model> boolean removeItem(Class<T> table, T item) {
        return removeItem(table, item.getId()) != null;
    }

    public static <T extends Model> List<T> removeAll(Class<T> table) {
        return new Delete().from(table).execute();
    }

    public static void startTransactionExecution(OnTransactionExecuteListener listener) {
        ActiveAndroid.beginTransaction();
        try {
            if (listener != null) {
                listener.onExecute();
            }
            ActiveAndroid.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ActiveAndroid.endTransaction();
        }
    }
}
