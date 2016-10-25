package net.ivanvega.sensoresenandroidcurso;


import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.


	SensorManager sm ;
	Sensor sensor ;
	TextView lblX, lblY, lblZ;
	Sensor senOri;

//    para sensor de orientacion
    Sensor acel, magn;

    float degree, azimut;

    float [] mGravity;

    float[] mGeomagnetic;


	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);
		
		lblX= (TextView)findViewById(R.id.lblX);
		lblZ= (TextView)findViewById(R.id.lblZ);
		lblY= (TextView)findViewById(R.id.lblY);
		
		sm =  (SensorManager)getSystemService(Context.SENSOR_SERVICE);

        //sm.getDefaultSensor(SensorManager.SENSOR_ORIENTATION);

        acel = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        magn = sm.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);

        mGravity = null;
        mGeomagnetic = null;


	}


	
	public void btnListar_click(View v) {
		List<Sensor> lstS = sm.getSensorList(Sensor.TYPE_ALL);
		
		for(Sensor s: lstS){
			String str ="";
			str += "Sensor: " + s.getName() + "\n" ;
			str += "Energia: " + s.getPower() + "\n" ;
			str += "Valor maximo: " + s.getMaximumRange() + "\n" ;

			Toast.makeText(getBaseContext(), str, Toast.LENGTH_LONG).show();
			Log.v("SENSORES", str);
		}
		
	}
	
	public void btnSensorAcele_click(View v) {
		sensor = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		sm.registerListener(listenerSensorAcele, sensor, SensorManager.SENSOR_DELAY_NORMAL);
	}
	
	SensorEventListener listenerSensorAcele = new SensorEventListener() {
		
		@Override
		public void onSensorChanged(SensorEvent event) {
			// TODO Auto-generated method stub
			MainActivity.this.setTitle(event.sensor.getName());

				
			lblX.setText("Eje X: " + event.values[0] + " m/s2");
			lblY.setText("Eje Y: " + event.values[1] + " m/s2");
			lblZ.setText("Eje Z: " + event.values[2] + " m/s2");
			


		}
		
		@Override
		public void onAccuracyChanged(Sensor sensor, int accuracy) {
			// TODO Auto-generated method stub
			
		}
	};
	
	public void btnSensorProxi_click(View v) {
		sensor = sm.getDefaultSensor(Sensor.TYPE_PROXIMITY);
		sm.registerListener(listenerSensorProxy, sensor, SensorManager.SENSOR_DELAY_NORMAL);
	}
	
	SensorEventListener listenerSensorProxy = new SensorEventListener() {
		
		@Override
		public void onSensorChanged(SensorEvent event) {
			// TODO Auto-generated method stub
			MainActivity.this.setTitle(event.sensor.getName());
			Toast.makeText(getBaseContext(), 
					"Estas a " + event.values[0] + "cm. de la pantalla."
					+ "Alejate vas a quedar " +
					"visco", Toast.LENGTH_LONG).show();
			Log.v("SENSOR", "" + event.sensor.getMaximumRange()  );
		}
		
		@Override
		public void onAccuracyChanged(Sensor sensor, int accuracy) {
			// TODO Auto-generated method stub
			
		}
	};
	
	@Override
	protected void onResume() {
		//AQUI DEBEMOS REGISTRAR EL ESCUCHADOR DEL SENSOR 
		super.onResume();
//		 senOri = sm.getDefaultSensor(Sensor.TYPE_ORIENTATION);
//		 sm.registerListener(listenerSenOrien, senOri, SensorManager.SENSOR_DELAY_NORMAL);
        sm.registerListener(acelSEL,acel, SensorManager.SENSOR_DELAY_UI);
        sm.registerListener(geoM,magn   , SensorManager.SENSOR_DELAY_UI);
	}
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		//AQUI DEBEMOS QUITAR EL REGISTRO DEL ESCUCHADOR DEL SENSOR 
		if(sensor.getType() == Sensor.TYPE_ACCELEROMETER){
			sm.unregisterListener(listenerSensorAcele);
		}
		if(sensor.getType() == Sensor.TYPE_PROXIMITY){
			sm.unregisterListener(listenerSensorProxy);
		}
		sm.unregisterListener(acelSEL);
        sm.unregisterListener(geoM);
	}
	
	SensorEventListener listenerSenOrien = new SensorEventListener() {
		
		@Override
		public void onSensorChanged(SensorEvent event) {
			// TODO Auto-generated method stub
			
			if (event.values[0]>=350 || event.values[0]<=10 ){
				MainActivity.this.setTitle("NORTE");
			}else
			if (event.values[0]>=12.5 && event.values[0]<=32.5){
				MainActivity.this.setTitle("Nornoreste");
			}else
			if (event.values[0]>=35 && event.values[0]<=55){
				MainActivity.this.setTitle("Noreste");
			}else
			if (event.values[0]>=57.5 && event.values[0]<=77.5){
				MainActivity.this.setTitle("Estenoreste");
			}else
			if (event.values[0]>=80 && event.values[0]<=100){
				MainActivity.this.setTitle("ESTE");
			}else
			if (event.values[0]>=102.5 && event.values[0]<=122.5){
				MainActivity.this.setTitle("Estesureste");
			}else
			if (event.values[0]>=125 && event.values[0]<=145){
				MainActivity.this.setTitle("Sureste");
			}else
			if (event.values[0]>=147.5 && event.values[0]<=167.5){
				MainActivity.this.setTitle("Sursureste");
			}else
			if (event.values[0]>=170 && event.values[0]<=190){
				MainActivity.this.setTitle("SUR");
			}else
			if (event.values[0]>=192.5 && event.values[0]<=212.5){
				MainActivity.this.setTitle("Sursuroeste");
			}else
			if (event.values[0]>=215.5 && event.values[0]<=235){
				MainActivity.this.setTitle("Suroeste");
			}else
			if (event.values[0]>=237.5 && event.values[0]<=257.5){
				MainActivity.this.setTitle("Oestesuroeste");
			}else
			if (event.values[0]>=260 && event.values[0]<=280){
				MainActivity.this.setTitle("OESTE");
			}else
			if (event.values[0]>=282.5 && event.values[0]<=302.5){
				MainActivity.this.setTitle("Oestenoroeste");
			}else
			if (event.values[0]>=305 && event.values[0]<=325){
				MainActivity.this.setTitle("Noroeste");
			}else
			if (event.values[0]>=327.5 && event.values[0]<=347.5){
				MainActivity.this.setTitle("Nornoroeste");
			}else{
				MainActivity.this.setTitle("GRADOS: " + event.values[0]);
			}
			
			
			
		}
		
		@Override
		public void onAccuracyChanged(Sensor sensor, int accuracy) {
			// TODO Auto-generated method stub
			
		}
	};
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

    SensorEventListener acelSEL = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {

        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {

        }
    };

    SensorEventListener geoM = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {

        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {

        }
    };


}
