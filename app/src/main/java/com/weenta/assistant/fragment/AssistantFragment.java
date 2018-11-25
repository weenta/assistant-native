package com.weenta.assistant.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.weenta.assistant.R;
import com.weenta.assistant.ui.ExpressActivity;

public class AssistantFragment extends Fragment {
    LinearLayout ll_express;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_assistant, null);
        initView(view);
        initEvent();
        return view;
    }

    private void initEvent() {
        ll_express.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.ll_express:
                        Intent intent = new Intent(getActivity(), ExpressActivity.class);
                        startActivity(intent);
                        break;
                }
            }
        });
    }

    private void initView(View v) {
     ll_express = v.findViewById(R.id.ll_express);

    }
}
