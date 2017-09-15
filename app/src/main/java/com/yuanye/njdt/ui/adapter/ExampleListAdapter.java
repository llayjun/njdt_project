package com.yuanye.njdt.ui.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.millet.androidlib.Base.IBaseAdapter;
import com.yuanye.njdt.R;
import com.yuanye.njdt.constants.AppUtils;
import com.yuanye.njdt.dao.PdfDownDao;
import com.yuanye.njdt.data.entity.ExampleEntity;
import com.yuanye.njdt.data.entity.PdfDownEntity;
import com.yuanye.njdt.ui.callback.PdfDownInfoListener;


/**
 * Created by llay on 2017/9/9.
 */

public class ExampleListAdapter extends IBaseAdapter {

    private PdfDownInfoListener mPdfDownInfoListener;

    public ExampleListAdapter(Context mContext) {
        super(mContext);
    }

    public void setPdfDownInfoListener(PdfDownInfoListener _pdfDownInfoListener) {
        mPdfDownInfoListener = _pdfDownInfoListener;
    }

    @Override
    protected int getItemLayoutId() {
        return R.layout.item_plan;
    }

    @Override
    protected IBaseViewHolder getViewHolder(View _view) {
        ViewHolder _viewHolder = new ViewHolder();
        _viewHolder.mItemPlanLabel = _view.findViewById(R.id.item_plan_label);
        _viewHolder.mItemPlanType = _view.findViewById(R.id.item_plan_value);
        _viewHolder.mItemPlanDownImage = _view.findViewById(R.id.item_plan_down_image);
        return _viewHolder;
    }

    @Override
    protected void updateView(IBaseViewHolder _holder, int _position) {
        ViewHolder _viewHolder = (ViewHolder) _holder;
        final ExampleEntity _exampleEntity = (ExampleEntity) getItem(_position);
        if (null == _exampleEntity) return;
        String _label = _exampleEntity.getLabel();
        String _type = _exampleEntity.getType();
        String _downUrl = _exampleEntity.getDownloadUrl();
        if (!TextUtils.isEmpty(_label)) {
            _viewHolder.mItemPlanLabel.setText(_label);
        } else {
            _viewHolder.mItemPlanLabel.setText("");
        }
        if (!TextUtils.isEmpty(_type)) {
            _viewHolder.mItemPlanType.setText(_type);
        } else {
            _viewHolder.mItemPlanType.setText("");
        }
        if (TextUtils.isEmpty(_downUrl)) return;
        String _fileName = AppUtils.getLastString(_downUrl);
        PdfDownEntity _pdfDownEntity = PdfDownDao.queryEntity(_fileName);
        if (null != _pdfDownEntity && AppUtils.existFile(_fileName)) {
            _viewHolder.mItemPlanDownImage.setImageResource(R.mipmap.icon_download_no);
            _viewHolder.mItemPlanDownImage.setEnabled(false);
        } else {
            if (null != _pdfDownEntity) {
                PdfDownDao.deletePdfEntity(_fileName);
            }
            if (AppUtils.existFile(_fileName)) {
                AppUtils.deleteFile(_fileName);
            }
            _viewHolder.mItemPlanDownImage.setImageResource(R.mipmap.icon_download_nor);
            _viewHolder.mItemPlanDownImage.setEnabled(true);
        }
        _viewHolder.mItemPlanDownImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                String _value = _exampleEntity.getDownloadUrl();
                if (TextUtils.isEmpty(_value)) return;
                if (null != mPdfDownInfoListener) {
                    mPdfDownInfoListener.pdfDownInfo(_value);
                }
            }
        });
    }

    public class ViewHolder implements IBaseViewHolder {
        private TextView mItemPlanLabel;
        private TextView mItemPlanType;
        private ImageView mItemPlanDownImage;
    }

}
