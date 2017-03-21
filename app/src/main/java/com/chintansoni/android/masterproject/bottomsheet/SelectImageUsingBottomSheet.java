package com.chintansoni.android.masterproject.bottomsheet;

import android.content.Context;

import com.chintansoni.android.masterproject.R;
import com.chintansoni.android.masterproject.base.BaseBottomSheetDialogFragment;

import butterknife.OnClick;

public class SelectImageUsingBottomSheet extends BaseBottomSheetDialogFragment {

    private OnSelectUsingListener mOnSelectUsingListener;

    public void setmOnSelectUsingListener(OnSelectUsingListener mOnSelectUsingListener) {
        this.mOnSelectUsingListener = mOnSelectUsingListener;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnSelectUsingListener)
            mOnSelectUsingListener = (OnSelectUsingListener) context;
    }

    @OnClick(R.id.btn_bottom_sheet_select_image_using_camera)
    void onCameraClick() {
        if (mOnSelectUsingListener != null)
            mOnSelectUsingListener.onCameraClick();
        dismiss();
    }

    @OnClick(R.id.btn_bottom_sheet_select_image_using_gallery)
    void onGalleryClick() {
        if (mOnSelectUsingListener != null)
            mOnSelectUsingListener.onGalleryClick();
        dismiss();
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.bottom_sheet_select_image_using;
    }

    public interface OnSelectUsingListener {
        void onCameraClick();

        void onGalleryClick();
    }
}
