package co.marcelosousa.amaro;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

import co.marcelosousa.amaro.model.Produtos;

public class ListaProdutosAdapter extends RecyclerView.Adapter<ListaProdutosAdapter.ViewHolder> {

    private ArrayList<Produtos> dataset;
    private Context context;

    public ListaProdutosAdapter(Context context) {
        this.context = context;
        this.dataset = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_produto, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Produtos produtos = dataset.get(position);
        holder.nomeProduto.setText(produtos.getName());

        Glide.with(context)
                .load(produtos.getImage())
                .fitCenter()
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .error(R.drawable.ic_no_photos)
                .into(holder.imageProduto);


    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public void addListaProduto(ArrayList<Produtos> listProdutos) {
        dataset.addAll(listProdutos);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView imageProduto;
        private TextView nomeProduto;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageProduto = itemView.findViewById(R.id.img_produto);
            nomeProduto = itemView.findViewById(R.id.nome_produto);
        }
    }
}
