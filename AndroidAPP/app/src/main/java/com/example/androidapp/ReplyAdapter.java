package com.example.androidapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

class ReplyAdapter extends BaseAdapter {
    private LayoutInflater mInflater; // 得到一个LayoutInfalter对象用来导入布局
    ArrayList<HashMap<String, Object>> listItem;

    public ReplyAdapter(Context context,ArrayList<HashMap<String, Object>> listItem) {
        this.mInflater = LayoutInflater.from(context);
        this.listItem = listItem;
    }

    // 返回在适配器中所代表的数据集合的条目数
    @Override
    public int getCount() {
        return listItem.size();
    }

    // 返回数据集合中与指定索引position对应的数据项
    @Override
    public Object getItem(int position) {
        return listItem.get(position);
    }

    // 返回在列表中与指定索引对应的行id
    @Override
    public long getItemId(int position) {
        return position;
    }

    // 利用convertView+ViewHolder来重写getView()
    // 声明一个外部静态类，实现缓存，加载速度快
    static class ViewHolder {
        public TextView content;
        public TextView author;
    }


    // 重写getView() 重用View时不用通过findViewById()重新寻找View组件，同时也减少了重绘View的次数，是ListView使用的最优化方案
    //返回指定索引对应的数据项的视图
    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        ViewHolder holder ;
        if(convertView == null)
        {
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.reply_item, null);
            holder.content = (TextView)convertView.findViewById(R.id.replyContent);
            holder.author = (TextView)convertView.findViewById(R.id.replyAuthor);
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder)convertView.getTag();
        }
        holder.content.setText((String) listItem.get(position).get("replyContent"));
        holder.author.setText(Objects.requireNonNull(listItem.get(position).get("replyAuthor")).toString());
        return convertView;
    }
}
