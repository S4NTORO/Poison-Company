package br.edu.ifsc.xap.mobile.poisoncompany.model;
import org.json.JSONException;
import org.json.JSONObject;
public class Pedido {
    private int id;

    private int sp_produto;

    private int sp_cor;

    private int sp_tamanho;

    private int numero;

    public Pedido() {
        this.setId(0);
        this.setSp_produto(0);
        this.setSp_cor(0);
        this.setSp_tamanho(0);
        this.setNumero(0);
    }

    public Pedido (JSONObject jp) {
        try {
            Integer numero = (int) jp.get("id");
            this.setId(numero);
            this.setSp_produto((Integer) jp.get("produto"));
            this.setSp_cor((Integer) jp.get("cor"));
            this.setSp_tamanho((Integer) jp.get("tamanho"));
            this.setNumero((Integer) jp.get("numero"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public JSONObject toJsonObject() {
        JSONObject json = new JSONObject();
        try {
            json.put("id", this.id);
            json.put("produto", this.sp_produto);
            json.put("cor", this.sp_cor);
            json.put("tamanho", this.sp_tamanho);
            json.put("numero", this.numero);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSp_produto() {
        return sp_produto;
    }

    public void setSp_produto(int sp_produto) {
        this.sp_produto = sp_produto;
    }

    public int getSp_cor() {
        return sp_cor;
    }

    public void setSp_cor(int sp_cor) {
        this.sp_cor = sp_cor;
    }

    public int getSp_tamanho() {
        return sp_tamanho;
    }

    public void setSp_tamanho(int sp_tamanho) {
        this.sp_tamanho = sp_tamanho;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        if(numero < 0)
            numero = 0;
        else if (numero > 10)
            numero = 10;
        this.numero = numero;
    }
}
