package com.pushplant.android.timesheet;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Administrator on 2016-10-28.
 */


    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

                private List<itemList2> mValues;





    public MyAdapter(Context context, List<itemList2> items) {
//        context.getTheme().resolveAttribute(R.attr.selectableItemBackground, mTypedValue, true);
//        mBackground = mTypedValue.resourceId;
        mValues = items;
    }




        // Create new views (invoked by the layout manager)
        @Override
        public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                       int viewType) {
            // create a new view
            View itemLayoutView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.list_item2,parent,false);

            // create ViewHolder

            ViewHolder viewHolder = new ViewHolder(itemLayoutView);
            return viewHolder;
        }

        // Replace the contents of a view (invoked by the layout manager)
        @Override
        public void onBindViewHolder(ViewHolder viewHolder, int position) {

            // - get data from your itemsData at this position
            // - replace the contents of the view with that itemsData

//            viewHolder.txtViewTitle1.setText(itemsData[position].mTitle());
  ///          viewHolder.txtViewTitle2.setText(itemsData[position].mContent());
     ///       viewHolder.imgViewIcon.setImageResource(itemsData[position].getmImg());



            //  holder.mBoundString = mValues.get(position);

            itemList2 l_Cheeses2 = new itemList2();

            l_Cheeses2 = mValues.get(position);

            viewHolder.mBoundString = l_Cheeses2.getmTitle();
            viewHolder.mTextView.setText(l_Cheeses2.getmTitle());
            viewHolder.mTextView2.setText(l_Cheeses2.getmContent());
            viewHolder.mTextView3.setText(l_Cheeses2.getmContent2());

            viewHolder.mId = l_Cheeses2.getmId();

            //l_Cheeses2.getmTitle().

//            holder.mTextView.setText(mValues.get(position));

            viewHolder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

            //Glide.with(viewHolder.mImageView.getContext())
              //      .load(l_Cheeses2.getmImg())
    //                //    .load("http://www.selphone.co.kr/homepage/img/team/3.jpg")
//                    .fitCenter()
  //                  .into(viewHolder.mImageView);



        }

        // inner class to hold a reference to each item of RecyclerView
        public static class ViewHolder extends RecyclerView.ViewHolder {

            public String mBoundString;
            public String mId;

            public final View mView;
            //public final ImageView mImageView;
            public final TextView mTextView;
            public final TextView mTextView2;
            public final TextView mTextView3;

            public ViewHolder(View view) {
                super(view);
                mView = view;
                //mImageView = (ImageView) view.findViewById(R.id.avatar);
                mTextView = (TextView) view.findViewById(android.R.id.text1);
                mTextView2 = (TextView) view.findViewById(android.R.id.text2);
                mTextView3 = (TextView) view.findViewById(R.id.text4);

            }

            @Override
            public String toString() {
                return super.toString() + " '" + mTextView.getText();
            }
        }


        // Return the size of your itemsData (invoked by the layout manager)
        @Override
        public int getItemCount() {
            return mValues.size();
        }
    }
