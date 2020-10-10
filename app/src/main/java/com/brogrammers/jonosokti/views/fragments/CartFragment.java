package com.brogrammers.jonosokti.views.fragments;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.brogrammers.jonosokti.R;
import com.brogrammers.jonosokti.ScheduleActivity;
import com.brogrammers.jonosokti.adapters.CartAdapter;
import com.brogrammers.jonosokti.bean.model.Cart;
import com.brogrammers.jonosokti.listeners.OnItemSelectListener;
import com.brogrammers.jonosokti.viewmodels.CartFragmentViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CartFragment extends Fragment implements OnItemSelectListener<Cart> {
    private CartFragmentViewModel viewModel;
    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private TextView tvTotalAmount,tvFoundNothing;

    private CartAdapter adapter;
    private List<Cart> cartList;
    private double total = 0;
    public CartFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        cartList = new ArrayList<>();
        adapter = new CartAdapter(requireActivity(),cartList,this);
        viewModel = new ViewModelProvider(requireActivity()).get(CartFragmentViewModel.class);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cart, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tvTotalAmount = view.findViewById(R.id.textview_total_fee);
        tvFoundNothing = view.findViewById(R.id.textview_found_nothing);
        toolbar = view.findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.icon_back_arrow_black);
        toolbar.setTitle(R.string.cart);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requireActivity().onBackPressed();
            }
        });

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));
        recyclerView.setAdapter(adapter);

        //continue button
        view.findViewById(R.id.button_continue).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (cartList.size()>0){
                    Intent intent = new Intent(requireActivity(), ScheduleActivity.class);
                    startActivity(intent);
                }else Toast.makeText(requireActivity(), "No service is added.", Toast.LENGTH_SHORT).show();

            }
        });

    }

    private Observer<List<Cart>> cartObserver = new Observer<List<Cart>>() {
        @Override
        public void onChanged(List<Cart> carts) {
            cartList.clear();
            cartList.addAll(carts);
            adapter.notifyDataSetChanged();

            total = 0;
            for (Cart cart: cartList){
                total+=cart.getServiceFee()*cart.getQuantity();
            }

            tvTotalAmount.setText(String.format(Locale.ENGLISH,"%.1f",total)+" BDT");
            if (cartList.size()<=0) tvFoundNothing.setVisibility(View.VISIBLE);
            else tvFoundNothing.setVisibility(View.GONE);

        }
    };

    @Override
    public void onResume() {
        super.onResume();

        viewModel.getAllCarts().removeObserver(cartObserver);
        viewModel.getAllCarts().observe(requireActivity(),cartObserver);

    }

    @Override
    public void onItemSelected(Cart cart) {
        showDeleteConfirmation(cart);
    }

    private void showDeleteConfirmation(Cart cart) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity())
                .setTitle("Confirmation")
                .setMessage("Do you want to delete "+cart.getSubCatName()+" ?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        viewModel.deleteCartItem(cart);
                        dialog.dismiss();
                        Toast.makeText(requireActivity(), "Item is removed from cart.", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        AlertDialog alertDialog = builder.create();
        alertDialog.setCancelable(false);
        alertDialog.show();
    }
}
