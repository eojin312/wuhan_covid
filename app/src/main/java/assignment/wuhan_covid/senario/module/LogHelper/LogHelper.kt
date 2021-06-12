package com.autocrypt.mi.green.dvr.utils.LogHelper

import android.util.Log
import android.widget.Toast
import assignment.wuhan_covid.senario.module.MainApp

class LogHelper {
    companion object {
        val TAG: String = "LogHelper"

        fun printLine() {
            Log.d(TAG, "--------------------")
        }

        fun printSection(sectionName: String?) {
            Log.d(TAG, "---------- $sectionName ----------")
        }

        fun printSection(title: String?, content: String?) {
            Log.d(TAG, "---------- $title BEGIN ----------")
            Log.d(TAG, ">>> $content <<<")
            Log.d(TAG, "---------- $title END ----------")
        }

        fun print(content: String?) {
            Log.d(TAG, ">>>>> $content <<<<<")
        }

        fun print(title: String, content: String?) {
            Log.d(TAG, "$title -> $content")
        }

        fun print(func: Any, content: String?) {
            val regex = Regex("((\\$).+(\\$))")
            val msg = func.javaClass.enclosingMethod?.toString() ?: "Unknown Error"

            val functionTitle = regex.find(msg)?.value

            Log.d(TAG, "${functionTitle ?: msg} --> $content")
        }

        fun printSectionError(title: String?, content: String?) {
            Log.e(TAG, "---------- $title BEGIN ----------")
            Log.e(TAG, ">>> $content <<<")
            Log.e(TAG, "---------- $title END ----------")
        }

        fun printError(content: String?) {
            Log.e(TAG, ">>>>> $content <<<<<")
        }

        fun printError(title: String, content: String?) {
            Log.e(TAG, "$title -> $content")
        }

        fun printError(func: Any, content: String?) {
            val regex = Regex("((\\$).+(\\$))")
            val msg = func.javaClass.enclosingMethod?.toString() ?: "Unknown Error"

            val functionTitle = regex.find(msg)?.value

            Log.e(TAG, "${functionTitle ?: msg} --> $content")
        }

        fun showToast(message: String?) {
            Toast.makeText(MainApp.ctx, message, Toast.LENGTH_SHORT).show()
        }
    }
}