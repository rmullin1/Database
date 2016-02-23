package com.ebookfrenzy.database;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class DatabaseActivity extends AppCompatActivity {

    TextView idView;
    EditText productBox;
    EditText quantityBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);

        idView = (TextView) findViewById(R.id.productID);
        productBox = (EditText) findViewById(R.id.productName);
        quantityBox =
                (EditText) findViewById(R.id.productQuantity);
    }//onCreate()

    public void newProduct (View view) {
        MyDBHandler dbHandler = new MyDBHandler(this, null, null, 1);

        int quantity =
                Integer.parseInt(quantityBox.getText().toString());

        Product product =
                new Product(productBox.getText().toString(), quantity);

        dbHandler.addProduct(product);
        idView.setText("Record Added");
        productBox.setText("");
        quantityBox.setText("");
    }//newProduct()

    public void lookupProduct (View view) {
        MyDBHandler dbHandler = new MyDBHandler(this, null, null, 1);

        Product product =
                dbHandler.findProduct(productBox.getText().toString());

        if (product != null) {
            idView.setText(String.valueOf(product.getID()));

            quantityBox.setText(String.valueOf(product.getQuantity()));
        } else {
            idView.setText("No Match Found");
        }
    }//lookupProduct()

    public void removeProduct (View view) {
        MyDBHandler dbHandler = new MyDBHandler(this, null,
                null, 1);

        boolean result = dbHandler.deleteProduct(
                productBox.getText().toString());

        if (result)
        {
            idView.setText("Record Deleted");
            productBox.setText("");
            quantityBox.setText("");
        }
        else
            idView.setText("No Match Found");
    }//removeProduct()(

    public void updateProduct(View view) {

        MyDBHandler dbHandler = new MyDBHandler(this, null, null, 1);
        if (productBox.getText().length() == 0 || quantityBox.getText().length() == 0)
            return;

        int quantity = Integer.parseInt(quantityBox.getText().toString());
        Product product = new Product(productBox.getText().toString(), quantity);

        boolean result = dbHandler.updateProduct(product);

        if (result) {
            idView.setText("Quantity Updated");
        } else {
            idView.setText("No Match Found");
        }
    }//updateProduct()

    public void deleteAllProduct(View view) {
        MyDBHandler dbHandler = new MyDBHandler(this, null, null, 1);
        boolean success = dbHandler.deleteAllProduct();
        productBox.setText("");
        quantityBox.setText("");
        if (success) {
            idView.setText("All Products Deleted");
        } else {
            idView.setText("No Products Found");
        }
    }//deleteAllProduct()

}
