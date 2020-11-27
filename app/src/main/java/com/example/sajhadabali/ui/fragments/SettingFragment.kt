package com.example.sajhadabali.ui.fragments

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.example.sajhadabali.R

class SettingFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.setting, rootKey)
    }


}