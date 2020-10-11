package com.brogrammers.jonosokti.views.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.brogrammers.jonosokti.R;
import com.brogrammers.jonosokti.adapters.MyOrderAdapter;
import com.brogrammers.jonosokti.bean.Order;
import com.brogrammers.jonosokti.helpers.ApplicationHelper;
import com.brogrammers.jonosokti.listeners.OnDataDownloadListener;
import com.brogrammers.jonosokti.listeners.OnItemSelectListener;
import com.brogrammers.jonosokti.repositories.DefaultRepository;
import com.brogrammers.jonosokti.views.OrderStatusActivity;

import java.util.ArrayList;
import java.util.List;

public class OrderHistoryFragment extends Fragment implements OnItemSelectListener<Order> {

    private TextView tvFoundNothing;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;

    private DefaultRepository defaultRepository;

    private List<Order> orders;
    private MyOrderAdapter adapter;
    public OrderHistoryFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        orders = new ArrayList<>();
        adapter = new MyOrderAdapter(requireActivity(),orders,this);
        defaultRepository = DefaultRepository.getInstance(requireActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_order_history, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tvFoundNothing = view.findViewById(R.id.textview_found_nothing);
        recyclerView = view.findViewById(R.id.recyclerView);
        progressBar = view.findViewById(R.id.progress_bar);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void onResume() {
        super.onResume();

        if (ApplicationHelper.getDatabase().getAuth().getCurrentUser()!=null) getMyOrderHistory();
        else {
            tvFoundNothing.setText("Please login to see history.");
            tvFoundNothing.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void onPause() {
        super.onPause();

        defaultRepository.removeActiveListeners(requireActivity());

    }

    private void getMyOrderHistory(){
        defaultRepository.getMyOrderHistory(ApplicationHelper.getDatabase().getAuth().getCurrentUser().getUid(), new OnDataDownloadListener<Order>() {
            @Override
            public void onStarted() {
                orders.clear();
            }

            @Override
            public void onDownloaded(Order order) {
                orders.add(order);
            }

            @Override
            public void onDownloaded(List<Order> list) {

            }

            @Override
            public void onFinish() {
                adapter.notifyDataSetChanged();
                progressBar.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void onItemSelected(Order order) {
        Intent intent = new Intent(requireActivity(), OrderStatusActivity.class);
        intent.putExtra("order",order);
        startActivity(intent);
    }

}
