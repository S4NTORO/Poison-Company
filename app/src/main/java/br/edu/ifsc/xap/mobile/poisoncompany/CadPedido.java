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

import br.edu.ifsc.xap.mobile.poisoncompany.model.Pedido;

public class CadPedido extends Fragment implements View.OnClickListener{
    private View view;
    private Spinner sp_produto;
    private Spinner sp_cor;
    private Spinner sp_tamanho;
    private EditText numero;
    private Button btSalvar;



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
                Context context = view.getContext();
                CharSequence text = "salvo com sucesso!";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText
                        (context, text, duration);
                toast.show();

        }
    }
}