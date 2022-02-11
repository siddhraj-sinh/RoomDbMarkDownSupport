package com.example.roomdemoapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_view_layout.view.*

class itemAdapter(val context:Context):RecyclerView.Adapter<viewHolder>() {
    val items = ArrayList<Employee>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        return viewHolder(LayoutInflater.from(context).inflate(R.layout.item_view_layout,parent,false))
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        val item = items.get(position)


        holder.tvName.text = item.name
        holder.tvEmail.text = item.email
        holder.tvMdName.text=item.mdname
        // Updating the background color according to the odd/even positions in list.
        if (position % 2 == 0) {
            holder.llMain.setBackgroundColor(
                ContextCompat.getColor(
                    context,
                    R.color.colorLightGray
                )
            )
        } else {
            holder.llMain.setBackgroundColor(ContextCompat.getColor(context, R.color.colorWhite))
        }
        holder.ivEdit.setOnClickListener {
            if (context is MainActivity) {
                   context.updateRecordDialog(item)
            }
        }
        holder.ivDelete.setOnClickListener {
            if (context is MainActivity) {
                context.deleteRecordAlertDialog(item)
            }
        }
    }

    override fun getItemCount(): Int {
return items.size
    }
    fun updateList(newList:List<Employee>){
        items.clear()
        items.addAll(newList)

        notifyDataSetChanged()
    }
}

class viewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val llMain = itemView.llMain
    val tvName = itemView.tv_name
    val tvEmail = itemView.tv_email
    val tvMdName = itemView.tv_md_name
    val ivEdit = itemView.iv_edit
    val ivDelete = itemView.iv_delete

}
