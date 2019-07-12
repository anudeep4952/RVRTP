package cm.example.android.rvrtp;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class MyAdapter1 extends RecyclerView.ViewHolder{
    public TextView txtnotif;

    public MyAdapter1(@NonNull View itemView) {
        super(itemView);
        txtnotif=(TextView)itemView.findViewById(R.id.notice1);

    }



}