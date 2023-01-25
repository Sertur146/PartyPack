package com.example.patypack;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class TestActivity extends AppCompatActivity {

    //ArrayList<String> arrayList = new ArrayList<>();
    //private TextView textView;
    //private EditText editText;
    //private ListView ListOfPlayers;
    //public ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        //textView = (TextView) findViewById(R.id.nameBUH2);
        //editText = (EditText) findViewById(R.id.editBUHgamer2);
        //ListOfPlayers = (ListView) findViewById(R.id.lstPlayers2);
        //adapter = new ArrayAdapter<>(this, R.layout.row, R.id.txtRow, arrayList);
        //ListOfPlayers.setAdapter(adapter);
    }

    //public void AddBUHCall(View view) {
    //    String strBUHgamer = editText.getText().toString();
    //    arrayList.add(strBUHgamer);
    //    adapter.notifyDataSetChanged();
    //    editText.setText("");
    //}
//
    //public void BUHCall(View view) {
    //    if(arrayList.size() == 0){textView.setText("Имя Игрока");}
    //    else {
    //        int i = (int) Math.floor(Math.random() * (arrayList.size() - 0) + 0);
    //        String nameofBUH = arrayList.get(i);
    //        textView.setText(nameofBUH);
    //    }
    //}
//
    //public void DeleteBUHCall(View view) {
    //    String strBUHloser = textView.getText().toString();
    //    Iterator<String> arrayIterator = arrayList.iterator();
    //    while (arrayIterator.hasNext())
    //    {
    //        String nexti =arrayIterator.next();
    //        if (nexti.equals(strBUHloser))
    //        {
    //            arrayIterator.remove();
    //        }
    //    }
    //    adapter.notifyDataSetChanged();
    //    textView.setText("Имя Игрока");
    //}
}