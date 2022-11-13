package com.overtune.investments.adapters

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.opengl.Visibility
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.overtune.investments.R
import java.text.SimpleDateFormat
import java.util.*

class ChatAdapter(private val dataSet: MutableList<MessageItem>, private val context: Context?) :
    RecyclerView.Adapter<ChatAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textMessage: TextView
        val timeMessage: TextView
        val fileSize: TextView
        val messageLayout: ConstraintLayout
        val fileImage: ImageView
        val options1: Button
        val options2: Button

        init {
            // Define click listener for the ViewHolder's View.
            textMessage = view.findViewById(R.id.textMessage)
            timeMessage = view.findViewById(R.id.textTimeMessage)
            fileSize = view.findViewById(R.id.fileSize)
            messageLayout = view.findViewById(R.id.messageLayout)
            fileImage = view.findViewById(R.id.fileImage)
            options1 = view.findViewById(R.id.options1)
            options2 = view.findViewById(R.id.options2)
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_message, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element

        if (dataSet[position].userId == "0" && dataSet[position].file) {
            viewHolder.messageLayout.background =
                ContextCompat.getDrawable(context!!, R.drawable.message_bounds)
            val layoutParams =
                viewHolder.messageLayout.layoutParams as (ConstraintLayout.LayoutParams)
            layoutParams.endToEnd = ConstraintLayout.LayoutParams.PARENT_ID
            viewHolder.messageLayout.layoutParams = layoutParams
            viewHolder.textMessage.text = dataSet[position].textMessage
            viewHolder.textMessage.setTextColor(Color.parseColor("#FFFFFF"))
            viewHolder.timeMessage.setTextColor(Color.parseColor("#FFFFFF"))
            viewHolder.timeMessage.text = dataSet[position].timeMessage

            viewHolder.fileImage.visibility = View.VISIBLE
            viewHolder.fileSize.visibility = View.VISIBLE

            viewHolder.textMessage.text = dataSet[position].textMessage
            viewHolder.fileSize.text = "16 мб"
            viewHolder.fileSize.setTextColor(Color.parseColor("#FFFFFF"))
            viewHolder.textMessage.setTextColor(Color.parseColor("#FFFFFF"))
            viewHolder.timeMessage.setTextColor(Color.parseColor("#FFFFFF"))
            viewHolder.timeMessage.text = dataSet[position].timeMessage
        } else if (dataSet[position].userId == "1" && dataSet[position].file) {
            viewHolder.messageLayout.background =
                ContextCompat.getDrawable(context!!, R.drawable.message_bounds_other_user)
            val layoutParams =
                viewHolder.messageLayout.layoutParams as (ConstraintLayout.LayoutParams)
            layoutParams.startToStart = ConstraintLayout.LayoutParams.PARENT_ID
            viewHolder.messageLayout.layoutParams = layoutParams
            viewHolder.textMessage.text = dataSet[position].textMessage
            viewHolder.textMessage.setTextColor(Color.parseColor("#000000"))
            viewHolder.timeMessage.setTextColor(Color.parseColor("#000000"))
            viewHolder.timeMessage.text = dataSet[position].timeMessage
            viewHolder.options1.visibility = View.VISIBLE
            viewHolder.options2.visibility = View.VISIBLE
        } else if (dataSet[position].userId == "0") {
            viewHolder.messageLayout.background =
                ContextCompat.getDrawable(context!!, R.drawable.message_bounds)
            val layoutParams =
                viewHolder.messageLayout.layoutParams as (ConstraintLayout.LayoutParams)
            layoutParams.endToEnd = ConstraintLayout.LayoutParams.PARENT_ID
            viewHolder.messageLayout.layoutParams = layoutParams
            viewHolder.textMessage.text = dataSet[position].textMessage
            viewHolder.textMessage.setTextColor(Color.parseColor("#FFFFFF"))
            viewHolder.timeMessage.setTextColor(Color.parseColor("#FFFFFF"))
            viewHolder.timeMessage.text = dataSet[position].timeMessage
        } else {
            viewHolder.messageLayout.background =
                ContextCompat.getDrawable(context!!, R.drawable.message_bounds_other_user)
            val layoutParams =
                viewHolder.messageLayout.layoutParams as (ConstraintLayout.LayoutParams)
            layoutParams.startToStart = ConstraintLayout.LayoutParams.PARENT_ID
            viewHolder.messageLayout.layoutParams = layoutParams
            viewHolder.textMessage.text = dataSet[position].textMessage
            viewHolder.textMessage.setTextColor(Color.parseColor("#000000"))
            viewHolder.timeMessage.setTextColor(Color.parseColor("#000000"))
            viewHolder.timeMessage.text = dataSet[position].timeMessage
        }
        viewHolder.options1.setOnClickListener {
            val sdf = SimpleDateFormat("hh:mm")
            val currentDate = sdf.format(Date())
            dataSet.add(MessageItem("Договор успешно подписан!", currentDate, "1"))
            notifyItemInserted(dataSet.size)
        }
        viewHolder.options2.setOnClickListener {
            val sdf = SimpleDateFormat("hh:mm")
            val currentDate = sdf.format(Date())
            dataSet.add(MessageItem("Операция отменена", currentDate, "1"))
            notifyItemInserted(dataSet.size)
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size
}