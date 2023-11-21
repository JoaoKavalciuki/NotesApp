package com.example.notesapp.contracts

import android.app.Activity.RESULT_OK
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.activity.result.contract.ActivityResultContract
import com.example.notesapp.ui.view.NewNoteActivity


class PassNoteDataContract : ActivityResultContract< Unit?, Pair<String, String>?>() {
    override fun createIntent(context: Context, input: Unit?): Intent {
        Log.d("Contract", "Create intent chamado")
        return Intent(context, NewNoteActivity::class.java)

    }

    override fun parseResult(resultCode: Int, intent: Intent?): Pair<String, String>? {
        return if (resultCode == RESULT_OK) {
            intent?.let {
                val string1 = it.getStringExtra(TITLE_EXTRA)?: ""
                val string2 = it.getStringExtra(CONTENT_EXTRA)?: ""
                Pair(string1, string2)
            }
        } else {
            null
        }
    }


    companion object{
        val TITLE_EXTRA = "TITLE"
        val CONTENT_EXTRA = "CONTENT"

    }
}