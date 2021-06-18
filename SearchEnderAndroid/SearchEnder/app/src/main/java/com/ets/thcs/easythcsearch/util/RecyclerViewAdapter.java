package com.ets.thcs.easythcsearch.util;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ets.thcs.easythcsearch.DeleteAddressActivity;
import com.ets.thcs.easythcsearch.EditAddressActivity;
import com.ets.thcs.easythcsearch.R;
import com.ets.thcs.easythcsearch.model.StudentAddressVo;
import com.google.gson.Gson;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder> {
    private List<StudentAddressVo> studentAddressVoList;
    private Context context;
    public RecyclerViewAdapter(List<StudentAddressVo> studentAddressVoList,Context context) {
        this.studentAddressVoList=studentAddressVoList;
        this.context=context;
    }

    @NonNull
    //@android.support.annotation.NonNull
    @Override
    public RecyclerViewAdapter.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View rootView=LayoutInflater.from(context).inflate(R.layout.activity_single_address_row,parent,false);
        RecyclerViewHolder recyclerViewHolder=new RecyclerViewHolder(rootView);
        return recyclerViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.RecyclerViewHolder holder, int position) {
        StudentAddressVo studentAddressVo=studentAddressVoList.get(position);
        String fullAddress =
                studentAddressVo.getAddress()+"\n"+
                studentAddressVo.getArea()+"\n"+
                studentAddressVo.getDistrict()+"\n"+
                studentAddressVo.getCity()+" - "+
                studentAddressVo.getPinCode()+"\n"+
                studentAddressVo.getLandMark()+"\n"+
                studentAddressVo.getState()+","+
                studentAddressVo.getCountry();

        holder.tvAddressTitle.setText(studentAddressVo.getAddressType());
        holder.tvAddress.setText(fullAddress);
        holder.btnEditAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("Inside RecycleViewAdapter btnEditAddress=> "+studentAddressVo.getId());
                String studentAddressJson=new Gson().toJson(studentAddressVo);
                Intent intent=new Intent(context, EditAddressActivity.class);
                System.out.println("inside btnEditAddress click method");
                intent.putExtra("TAG","STUDENT");
                intent.putExtra("studentAddressJson",studentAddressJson);
                context.startActivity(intent);
                //context..overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
            }
        });
        holder.btnDeleteAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("Inside RecycleViewAdapter btnDeleteAddress=> "+studentAddressVo.getId());
                String studentAddressJson=new Gson().toJson(studentAddressVo);
                Intent intent=new Intent(context, DeleteAddressActivity.class);
                System.out.println("inside btnDeleteAddress click method");
                intent.putExtra("TAG","STUDENT");
                intent.putExtra("studentAddressJson",studentAddressJson);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return this.studentAddressVoList.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        private TextView tvAddressTitle;
        private TextView tvAddress;
        private Button btnEditAddress;
        private Button btnDeleteAddress;
        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            tvAddressTitle=itemView.findViewById(R.id.tv_address_title);
            tvAddress=itemView.findViewById(R.id.tv_address);
            btnEditAddress=itemView.findViewById(R.id.btn_edit_address);
            btnDeleteAddress=itemView.findViewById(R.id.btn_delete_address);

        }
    }
}
