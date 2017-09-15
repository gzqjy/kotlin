package tk.danielgong.criminalintent.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*


/*
 * Created by gongzhq on 2017/9/15.
 */

class CrimeBaseHelper(ctx: Context): ManagedSQLiteOpenHelper(ctx, DB_NAME, null, DB_VERSION) {
    companion object {
        private var instance: CrimeBaseHelper? = null
        val DB_NAME = "crimeBase.db"
        val DB_VERSION = 1

        @Synchronized
        fun getInstance(ctx: Context): CrimeBaseHelper {
            if (instance == null) {
                instance = CrimeBaseHelper(ctx.applicationContext)
            }
            return instance!!
        }
    }
    override fun onCreate(db: SQLiteDatabase) {
        // Here you create tables
        db.createTable(CrimeDbSchema.NAME, ifNotExists = true, columns = *arrayOf(
                CrimeDbSchema.ID to INTEGER + PRIMARY_KEY + UNIQUE,
                CrimeDbSchema.UUID to TEXT,
                CrimeDbSchema.TITLE to TEXT,
                CrimeDbSchema.DATE to TEXT,
                CrimeDbSchema.SOLVED to TEXT))
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Here you can upgrade tables, as usual
        db.dropTable( CrimeDbSchema.NAME, true)
        onCreate(db)
    }
}

// Access property for Context
val Context.database: CrimeBaseHelper
    get() = CrimeBaseHelper.getInstance(applicationContext)