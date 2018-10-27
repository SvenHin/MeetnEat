package com.svenhaakon.meetneat;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

public class DeleteDialog extends DialogFragment {
    private DialogClickListener callback;

    public interface DialogClickListener{
        public void onYesClick();
        public void onNoClick();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            callback = (DialogClickListener)getActivity();
        }
        catch (ClassCastException e){
            throw new ClassCastException("");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        return new AlertDialog.Builder(getActivity())
                .setTitle(R.string.deleteDialog).setPositiveButton(R.string.deleteDialogOk,
                        new DialogInterface.OnClickListener(){
                            public void onClick(DialogInterface dialog, int whichButton){
                                callback.onYesClick();
                            }
                        }
                )
                .setNegativeButton(R.string.deleteDialogNotOk,
                        new DialogInterface.OnClickListener(){
                            public void onClick(DialogInterface dialog, int whichButton){
                                callback.onNoClick();
                            }
                        }
                )
                .create();
    }
}
