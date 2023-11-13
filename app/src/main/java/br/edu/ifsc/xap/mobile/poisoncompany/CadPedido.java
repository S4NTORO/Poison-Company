package br.edu.ifsc.xap.mobile.poisoncompany;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.snackbar.Snackbar;


import org.json.JSONException;
import org.json.JSONObject;

import br.edu.ifsc.xap.mobile.poisoncompany.model.Pedido;

public class CadPedido extends Fragment implements View.OnClickListener{
    private View view;
    private Spinner sp_produto;
    private Spinner sp_cor;
    private Spinner sp_tamanho;
    private EditText numero;
    private Button btSalvar;


    private RequestQueue requestQueue;
    private JsonObjectRequest jsonObjectReq;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.fragment_cad_pedido, container, false);

        this.sp_produto = (Spinner) view.findViewById(R.id.sp_produto);

        this.sp_cor = (Spinner) view.findViewById(R.id.sp_cor);

        this.sp_tamanho = (Spinner) view.findViewById(R.id.sp_tamanho);

        this.numero = (EditText) view.findViewById(R.id.numero);

        this.btSalvar = (Button) view.findViewById(R.id.btSalvar);

        this.btSalvar.setOnClickListener(this);

        //instanciando a fila de requests - caso o objeto seja o view
        this.requestQueue = Volley.newRequestQueue(view.getContext());
//inicializando a fila de requests do SO
        this.requestQueue.start();

        return this.view;
    }
    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btSalvar){
                Pedido u = new Pedido();

                u.setSp_produto(this.sp_produto.getSelectedItemPosition());

                u.setSp_cor(this.sp_cor.getSelectedItemPosition());

                u.setSp_tamanho(this.sp_tamanho.getSelectedItemPosition());

                u.setNumero(Integer.valueOf( this.numero.getText().toString()));

//mensagem de sucesso
            jsonObjectReq = new JsonObjectRequest(
                    Request.Method.POST,
                    "http://10.0.2.2/poisonCompany/cadPedido.php",
                    u.toJsonObject(), this::onResponse, this::onErrorResponse);
            requestQueue.add(jsonObjectReq);
        }
    }

    public void onErrorResponse(VolleyError error) {
        Snackbar mensagem = Snackbar.make(view,
                "Ops! Houve um problema ao realizar o cadastro: " +
                        error.toString(),Snackbar.LENGTH_LONG);
        mensagem.show();
    }

    public void onResponse(Object response) {
        try {
            //instanciando objeto para manejar o JSON que recebemos
            JSONObject jason = new JSONObject(response.toString());
            //context e text são para a mensagem na tela o Toast
            Context context = view.getContext();
            //pegando mensagem que veio do json
            CharSequence mensagem = jason.getString("message");
            //duração da mensagem na tela
            int duration = Toast.LENGTH_SHORT;
            //verificando se salvou sem erro para limpar campos da tela
            if (jason.getBoolean("success")){
                //campos texto
                this.numero.setText(0);
                //selecionando primiro item dos spinners
                this.sp_produto.setSelection(0);
                this.sp_tamanho.setSelection(0);
                this.sp_cor.setSelection(0);
            }
            //mostrando a mensagem que veio do JSON
            Toast toast = Toast.makeText (context, mensagem, duration);
            toast.show();

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        //mensagem de sucesso


    }
}