package com.example.javaLearningApp.QuestionsFragment

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class NotesDBHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "DatabaseInterviewQue2"
        private const val DATABASE_VERSION = 1
        private const val TABLE_NAME = "QuestionTable2"
        private const val COLUMN_ID = "id"
        private const val COLUMN_QUE = "Question1"
        private const val COLUMN_ANS = "Answer1"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val SQL_CREATE_TABLE =
            "CREATE TABLE $TABLE_NAME ($COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, $COLUMN_QUE TEXT, $COLUMN_ANS TEXT)"

        db.execSQL(SQL_CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun insertNote(title: String, description: String): Long {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_QUE, title)
            put(COLUMN_ANS, description)
        }
        val newRowId = db.insert(TABLE_NAME, null, values)
        db.close()
        return newRowId
    }
    fun getAllNotes(): List<QueData> {
        val notesList = mutableListOf<QueData>()
        val db = readableDatabase
        val projection = arrayOf(COLUMN_ID, COLUMN_QUE, COLUMN_ANS)
        val cursor: Cursor? = db.query(
            TABLE_NAME, projection, null, null, null, null, null
        )
        cursor?.use {
            while (it.moveToNext()) {
                val id = it.getLong(it.getColumnIndexOrThrow(COLUMN_ID))
                val title = it.getString(it.getColumnIndexOrThrow(COLUMN_QUE))
                val description = it.getString(it.getColumnIndexOrThrow(COLUMN_ANS))
                notesList.add(QueData(id, title, description))
            }
        }
        return notesList
    }
}
data class QueData(val id: Long, val question: String, val answer: String,  var isExpandable: Boolean = false)
