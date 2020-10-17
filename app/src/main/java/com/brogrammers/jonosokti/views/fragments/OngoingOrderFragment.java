package com.brogrammers.jonosokti.views.fragments;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.brogrammers.jonosokti.Constants;
import com.brogrammers.jonosokti.R;
import com.brogrammers.jonosokti.adapters.MyOrderAdapter;
import com.brogrammers.jonosokti.bean.Order;
import com.brogrammers.jonosokti.helpers.ApplicationHelper;
import com.brogrammers.jonosokti.listeners.OnDataDownloadListener;
import com.brogrammers.jonosokti.listeners.OnItemSelectListener;
import com.brogrammers.jonosokti.listeners.OnUploadListener;
import com.brogrammers.jonosokti.repositories.DefaultRepository;
import com.brogrammers.jonosokti.views.OrderStatusActivity;

import java.util.ArrayList;
import java.util.List;

public class OngoingOrderFragment extends Fragment implements OnItemSelectListener<Order> {
    private TextView tvFoundNothing;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private DefaultRepository defaultRepository;

    private List<Order> orders;
    private MyOrderAdapter adapter;

    private Dialog loadingDialog;
    public OngoingOrderFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        orders = new ArrayList<>();
        adapter = new MyOrderAdapter(requireActivity(),orders,this);
        defaultRepository = DefaultRepository.getInstance(requireActivity());

        loadingDialog = ApplicationHelper.getUtilsHelper().getLoadingDialog(requireActivity());
        loadingDialog.setCancelable(false);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ongoing_order, container, false);
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

        if (ApplicationHelper.getDatabase().getAuth().getCurrentUser()!=null) getMyOngoingOrders();
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

    private void getMyOngoingOrders(){
        defaultRepository.getMyOnGoingOrders(ApplicationHelper.getDatabase().getAuth().getCurrentUser().getUid(), new OnDataDownloadListener<Order>() {
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

    @Override
    public void onDelete(Order order) {
        loadingDialog.show();
        showConfirmationDialog(Constants.DELETE,"Do you want to delete this order?",order);
    }

    @Override
    public void onCancel(Order order) {
        loadingDialog.show();
        showConfirmationDialog(Constants.CANCEL,"Do you want to cancel this order?",order);
    }

    private void showConfirmationDialog(String tag, String message, final Order order){
        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity())
                .setTitle("Confirmation")
                .setMessage(message)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (tag){
                            case Constants.DELETE:{
                                deleteOrder(order);
                                break;
                            }
                            case Constants.CANCEL:{
                                cancelOrder(order);
                                break;
                            }

                            default:
                        }
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void deleteOrder(Order order) {
        defaultRepository.deleteOrder(order, new OnUploadListener() {
            @Override
            public void onUploaded() {
                loadingDialog.dismiss();
                Toast.makeText(requireActivity(), "Order deleted.", Toast.LENGTH_SHORT).show();
                try{
                    orders.remove(order);
                    adapter.notifyDataSetChanged();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailed() {
                loadingDialog.dismiss();
                Toast.makeText(requireActivity(), "Please try again later.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void cancelOrder(Order order) {
        defaultRepository.updateCancelByUser(order, new OnUploadListener() {
            @Override
            public void onUploaded() {
                loadingDialog.dismiss();
                Toast.makeText(requireActivity(), "Order cancelled.", Toast.LENGTH_SHORT).show();
                try{
                    orders.remove(order);
                    adapter.notifyDataSetChanged();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailed() {
                loadingDialog.dismiss();
                Toast.makeText(requireActivity(), "Please try again later.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onApprove(Order order) {

    }
}
