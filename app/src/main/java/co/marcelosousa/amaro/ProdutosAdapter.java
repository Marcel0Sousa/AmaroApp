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

import co.marcelosousa.amaro.models.Produtos;
import okhttp3.internal.cache.DiskLruCache;

public class ProdutosAdapter extends RecyclerView.Adapter<ProdutosAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<Produtos> mProdutos;

    private OnItemClickListener mClickListener;

    public interface OnItemClickListener {
        void onItemClick(int position);

    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mClickListener = listener;
    }

    public ProdutosAdapter(Context context) {
        mContext = context;
        mProdutos = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_produto, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Produtos produtos = mProdutos.get(position);


        holder.mNomeProduto.setText(produtos.getName());
        holder.mValorProduto.setText(produtos.getActual_price());

        Glide.with(mContext)
                .load(produtos.getImage())
                .fitCenter()
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .error(R.drawable.ic_no_photos)
                .into(holder.mImageProduto);


    }

    @Override
    public int getItemCount() {
        return mProdutos.size();
    }

    public void addListaProduto(ArrayList<Produtos> listProdutos) {
        mProdutos.addAll(listProdutos);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        int position;

        private ImageView mImageProduto;
        private TextView mNomeProduto;
        private TextView mValorProduto;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mImageProduto = itemView.findViewById(R.id.img_produto);
            mNomeProduto = itemView.findViewById(R.id.nome_produto);
            mValorProduto = itemView.findViewById(R.id.valor_produto);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mClickListener != null && position != RecyclerView.NO_POSITION) {
                        position = getAdapterPosition();
                        mClickListener.onItemClick(position);
                    }
                }
            });
        }
    }
}
