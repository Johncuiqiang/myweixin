package com.yzh.weixin.samplewater;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.*;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;
import com.yzh.weixin.R;
import com.yzh.weixin.gridwater.StaggeredGridView;


import java.util.ArrayList;

public class StaggeredGridActivityFragment extends FragmentActivity {

    private static final String TAG = "StaggeredGridActivityFragment";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle("SGV");

        final FragmentManager fm = getSupportFragmentManager();

        // Create the list fragment and add it as our sole content.
        if (fm.findFragmentById(android.R.id.content) == null) {
            final StaggeredGridFragment fragment = new StaggeredGridFragment();
            fm.beginTransaction().add(android.R.id.content, fragment).commit();
        }
    }


    private class StaggeredGridFragment extends Fragment implements
            AbsListView.OnScrollListener, AbsListView.OnItemClickListener {


        private StaggeredGridView mGridView;
        private boolean mHasRequestedMore;
        private SampleAdapter mAdapter;

        private ArrayList<String> mData;

        @Override
        public void onCreate(final Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setRetainInstance(true);
        }

        @Override
        public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
            return inflater.inflate(R.layout.act_sgv_water, container, false);
        }

        @Override
        public void onActivityCreated(final Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);

            mGridView = (StaggeredGridView) getView().findViewById(R.id.grid_view);

            if (savedInstanceState == null) {
                final LayoutInflater layoutInflater = getActivity().getLayoutInflater();

                View header = layoutInflater.inflate(R.layout.item_header_footer_water, null);
                View footer = layoutInflater.inflate(R.layout.item_header_footer_water, null);
                TextView txtHeaderTitle = (TextView) header.findViewById(R.id.txt_title);
                TextView txtFooterTitle = (TextView) footer.findViewById(R.id.txt_title);
                txtHeaderTitle.setText("THE HEADER!");
                txtFooterTitle.setText("THE FOOTER!");

                mGridView.addHeaderView(header);
                mGridView.addFooterView(footer);
            }

            if (mAdapter == null) {
                mAdapter = new SampleAdapter(getActivity(), R.id.txt_line1);
            }

            if (mData == null) {
                mData = SampleData.generateSampleData();
            }

            for (String data : mData) {
                mAdapter.add(data);
            }

            mGridView.setAdapter(mAdapter);
            mGridView.setOnScrollListener(this);
            mGridView.setOnItemClickListener(this);
        }

        @Override
        public void onScrollStateChanged(final AbsListView view, final int scrollState) {

        }

        @Override
        public void onScroll(final AbsListView view, final int firstVisibleItem, final int visibleItemCount, final int totalItemCount) {

            // our handling
            if (!mHasRequestedMore) {
                int lastInScreen = firstVisibleItem + visibleItemCount;
                if (lastInScreen >= totalItemCount) {

                    mHasRequestedMore = true;
                    onLoadMoreItems();
                }
            }
        }

        private void onLoadMoreItems() {
            final ArrayList<String> sampleData = SampleData.generateSampleData();
            for (String data : sampleData) {
                mAdapter.add(data);
            }
            // stash all the data in our backing store
            mData.addAll(sampleData);
            // notify the adapter that we can update now
            mAdapter.notifyDataSetChanged();
            mHasRequestedMore = false;
        }

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
            Toast.makeText(getActivity(), "Item Clicked: " + position, Toast.LENGTH_SHORT).show();
        }
    }
}
