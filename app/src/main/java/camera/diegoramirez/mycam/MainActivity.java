package camera.diegoramirez.mycam;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends Activity implements SurfaceHolder.Callback {

    Camera camera;
    private ProgressBar bar;
    Button btn;
    ImageView image;
    SurfaceView surfaceView;
    SurfaceHolder surfaceHolder;
    ProgressDialog pd;
    ImageView imv;
    ImageView imgs ;
    EditText edt_descripcion;
    String descripcion;
   // private static final String SERVER = "dpruebas.96.lt/";
     private static final String SERVER = "http://www.wimeapp.com/prueba.php";
    private static final byte[] data = new byte[0];
    PictureCallback jpegCallback;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        surfaceView = (SurfaceView) findViewById(R.id.surfaceView);
        surfaceHolder = surfaceView.getHolder();
        btn = (Button)findViewById(R.id.button);



        // bar = (ProgressBar) this.findViewById(R.id.progressBar);
        // Install a SurfaceHolder.Callback so we get notified when the
        // underlying surface is created and destroyed.
        surfaceHolder.addCallback(this);
        // Button switchCamera = (Button) findViewById(R.id.front);
        //  switchCamera.setOnClickListener(switchCameraListener);

        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        // deprecated setting, but required on Android versions prior to 3.0
        surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

        jpegCallback = new PictureCallback() {
            public void onPictureTaken(final byte[] data, Camera camera) {
                FileOutputStream outStream = null;
                //Toast.makeText(getApplicationContext(),"Mensaje",Toast.LENGTH_SHORT).show();
                try {
                    //Toast.makeText(getApplicationContext(),"Mensaj2",Toast.LENGTH_SHORT).show();
                   // String ruta = "/sdcard/Pictures/Layout/%d.jpg";
                    outStream = new FileOutputStream(String.format("/sdcard/Pictures/Layout/%d.jpg", System.currentTimeMillis()));
                    //outStream = new FileOutputStream(String.format(ruta, System.currentTimeMillis()));
                    outStream.write(data);
                    outStream.close();







                    //Bitmap bitmap = BitmapFactory.decodeFile(ruta);
                    //File f = new File(ruta);
                    //Bitmap bmp = BitmapFactory.decodeFile(f.getAbsolutePath());

                    // Bitmap bit ;
                    //Bitmap bit2;
                    //bit = ByteArrayToBitmap(data);
                    // BitmapDrawable d = new BitmapDrawable(getResources(), myFile.getAbsolutePath());
                    //new MiTarea(ByteArrayToBitmap(data)).execute();
                    // new MiTarea(bitmap).execute();

                    //refreshCamera();
                    /*byte[] imgbytes = Base64.decode(data, Base64.DEFAULT);
                    Bitmap bitmap = BitmapFactory.decodeByteArray(imgbytes, 0,
                            imgbytes.length);

                    Log.e("BitMap " ," "+  bitmap);
                    Toast.makeText(getApplicationContext(),"BITMAP : "+ bitmap,Toast.LENGTH_LONG).show();*/
                    // imageupload.setImageBitmap(bitmap);
                    //Toast.makeText(getApplicationContext(),"Byte[]array : "+ data,Toast.LENGTH_LONG).show();
                    // Log.d("Log", "onPictureTaken - wrote bytes: " + data.length);
                    // Toast.makeText(getApplicationContext(),"Subiendo Imagen",Toast.LENGTH_LONG).show();
                    //  Bitmap i2  = getBitmap(data);


                    //INICIO

                    /*

                    AlertDialog.Builder dialogo1 = new AlertDialog.Builder(MainActivity.this);
                    dialogo1.setTitle("Envio de Foto");
                    dialogo1.setMessage(" Desea enviar la imagen de la campana ?");
                    //dialogo1.setView(i2);
                    Bitmap imgv = ByteArrayToBitmap(data);
                    // Bitmap i = ((BitmapDrawable)).getBitmap();
                    imv = (ImageView) findViewById(R.id.goProDialogImage);
                    imv.setImageBitmap(imgv);
                    dialogo1.setCancelable(false);


                    dialogo1.setPositiveButton("Enviar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogo1, int id) {
                            // aceptar();
                            // camera.takePicture(null, null, jpegCallback);

                                Toast.makeText(getApplication(),"Proceso",Toast.LENGTH_SHORT).show();
                            //new MiTarea(getDrwableFromBytes(data)).execute();
                            //  new MiTarea(convertBase64ToImage(data)).execute();

                        }
                    });
                    dialogo1.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogo1, int id) {
                            Toast.makeText(getApplicationContext(), "Cancelado", Toast.LENGTH_SHORT).show();
                            refreshCamera();
                        }
                    });



                    dialogo1.show();*/



                    //FIN

/*
                    AlertDialog.Builder alertadd = new AlertDialog.Builder(
                            MainActivity.this);

                    alertadd.setTitle("Quieres Evniar la Imagen de la campaña ?");

                    LayoutInflater factory = LayoutInflater.from(getApplicationContext());
                    final View view = factory.inflate(R.layout.dialog_image, null);
                    Bitmap imgv = ByteArrayToBitmap(data);
                    image = (ImageView) view.findViewById(R.id.imageView);
                    image.setImageBitmap(imgv);
                    edt_descripcion = (EditText)view.findViewById(R.id.edt_descripcion);

                    final Bitmap UploadImage = ((BitmapDrawable) image.getDrawable()).getBitmap();
                    // TextView text= (TextView) view.findViewById(R.id.textView);
                    //text.setText("Android");

                    alertadd.setView(view);
                    alertadd.setPositiveButton("Enviar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogo1, int id) {
                            // aceptar();
                            // camera.takePicture(null, null, jpegCallback);

                            // Bitmap UploadImage = ((BitmapDrawable imgv.getDrawable()).getBitmap();
                            //  new MiTarea(getDrwableFromBytes(data)).execute();

                            //descripcion = edt_descripcion.getText().toString();
                            new MiTarea(MainActivity.this.edt_descripcion.getText().toString(),UploadImage).execute();

                        }
                    });
                    alertadd.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogo1, int id) {
                            Toast.makeText(getApplicationContext(), "Cancelado", Toast.LENGTH_SHORT).show();
                            refreshCamera();
                        }
                    });
                    alertadd.setNeutralButton("ok", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dlg, int sumthin) {

                        }
                    });

                    alertadd.show();
                    */


                  /*  imgs = (ImageView) findViewById(R.id.imgs);
                    Bitmap i2  = getBitmap(data);
                    imgs.setImageBitmap(i2);
                    new MiTarea(i2,"hola").execute();
                    */


                    Toast.makeText(getApplicationContext(), "Picture Guardada", Toast.LENGTH_SHORT).show();
                }
                catch (FileNotFoundException e)
                {
                    e.printStackTrace();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
                finally
                {

                }


                AlertDialog.Builder alertadd = new AlertDialog.Builder(
                        MainActivity.this);

                alertadd.setTitle("Quieres Evniar la Imagen de la campaña ?");

                LayoutInflater factory = LayoutInflater.from(getApplicationContext());

                final View view = factory.inflate(R.layout.dialog_image, null);
                Bitmap imgv = ByteArrayToBitmap(data);
                image = (ImageView) view.findViewById(R.id.imageView);
                image.setRotation(90);
                image.setImageBitmap(imgv);

                edt_descripcion = (EditText)view.findViewById(R.id.edt_descripcion);

                final Bitmap UploadImage = ((BitmapDrawable) image.getDrawable()).getBitmap();
                // TextView text= (TextView) view.findViewById(R.id.textView);
                //text.setText("Android");

                alertadd.setView(view);
                alertadd.setPositiveButton("Enviar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogo1, int id) {
                        // aceptar();
                        // camera.takePicture(null, null, jpegCallback);

                        // Bitmap UploadImage = ((BitmapDrawable imgv.getDrawable()).getBitmap();
                        //  new MiTarea(getDrwableFromBytes(data)).execute();

                        //descripcion = edt_descripcion.getText().toString();



                        new MiTarea(UploadImage,edt_descripcion.getText().toString()).execute();
                        btn.setEnabled(true);

                    }
                });
                alertadd.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogo1, int id) {
                        Toast.makeText(getApplicationContext(), "Cancelado", Toast.LENGTH_SHORT).show();
                        refreshCamera();
                        btn.setEnabled(true);
                    }
                });

                alertadd.setCancelable(false);



               /* alertadd.setNeutralButton("ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dlg, int sumthin) {

                    }
                });
                    */
                alertadd.show();







            }
        };


    }








    public void captureImage(View v) throws IOException {
        //take the picture

        Boolean conexion = verificaConexion(getApplicationContext());

        if (conexion != true )
        {
            Toast.makeText(getApplicationContext(),"Verifica tu conexion a internet ",Toast.LENGTH_SHORT).show();
            refreshCamera();
            btn.setEnabled(true);
        }
        else
        {
           // new MiTarea(UploadImage,edt_descripcion.getText().toString()).execute();
            camera.takePicture(null, null, jpegCallback);
            btn.setEnabled(false);
        }





    }

    public void refreshCamera()
    {
        if (surfaceHolder.getSurface() == null) {
            // preview surface does not exist
            return;
        }

        // stop preview before making changes
        try {
            camera.stopPreview();
        } catch (Exception e) {
            // ignore: tried to stop a non-existent preview
        }

        // set preview size and make any resize, rotate or
        // reformatting changes here
        // start preview with new settings
        try {
            camera.setPreviewDisplay(surfaceHolder);
            camera.startPreview();
        } catch (Exception e) {

        }
    }



    public void surfaceChanged(SurfaceHolder holder, int format, int w, int h) {
        // Now that the size is known, set up the camera parameters and begin
        // the preview.
        refreshCamera();
    }

    public void surfaceCreated(SurfaceHolder holder)
    {
        try {
            // open the camera
            camera = Camera.open();
        } catch (RuntimeException e) {
            // check for exceptions
            System.err.println(e);
            return;
        }


        Camera.Parameters param;

        param = camera.getParameters();

        // param.setColorEffect(Camera.Parameters.EFFECT_SEPIA);


        //  param.setPreviewSize(100,100);

        //param.setPictureFormat(ImageFormat.JPG);

        //param.set("jpeg-quality", 100);

        //param.set("orientation", "landscape");

        //param.set("rotation", 90);

        //param.setFocusMode(param.FOCUS_MODE_CONTINUOUS_PICTURE);





        //mCamera.setParameters(parameters);
        //modify parameter
        //param.setPreviewSize(352, 288);
        //param.setColorEffect("#FFF");
        //param.setFlashMode(param.FLASH_MODE_AUTO);
        //param.setPictureSize(1500,1500);




        camera.setParameters(param);
        camera.setDisplayOrientation(90);
        try {
            // The Surface has been created, now tell the camera where to draw
            // the preview.
            camera.setPreviewDisplay(surfaceHolder);
            camera.startPreview();
        } catch (Exception e) {
            // check for exceptions
            System.err.println(e);
            return;
        }

    }

    public void surfaceDestroyed(SurfaceHolder holder) {
        // stop preview and release camera
        camera.stopPreview();
        camera.release();
        camera = null;
    }


   /* class MiTarea extends AsyncTask<Integer, Integer, Integer> {

        private ProgressDialog progreso;

        @Override protected void onPreExecute() {

            progreso = new ProgressDialog(MainActivity.this);

            progreso.setProgressStyle(ProgressDialog.
                    STYLE_HORIZONTAL);

            progreso.setMessage("Aqui se subira la foto ...");

            progreso.setCancelable(false);

            progreso.setMax(100);

            progreso.setProgress(0);

            progreso.show();

        }

        @Override protected Integer doInBackground(Integer... n) {

            int res = 1;

            for (int i = 1; i <= n[0]; i++) {

                res *= i;

                SystemClock.sleep(1000);

                publishProgress(i*100 / n[0]);

            }

            return res;

        }

        @Override protected void onProgressUpdate(Integer... porc) {

            progreso.setProgress(porc[0]);

        }

        @Override protected void onPostExecute(Integer res) {

            progreso.dismiss();

            // salida.append(res + "\n");

        }

    }*/

    class MiTarea extends AsyncTask<Void, Void, Void> {
        Bitmap img;
        String descripcion;


        public MiTarea (Bitmap imagen, String des)
        {
            this.img = imagen;
            this.descripcion = des;

        }



        @Override
        protected void onPreExecute() {





            pd = new ProgressDialog(MainActivity.this);
            pd.setMessage("Subiendo Imagen....");
            pd.setCancelable(false);
            pd.show();

            // bar.setVisibility(View.VISIBLE);

        }

        @Override
        protected Void doInBackground(Void... params) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            img.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
            String encodedImage = Base64.encodeToString(byteArrayOutputStream.toByteArray(),Base64.DEFAULT);

            ArrayList<NameValuePair> dataSend = new ArrayList<>();



            dataSend.add(new BasicNameValuePair("img", encodedImage));
            dataSend.add(new BasicNameValuePair("name", descripcion));

            Log.e("Imagen : ", encodedImage);
            Log.e("Descripcion : ",descripcion);
            Log.e("URL : ",SERVER);

            HttpParams httpRequestParams =  getHttpRequestParams();
            //HttpClient httpRequestParams = HttpClientBuilder.create().build();

            HttpClient client = new DefaultHttpClient(httpRequestParams);
            HttpPost post= new HttpPost(SERVER);

            try {
                post.setEntity(new UrlEncodedFormEntity(dataSend));
                client.execute(post);
            }catch (Exception e )
            {
                e.printStackTrace();
            }

            return null;

        }

        @Override
        protected void onProgressUpdate(Void... porc) {


        }

        @Override
        protected void onPostExecute(Void res) {
            super.onPostExecute(res);
            if (pd != null)
            {
                pd.dismiss();
            }

            // bar.setVisibility(View.GONE);
            Toast.makeText(getApplicationContext(),"Imagen Enviada",Toast.LENGTH_SHORT).show();
            refreshCamera();

        }

    }

    private HttpParams getHttpRequestParams(){
        HttpParams httpRequestParams = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(httpRequestParams, 1000 * 30);
        HttpConnectionParams.setSoTimeout(httpRequestParams,1000*30);
        return httpRequestParams;
    }

    public static Bitmap convertBase64ToImage(byte[] base64)
    {
        byte[] byteArray = Base64.decode(base64, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
    }

    public Bitmap ByteArrayToBitmap(byte[] byteArray)
    {
        ByteArrayInputStream arrayInputStream = new ByteArrayInputStream(byteArray);
        Bitmap bitmap = BitmapFactory.decodeStream(arrayInputStream);
        return bitmap;
    }


    public Drawable getDrwableFromBytes(byte[] imageBytes) {

        if (imageBytes != null)
            return new BitmapDrawable(BitmapFactory.decodeByteArray(imageBytes,
                    0, imageBytes.length));
        else
            return null;
    }

    public Bitmap getBitmap(byte[] bitmap)
    {
        return BitmapFactory.decodeByteArray(bitmap , 0, bitmap.length);
    }

    public static boolean verificaConexion(Context ctx) {
        boolean bConectado = false;
        ConnectivityManager connec = (ConnectivityManager) ctx
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        // No sólo wifi, también GPRS
        NetworkInfo[] redes = connec.getAllNetworkInfo();
        // este bucle debería no ser tan ñapa
        for (int i = 0; i < 2; i++) {
            // ¿Tenemos conexión? ponemos a true
            if (redes[i].getState() == NetworkInfo.State.CONNECTED) {
                bConectado = true;
            }
        }
        return bConectado;
    }

    //Parte para agreagr zoom a la app

  /*  public void zoom()
    {
        Camera.Parameters params=camera.getParameters();
        params.setZoom(params.getMaxZoom());
        camera.setParameters(params);
    }

    public void unzoom()
    {
        Camera.Parameters params=camera.getParameters();
        params.setZoom(0);
        camera.setParameters(params);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        super.onKeyLongPress(keyCode, event);
        if (keyCode == KeyEvent.KEYCODE_VOLUME_UP)
        {
            //Toast.makeText(getApplicationContext(),"Oprimiendo Volumen arriba",Toast.LENGTH_SHORT).show();
            zoom();
            //Log.w("LightWriter", "I WORK BRO.");
            return true;
        }
        return false;
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        super.onKeyDown(keyCode, event);
        if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN)
        {
            //Toast.makeText(getApplicationContext(),"Oprimiendo Volumen abajo",Toast.LENGTH_SHORT).show();
            unzoom();
            //Log.w("LightWriter", "I WORK BRO.");
            return true;
        }
        return true;
    }
    */


}

