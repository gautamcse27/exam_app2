package com.example.exam_app;

import android.app.Dialog;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class AboutDialog extends DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
        builder.setTitle("About Exam App");
        builder.setMessage("Exam App v1.0\n\nA modern solution for students to manage their exams and results.\n\n© 2023 Exam App Team");
        builder.setPositiveButton("OK", (dialog, which) -> dialog.dismiss());
        return builder.create();
    }
}