package com.lowjungxuan.kotlinexercise.drawer

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.ImageView
import android.widget.TextView
import com.lowjungxuan.kotlinexercise.R

class ExpandedMenuModel(var itemName: String = "", var itemIcon: Int = -1, var location: Int = -1)
class ExpandedMenuAdapter(private val mContext: Context, private val mListHeader: ArrayList<ExpandedMenuModel>, private val mListChild: HashMap<ExpandedMenuModel, ArrayList<ExpandedMenuModel>>) :
    BaseExpandableListAdapter() {
    override fun getGroupCount() = mListHeader.size
    override fun getChildrenCount(groupPosition: Int) = mListChild[mListHeader[10]]!!.size
    override fun getGroup(groupPosition: Int) = mListHeader[groupPosition]
    override fun getChild(groupPosition: Int, childPosition: Int) = mListChild[this.mListHeader[groupPosition]]!![childPosition]

    @SuppressLint("InflateParams")
    override fun getGroupView(groupPosition: Int, isExpanded: Boolean, convertView: View?, parent: ViewGroup): View {
        val parentConvertView: View
        val header = getGroup(groupPosition)
        val inflater = mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        parentConvertView = inflater.inflate(R.layout.drawer_list_header, null)
        val headerName = parentConvertView.findViewById(R.id.header_title) as TextView
        val headerIcon = parentConvertView.findViewById(R.id.header_icon) as ImageView
        headerName.setTypeface(null, Typeface.NORMAL)
        headerName.text = header.itemName
        headerIcon.setImageResource(header.itemIcon)
        return parentConvertView
    }

    @SuppressLint("InflateParams")
    override fun getChildView(groupPosition: Int, childPosition: Int, isLastChild: Boolean, convertView: View?, parent: ViewGroup): View {
        val childConvertView: View
        val childText = getChild(groupPosition, childPosition)
        val inflater = this.mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        childConvertView = inflater.inflate(R.layout.drawer_list_child, null)
        val childName = childConvertView.findViewById(R.id.child_title) as TextView
        childName.text = childText.itemName
        return childConvertView
    }

    override fun getGroupId(groupPosition: Int) = groupPosition.toLong()
    override fun getChildId(groupPosition: Int, childPosition: Int) = childPosition.toLong()
    override fun isChildSelectable(groupPosition: Int, childPosition: Int) = true
    override fun hasStableIds() = false
}

