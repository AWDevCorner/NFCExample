package it.androidworld.devcorner.androidbeamexample;



import android.app.Activity;
import android.content.pm.PackageManager;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.NfcAdapter.CreateNdefMessageCallback;
import android.nfc.NfcAdapter.OnNdefPushCompleteCallback;
import android.nfc.NfcEvent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.Toast;


public class MainActivity extends Activity implements CreateNdefMessageCallback, OnNdefPushCompleteCallback{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	     Log.i("isBeam",isBeamAvailable()+"");   

		if(isBeamAvailable()){
			 //gets NFC Adapter
            NfcAdapter nfcAdapter = NfcAdapter.getDefaultAdapter(this);
            // Register callback to set NDEF message
            nfcAdapter.setNdefPushMessageCallback(this, this);
            // Register callback to listen for message-sent success
            nfcAdapter.setOnNdefPushCompleteCallback(this, this);
		}
		else{
			Toast.makeText(this, "NFC non disponibile", Toast.LENGTH_LONG);
		}
	}
	
	private boolean isBeamAvailable(){
	     Log.i("TAG","isBeamAvailable");   
        PackageManager packageManager = getPackageManager();
        return packageManager.hasSystemFeature(PackageManager.FEATURE_NFC);
    }

	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}


	@Override
	public void onNdefPushComplete(NfcEvent arg0) {
		// TODO Auto-generated method stub
		Log.i("TAG", "Link inviato");
		
	}

	@Override
	public NdefMessage createNdefMessage(NfcEvent nfcEvent) 
	{
		// TODO Auto-generated method stub
	     Log.i("TAG","Metodo invio link");   
		return new NdefMessage(new NdefRecord[] {
			    NdefRecord.createUri("http://www.androidworld.it")
			});

}
}