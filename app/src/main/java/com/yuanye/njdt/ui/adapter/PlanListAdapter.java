package com.yuanye.njdt.ui.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.millet.androidlib.Base.IBaseAdapter;
import com.yuanye.njdt.R;
import com.yuanye.njdt.constants.ApiConfig;
import com.yuanye.njdt.constants.AppUtils;
import com.yuanye.njdt.dao.PdfDownDao;
import com.yuanye.njdt.data.entity.PdfDownEntity;
import com.yuanye.njdt.data.entity.PlanEntity;
import com.yuanye.njdt.ui.callback.PdfDownInfoListener;


/**
 * Created by llay on 2017/9/9.
 */

public class PlanListAdapter extends IBaseAdapter {
    private PdfDownInfoListener mPdfDownInfoListener;

    public PlanListAdapter(Context mContext) {
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
        _viewHolder.mItemPlanValue = _view.findViewById(R.id.item_plan_value);
        _viewHolder.mItemPlanDownImage = _view.findViewById(R.id.item_plan_down_image);
        return _viewHolder;
    }

    @Override
    protected void updateView(IBaseViewHolder _holder, int _position) {
        ViewHolder _viewHolder = (ViewHolder) _holder;
        final PlanEntity _planEntity = (PlanEntity) getItem(_position);
        if (null == _planEntity) return;
        String _label = _planEntity.getLabel();
        String _value = _planEntity.getValue();
        if (!TextUtils.isEmpty(_label)) {
            _viewHolder.mItemPlanLabel.setText(_label);
        } else {
            _viewHolder.mItemPlanLabel.setText("");
        }
        if (!TextUtils.isEmpty(_value)) {
            _viewHolder.mItemPlanValue.setText(_value);
        } else {
            _viewHolder.mItemPlanValue.setText("");
        }
        String _fileName = _value + ApiConfig.PDF_URL_SUFF;
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
                String _value = _planEntity.getValue();
                if (TextUtils.isEmpty(_value)) return;
                if (null != mPdfDownInfoListener) {
                    mPdfDownInfoListener.pdfDownInfo(_value);
                }
            }
        });
    }

    public class ViewHolder implements IBaseViewHolder {
        private TextView mItemPlanLabel;
        private TextView mItemPlanValue;
        private ImageView mItemPlanDownImage;
    }

}
