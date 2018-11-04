package lenovo.example.com.app1103;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

/**
 * author：shashe
 * 日期：2018/11/3
 * 商品适配器
 */
public class CarAdapter2 extends RecyclerView.Adapter<CarAdapter2.MyViewHolder>{
    private Context context;
    private List<CarBean.DataBean.ListBean> list=new ArrayList<>();
    public CarAdapter2(Context context, List<CarBean.DataBean.ListBean> list) {
        this.context=context;
        this.list=list;
    }

    @NonNull
    @Override
    public CarAdapter2.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.car2_layout, null);
        MyViewHolder myViewHolder=new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CarAdapter2.MyViewHolder myViewHolder, int i) {
        myViewHolder.mDesc.setText(list.get(i).getTitle());
        myViewHolder.mSimple.setImageURI(list.get(i).getImages().split("\\|")[0]);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        SimpleDraweeView mSimple;
        TextView mDesc;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mSimple=itemView.findViewById(R.id.simple);
            mDesc=itemView.findViewById(R.id.desc);
        }
    }
}
