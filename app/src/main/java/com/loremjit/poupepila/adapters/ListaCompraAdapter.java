package com.loremjit.poupepila.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.loremjit.poupepila.R;
import com.loremjit.poupepila.adapters.view_holders.CompraViewHolder;
import com.loremjit.poupepila.database.PoupePilaDAO;
import com.loremjit.poupepila.database.model.Estabelecimento;
import com.loremjit.poupepila.database.model.Produto;
import com.loremjit.poupepila.database.model.ProdutoCompra;
import com.loremjit.poupepila.security.Sessao;

public class ListaCompraAdapter extends RecyclerView.Adapter {
    private PoupePilaDAO db = PoupePilaDAO.getInstance();

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //1: inflar XML
        ConstraintLayout elementoPrincipalXML = (ConstraintLayout) LayoutInflater.from(parent.getContext()).inflate(
                R.layout.item_lista_produto1, parent, false
        );
        //2: associar os objetos inflados a um novo view holder
        CompraViewHolder gaveta = new CompraViewHolder(elementoPrincipalXML);
        //3: retornar o view holder que foi criado no passo 2
        return gaveta;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        CompraViewHolder viewHolder = (CompraViewHolder) holder;
        ProdutoCompra compra = (ProdutoCompra) db.getLista(Sessao.getInstance().getIdSession(),"compra").get(position);
        Produto produto = db.getProduto(compra.getId());
        Estabelecimento estabelecimento = db.getEstabelecimento(compra.getEstabelecimento_id());

        viewHolder.tvMercado.setText(estabelecimento.getNome());
        viewHolder.tvProduto.setText(produto.getNome());
        viewHolder.tvPreco.setText(String.valueOf(compra.getPreco()));
        viewHolder.setId(compra.getId());
        viewHolder.setPosicao(position);
    }

    @Override
    public int getItemCount() {
        return db.getLista(Sessao.getInstance().getIdSession(),"compra").size();
    }
}
