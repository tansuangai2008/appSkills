package com.example.myapplication.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.databinding.ItemFunctionsBinding
import com.example.myapplication.model.FunctionObj


/**
 *    author : LIU YANG
 *    date   : 2024/2/5
 *    desc   : 首页功能模块入口
 */
class FunctionAdapter(val context: Context, val list: List<FunctionObj>) : RecyclerView.Adapter<FunctionAdapter.ItemViewHolder>() {

    private val TAG: String = "FunctionAdapter"
    private lateinit var binding: ItemFunctionsBinding
    var itemOnclickListener: ItemOnclickListener? = null
//    private var list: MutableList<FunctionObj> = mutableListOf<FunctionObj>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        binding = ItemFunctionsBinding.inflate(LayoutInflater.from(context), parent, false)
        return ItemViewHolder(binding.root)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        var obj = list[position]
        obj?.let {
            holder.textView.text = it.label
            itemOnclickListener?.let {
                holder.itemView.setOnClickListener(object : View.OnClickListener {
                    override fun onClick(v: View?) {
                        it.onClick(obj, holder.adapterPosition)
                    }
                })
            }
        }
    }

    public inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textView = itemView.rootView.findViewById<TextView>(R.id.tv_txt)

    }

    interface ItemOnclickListener {
        fun onClick(any: Any, position: Int)
    }
}