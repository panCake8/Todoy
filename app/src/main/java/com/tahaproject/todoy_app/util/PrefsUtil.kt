package com.tahaproject.todoy_app.util

import android.content.Context
import android.content.SharedPreferences
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey


class PrefsUtil {
    companion object {
        private const val MASTER_KEY_ALIAS = "MASTER_KEY_ALIAS"

        fun initSharedPref(context: Context): SharedPreferences {
            return EncryptedSharedPreferences.create(
                context,
                Constants.SharedPref.SHARED_PREF_NAME,
                getMasterKey(context),
                EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
            )
        }

        private fun getMasterKey(context: Context) = MasterKey.Builder(context)
            .setKeyGenParameterSpec(getSpec())
            .build()

        private fun getSpec() = KeyGenParameterSpec.Builder(
            MASTER_KEY_ALIAS,
            KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT
        )
            .setBlockModes(KeyProperties.BLOCK_MODE_GCM)
            .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_NONE)
            .setKeySize(16)
            .build()

    }
}