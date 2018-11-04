package lenovo.example.com.app1103;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * author：shashe
 * 日期：2018/11/3
 * 商家名称适配器
 */
public class CarAdapter extends RecyclerView.Adapter<CarAdapter.MyViewHolder>{
    private List<CarBean.DataBean> list=new ArrayList<>();
    private Context context;
    public CarAdapter(Context context) {
        this.context=context;
    }

    @NonNull
    @Override
    public CarAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.car_layout, null);
        MyViewHolder myViewHolder=new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CarAdapter.MyViewHolder myViewHolder, int i) {
        myViewHolder.mTitle.setText(list.get(i).getSellerName());
        //定义创建商品适配器的方法
        createRecycler(myViewHolder.mRecyclerView,list.get(i).getList());
    }

    private void createRecycler(RecyclerView mRecyclerView, List<CarBean.DataBean.ListBean> list) {
        CarAdapter2 carAdapter2 = new CarAdapter2(context, list);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(staggeredGridLayoutManager);
        mRecyclerView.setAdapter(carAdapter2);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    //传递数据
    public void setList(List<CarBean.DataBean> list) {
        //记得移除，不然报空指针异常
        list.remove(0);
        this.list = list;
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView mTitle;
        RecyclerView mRecyclerView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mTitle=itemView.findViewById(R.id.title);
            mRecyclerView=itemView.findViewById(R.id.recy_view);
        }
    }
}
