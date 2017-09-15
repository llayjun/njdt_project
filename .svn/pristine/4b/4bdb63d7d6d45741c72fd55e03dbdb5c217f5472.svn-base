package com.yuanye.njdt.ui.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.millet.androidlib.Base.IBaseAdapter;
import com.millet.androidlib.Utils.DateUtils;
import com.yuanye.njdt.R;
import com.yuanye.njdt.data.entity.EmergencyListEntity;


/**
 * Created by llay on 2017/9/9.
 */

public class HistoryListAdapter extends IBaseAdapter {

    public HistoryListAdapter(Context mContext) {
        super(mContext);
    }

    @Override
    protected int getItemLayoutId() {
        return R.layout.item_history;
    }

    @Override
    protected IBaseViewHolder getViewHolder(View _view) {
        ViewHolder _viewHolder = new ViewHolder();
        _viewHolder.mItemEmergencyTitle = _view.findViewById(R.id.item_emergency_title);
        _viewHolder.mItemEmergencyLine = _view.findViewById(R.id.item_emergency_line);
        _viewHolder.mItemEmergencyTime = _view.findViewById(R.id.item_emergency_time);
        _viewHolder.mItemEmergencyState = _view.findViewById(R.id.item_emergency_state);
        return _viewHolder;
    }

    @Override
    protected void updateView(IBaseViewHolder _holder, int _position) {
        ViewHolder _viewHolder = (ViewHolder) _holder;
        EmergencyListEntity _emergencyListEntity = (EmergencyListEntity) getItem(_position);
        if (null == _emergencyListEntity) return;
        String _title = _emergencyListEntity.getEmergencyTitle();
        String _lineNumber = _emergencyListEntity.getEmergencyCircuitNumber();
        String _lineStation = _emergencyListEntity.getEmergencyCircuitStation();
        long _createTime = _emergencyListEntity.getCreateTime();
        if (!TextUtils.isEmpty(_title)) {
            _viewHolder.mItemEmergencyTitle.setText(_title);
        } else {
            _viewHolder.mItemEmergencyTitle.setText("");
        }
        _viewHolder.mItemEmergencyLine.setText(_lineNumber + "/" + _lineStation);
        if (0 != _createTime) {
            String _data = DateUtils.formatYMDHMA(_createTime);
            if (!TextUtils.isEmpty(_data)) {
                _viewHolder.mItemEmergencyTime.setText(_data);
            } else {
                _viewHolder.mItemEmergencyTime.setText("");
            }
        } else {
            _viewHolder.mItemEmergencyTime.setText("");
        }
    }

    public class ViewHolder implements IBaseViewHolder {
        private TextView mItemEmergencyTitle;
        private TextView mItemEmergencyLine;
        private TextView mItemEmergencyTime;
        private TextView mItemEmergencyState;
    }

}
