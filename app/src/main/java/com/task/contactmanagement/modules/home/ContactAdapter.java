

package com.task.contactmanagement.modules.home;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.task.contactmanagement.R;
import com.task.contactmanagement.helper.ImageHandler;
import com.task.contactmanagement.mvp.model.Contact;
import com.task.contactmanagement.utilities.RoundImageTransform;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
/**
 * Created by Rajeesh adambil on 29/01/2017.
 */

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.Holder> {

    private LayoutInflater mLayoutInflater;
    private List<Contact> mContactList = new ArrayList<>();

    public ContactAdapter(LayoutInflater inflater) {
        mLayoutInflater = inflater;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.list_item_layout, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        holder.bind(mContactList.get(position));
    }

    @Override
    public int getItemCount() {
        return mContactList.size();
    }

    public void addContacts(List<Contact> contacts) {
        mContactList.addAll(contacts);
        notifyDataSetChanged();
    }

    public void clearContact() {
        mContactList.clear();
        notifyDataSetChanged();
    }

    public class Holder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @Bind(R.id.contact_icon) protected ImageView mContactImage;
        @Bind(R.id.textview_name) protected TextView mContactName;

        private Context mContext;
        private Contact mContact;

        public Holder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            mContext = itemView.getContext();
            ButterKnife.bind(this, itemView);
        }

        public void bind(Contact contact) {
            mContact = contact;
            mContactName.setText(mContact.getFirst_name()+" "+mContact.getLast_name());


            Glide.with(mContext)
                    .load(mContact.getProfile_pic())
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .transform(new RoundImageTransform(mContext))
                    .into(new ImageHandler(mContactImage));

        }

        @Override
        public void onClick(View v) {
            if (mContactClickListener != null) {
                //mContactClickListener.onClick(mContactImage, mContact, getAdapterPosition());
                mContactClickListener.onClick(mContactImage, "", getAdapterPosition());
            }
        }
    }

    public void setOnContactClickListener(OnContactClickListener listener) {
        mContactClickListener = listener;
    }

    private OnContactClickListener mContactClickListener;

    public interface OnContactClickListener {

        void onClick(View v, String contact_Id, int position);
    }
}
