package com.example.shopcluesshoppingapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ProductClickListener {

    private RecyclerView recyclerView;
    private ArrayList<LayoutBaseModel> layouts;
    private ImageView productImage;
    private TextView productName;
    private TextView description;
    private TextView price;
    private TextView actualPrice;
    private TextView discount;
    private TextView buyButton;
    private TextView addToCartButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        buildList();
        setRecyclerView();

    }

    private void initViews() {
        recyclerView = findViewById(R.id.recyclerView);
        productName = findViewById(R.id.product_name);
        description = findViewById(R.id.product_desc);
        price = findViewById(R.id.price);
        actualPrice = findViewById(R.id.actual_price);
        discount = findViewById(R.id.discount);
        buyButton = findViewById(R.id.ButtonBuy);
        addToCartButton = findViewById(R.id.ButtonAddToCart);
    }


    private void buildList() {

        layouts = new ArrayList<>();

        layouts.add(new ProductModel(R.drawable.product1, "Pause solid blue Causal Shirt",
                "Odoky Summer's Cotton Blend Blue Shrug For Men",
                "599", "1099", "70% off"));
        layouts.add(new ProductModel(R.drawable.product2, "Craftwell Blue Double Bedsheet",
                "Craftwell Blue Polycotton 3D Printed Double Bedsheet With 2 Pillow Covers (225 Inch* 225 inch)",
                "228", "599", "74% off"));
        layouts.add(new ProductModel(R.drawable.product3, "Bihari JI Pure Ghe 1Ltr.",
                "Bihari Ji desi ghee is believed to be the best for human consumption, Bihari Ji desi ghee is made from fresh cream and it has a typical rich aroma and granular texture, Bihari Ji desi ghee is an ethnic product made by dairies with decades of experience and a rich source of vitamin A, D, E and Ghee stimulates muscle movements, nourish the skin and improves complexion, Ghee is a good source of energy and provides vitality to the human body.", "424", "450", "5% off"));
        layouts.add(new ProductModel(R.drawable.product4, "Lycra V-Neck Men's T-Shirt",
                "The T-Shirt have two color variants i.e White & Black. The feel of this tee is absolutely amazing thanks to the designer's use of cotton lycra.", "178", "1999", "91% off"));
        layouts.add(new ProductModel(R.drawable.product5, "leather Brown Men's Wallet",
                "", "142", "1799", "92% off"));
        layouts.add(new ProductModel(R.drawable.product6, "Liddu TG 113 Speaker",
                "", "408", "299", "86% off"));
        layouts.add(new ProductModel(R.drawable.product7, "Palco M1101 USB Bluetooth",
                "", "931", "2000", "53% off"));
        layouts.add(new ProductModel(R.drawable.product8, "Trendyz Men Black Hooded Tshirt",
                "", "475", "899", "47% off"));
        layouts.add(new ProductModel(R.drawable.product9, "Men Red Solid High Neck T-Shirt",
                "", "278", "1099", "74% off"));
        layouts.add(new ProductModel(R.drawable.product10, "Ikall TA-777 Portable Bluetooth",
                "", "2299", "4999", "54% off"));
        layouts.add(new ProductModel(R.drawable.product11, "Krishna Multicolor Wall Stickers",
                "", "95", "599", "84% off"));
        layouts.add(new ProductModel(R.drawable.product12, "Clymb Men Sport Shoe",
                "", "497", "999", "50% off"));
        layouts.add(new ProductModel(R.drawable.product13, "Voorkoms body Tempoary Tattoo",
                "", "142", "499", "71% off"));
        layouts.add(new ProductModel(R.drawable.product14, "Cute Panda Wall Sticker",
                "", "95", "599", "84% off"));
        layouts.add(new ProductModel(R.drawable.product15, "Titan Men Classic Analog Watch",
                "", "478", "1999", "76% off"));
        layouts.add(new ProductModel(R.drawable.product16, "Lotus herbals radiant gold",
                "", "276", "1010", "72% off"));
        layouts.add(new ProductModel(R.drawable.product17, "BUCIK Black Synthetic Sandals",
                "", "361", "1999", "81% off"));
        layouts.add(new ProductModel(R.drawable.product18, "Trackpant With Full Sleeve T-Shirt",
                "", "579", "1199", "51% off"));
        layouts.add(new ProductModel(R.drawable.product19, "Kaltron Mini 8 Plug 1 Extension",
                "", "199", "599", "66% off"));
        layouts.add(new ProductModel(R.drawable.product20, "Men Black Hooded Tshirt",
                "", "398", "1099", "63% off"));
        layouts.add(new ProductModel(R.drawable.product21, "True Choice Men Analog Watch",
                "", "218", "1999", "89% off"));
        layouts.add(new ProductModel(R.drawable.product22, "Red Solid Cotton Shrug for Men",
                "", "398", "999", "60% off"));
        layouts.add(new ProductModel(R.drawable.product23, "Baby Himalaya Face Cream ",
                "", "150", "300", "50% off"));
        layouts.add(new ProductModel(R.drawable.product24, "Wild Stone Body Spray",
                "", "199", "249", "40% off"));
        layouts.add(new ProductModel(R.drawable.product25, "Colorblock Cardigan for Men",
                "", "478", "1499", "68% off"));
        layouts.add(new ProductModel(R.drawable.product26, "Men's Brown Ethnic Shoe",
                "", "399", "699", "50% off"));
        layouts.add(new ProductModel(R.drawable.product27, "Blue Heaven Lipstick",
                "", "266", "299", "11% off"));
        layouts.add(new ProductModel(R.drawable.product28, "Kaltron Diagonal Steel",
                "", "209", "599", "65% off"));
        layouts.add(new ProductModel(R.drawable.product29, "Meia White Pink Kanchipuram Saree",
                "", "730", "2999", "75% off"));
        layouts.add(new ProductModel(R.drawable.product30, "Set of 4 MultiColor Wall Sticker",
                "EJA Art brings here an interesting Wall stickers (DIY) to decorate your walls, paste at your desired place. Package Include: 1 x PVC Vinyl sticker Material: PVC Vinyl Compatible Features: PVC, Non-toxic, Eco-friendly and Waterproof. Occasion:Ideal for Family Lounge, Bedroom, Cafe and Restaurant, Kids room, Nursery Room etc. Installation prompt: Please keep the wall clean before pasting. First according to the template on the ground, put the product well. Suggest to take a small piece of component test whether suitable for me tope. Press firmly to squeeze out any air bubbles. Cautions: Do not apply on wet walls. Do not apply on ash surfaces. Do not apply on broken surfaces. ", "190", "499", "57% off"));
//            layouts.add(new SmallBannerModel(R.drawable.small_banner_1));

    }


    private void setRecyclerView() {
        ProductAdapter productAdapter = new ProductAdapter(layouts, this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(productAdapter);
    }


    @Override
    public void onProductClick(LayoutBaseModel layoutBaseModel, int position) {
        Intent intent = new Intent(this, ProductDetailActivity.class);
        intent.putExtra("name", layouts.toString());
        intent.putExtra("desc", layouts.toString());
        intent.putExtra("price", layouts.toString());
        intent.putExtra("actualPrice", layouts.toString());
        intent.putExtra("discount", layouts.toString());
//        intent.putExtras(productImage.getImageTintMode());
        startActivity(intent);
    }
}