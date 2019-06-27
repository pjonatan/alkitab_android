package base.pb;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.*;


public class Tampil extends Activity  {

	TextView tV, tV2;
	ImageButton iB, iBmin, iBplus;
	String kitab, pindah;
	int index, buku, max;
	DBH dbh;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tampil);

		pindah = getIntent().getExtras().getString("Pindah");
		tV     = (TextView)findViewById(R.id.pasal);
		tV2    = (TextView)findViewById(R.id.textView);
		iB     = (ImageButton)findViewById(R.id.exit);
		iBmin  = (ImageButton)findViewById(R.id.min);
		iBplus = (ImageButton)findViewById(R.id.plus);
        int Ind = -1;
        do {
            Ind++;
        } while (this.pindah.charAt(Ind) != '/'); 
        kitab = pindah.substring(0, Ind);
        index = Integer.valueOf(pindah.substring(Ind + 1));
		dbh = new DBH(this);
        Cursor c1 = dbh.getBuku(kitab);
        c1.moveToFirst();
        max = Integer.parseInt(c1.getString(2));
        buku = Integer.parseInt(c1.getString(0));
        if(index>max)index=max;
        tampilkan();
		
		
        tV.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Tampil.this.startActivity(new Intent(Tampil.this, Main.class));
            	android.os.Process.killProcess(android.os.Process.myPid());
            }
          });		
		
		iB.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
            	   android.os.Process.killProcess(android.os.Process.myPid());
            	}
          });

		iBmin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
            	   index = index -1;
            	   if(index<1)index=1;
            	   tampilkan();
            	}
          });

		iBplus.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
            	   index++;
            	   if(index>max)index=max;
            	   tampilkan();
            	}
          });
	}

	public void tampilkan()
	{
		tV.setText(kitab+ " --> " + Integer.toString(index));
		Cursor c2 = dbh.getTeks(buku, index);
		c2.moveToFirst();
		tV2.setText(c2.getString(3));
	}
}
