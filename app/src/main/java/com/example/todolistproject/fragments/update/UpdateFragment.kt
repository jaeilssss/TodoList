package com.example.todolistproject.fragments.update

import android.os.Bundle
import android.view.*
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.todolistproject.R
import com.example.todolistproject.fragments.SharedViewModel
import com.example.todolistproject.fragments.data.models.Priority
import com.example.todolistproject.fragments.data.models.ToDoData
import kotlinx.android.synthetic.main.fragment_add.view.*
import kotlinx.android.synthetic.main.fragment_update.view.*
import kotlinx.android.synthetic.main.row_layout.view.*

class UpdateFragment : Fragment() {

  private val args by navArgs<UpdateFragmentArgs>()

    private val mSharedViewModel : SharedViewModel by viewModels()
  lateinit var data : ToDoData

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {



        // Inflate the layout for this fragment
        var view =  inflater.inflate(R.layout.fragment_update, container, false)

        view.findViewById<EditText>(R.id.currentTitleEdit).setOnClickListener { findNavController().navigate(R.id.action_updateFragment_to_listFragment) }
        // Set Menu
        setHasOptionsMenu(true)

        view.currentTitleEdit.setText(args.currentItem.title)

        view.currentEditTextMultiLine.setText(args.currentItem.description)



        view.currentSpinner.setSelection(parsePriority(args.currentItem.priority))

        view.currentSpinner.onItemSelectedListener = mSharedViewModel.listener


        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.update_fragment_menu,menu)
    }

    fun parsePriority(priority: Priority) : Int{
        return when(priority){
            Priority.HIGH  -> 0
            Priority.MEDIUM -> 1
            Priority.LOW -> 2
        }
    }
}