package com.example.yuanyuanlai.checkrecyclerview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class FirstAdapter extends RecyclerView.Adapter<FirstAdapter.FirstViewHolder>{

    private Context context;
    private List<FirstItem> firstItems;
    private OnCheckListener onCheckListener;
    private List<Integer> checkList = new ArrayList<>();
    private boolean isAll, isSingle;

    public class FirstViewHolder extends RecyclerView.ViewHolder{

        private TextView titleTextView;
        private CheckBox doneCHeckBox;

        public FirstViewHolder(View itemView) {
            super(itemView);
            titleTextView = (TextView)itemView.findViewById(R.id.title);
            doneCHeckBox = (CheckBox)itemView.findViewById(R.id.check);

        }

    }

    public FirstAdapter(Context context, List<FirstItem> firstItems){

        this.context = context;
        this.firstItems = firstItems;
    }

    @Override
    public int getItemCount() {
        return firstItems.size();
    }

    @Override
    public void onBindViewHolder(@NonNull final FirstViewHolder holder, final int position) {

            ((FirstViewHolder)holder).doneCHeckBox.setTag(new Integer(position));


            if (!isSingle&&isAll){
                if (!checkList.contains(((FirstViewHolder)holder).doneCHeckBox.getTag())){
                    checkList.add(new Integer(position));
                }
                ((FirstViewHolder) holder).doneCHeckBox.setChecked(true);

            }else if(!isSingle&&!isAll){
                if (checkList.contains(((FirstViewHolder)holder).doneCHeckBox.getTag())){
                    checkList.remove(new Integer(position));
                }
                ((FirstViewHolder) holder).doneCHeckBox.setChecked(false);
            }else if(isSingle&&checkList.contains(((FirstViewHolder) holder).doneCHeckBox.getTag()))
                ((FirstViewHolder) holder).doneCHeckBox.setChecked(true);
            else
                ((FirstViewHolder) holder).doneCHeckBox.setChecked(false);

            if (!isSingle&&position==(firstItems.size()-1)){
                isSingle = true;
            }


        FirstItem firstItem = firstItems.get(position);
        holder.titleTextView.setText(firstItem.getTitle());

        holder.doneCHeckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                onCheckListener.onCheck(buttonView, isChecked);


                if (isChecked){
                    if (!checkList.contains(((FirstViewHolder)holder).doneCHeckBox.getTag())){
                         checkList.add(new Integer(position));
                    }
                }else {
                    if (checkList.contains(((FirstViewHolder)holder).doneCHeckBox.getTag())){
                        checkList.remove(new Integer(position));
                    }
                }

            }
        });

    }

    public void setCheckAll(boolean isAll, boolean isSingle){
        this.isAll = isAll;
        this.isSingle = isSingle;
    }

    interface OnCheckListener{
        void onCheck(CompoundButton buttonView, boolean isChecked);
    }


    public void setOnCheckAllListener(boolean isCheckAll){

          checkList.clear();
          if (isCheckAll){
              Log.d("FirstAdapter", "---->调用了");
              for (int i=0; i<checkList.size(); i++){
                  checkList.add(new Integer(i));
              }
          }
          notifyDataSetChanged();

    }

    public void setOnCheckListener(OnCheckListener onCheckListener){
        this.onCheckListener = onCheckListener;

    }



    @NonNull
    @Override
    public FirstViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.first_item, parent, false);
        return new FirstViewHolder(view);

    }
}
