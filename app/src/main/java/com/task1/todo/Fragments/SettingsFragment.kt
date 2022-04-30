package com.task1.todo.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import com.google.android.material.textfield.TextInputLayout
import com.task1.todo.R

class SettingsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.settings_fragment, container, false)
    }


    var items_Drop_Down: Array<String> = arrayOf("Dark", "Light")
    lateinit var textInputLayout: TextInputLayout
    lateinit var autoCompleteTextView: AutoCompleteTextView
    lateinit var adapter: ArrayAdapter<String>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        autoCompleteTextView = requireView().findViewById(R.id.autoCompleteTextView)
        textInputLayout = requireView().findViewById(R.id.textInputLayout)

        adapter = ArrayAdapter<String>(
            requireContext(),
            R.layout.dark_text_input_item_settings,
            items_Drop_Down
        )

        autoCompleteTextView.setAdapter(adapter)


        autoCompleteTextView.setOnItemClickListener { parent, view, position, id ->

            if (parent.getItemAtPosition(position).toString() == "Light") {

                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            } else if (parent.getItemAtPosition(position).toString() == "Dark") {

                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)

            }
        }

    }
}