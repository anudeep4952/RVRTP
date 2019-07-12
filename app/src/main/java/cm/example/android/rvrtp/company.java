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

public class company extends Fragment {
    List<List_data> list;
    RecyclerView recycle;
    Fragment fragment;
    private FirebaseRecyclerOptions<List_data> options;
    private FirebaseRecyclerAdapter<List_data,MyAdapter> adapter;
    private DatabaseReference databaseReference;
    ProgressDialog dialog;
    int count=0;
    @Override
    public void onStart() {

        super.onStart();
        adapter.startListening();
        if(count==0) {
            dialog.setMessage("LoAdInG CoMpAnIeS");
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
        list = new ArrayList<List_data>();
        databaseReference= FirebaseDatabase.getInstance().getReference().child("companies");
        databaseReference.keepSynced(true);
        options=new FirebaseRecyclerOptions.Builder<List_data>().setQuery(databaseReference,List_data.class).build();

        adapter=new FirebaseRecyclerAdapter<List_data, MyAdapter>(options) {
            @Override
            protected void onBindViewHolder(@NonNull final MyAdapter holder, int position, @NonNull final List_data model) {
                holder.txtname.setText(model.getName());
                holder.txtsal.setText(model.getSal());
                holder.txtdate.setText(model.getDate());
                holder.txtnotice.setTextColor(Color.BLUE);
                holder.txtnotice.setText("no noice");
                if(model.getNotice().contains("https")) {
                    holder.txtnotice.setText("click here to see");
                    holder.txtnotice.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(model.getNotice())));
                        }
                    });
                }
            }

            @NonNull
            @Override
            public MyAdapter onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                if (dialog.isShowing()) {
                    dialog.dismiss();
                }
                return new MyAdapter(LayoutInflater.from(getContext()).inflate(R.layout.list_data,viewGroup,false));
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
        getActivity().setTitle("Companies");
        dialog = new ProgressDialog(getActivity());
        dialog.setMessage("LoAdInG CoMpAnIeS");
        dialog.show();




    }
}
