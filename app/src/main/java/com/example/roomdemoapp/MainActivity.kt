package com.example.roomdemoapp

import android.app.AlertDialog
import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.yahiaangelo.markdownedittext.MarkdownEditText
import com.yahiaangelo.markdownedittext.MarkdownStylesBar
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.dialog_update.*

class MainActivity : AppCompatActivity(){
    lateinit var viewModel: EmployeeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rv_itemlist.layoutManager = LinearLayoutManager(this)
        val adapter = itemAdapter(this)
        rv_itemlist.adapter = adapter

        viewModel = ViewModelProvider(this).get(EmployeeViewModel::class.java)

        viewModel.allEmp.observe(this, Observer { List->
           List?.let { adapter.updateList(it)
               if (it.isNotEmpty()){
                   rv_itemlist.visibility= View.VISIBLE
                   tv_NoRecordsAvailable.visibility= View.GONE
               }
               else{
                   rv_itemlist.visibility= View.GONE
                   tv_NoRecordsAvailable.visibility = View.VISIBLE
               }
           }

       })
        //on add record click
        btn_add_record.setOnClickListener {
            submitData()
        }
        //attaching style bar to markdown edittext
        val markdownEditText = findViewById<MarkdownEditText>(R.id.et_name)
        val stylesBar = findViewById<MarkdownStylesBar>(R.id.styleBar)
        markdownEditText.setStylesBar(stylesBar)
    }

    private fun submitData() {
        val name = et_name.text.toString()
        //getting markdown text
        val nameMd = et_name.getMD()
        val email = et_email_id.text.toString()
        if(name.isNotEmpty()&&email.isNotEmpty()){

                viewModel.insertEmp(Employee(0,name,email,nameMd))
            et_name.text?.clear()
            et_email_id.text.clear()
            Toast.makeText(this,nameMd,Toast.LENGTH_SHORT).show()
        }

    }
   fun deleteRecordAlertDialog(emp:Employee){

       val builder = AlertDialog.Builder(this)
       //set title for alert dialog
       builder.setTitle("Delete Record")
       //set message for alert dialog
       builder.setMessage("Are you sure you wants to delete ${emp.name}.")
       builder.setIcon(android.R.drawable.ic_dialog_alert)
       //performing positive action
       builder.setPositiveButton("Yes") { dialogInterface, which ->
           viewModel.deleteEmp(emp)
           dialogInterface.dismiss() // Dialog will be dismissed
       }
       //performing negative action
       builder.setNegativeButton("No") { dialogInterface, which ->
           dialogInterface.dismiss() // Dialog will be dismissed
       }
       // Create the AlertDialog
       val alertDialog: AlertDialog = builder.create()
       // Set other dialog properties
       alertDialog.setCancelable(false) // Will not allow user to cancel after clicking on remaining screen area.
       alertDialog.show()
   }
    fun updateRecordDialog(emp: Employee){
        val updateDialog = Dialog(this, R.style.Theme_Dialog)
        updateDialog.setCancelable(false)
        /*Set the screen content from a layout resource.
         The resource will be inflated, adding all top-level views to the screen.*/
        updateDialog.setContentView(R.layout.dialog_update)
        updateDialog.etUpdateName.setStylesBar(updateDialog.styleBar_update)
        updateDialog.etUpdateName.renderMD(emp.mdname!!)
        updateDialog.etUpdateEmailId.setText(emp.email)

        updateDialog.tvUpdate.setOnClickListener(View.OnClickListener {

            val name = updateDialog.etUpdateName.text.toString()
            val email = updateDialog.etUpdateEmailId.text.toString()


            if (!name.isEmpty() && !email.isEmpty()) {


                viewModel.updateEmp(Employee(emp.id,name,email,updateDialog.etUpdateName.getMD()))
                    updateDialog.dismiss() // Dialog will be dismissed

            }
        })
        updateDialog.tvCancel.setOnClickListener(View.OnClickListener {
            updateDialog.dismiss()
        })
        //Start the dialog and display it on screen.
        updateDialog.show()
    }

}