package edu.usc.projecttalent.cognitive.spatial;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import edu.usc.projecttalent.cognitive.R;

public class SPpracticeCorrect_Activity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sppractice_correct_);
		ImageView myImage = (ImageView) findViewById(R.id.question_image);
	    myImage.setImageBitmap(decodeImage(getResources(),R.drawable.sp_s1_main,myImage.getLayoutParams().width,myImage.getLayoutParams().height));

	    myImage = (ImageView) findViewById(R.id.imageView1);
	    myImage.setImageBitmap(decodeImage(getResources(),R.drawable.sp_s1_1,myImage.getLayoutParams().width,myImage.getLayoutParams().height));

	    myImage = (ImageView) findViewById(R.id.imageView2);
	    myImage.setImageBitmap(decodeImage(getResources(),R.drawable.sp_s1_2,myImage.getLayoutParams().width,myImage.getLayoutParams().height));

	    myImage = (ImageView) findViewById(R.id.imageView3);
	    myImage.setImageBitmap(decodeImage(getResources(),R.drawable.sp_s1_3,myImage.getLayoutParams().width,myImage.getLayoutParams().height));

	    myImage = (ImageView) findViewById(R.id.imageView4);
	    myImage.setImageBitmap(decodeImage(getResources(),R.drawable.sp_s1_4,myImage.getLayoutParams().width,myImage.getLayoutParams().height));

	    myImage = (ImageView) findViewById(R.id.imageView5);
	    myImage.setImageBitmap(decodeImage(getResources(),R.drawable.sp_s1_5,myImage.getLayoutParams().width,myImage.getLayoutParams().height));

	    Button button = (Button) findViewById(R.id.button3);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
            		Intent intent = new Intent(SPpracticeCorrect_Activity.this, SPIntro_Activity.class);
            		startActivity(intent);
            }
        });
        button = (Button) findViewById(R.id.button4);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
            	Intent intent = new Intent(SPpracticeCorrect_Activity.this, SPpractice_Activity.class);
            	startActivity(intent);

            }
        });
	}
	public static Bitmap decodeImage(Resources res,int resid,int WIDTH,int HIGHT){
		 //Decode image size
		 BitmapFactory.Options o = new BitmapFactory.Options();
		 o.inJustDecodeBounds = true;
		 BitmapFactory.decodeResource(res,resid,o);

		 //The new size we want to scale to
		 final int REQUIRED_WIDTH=WIDTH;
		 final int REQUIRED_HIGHT=HIGHT;
		 //Find the correct scale value. It should be the power of 2.
		 int scale=1;
		 while(o.outWidth/scale/2>=REQUIRED_WIDTH && o.outHeight/scale/2>=REQUIRED_HIGHT)
		     scale*=2;

		 //Decode with inSampleSize
		 BitmapFactory.Options o2 = new BitmapFactory.Options();
		 o2.inSampleSize=scale;
		 return BitmapFactory.decodeResource(res,resid, o2);
		}
}
