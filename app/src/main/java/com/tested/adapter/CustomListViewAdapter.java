package com.tested.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.tested.app.R;
import com.tested.model.TestModel;

import java.util.List;

public class CustomListViewAdapter extends BaseAdapter {

    private List<TestModel> testList;
    private LayoutInflater layoutInflater;

    public CustomListViewAdapter(Context context, List<TestModel> testList) {
        this.testList = testList;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return testList.size();
    }

    @Override
    public Object getItem(int position) {
        return testList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if(view == null)
            view = layoutInflater.inflate(R.layout.item_row, parent, false);

        TestModel testModel = getTestModel(position);

        TextView textView = (TextView) view.findViewById(R.id.testname);
        TextView idRow = (TextView) view.findViewById(R.id.idRow);
        TextView textView2 = (TextView) view.findViewById(R.id.numb);

        idRow.setText(testModel.getId());
        textView.setText(testModel.getName());
        textView2.setText(Integer.toString(testModel.getCount()));

        return view;
    }
    private TestModel getTestModel(int position){
        return (TestModel) getItem(position);
    }

}
