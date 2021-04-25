package com.LastSolutionTeam.tastit.Adaptadores;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.LastSolutionTeam.tastit.FragmentCartaMesa;
import com.LastSolutionTeam.tastit.Fragment_plato_Mesa;
import com.LastSolutionTeam.tastit.R;
import com.google.android.material.badge.BadgeDrawable;

import java.util.ArrayList;
import java.util.HashMap;

public class MAinAdapterCarta extends BaseExpandableListAdapter {
    ArrayList<String>ListGroup;
    HashMap<String,ArrayList<String>> ListChild;
    Context context;
    FrameLayout fragmentContainer;
    Fragment fragmentplato;
    FragmentTransaction transaction;
    public MAinAdapterCarta(ArrayList<String> ListGroup, HashMap<String,ArrayList<String>> ListChild,Context context){
this.ListGroup=ListGroup;
this.ListChild=ListChild;
this.context=context;
    }
    @Override
    public int getGroupCount() {
        return ListGroup.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return ListChild.get(ListGroup.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return ListGroup.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {


        return ListChild.get(ListGroup.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        convertView= LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_expandable_list_item_1,parent,false);
        TextView textView=convertView.findViewById(android.R.id.text1);
        String sGroup=String.valueOf(getGroup(groupPosition));
        textView.setText(sGroup);
        textView.setTypeface(null, Typeface.BOLD);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        convertView=LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_selectable_list_item,parent,false);
        TextView textView=convertView.findViewById(android.R.id.text1);
        String schild=String.valueOf(getChild(groupPosition,childPosition));
        textView.setText(schild);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment_plato_Mesa fragmentPlatoMesa=Fragment_plato_Mesa.newInstance();
                fragmentContainer=(FrameLayout) v.findViewById(R.id.ContenederoFragmentPlato);
                FragmentManager fragmentManager= ((AppCompatActivity)context).getSupportFragmentManager();
                FragmentTransaction transaction=fragmentManager.beginTransaction();
                transaction.setCustomAnimations(R.anim.fragment_open_enter,R.anim.fragment_open_exit,R.anim.fragment_open_enter,R.anim.fragment_open_exit);
                transaction.add(R.id.contenedorfragment,fragmentPlatoMesa,"BLANK_FRAGMENT");

                transaction.commit();
            }
        });


        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
