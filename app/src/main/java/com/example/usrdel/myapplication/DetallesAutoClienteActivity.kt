package com.example.usrdel.myapplication

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import kotlinx.android.synthetic.main.activity_detalles_auto_cliente.*
import android.widget.Toast
import android.content.Intent
import android.database.Cursor
import android.net.Uri
import android.provider.ContactsContract


class DetallesAutoClienteActivity : AppCompatActivity() {

    var auto: Auto? = null
    lateinit var myBitmapAgain: Bitmap
    lateinit var idProducto:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalles_producto_cliente)

        auto = intent.getParcelableExtra("detallesAutoCliente")


        txtShowNombre.text = auto?.nombre
        txtShowMarca.text = auto?.marca
        txtShowColor.text = auto?.color
        txtShowTipo.text = auto?.tipo
        txtShowAnios.text = auto?.anio.toString()
        txtShowPrecio.text = auto?.precio

        txtShowNumeroDuenios.text = auto?.numeroDuenios.toString()
        txtShowMatriculado.text = auto?.matriculadoActual.toString()

        //Toast.makeText(this,auto?.id.toString(),Toast.LENGTH_SHORT).show()


        myBitmapAgain = decodeBase64(auto?.imagenAuto.toString()!!)


        btnCompartir.setOnClickListener(){
            selectContact()
        }

    }

    fun decodeBase64(input: String): Bitmap {
        val decodedBytes =  Base64.decode(input,0)
        return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.size)
    }




    static final int REQUEST_SELECT_PHONE_NUMBER = 1;

    public fun selectContact() {
        // Start an activity for the user to pick a phone number from contacts
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, REQUEST_SELECT_PHONE_NUMBER);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_SELECT_PHONE_NUMBER && resultCode == RESULT_OK) {
            // Get the URI and query the content provider for the phone number
            Uri contactUri = data.getData();
            String[] projection = new String[]{ ContactsContract.CommonDataKinds.Phone.NUMBER};
            Cursor cursor = getContentResolver().query(contactUri, projection,
                    null, null, null);
            // If the cursor returned is valid, get the phone number
            if (cursor != null && cursor.moveToFirst()) {
                int numberIndex = cursor.getColumnIndex(CommonDataKinds.Phone.NUMBER);
                String number = cursor.getString(numberIndex);
                sendEmail()
            }
        }
    }


    protected fun sendEmail() {
        val TO = arrayOf("contacto@seogalicia.es") //aquí correo
        val CC = arrayOf("")
        val emailIntent = Intent(Intent.ACTION_SEND)
        emailIntent.data = Uri.parse("mailto:")
        emailIntent.type = "text/plain"
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO)
        emailIntent.putExtra(Intent.EXTRA_CC, CC)
        // Esto podrás modificarlo si quieres, el asunto y el cuerpo del mensaje
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Asunto")
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Estimado aqui va los detalles del auto...")

        try {
            startActivity(Intent.createChooser(emailIntent, "Enviar email..."))
            finish()
        } catch (ex: android.content.ActivityNotFoundException) {
            Toast.makeText(this@DetallesAutoClienteActivity,
                    "No tienes clientes de email instalados.", Toast.LENGTH_SHORT).show()
        }

    }

}
