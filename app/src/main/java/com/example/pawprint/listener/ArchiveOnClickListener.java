package com.example.pawprint.listener;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import com.example.pawprint.R;
import com.example.pawprint.activity.ArchiveActivity;
import com.example.pawprint.activity.EditActivity;

public class ArchiveOnClickListener implements View.OnClickListener {
    private ArchiveActivity activity;
    public ArchiveOnClickListener(ArchiveActivity activity) {
        this.activity = activity;
    }

    @Override
    public void onClick(View view) {
        Context context = view.getContext();
        if(view.getId() == R.id.archive_btn_edit) {
            Intent intent = new Intent(context, EditActivity.class);
            intent.putExtra("id", activity.getAnimal().getId());
            intent.putExtra("edit_type", true);
            view.getContext().startActivity(intent);
        }
        if(view.getId() == R.id.archive_btn_return) {
            activity.finish();
        }
    }
}
