package com.example.todolistproject.fragments.add

import android.os.Bundle
import android.text.TextUtils
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.todolistproject.R
import com.example.todolistproject.fragments.data.models.Priority
import com.example.todolistproject.fragments.data.models.ToDoData
import com.example.todolistproject.fragments.data.viewmodel.ToDoViewModel
import kotlinx.android.synthetic.main.fragment_add.*

class AddFragment : Fragment() {

    private val mTodoViewModel : ToDoViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        // Set Menu
        setHasOptionsMenu(true)

        val view = inflater.inflate(R.layout.fragment_add, container, false)

        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {

        inflater.inflate(R.menu.add_fragment_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menu_add){
            insertDataToDb()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun insertDataToDb() {
        val mTitle = titleEdit.text.toString()
        val mPriority = spinner.selectedItem.toString()
        val mDescription =editTextMultiLine.text.toString()

        val validation = verifyDataFromUser(mTitle , mDescription)
        if(validation){
            // Insert DATA to Database
            val newData = ToDoData(
                    0,
                    mTitle,
                    parsePriority(mPriority),
                    mDescription
            )
            mTodoViewModel.insertData(newData)
            Toast.makeText(requireContext(),"Successful added", Toast.LENGTH_SHORT).show()
            //Navigation back

            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        }else{
            Toast.makeText(requireContext(),"please fill out all fields", Toast.LENGTH_SHORT).show()

        }
    }


    private fun verifyDataFromUser(title : String , description : String ): Boolean{
        return if(TextUtils.isEmpty(title) || TextUtils.isEmpty(description) ){
            false
        } else !(title.isEmpty() || description.isEmpty())

    }

    private fun parsePriority(priority: String) : Priority{
        return when(priority){
            "High Priority" -> {Priority.HIGH}
            "Medium Priority" -> {Priority.MEDIUM}
            "Low Priority" -> {Priority.LOW}
            else -> Priority.LOW
        }
    }
}