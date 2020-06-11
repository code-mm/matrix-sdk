package org.ms.matrix.app.ui.fragment.my;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import org.ms.matrix.app.R;
import org.ms.module.base.view.BaseFragment;

public class MyFragment  extends BaseFragment {


    @Override
    protected int getLayout() {
        return R.layout.fragment_my;
    }

    public static MyFragment newInstance() {

        Bundle args = new Bundle();

        MyFragment fragment = new MyFragment();
        fragment.setArguments(args);
        return fragment;
    }


}
