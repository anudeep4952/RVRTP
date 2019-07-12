package cm.example.android.rvrtp;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class MyAdapter extends RecyclerView.ViewHolder{
    public TextView txtsal,txtname,txtnotice,txtdate;

    public MyAdapter(@NonNull View itemView) {
        super(itemView);
        txtsal=(TextView)itemView.findViewById(R.id.sal);
        txtname=(TextView)itemView.findViewById(R.id.name);
        txtnotice=(TextView)itemView.findViewById(R.id.notice);
        txtdate=(TextView)itemView.findViewById(R.id.date);
    }



}