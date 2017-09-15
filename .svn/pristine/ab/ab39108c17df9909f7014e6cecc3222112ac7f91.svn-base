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
import com.yuanye.njdt.data.entity.MaterialEntity;

import java.util.List;

public class MyMaterialExpandableAdapter extends BaseExpandableListAdapter {

    private List<String> groupMapList;
    private List<List<MaterialEntity>> childMapList;
    private Context mContext;

    public MyMaterialExpandableAdapter(Context context, List<String> groupMapList, List<List<MaterialEntity>> childMapList) {
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
            view = LayoutInflater.from(mContext).inflate(R.layout.item_material_group_expand, null);
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
        String _title = groupMapList.get(groupPosition);
        if (!TextUtils.isEmpty(_title)) {
            holder.groupName.setText(_title);
        } else {
            holder.groupName.setText("");
        }
        return view;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        View view = convertView;
        ChildHolder holder = null;
        if (view == null) {
            holder = new ChildHolder();
            view = LayoutInflater.from(mContext).inflate(R.layout.item_material_child_expand, null);
            holder.mScope = view.findViewById(R.id.item_material_scope);
            holder.mName = view.findViewById(R.id.item_material_name);
            holder.mPhone = view.findViewById(R.id.item_material_phone);
            holder.mLocaiton = view.findViewById(R.id.item_material_location);
            holder.mMaterial = view.findViewById(R.id.item_material_material);
            view.setTag(holder);
        } else {
            holder = (ChildHolder) view.getTag();
        }
        String _scope = childMapList.get(groupPosition).get(childPosition).getScope();
        if (!TextUtils.isEmpty(_scope)) {
            holder.mScope.setText(_scope);
        } else {
            holder.mScope.setText("");
        }
        String _name = childMapList.get(groupPosition).get(childPosition).getTeamName();
        if (!TextUtils.isEmpty(_name)) {
            holder.mName.setText(_name);
        } else {
            holder.mName.setText("");
        }
        String _phone = childMapList.get(groupPosition).get(childPosition).getTeamPhone();
        if (!TextUtils.isEmpty(_phone)) {
            holder.mPhone.setText(_phone);
        } else {
            holder.mPhone.setText("");
        }
        String _location = childMapList.get(groupPosition).get(childPosition).getStorageLocation();
        if (!TextUtils.isEmpty(_location)) {
            holder.mLocaiton.setText(_location);
        } else {
            holder.mLocaiton.setText("");
        }
        String _material = childMapList.get(groupPosition).get(childPosition).getMaterialName();
        if (!TextUtils.isEmpty(_material)) {
            holder.mMaterial.setText(_material);
        } else {
            holder.mLocaiton.setText("");
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
        public TextView mScope;
        public TextView mName;
        public TextView mPhone;
        public TextView mLocaiton;
        public TextView mMaterial;
    }
}