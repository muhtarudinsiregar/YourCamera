package com.example.ardin.yourcameraandroid

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    companion object {
        const val IMAGE_REQUEST_CODE = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        captureImage.setOnClickListener {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivityForResult(intent, IMAGE_REQUEST_CODE);
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == IMAGE_REQUEST_CODE && resultCode == RESULT_OK) {
            val extras = data?.getExtras()
            val imageBitmap = extras?.get("data") as Bitmap
            showImage.setImageBitmap(imageBitmap)
        } else {
            Toast.makeText(this, "Failed to load image", Toast.LENGTH_LONG)
                    .show()
        }
    }
}
