package jp.yagni.widget;

import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * List adapter of icon and text row list
 * 
 * @author YUKI Kaoru
 */
public class IconArrayAdapter<T, U> extends ArrayAdapter<IconArrayAdapter.IconTextItem<T, U>>
{
    private List<IconTextItem<T, U>> mItems;
    private LayoutInflater mInflater;
    
    private int mResource;
    private int mTextViewResourceId;
    private int mIconViewResourceId;
    
    public IconArrayAdapter(Context context, int resource, int textViewResourceId, int iconViewResourceId, List<IconTextItem<T, U>> objects)
    {
        super(context, resource, textViewResourceId, objects);
        
        mItems = objects;
        mInflater = LayoutInflater.from(context);
        mResource = resource;
        mTextViewResourceId = textViewResourceId;
        mIconViewResourceId = iconViewResourceId;
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        ViewHolder holder;
        View view = convertView;
        IconTextItem<T, U> item = mItems.get(position);
        if (view == null) {
            view = mInflater.inflate(mResource, null);
            holder = new ViewHolder();
            holder.text = (TextView)view.findViewById(mTextViewResourceId);
            holder.icon = (ImageView)view.findViewById(mIconViewResourceId);
            view.setTag(holder);
        } else {
            holder = (ViewHolder)view.getTag();
        }
        if (item.getText() instanceof String) {
            holder.text.setText((String)item.getText());
        } else {
            holder.text.setText((Integer)item.getText());
        }
        if (item.getIcon() instanceof Bitmap) {
            holder.icon.setImageBitmap((Bitmap)item.getIcon());
        } else if (item.getIcon() instanceof Drawable) {
            holder.icon.setImageDrawable((Drawable)item.getIcon());
        } else {
            holder.icon.setImageResource((Integer)item.getIcon());
        }
        
        return view;
    }
    
    private class ViewHolder
    {
        public TextView text;
        public ImageView icon;
    }
    
    public static class IconTextItem<T, U>
    {
        private T mText;
        private U mIcon;
        
        public IconTextItem()
        {
            mText = null;
            mIcon = null;
        }
        
        public IconTextItem(T text, U icon)
        {
            setText(text);
            setIcon(icon);
        }
        
        public T getText()
        {
            return mText;
        }
        
        public IconTextItem<T, U> setText(T text)
        {
            mText = text;
            return this;
        }
        
        public U getIcon()
        {
            return mIcon;
        }
        
        public IconTextItem<T, U> setIcon(U icon)
        {
            mIcon = icon;
            return this;
        }
    }
}
