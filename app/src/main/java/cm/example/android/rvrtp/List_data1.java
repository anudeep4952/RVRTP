package cm.example.android.rvrtp;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class List_data1  {
    public String noti;

    public List_data1() {
    }

    public List_data1(String txtnotif) {
        this.noti = txtnotif;
    }

    public String getTxtnotif() {
        return noti;
    }

    public void setTxtnotif(String txtnotif) {
        this.noti = txtnotif;
    }
}
