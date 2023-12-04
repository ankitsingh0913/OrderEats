package com.xclone.ordereats;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;
import com.xclone.ordereats.Adapter.CartListAdapter;
import com.xclone.ordereats.Adapter.CategoryAdapter;
import com.xclone.ordereats.Helper.ManagementCart;
import com.xclone.ordereats.Interface.ChangeNumberItemListener;

import org.json.JSONException;
import org.json.JSONObject;

public class CartListActivity extends AppCompatActivity implements PaymentResultListener {
    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerViewList;
    private ManagementCart managementCart;
    TextView totalFeeTxt, taxTxt, addMore, deliveryTxt, totalTxt, emptyTxt;
    private double tax;
     private ScrollView scrollView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_list);

        managementCart = new ManagementCart(this);

        initView();
        initList();
        CalculateCart();
        bottomNavigation();

        TextView PaymentBtn = findViewById(R.id.checkOutBtn);
        PaymentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startPayment();
            }
        });
        NavigationMethod();
    }

    private void NavigationMethod() {
        LinearLayout Home = findViewById(R.id.homeBtn);
        Home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CartListActivity.this,MainActivity.class));
            }
        });
        LinearLayout Profile = findViewById(R.id.profileBtn);
        Profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(CartListActivity.this,com.xclone.ordereats.Profile.class);
                startActivity(a);
            }
        });
        LinearLayout Setting = findViewById(R.id.settingBtn);
        Setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent b = new Intent(CartListActivity.this,Settings.class);
                startActivity(b);
            }
        });
        LinearLayout Support = findViewById(R.id.supportBtn);
        Support.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CartListActivity.this, com.xclone.ordereats.Support.class));
            }
        });
    }

    private double total;
    private void startPayment() {

        SharedPreferences preferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        String MobileNumber = preferences.getString("MobileNo", "DefaultNumber");
        String EmailId = preferences.getString("EmailId","DefaultId");


        Checkout checkout = new Checkout();
        checkout.setImage(R.mipmap.ic_launcher);
        final Activity activity = this;
        try {
            JSONObject option = new JSONObject();
            option.put("name",R.string.app_name);
            option.put("description","Payment for anything");
            option.put("send_sms_hash",true);
            option.put("allow_rotation",false);

            option.put("currency","INR");
            option.put("amount",total*100);

            JSONObject preFill = new JSONObject();
            preFill.put("email",EmailId);
            preFill.put("contact",MobileNumber);

            option.put("preFill",preFill);
            checkout.open(activity,option);
        } catch (Exception e) {
            Toast.makeText(activity, "Error in Payment: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }

    }

    private void bottomNavigation(){
        FloatingActionButton floatingActionButton = findViewById(R.id.cartBtn);
        LinearLayout homeBtn = findViewById(R.id.homeBtn);
        addMore = findViewById(R.id.addMoreBtn);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CartListActivity.this,CartListActivity.class));
            }
        });
        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CartListActivity.this,MainActivity.class));
            }
        });
        addMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CartListActivity.this,MainActivity.class));
            }
        });


    }

    private void initView() {
        recyclerViewList = findViewById(R.id.recyclerView);
        totalFeeTxt = findViewById(R.id.totalFeeTxt);
        taxTxt = findViewById(R.id.taxTxt);
        deliveryTxt = findViewById(R.id.deliveryTxt);
        totalTxt=findViewById(R.id.totalTxt);
        emptyTxt=findViewById(R.id.emptyTxt);
        scrollView = findViewById(R.id.scrollView3);
        recyclerViewList=findViewById(R.id.cartView);
    }

    private void initList(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerViewList.setLayoutManager(linearLayoutManager);
        adapter = new CartListAdapter(managementCart.getListCart(), this, new ChangeNumberItemListener() {
            @Override
            public void change() {
                CalculateCart();
            }
        });
        recyclerViewList.setAdapter(adapter);
        if(managementCart.getListCart().isEmpty()){
            emptyTxt.setVisibility(View.VISIBLE);
            scrollView.setVisibility(View.GONE);
        }else{
            emptyTxt.setVisibility(View.GONE);
            scrollView.setVisibility(View.VISIBLE);
        }
    }
    public void CalculateCart(){
        double percentTax = 0.02;
        double delivery = 40;

        tax = Math.round((managementCart.getTotalFee()*percentTax)*100)/100;
        total = Math.round((managementCart.getTotalFee()+tax+delivery)*100)/100;
        double itemTotal = Math.round(managementCart.getTotalFee()*100)/100;

        totalFeeTxt.setText("₹"+itemTotal);
        taxTxt.setText("₹"+tax);
        deliveryTxt.setText("₹"+delivery);
        totalTxt.setText("₹"+total);
    }

    @Override
    public void onPaymentSuccess(String s) {
        Toast.makeText(this, "Payment Success! "+s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPaymentError(int i, String s) {
        Toast.makeText(this, "Payment error! "+s, Toast.LENGTH_SHORT).show();
    }
}