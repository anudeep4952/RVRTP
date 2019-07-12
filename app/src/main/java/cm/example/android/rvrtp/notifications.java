package cm.example.android.rvrtp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class notifications extends Fragment {
    List<List_data1> list;
    RecyclerView recycle;
    Fragment fragment;
    private FirebaseRecyclerOptions<List_data1> options;
    private FirebaseRecyclerAdapter<List_data1,MyAdapter1> adapter;
    private DatabaseReference databaseReference;
    ProgressDialog dialog;
    int count=0;


    @Override
    public void onStart() {

        super.onStart();
        adapter.startListening();
        if(count==0) {
            dialog.setMessage("LoAdInG NoTiFiCaTiOnS");
            dialog.show();
        }
        count=1;




    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();


    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments


        View m=inflater.inflate(R.layout.activity_company, container, false);
        recycle = (RecyclerView) m.findViewById(R.id.recycle);
        recycle.setHasFixedSize(true);
        ProgressBar progressBar = new ProgressBar(getActivity(), null, android.R.attr.progressBarStyleSmall);
        recycle.setLayoutManager(new LinearLayoutManager(getActivity()));
        recycle.setItemAnimator( new DefaultItemAnimator());
        list = new ArrayList<List_data1>();
        databaseReference= FirebaseDatabase.getInstance().getReference().child("notifications");
        databaseReference.keepSynced(true);
        options=new FirebaseRecyclerOptions.Builder<List_data1>().setQuery(databaseReference,List_data1.class).build();

        adapter=new FirebaseRecyclerAdapter<List_data1, MyAdapter1>(options) {
            @Override
            protected void onBindViewHolder(@NonNull final MyAdapter1 holder, int position, @NonNull final List_data1 model) {
                holder.txtnotif.setText(model.getTxtnotif());
                System.out.println(model.getTxtnotif());
                           }

            @NonNull
            @Override
            public MyAdapter1 onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                if (dialog.isShowing()) {
                    dialog.dismiss();
                }
                return new MyAdapter1(LayoutInflater.from(getContext()).inflate(R.layout.list_data1,viewGroup,false));
            }
        };

        if (dialog.isShowing()) {
            dialog.dismiss();
        }

        recycle.setAdapter(adapter);



        return m;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle("Notifications");
        dialog = new ProgressDialog(getActivity());
        dialog.setMessage("LoAdInG NoTiFiCaTiOnS");
        dialog.show();




    }
}
