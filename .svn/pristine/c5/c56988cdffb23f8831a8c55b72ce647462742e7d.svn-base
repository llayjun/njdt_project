package com.yuanye.njdt.ui.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.yuanye.njdt.R;
import com.yuanye.njdt.data.entity.ExpandEntity;

import java.util.List;

public class MyExpandableAdapter extends BaseExpandableListAdapter {

    private List<ExpandEntity> groupMapList;
    private List<List<ExpandEntity>> childMapList;
    private Context mContext;

    public MyExpandableAdapter(Context context, List<ExpandEntity> groupMapList, List<List<ExpandEntity>> childMapList) {
        mContext = context;
        this.groupMapList = groupMapList;
        this.childMapList = childMapList;
    }

    @Override
    public int getGroupCount() {
        return groupMapList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return childMapList.get(groupPosition).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groupMapList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return childMapList.get(groupPosition).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        View view = convertView;
        GroupHolder holder = null;
        if (view == null) {
            holder = new GroupHolder();
            view = LayoutInflater.from(mContext).inflate(R.layout.item_group_expand, null);
            holder.groupName = view.findViewById(R.id.item_group_left_text);
            holder.groupRightText = view.findViewById(R.id.item_group_right_text);
            view.setTag(holder);
        } else {
            holder = (GroupHolder) view.getTag();
        }
        //判断是否已经打开列表
        if (isExpanded) {
            Drawable drawable = mContext.getResources().getDrawable(R.mipmap.icon_arr_up);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            holder.groupRightText.setCompoundDrawables(null, null, drawable, null);
        } else {
            Drawable drawable = mContext.getResources().getDrawable(R.mipmap.icon_arr_down);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            holder.groupRightText.setCompoundDrawables(null, null, drawable, null);
        }
        String _key = groupMapList.get(groupPosition).getKey();
        if (!TextUtils.isEmpty(_key)) {
            holder.groupName.setText(_key);
        } else {
            holder.groupName.setText("");
        }
        String _value = groupMapList.get(groupPosition).getValue();
        if (!TextUtils.isEmpty(_value)) {
            holder.groupRightText.setText(_value);
        } else {
            holder.groupRightText.setText("");
        }
        return view;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        View view = convertView;
        ChildHolder holder = null;
        if (view == null) {
            holder = new ChildHolder();
            view = LayoutInflater.from(mContext).inflate(R.layout.item_child_expand, null);
            holder.childName = view.findViewById(R.id.item_child_left_text);
            holder.childRightText = view.findViewById(R.id.item_child_right_text);
            view.setTag(holder);
        } else {
            holder = (ChildHolder) view.getTag();
        }
        String _key = childMapList.get(groupPosition).get(childPosition).getKey();
        if (!TextUtils.isEmpty(_key)) {
            holder.childName.setText(_key);
        } else {
            holder.childName.setText("");
        }
        String _value = childMapList.get(groupPosition).get(childPosition).getValue();
        if (!TextUtils.isEmpty(_value)) {
            holder.childRightText.setText(_value);
        } else {
            holder.childRightText.setText("");
        }
        return view;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    class GroupHolder {
        public TextView groupName;
        public TextView groupRightText;
    }

    class ChildHolder {
        public TextView childName;
        public TextView childRightText;
    }
}