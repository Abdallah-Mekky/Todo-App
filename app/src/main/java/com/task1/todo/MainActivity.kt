package com.task1.todo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.ListFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationBarView
import com.task1.todo.Fragments.BottomSheet
import com.task1.todo.Fragments.SettingsFragment

class MainActivity : AppCompatActivity() {

    lateinit var bottomNavigationView: BottomNavigationView
    lateinit var floatingAction: FloatingActionButton
    lateinit var bottomSheetDialogFragment: BottomSheet
    var listFragment = com.task1.todo.Fragments.ListFragment()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavigationView = findViewById(R.id.bottom_Navigation_View)
        bottomNavigationView.background = null
        floatingAction = findViewById(R.id.floating_Action_Button)

        bottomNavigationView.setOnItemSelectedListener(NavigationBarView.OnItemSelectedListener {

            if (it.itemId == R.id.settings) {
                pushFragment(SettingsFragment())
            } else if (it.itemId == R.id.list) {
                pushFragment(listFragment)
            }


            return@OnItemSelectedListener true
        })

        bottomNavigationView.selectedItemId = R.id.list

        floatingAction.setOnClickListener(View.OnClickListener {

            showAddBottomSheet()

        })


    }


    private fun pushFragment(fragment: Fragment) {

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_Container, fragment)
            .commit()
    }

    fun showAddBottomSheet() {
        bottomSheetDialogFragment = BottomSheet()
        bottomSheetDialogFragment.show(supportFragmentManager, "")

        bottomSheetDialogFragment.onTodoAdded = object : BottomSheet.onTaskAddedSuccessfully {
            override fun onTaskAdded() {

                if (listFragment.isVisible)
                    listFragment.get_All_Todos_From_DB_By_Day();
            }

        }

    }
}