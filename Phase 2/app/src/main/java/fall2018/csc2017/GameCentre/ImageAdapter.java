package fall2018.csc2017.GameCentre;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

/**
 * Adapter for tile buttons in background choosing page
 */
public class ImageAdapter extends BaseAdapter {

    /**
     * The context of activity
     */
    private Context mContext;

    /**
     * Constructor for ImageAdapter
     * @param c
     */
    public ImageAdapter(Context c) {
        mContext = c;
    }

    /**
     * Method that returns the number of images in mThumbIds
     * @return number of images
     */
    public int getCount() {
        return mThumbIds.length;
    }

    /**
     * Getter for item
     * @param position
     * @return null
     */
    public Object getItem(int position) {
        return null;
    }

    /**
     * Getter for item id
     * @param position
     * @return 0
     */
    public long getItemId(int position) {
        return 0;
    }

    /**
     * Create a new ImageView for each item referenced by the Adapter
     * @param position
     * @param convertView
     * @param parent
     * @return imageView
     */
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            // If it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(400, 500));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(24, 24, 24, 24);
        } else {
            imageView = (ImageView) convertView;
        }

        imageView.setImageResource(mThumbIds[position]);
        return imageView;
    }

    /**
     * References to our images
     */
    private Integer[] mThumbIds = {
            R.drawable.toronto,
            R.drawable.cat, R.drawable.dog,
            R.drawable.meme,
    };
}