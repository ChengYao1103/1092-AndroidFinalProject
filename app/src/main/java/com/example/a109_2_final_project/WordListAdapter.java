package com.example.a109_2_final_project;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;

public class WordListAdapter extends RecyclerView.Adapter<WordListAdapter.WordViewHolder>{

    //參考04.5
    private final LinkedList<String> mTypeList;
    private final LinkedList<String> mValueList;
    private final LinkedList<String> mDescribeList;
    private final LinkedList<String> mCreateAtList;
    private final LayoutInflater mInflater;
    private Context mContext;

    class WordViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public final TextView wordTypeView;
        public final TextView wordValueView;
        public final TextView wordDesView;
        public final TextView wordDateView;
        final WordListAdapter mAdapter;


        public WordViewHolder(View itemView, WordListAdapter adapter) {
            super(itemView);
            wordTypeView = itemView.findViewById(R.id.word_type);
            wordValueView = itemView.findViewById(R.id.word_value);
            wordDesView = itemView.findViewById(R.id.word_describe);
            wordDateView = itemView.findViewById(R.id.word_createAt);
            this.mAdapter = adapter;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            /*// Get the position of the item that was clicked.
            int mPosition = getLayoutPosition();
            // Use that to access the affected item in mWordList.
            String element_title = mTitleList.get(mPosition);
            String element_content = mWordList.get(mPosition);
            // Change the word in the mWordList.
            //mWordList.set(mPosition, "Clicked! " + element);
            // Notify the adapter, that the data has changed so it can
            // update the RecyclerView to display the data.
            mAdapter.notifyDataSetChanged();
            Intent intent = new Intent(view.getContext(), MainActivity2.class);
            intent.putExtra("Title", element_title);
            intent.putExtra("Content", element_content);
            view.getContext().startActivity(intent);*/
        }
    }

    public WordListAdapter(Context context, LinkedList<String> wordList_type, LinkedList<String> wordList_value, LinkedList<String> wordList_describe, LinkedList<String> wordList_createAt) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        this.mTypeList = wordList_type;
        this.mValueList = wordList_value;
        this.mDescribeList = wordList_describe;
        this.mCreateAtList = wordList_createAt;
    }

    @NonNull
    @Override
    public WordListAdapter.WordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.wordlist_item, parent, false);
        return new WordViewHolder(mItemView, this);
    }



    @Override
    public void onBindViewHolder(@NonNull WordListAdapter.WordViewHolder holder, int position) {
        String mCurrent_type = mTypeList.get(position);
        String mCurrent_value = mValueList.get(position);
        int value = Integer.parseInt(mCurrent_value);
        String mCurrent_describe = mDescribeList.get(position);
        String mCurrent_createAt = mCreateAtList.get(position);
        holder.wordTypeView.setText(mCurrent_type);
        holder.wordTypeView.setTextColor(mContext.getResources().getColor(R.color.white));
        holder.wordValueView.setText(mCurrent_value);
        holder.wordDesView.setText(mCurrent_describe);
        holder.wordDateView.setText(mCurrent_createAt);

        if(value > 0){
            holder.wordValueView.setTextColor(mContext.getResources().getColor(R.color.green));
        }
        else {
            holder.wordValueView.setTextColor(mContext.getResources().getColor(R.color.red));
        }

        Log.d("TEST", mCurrent_type);
        switch (mCurrent_type){
            case "工作":
                holder.wordTypeView.setBackgroundColor(mContext.getResources().getColor(R.color.purple_200));
                break;
            case "飲食":
                holder.wordTypeView.setBackgroundColor(mContext.getResources().getColor(R.color.purple_500));
                break;
            case "帳單":
                holder.wordTypeView.setBackgroundColor(mContext.getResources().getColor(R.color.teal_200));
                break;
            case "交通":
                holder.wordTypeView.setBackgroundColor(mContext.getResources().getColor(R.color.teal_700));
                break;
            case "生活":
                holder.wordTypeView.setBackgroundColor(mContext.getResources().getColor(R.color.primary));
                break;
            case "娛樂":
                holder.wordTypeView.setBackgroundColor(mContext.getResources().getColor(R.color.pink));
                break;
            case "投資":
                holder.wordTypeView.setBackgroundColor(mContext.getResources().getColor(R.color.yellow));
                break;
            case "其他":
                holder.wordTypeView.setBackgroundColor(mContext.getResources().getColor(R.color.gray));
                break;
            default:
                break;
        }
    }

    @Override
    public int getItemCount() {
        return mTypeList.size();
    }
}

