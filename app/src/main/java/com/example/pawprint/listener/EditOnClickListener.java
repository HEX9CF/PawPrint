package com.example.pawprint.listener;

import android.view.View;
import android.widget.Toast;
import com.example.pawprint.R;

public class EditOnClickListener implements View.OnClickListener {
    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.btn_submit) {
            Toast.makeText(view.getContext(), "提交成功", Toast.LENGTH_SHORT).show();
        }
        if(view.getId() == R.id.btn_cancel) {
            Toast.makeText(view.getContext(), "取消编辑", Toast.LENGTH_SHORT).show();
        }
    }
}
