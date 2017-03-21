package com.chintansoni.android.masterproject.base;

import android.app.Dialog;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialog;

public abstract class BaseBottomSheetDialogFragment extends BaseDialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new BottomSheetDialog(getContext(), getTheme());
    }
}
